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
    
    public final double MAXSPEED = 88.5; // in revolutions per second
    public final double GOAL_HEIGHT = 4.5748; // in meters
    public final double SHOOTER_HEIGHT = 0.3048; // in meters
    public final double FLYWHEEL_RADIUS = 0.1016; // in meters

    public Shooter (double radius) {
	this.radius = radius;
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
	return distance / Math.sqrt(GOAL_HEIGHT - SHOOTER_HEIGHT/9.807);
    }

    private double getMotorPower(double velocity) {
	return velocity / (2 * Math.PI * radius * (MAXSPEED * FLYWHEEL_RADIUS));
    }
    
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

