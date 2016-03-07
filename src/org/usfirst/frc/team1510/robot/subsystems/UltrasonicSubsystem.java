package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicSubsystem extends SensorBase{
	
	private Ultrasonic sonic;
	
	/**
	 * Constructor for objects of class UltrasonicSubsystem
	 * @param a Ultrasonic ping channel
	 * @param b Ultrasonic echo channel
	 */
	public UltrasonicSubsystem (int a, int b){
		sonic = new Ultrasonic(a,b);
	}
	
	/**
	 * Returns the recorded distance from the Ultrasonic sensor
	 * @return the recorded distance from the Ultrasonic sensor
	 */
	public double getInches(){
		return sonic.getRangeInches();
	}
	
	/**
	 * Returns the recorded distance from the Ultrasonic sensor
	 * @return the recorded distance from the Ultrasonic sensor
	 */
	public double getMillimeters(){
		return sonic.getRangeMM();
	}
	


}
