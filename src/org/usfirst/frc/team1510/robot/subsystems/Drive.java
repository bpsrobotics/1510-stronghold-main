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
    private static CANTalon[] leftMotors = {new CANTalon(1), new CANTalon(2)};
    private static CANTalon[] rightMotors = {new CANTalon(3), new CANTalon(4)};

    // Robot drive class
    private static RobotDrive drive = new RobotDrive(leftMotors[1], leftMotors[2], rightMotors[1], rightMotors[2]);
    
    // The encoders that are hooked up to the gearboxes
    private static Encoder leftEncoder = new Encoder(1,2);
    private static Encoder rightEncoder = new Encoder(3,4);

    /**
     * Move based on left and right motor values
     *
     * @param left The left motor value
     * @param right The right motor value
     */
    public void move(double left, double right) {
    	drive.tankDrive(left,right,true);
    }

    /**
     * Move based on left and right joystick
     *
     * @param left The left joystick
     * @param right The right joystick
     */
    public void move(GenericHID left, GenericHID right) {
	drive.tankDrive(left,right,true);
    }


    public double[] getEncoderValues() {
	double[] result = {leftEncoder.getDistance(), rightEncoder.getDistance()};
	
	return result;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

