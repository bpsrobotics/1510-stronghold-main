package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class WheelArms extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(0);
    
    
    // Wheel motors
    private CANTalon leftMotor = new CANTalon(5);
    private CANTalon rightMotor = new CANTalon(6);
    private RobotDrive drive = new RobotDrive(leftMotor, rightMotor);
    
    // Controls enabled status
    private boolean enabled = false;
    
    // Motor ramping variables
    private double[] goalSpeed = {0,0};
    private double[] currentSpeed = {0,0};
    private double speedAdjustPerCycle = 0.04;
    
    public void move(double left, double right) {
    	if (!enabled) return;
    	//drive.tankDrive(left,right,true);
    	
    	// Set goal speed
    	goalSpeed[0] = left;
    	goalSpeed[1] = right;
    	
    	// Logic for left motors
    	if (Math.abs(goalSpeed[0] - currentSpeed[0]) < speedAdjustPerCycle)
    		// If within one-cycle range of goal
    		currentSpeed[0] = goalSpeed[0];
    	else if (currentSpeed[0] > goalSpeed[0])
    		// If more than goal
    		currentSpeed[0] -= speedAdjustPerCycle;
    	else if (currentSpeed[0] < goalSpeed[0])
    		// If less than goal
    		currentSpeed[0] += speedAdjustPerCycle;
    	
    	// Logic for right motors
    	if (Math.abs(goalSpeed[1] - currentSpeed[1]) < speedAdjustPerCycle)
    		// If within one-cycle range of goal
    		currentSpeed[1] = goalSpeed[1];
    	else if (currentSpeed[1] > goalSpeed[1])
    		// If more than goal
    		currentSpeed[1] -= speedAdjustPerCycle;
    	else if (currentSpeed[1] < goalSpeed[1])
    		// If less than goal
    		currentSpeed[1] += speedAdjustPerCycle;
    	
    	// Update motor throttle
    	drive.tankDrive(currentSpeed[0], currentSpeed[1], true);
    }
	
    public void enable() {
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

