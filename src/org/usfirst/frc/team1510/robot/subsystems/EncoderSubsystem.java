package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderSubsystem {
	private Encoder enc;
	
	/**
	 * Constructor for objects of class EncoderSubsystem
	 * @param a Encoder PWM channel A
	 * @param b Encoder PWM channel B
	 */
	public EncoderSubsystem (int a, int b){
		enc = new Encoder (a,b);
	}
	
	/**
	 * Returns the recorded distance of the encoder since last reset
	 * @return the recorded distance of the encoder since last reset
	 */
	public double getDistance(){
		return enc.getDistance();
	}
	
	/**
	 * Resets the encoder's distance reading
	 */
	public void reset(){
		enc.reset();
	}
	
	/**
	 * Reverses the encoder
	 */
	public void reverse() {
		enc.setReverseDirection(!enc.getDirection());
	}
	
	/**
	 * Get current direction
	 * @return current direction
	 */
	public boolean direction() {
		return enc.getDirection();
	}
}
