package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class TargetLight extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Relay relay;
	
	public TargetLight(int channel){
		//we only want to use forward cuz something about polarity or some crap idk
		relay = new Relay(channel , Relay.Direction.kForward);
	}
	
	public void off(){
		relay.set(Relay.Value.kOff);
		System.out.println("off");
	}
	
	public void on(){
		relay.set(Relay.Value.kOn);
				
		System.out.println("on");
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetRelay(){
    	relay.free();
    }
}

