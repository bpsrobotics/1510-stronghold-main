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
    public final double CAM_FOV = 28.6;

    // Begin shooter calibration curve constants
    public final double CALIB_A = 3.2188255000367;
    public final double CALIB_B = 0.98337515163017;
    private NetworkTable targetInfo = NetworkTable.getTable("GRIP/Target");
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
    	double[] defaultValue = {};
    	double height = 0;
    	try {
    		double[] heights = targetInfo.getNumberArray("height", defaultValue);
    		
    		for (double possibleHeight : heights) {
    			if (possibleHeight > height)
    				height = possibleHeight;
    		}
    		
    		/*
    		for(int i = 0; i < targetInfo.getNumberArray("height", defaultValue).length; i++){
    			if(targetInfo.getNumberArray("height",defaultValue)[i] > height){
    				height = targetInfo.getNumberArray("height", defaultValue)[i]; 
    			}
    		}
    		*/
	    	//double height = targetInfo.getNumberArray("height",defaultValue)[0];
	    	changeHeight(getMotorPower(getDistance(height)));
	    	changeDistance(1);
	    	System.out.println(height + " " + getMotorPower(getDistance(height)));
    	} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("no target");
		}
    }
    
    public double getRecPower() {
    	double[] defaultValue = {};
    	double height = 0;
    	try {
    		double[] heights = targetInfo.getNumberArray("height", defaultValue);
    		
    		for (double possibleHeight : heights) {
    			if (possibleHeight > height)
    				height = possibleHeight;
    		}
    		
    		/*
    		for(int i = 0; i < targetInfo.getNumberArray("height", defaultValue).length; i++){
    			if(targetInfo.getNumberArray("height",defaultValue)[i] > height){
    				height = targetInfo.getNumberArray("height", defaultValue)[i]; 
    			}
    		}
    		*/
	    	//double height = targetInfo.getNumberArray("height",defaultValue)[0];
	    	return getMotorPower(getDistance(height));
    	} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("no target");
			return 0;
		}
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


    //returns distance in inches
    public double getDistance(double Tpx){
		return (240 / (2 * Math.tan(Math.toRadians(CAM_FOV))* Tpx)) * 12;
    }
    


    private double getMotorPower(double distance) {
	return CALIB_A * Math.pow(CALIB_B, distance);
    }
    
    
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

