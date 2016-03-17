package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UltrasonicSubsystem extends Subsystem{
	
	
	/**
	 * Factor for calibration. Will be made final once calibration is complete.
	 */
	public static double CALIBRATION_FACTOR;
	private AnalogInput channel;

	/**
	 * Constructor for analog ultrasonic sensors
	 *
	 * @param analogChannel The analog channel the sensor is connected to
	 */
	public UltrasonicSubsystem(int analogChannel) {
		channel = new AnalogInput(analogChannel);
	}

	/**
	 * Returns calibrated distance
	 *
	 * @return Calibrated distance
	 */
	public double getRange() {
		return getVoltage() * CALIBRATION_FACTOR;
	}

	/**
	 * Returns raw analog voltage
	 *
	 * @return raw analog voltage
	 */
	public double getVoltage() {
		return channel.getVoltage();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	


}
