package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class WheelArms extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(0);
    // Wheel motors
    private CANTalon wheelMotor = new CANTalon(6);
    
    // Controls enabled status
    private boolean enabled = false;
    
    // Motor ramping variables
    private double goalSpeed = 0;
    private double currentSpeed = 0;
    private double speedAdjustPerCycle = 0.04;
    
    private DigitalInput limitSwitch = new DigitalInput(1);
    private Counter counter = new Counter(limitSwitch);
    
    public boolean isSwitchSet() {
        return counter.get() > 0;
    }
    
    public void initializeCounter() {
        counter.reset();
    }
    
    public void extend(int distance) { 
    	initializeCounter();
    	armMotor.set(-.25);
    	if(isSwitchSet()) armMotor.set(0);
    }
    public void retract(int distance) { 
    	initializeCounter();
    	armMotor.set(.25);
    	if(isSwitchSet()) armMotor.set(0);
    }
    
    public void move(double speed) {
    	if (!enabled) return;    	
    	// Set goal speed
    	goalSpeed = speed;
    	
    	// Logic for left motors
    	if (Math.abs(goalSpeed - currentSpeed) < speedAdjustPerCycle)
    		// If within one-cycle range of goal
    		currentSpeed = goalSpeed;
    	else if (currentSpeed > goalSpeed)
    		// If more than goal
    		currentSpeed -= speedAdjustPerCycle;
    	else if (currentSpeed < goalSpeed)
    		// If less than goal
    		currentSpeed += speedAdjustPerCycle;
     	
    	// Update motor throttle
    	wheelMotor.set(currentSpeed);
    }
	
    public void enable() {
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

