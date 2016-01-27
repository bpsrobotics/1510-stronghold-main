package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class WheelArms extends Subsystem {
    
	// Actuator for the arm itself
    private Talon armMotor = new Talon(0);
    
    // Wheel motors
    private CANTalon leftMotor = new CANTalon(5);
    private CANTalon rightMotor = new CANTalon(6);
    
    
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

