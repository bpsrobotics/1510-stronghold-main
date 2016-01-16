package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.GenericHID;

/**
 *
 */
public class Drive extends Subsystem {

    // Arrays that contain the motor controllers
    private CANTalon[] leftMotors = {new CANTalon(1), new CANTalon(2)};
    private CANTalon[] rightMotors = {new CANTalon(3), new CANTalon(4)};
    /*
     * "private" -- Access modifier, can be package-protected (no keyword, default), protected (only subclasses), public (anybody), or private (only this class)
     * "CANTalon" -- Data type; primitives are int, double, float, boolean; classes are valid data types as well
     * "[]" -- Defines the data type as an array type
     * "leftMotors", "rightMotors" -- The identifier of the variable (the name)
     * "{...}" -- Array syntax, defines array. List contents inside {}, elements separated by ","; no "," after last element
     * "new" -- Declares that the following method call is actually a constructor call and will return a new object
     * "CANTalon(...)" -- Constructor call; name is class name (must be subclass or same class as data type)
     * "1", "2", "3", "4" -- Arguments to constructor, similar to array elements just () instead of {}
     */

    // Robot drive class
    private RobotDrive drive = new RobotDrive(leftMotors[1], leftMotors[2], rightMotors[1], rightMotors[2]);
    
    // The encoders that are hooked up to the gearboxes
    private Encoder leftEncoder = new Encoder(1,2);
    private Encoder rightEncoder = new Encoder(3,4);
    
    // Enabled
    private boolean enabled = false;

    /**
     * Move based on left and right motor values
     *
     * @param left The left motor value
     * @param right The right motor value
     */
    public void move(double left, double right) {
    	if (!enabled) return;
    	drive.tankDrive(left,right,true);
    }

    /**
     * Move based on left and right joystick
     *
     * @param left The left joystick
     * @param right The right joystick
     */
    public void move(GenericHID left, GenericHID right) {
    	if (!enabled) return;
    	drive.tankDrive(left,right,true);
    }


    public double[] getEncoderValues() {
    	double[] result = {leftEncoder.getDistance(), rightEncoder.getDistance()};
	
    	return result;
    }
    
    public void stop() {
    	drive.stopMotor();
    }
    
    public void enable() {
    	enabled = true;
    }
    
    public void disable() {
    	enabled = false;
    	stop();
    	resetEncoders();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

