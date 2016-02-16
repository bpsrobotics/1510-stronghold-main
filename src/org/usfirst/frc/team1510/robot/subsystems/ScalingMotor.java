package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScalingMotor extends Subsystem {
	private Talon scalingMotor = new Talon(0);
	private boolean enabled = false;
			private CANTalon climbMotor = new CANTalon(1);
			 private double[] goalSpeed = {0,0};
			    private double[] currentSpeed = {0,0};
			    private double speedAdjustPerCycle = 0.04;
			    
			    public void move() {
			    	if (!enabled) return;
			
			if(Math.abs(goalSpeed[0] - currentSpeed[0]) < speedAdjustPerCycle)
			{
	    		// If within one-cycle range of goal
	    		currentSpeed[0] = goalSpeed[0];}
	    	else if (currentSpeed[0] > goalSpeed[0])
	    		// If more than goal
	    		{currentSpeed[0] -= speedAdjustPerCycle;}
	    	else if (currentSpeed[0] < goalSpeed[0])
	    		// If less than goal
	    		{currentSpeed[0] += speedAdjustPerCycle;
			}
			    }
			    public void enable() {
			    	
			    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

