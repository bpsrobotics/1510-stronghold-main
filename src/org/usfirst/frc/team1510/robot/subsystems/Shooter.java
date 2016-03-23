package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon shooterMotor = new CANTalon(3);
    private Talon[] guideWheels = {new Talon(3), new Talon(4)};
    private boolean justShot = false;
    public final double MAXSPEED = 88.5; // in revolutions per second
    public final double GOAL_HEIGHT = 4.5748; // in meters
    public final double SHOOTER_HEIGHT = 0.3048; // in meters
    public final double FLYWHEEL_RADIUS = 0.1016; // in meters
    public final double GEAR_RATIO = 1.0/1.0;

    /**
     * Fires the shooter with the given distance
     *
     * @param distance The distance, in meters, 
     */
    public void fire(double distance) { 

	shooterMotor.set(getMotorPower(getVelocity(distance)));
	guideWheels[0].set(getMotorPower(getVelocity(distance)));
	guideWheels[1].set(-getMotorPower(getVelocity(distance)));
	
    }
    
    public void changeHeight(double power) {

    	shooterMotor.set(-power);
    	
    }
    
    public void changeDistance(double power) {

    	guideWheels[0].set(power);
    	guideWheels[1].set(-power);
    	
    }

    public void stop() {

    	shooterMotor.set(0);
    	guideWheels[0].set(0);
    	guideWheels[1].set(0);
	
    }

    private double getVelocity(double distance) {
	return distance / Math.sqrt(GOAL_HEIGHT - SHOOTER_HEIGHT/9.807);
    }

    private double getMotorPower(double velocity) {
	return velocity / (2 * Math.PI * FLYWHEEL_RADIUS * (MAXSPEED * GEAR_RATIO));
    }
    
    private double getRecSpeed(double distance){
    	int distance1 = (int)Math.rint(distance);
    	double recSpeed = 0;
    	switch(distance1){
    	case 0:
    		recSpeed = 1;
    	case 1:
    		recSpeed = 1;
    	case 2:
    		recSpeed = 1;
    	case 3:
    		recSpeed = 1;
    	case 4:
    		recSpeed = .95;
    	case 5:
    		recSpeed = .95;
    	case 6:
    		recSpeed = .85;
    	case 7:
    		recSpeed = .85;
    	case 8:
    		recSpeed = .75;
    	case 9:
    		recSpeed = .75;
    	case 10:
    		recSpeed = .85;
    	case 11:
    		recSpeed = .85;
    	case 12:
    		recSpeed = .95;
    	case 13:
    		recSpeed = .95;
    	case 14:
    		recSpeed = 1;
    	case 15:
    		recSpeed = 1;
    	}
    	return recSpeed;
    }
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

