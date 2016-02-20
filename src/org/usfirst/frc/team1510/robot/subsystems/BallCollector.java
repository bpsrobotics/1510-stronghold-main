package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class BallCollector extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(1);
    private Relay rollerMotor = new Relay(2 , Relay.Direction.kForward);
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
