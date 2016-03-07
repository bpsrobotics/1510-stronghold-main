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
    private DigitalInput limitSwitch1 = new DigitalInput(1);
    private Counter counter1 = new Counter(limitSwitch1);
    private DigitalInput limitSwitch2 = new DigitalInput(2);
    private Counter counter2 = new Counter(limitSwitch2);

    
    public boolean extend() { 
	armMotor.set(-0.25);
	if (limitSwitch2.get()) {
	    armMotor.set(0);
	    return true;
	}
	return false;
    }
    public boolean retract() { 
	armMotor.set(0.25);
	if (limitSwitch1.get()) {
	    armMotor.set(0);
	    return true;
	}
		return false;
    }
    public void forward(){
    	rollerMotor.set(Relay.Value.kForward);
    	rollerMotor.set(Relay.Value.kOn);
    }
    
    public void reverse(){
    	rollerMotor.set(Relay.Value.kReverse);
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
