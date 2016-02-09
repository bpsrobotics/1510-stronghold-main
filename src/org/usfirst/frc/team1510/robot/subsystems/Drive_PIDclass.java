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
    private RobotDrive drive = new RobotDrive(leftMotors[0], leftMotors[1], rightMotors[0], rightMotors[1]);
    
    // The encoders that are hooked up to the gearboxes
    private Encoder leftEncoder = new Encoder(1,2);
    private Encoder rightEncoder = new Encoder(3,4);
    
    // Enabled
    private boolean enabled = false;
    
    private class PIDcontroller(double Kp, double Ki, double Kd) {
        private double prevError = {0.0}; // Used for calculating Ki

        private double goalSpeed = {0.0}, // For left and right motors, respectively
                       currentSpeed = {0.0};

        private double returnSpeed = {0.0}; // speed to pass to motor controlling class (the direct one, not through PID)

        private double integrator = {0.0}, // for left and right motors, respectively
                         derivator = {0.0};
        
        private double integratorMax = 0.0, // Max and min values of integrator, to prevent it from being stupid
                       integratorMin = 0.0;

        private double CalcError(double speed, goalspeed) { // Calculates error from input values
            return goalspeed - speed;
        }

        private double CalcKp(double error) {
            return error * Kp
        }

        private double CalcKi() { // Calculates Ki value
            return integrator * Ki
        }

        private double CalcKd(double error) { // Calculates Kd value -- TODO
            return Kd * (error - derivator)
        }

        private double UpdateIntegrator(double error) {
            integrator += error;
        }

        private double
        
        private double PIDRun() {
            double error = CalcError(currentSpeed, goalSpeed);
            UpdateIntegrator(error);
            double pValue = CalcKp(error);
            double iValue = CalcKi();
        }
        
    }

    /**
     * Move based on left and right motor values
     *
     * @param left The left motor value
     * @param right The right motor value
     */

    public void move(double left, double right) { //TODO: Integrate with PID controller
        if (!enabled) return;
        drive.tankDrive(left, right, false)
        
    }

    /**
     * Move based on left and right joystick
     *
     * @param left The left joystick
     * @param right The right joystick
     */
    public void move(GenericHID left, GenericHID right) { //TODO: Integrate with PID controller
    	if (!enabled) return;
    	drive.tankDrive(left,right,true);
    }


    public double[] getEncoderValues() {
    	double[] result = {leftEncoder.getDistance(), rightEncoder.getDistance()};
	
    	return result;
    }
    
    public void stop() {
    	drive.stopMotor();
    	goalSpeed[0] = 0;
    	goalSpeed[1] = 0;
    	currentSpeed[0] = 0;
    	currentSpeed[1] = 0;
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

