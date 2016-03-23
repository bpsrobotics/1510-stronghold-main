package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team1510.robot.subsystems.PIDcontroller;
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
    
    // Enabled
    private boolean enabled = false;
    
    // Speed ramping variables
    private double[] currentSpeed = {0.0, 0.0};
    private double[] goalSpeed = {0.0, 0.0};

    double[] stopped = {0, 0};

    double Kp = 0.5,
           Ki = 0.1,
           Kd = -0.1;

    private PIDcontroller leftController = new PIDcontroller(Kp, Ki, Kd, leftMotors);
    private PIDcontroller rightController = new PIDcontroller(Kp, Ki, Kd, rightMotors);

    /**
     * Move based on left and right motor values
     *
     * @param left The left motor value
     * @param right The right motor value
     */


    /**
     * Move based on left and right joystick
     *
     * @param left The left joystick
     * @param right The right joystick
     */

    /*public void move(GenericHID left, GenericHID right) {
    	if (!enabled) return;
    	//move(left.getY(),right);
    }*/

    public void UpdatePIDTarget(double[] goalSpeed) { // Updates PID goal speeds, left then right
        leftController.SetGoal(goalSpeed[0]);
        rightController.SetGoal(goalSpeed[1]);
    }

    private double[] PIDRun() { // Shouldn't be used ever. Use UpdatePIDMotors instead
        double[] speeds = {leftController.PIDRun(), rightController.PIDRun()}; // Runs PID code for the two controllers
        return speeds;
    }

    public void UpdatePIDMotors() {
        if (enabled) {
            double[] speeds = PIDRun(); // Gets speeds motors should be running at
            drive.tankDrive(speeds[0], speeds[1]); // Normal move stuff
        } else {
           double[] speeds = stopped;
        }
    }

    public void move(double left, double right) {
        if (enabled) {
            double[] goal_speed = {left, right};
            UpdatePIDTarget(goal_speed); //Updates targets w/ values
            UpdatePIDMotors();
        }
    }

    public void move() {
        UpdatePIDMotors();
    }
    /**
     * 
     * @param distance in feet
     * @param motorThrottle
     * @return
     */
    // public boolean driveDistance(double distance, double motorThrottle) {
    // 	//distance = distance / 3 * 8 * Math.PI / 12;
    // 	if (Math.abs(getAverageDistance()) >= distance) {
    // 		//resetEncoders();
    // 		//goalSpeed[0] = 0;
    // 		//goalSpeed[1] = 0;
    // 		//currentSpeed[0] = 0;
    // 		//currentSpeed[1] = 0;
    // 		//move(0,0);
    // 		//return true;
    // 	}

	// double kp = 0.03;

	// double angle = gyro.getAngle();
	// 
    // 	move(motorThrottle, 0);
	// 
	// /*
    // 	if (Math.abs(getAverageDistance()) >= distance) {
    // 		resetEncoders();
    // 		goalSpeed[0] = 0;
    // 		goalSpeed[1] = 0;
    // 		currentSpeed[0] = 0;
    // 		currentSpeed[1] = 0;
    // 		move(0,0);
    // 		return true;
    // 	}
	// */

    // 	return false;
    // }


    public double[] GetSpeeds() {
        double[] speeds = {leftController.GetSpeed(), rightController.GetSpeed()};
        return speeds;
    }

    // Not needed due to PID controlling everything
    // public boolean setBrake(){
    // 	leftMotors[0].enableBrakeMode(true);
    // 	leftMotors[1].enableBrakeMode(true);
    // 	rightMotors[0].enableBrakeMode(true);
    // 	rightMotors[1].enableBrakeMode(true);
    // 	return true;
    // }
    // public boolean setCoast(){
    // 	leftMotors[0].enableBrakeMode(false);
    // 	leftMotors[1].enableBrakeMode(false);
    // 	rightMotors[0].enableBrakeMode(false);
    // 	rightMotors[1].enableBrakeMode(false);
    // 	return true;
    // }

    public void stop() {
        UpdatePIDTarget(stopped);
    }
    
    public void enable() {
        enabled = true;
    }
    
    public void disable() {
        enabled = false;
        stop();
    }
    
    // public void resetEncoders() {
    //     leftEncoder.reset();
    //     rightEncoder.reset();
    // }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
