package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;



/**
 *
 */
public class BallCollector extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(1);
    private Relay rollerMotor = new Relay(2 , Relay.Direction.kForward);
    private EncoderSubsystem armEncoder  = new EncoderSubsystem(1,2);
    
    
    public void armLower(int distance) { 
    	armEncoder.reset();
    	armEncoder.reverse();
    	while(armEncoder.getDistance() < distance)
    	{
    		armMotor.set(0.25);
    	}
    }
    public void armRaise(int distance) { 
    	armEncoder.reset();
    	while(armEncoder.getDistance() < distance)
    	{
    		armMotor.set(-0.25);
    	}
    }
    public void on(){
		rollerMotor.set(Relay.Value.kForward);
    	rollerMotor.set(Relay.Value.kOn);
    }
    public void off() {
    	rollerMotor.set(Relay.Value.kOff);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
