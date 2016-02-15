package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    private Talon shooterMotor = new Talon(1);
    private boolean justShot = false;
    private Encoder encoder = new Encoder(5,6);
    private double radius;
    private double gearRatio;

    public Shooter (double radius, double gearRatio) {
	this.radius = radius;
	this.gearRatio = gearRatio;
    }
    

    /**
     * Fires the shooter with the given distance
     *
     * @param distance The distance, in meters, 
     */
    public void fire(double distance) { 

	shooterMotor.set(getMotorPower(getVelocity(distance)));
	
    }

    public void stop() {

	shooterMotor.stop();
	
    }

    private double getVelocity(double distance) {
	return distance / Math.sqrt(4.27/9.807);
    }

    private double getMotorPower(double velocity) {
	return velocity / (2 * Math.PI * radius * (88.5 * gearRatio));
    }
    
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

