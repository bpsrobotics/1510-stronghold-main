package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderSubsystem {
	private Encoder enc;

	public EncoderSubsystem (){
		enc = new Encoder (1,2, false);
	}
	public double getDistance(){
		return enc.getDistance();
	}
	public void reset(){
		enc.reset();
	}
}
