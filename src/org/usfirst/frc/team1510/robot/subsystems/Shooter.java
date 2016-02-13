package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	private Talon shooterMotor = new Talon(1);
	private boolean justShot=false;
	 public void Fire() { 
		 shooterMotor.set(.5);
		 justShot=true;
	    	
	    	if(justShot==true){
	    		shooterMotor.stopMotor();
	    		justShot=false;
	    	}
	 }
	 public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	        //setDefaultCommand(new MySpecialCommand());
	    }
}
	 

