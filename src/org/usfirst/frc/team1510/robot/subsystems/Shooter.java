package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Shooter extends Subsystem {
    private CANTalon shooterMotor = new CANTalon(3);
    public Talon[] guideWheels = {new Talon(3), new Talon(4)};
    private boolean justShot = false;
    public final double MAXSPEED = 88.5; // in revolutions per second
    public final double GOAL_HEIGHT = 4.5748; // in meters
    public final double SHOOTER_HEIGHT = 0.3048; // in meters
    public final double FLYWHEEL_RADIUS = 0.1016; // in meters
    public final double GEAR_RATIO = 1.0/1.0;

    private NetworkTable autoAimTable;

    public Shooter () {
	autoAimTable = NetworkTable.getTable("AutoAim");
    }

    /**
     * Fires the shooter with the given distance
     *
     * @param distance The distance, in meters, 
     */
    public void fire() { 

	
	
	shooterMotor.set(getMotorPower(distance));
	guideWheels[0].set(1);
	guideWheels[1].set(-1);
	
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

    private double getDistance() {
	
    }


    private double getMotorPower(double distance) {
	
    }
    
    public double getRecSpeed(double distance){
    	int distance1 = (int)Math.rint(distance);
    	switch(distance1){
    	case 0:
    		return 1;
    	case 1:
    		return 1;
    	case 2:
    		return 1;
    	case 3:
    		return 1;
    	case 4:
    		return .95;
    	case 5:
    		return .95;
    	case 6:
    		return .85;
    	case 7:
    		return .85;
    	case 8:
    		return .75;
    	case 9:
    		return .75;
    	case 10:
    		return .85;
    	case 11:
    		return .85;
    	case 12:
    		return .95;
    	case 13:
    		return .95;
    	case 14:
    		return 1;
    	case 15:
    		return 1;
    	default:
    		return .85;
    	}
    }
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

