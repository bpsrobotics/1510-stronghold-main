package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	private Talon shooterMotor = new Talon(1);
	private EncoderSubsystem shooterEncoder  = new EncoderSubsystem(1,2);
	 public void armLower(int distance) { 
	    	shooterEncoder.reset();
	    	shooterEncoder.reverse();
	    	while(shooterEncoder.getDistance() < distance)
	    	{
	    		shooterMotor.set(.5);
	    	}
	    }
	    public void armRaise(int distance) { 
	    	shooterEncoder.reset();
	    	while(shooterEncoder.getDistance() < distance)
	    	{
	    		shooterMotor.set(.5);
	    		
	    	}
	    }
	    public void on(){
	    	shooterMotor.set(Relay.Value.kForward);
	    	shooterMotor.set(Relay.Value.kOn);
	    }
	    public void off() {
	    	shooterMotor.set(Relay.Value.kOff);
	    } 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

