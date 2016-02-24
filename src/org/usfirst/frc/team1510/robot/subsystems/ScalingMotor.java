package org.usfirst.frc.team1510.robot.subsystems;

import org.usfirst.frc.team1510.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScalingMotor extends Subsystem {
	private Talon scalingMotorLeft = new Talon(0);
	private Talon scalingMotorRight = new Talon(0);
	public final double MaxSpeed = 88.5;
	private Encoder encoder1=new Encoder(7,8);
	private Encoder encoder2=new Encoder(7,8);

	public void ExtendMax()
	{
		scalingMotorLeft.set(MaxSpeed);
		scalingMotorRight.set(MaxSpeed);
	}
	public void ExtendWithJoystick()
	{
		
		double OICoords= Robot.oi.leftStick.getY();
		if(OICoords>0)
		{
			scalingMotorLeft.set(MaxSpeed);
			scalingMotorRight.set(MaxSpeed);
		}
		else if(OICoords<0)
		{
			scalingMotorLeft.set(-1*MaxSpeed);
			scalingMotorRight.set(-1*MaxSpeed);
		}
		else{scalingMotorLeft.stopMotor();}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

