package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
/**
 *
 */
public class Gyro extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public void getAngle(){
		gyro.getAngle();
	}
	
	public void reset() {
		gyro.reset();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

