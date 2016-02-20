package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogGyro;
/**
 *
 */
public class Gyro extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands
	AnalogGyro gyro = new AnalogGyro(1);

	public double getAngle(){
		return gyro.getAngle();
	}
	
	public void reset() {
		gyro.reset();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

