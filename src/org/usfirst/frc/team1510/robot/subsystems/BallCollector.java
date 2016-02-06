package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;


/**
 *
 */
public class BallCollector extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(1);
    private Relay rollerMotor = new Relay(2 , Relay.Direction.kForward);
    
    
    public void armLower() { 
    	
    }
    public void armRaise() { 
    	
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
