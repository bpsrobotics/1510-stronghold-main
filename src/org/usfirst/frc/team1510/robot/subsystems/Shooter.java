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
    public boolean inRange = false;
    
    public final double MAXSPEED = 88.5; // in revolutions per second
    public final double GOAL_HEIGHT = 4.5748; // in meters
    public final double SHOOTER_HEIGHT = 0.3048; // in meters
    public final double FLYWHEEL_RADIUS = 0.1016; // in meters
    public final double GEAR_RATIO = 1.0/1.0;
    public final double CAM_FOV = 28.6;//38.7;
    //28.6

    // Begin shooter calibration curve constants for range 2
    public final double RANGE_1_A = 3.2188255000367;
    public final double RANGE_1_B = 0.98337515163017;
    //Begin shooter calibration curve constants for range 1
    public final double RANGE_2_A = .002398456;
    public final double RANGE_2_B = -.4552580002;
    public final double RANGE_2_C =  22.10867015;
    //Begin shooter calibration curve constants for range 3
    public final double RANGE_3_A = -.0001930294;
    public final double RANGE_3_B = .0534539807;
    public final double RANGE_3_C =  -3.251681599;
   
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
    	return getMotorPower(getDistance(getTargetInfo()[1]));
    	/*double[] defaultValue = {};
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
    	/*
	    	//double height = targetInfo.getNumberArray("height",defaultValue)[0];
	    	return getMotorPower(getDistance(height));
    	} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("no target");
			return 0;
		}*/
    	
    }
    
    /** 
	*Return array of values containing target info
	*values[0] = area
	*values[1] = height
	*values[2] = width
	*values[3] = centerX
	*values[4] = centerY
	*/
    
    public double[] getTargetInfo(){
    	double[][] dataSample = new double [5][5];
    	double[] finalData = new double[5];
    	for(int j = 0; j < 5; j++){
    		double[] values = {0,0,0,0,0};
    		try {
    			double[] defaultValue = {};
    			double[] heights = targetInfo.getNumberArray("height", defaultValue);
    			double[] widths = targetInfo.getNumberArray("width", defaultValue);
    			double[] areas = targetInfo.getNumberArray("area", defaultValue);
    			double[] possibleX = targetInfo.getNumberArray("centerX", defaultValue);
    			double[] possibleY = targetInfo.getNumberArray("centerY", defaultValue);
    			//Sort through contours to minimize list based on height
    			/*int[] contours = {0,0,0,0,0};
    			int cindex = 0;
    			for(int i = 0; i < heights.length; i++){
    			if(heights[i] < 35 && heights[i] > 15){
    				contours[cindex] = i;
    				cindex++;
    			}
    			}*/	
    			int index = 0;    	
    			for(int i = 0; i < areas.length; i++){
    				if(areas[i] > values[0] && heights[i] < 35 && heights[i] > 15){
    					values[0] = areas[i];
    					index = i;
    				}	
    			}
    	
    			values[1] = heights[index];
    			values[2] = widths[index];
    			values[3] = possibleX[index];
    			values[4] = possibleY[index];
    			dataSample[j] = values;
    			Thread.sleep(50);
    			
    		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
    			System.out.println("no target");
    			dataSample[j] = values;
    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    	}
    	finalData[0]= (dataSample[0][0] + dataSample[1][0] + dataSample[2][0] + dataSample[3][0] + dataSample[4][0])/5;
    	finalData[1]= (dataSample[0][1] + dataSample[1][1] + dataSample[2][1] + dataSample[3][1] + dataSample[4][1])/5;
    	finalData[2]= (dataSample[0][2] + dataSample[1][2] + dataSample[2][2] + dataSample[3][2] + dataSample[4][2])/5;
    	finalData[3]= (dataSample[0][3] + dataSample[1][3] + dataSample[2][3] + dataSample[3][3] + dataSample[4][3])/5;
    	finalData[4]= (dataSample[0][4] + dataSample[1][4] + dataSample[2][4] + dataSample[3][4] + dataSample[4][4])/5;

    	return finalData;
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
		double distance = (240 / (2 * Math.tan(Math.toRadians(CAM_FOV))* Tpx)) * 12;
		double correction = 0;
		/*
		double kP = DIST_A * Math.pow(distance, 3) + DIST_B * Math.pow(distance, 2) 
		+ DIST_C * Math.pow(distance, 1) + DIST_D * Math.pow(distance, 0); //+ DIST_E * Math.pow(distance, 0);
		*/
		return distance + correction;
    }
    


    private double getMotorPower(double distance) {
    	/*if(97 <= distance && distance <= 122){
    		inRange = true;
    		return RANGE_1_A * Math.pow(RANGE_1_B, distance);	
    	}*/
    	if( 75 < distance && distance < 90){
    		inRange = true;
    		return .75;
    		//return RANGE_2_A * Math.pow(distance, 2) + RANGE_2_B * distance + RANGE_2_C;
    	}
    	else if( 90 < distance && distance < 110){
    		inRange = true;
    		return RANGE_2_A * Math.pow(distance, 2) + RANGE_2_B * distance + RANGE_2_C - .15;
    	}
    	/*
    	else if(122 < distance && distance < 145){
    		inRange = true;
    		return RANGE_3_A * Math.pow(distance, 2) + RANGE_3_B * distance + RANGE_3_C;
    	}*/
    	else {
    		inRange = false;
    		return .75;
    	}
    }
    
    public double[] getApproxData(){
    	double[] values = {0,0,0,0,0};
		try {
			double[] defaultValue = {};
			double[] heights = targetInfo.getNumberArray("height", defaultValue);
			double[] widths = targetInfo.getNumberArray("width", defaultValue);
			double[] areas = targetInfo.getNumberArray("area", defaultValue);
			double[] possibleX = targetInfo.getNumberArray("centerX", defaultValue);
			double[] possibleY = targetInfo.getNumberArray("centerY", defaultValue);
			//Sort through contours to minimize list based on height
			/*int[] contours = {0,0,0,0,0};
			int cindex = 0;
			for(int i = 0; i < heights.length; i++){
			if(heights[i] < 35 && heights[i] > 15){
				contours[cindex] = i;
				cindex++;
			}
			}*/	
			int index = 0;    	
			for(int i = 0; i < areas.length; i++){
				if(areas[i] > values[0] && heights[i] < 35 && heights[i] > 15){
					values[0] = areas[i];
					index = i;
				}	
			}
	
			values[1] = heights[index];
			values[2] = widths[index];
			values[3] = possibleX[index];
			values[4] = possibleY[index];
			return values;
			
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("no target");
			return values;
		} 
    }
    public boolean getInRange(double dist) {
    	double distance = dist;
    	if(102 <= distance && distance <= 122){
    		return true;	
    	}
    	else if( 95 < distance && distance < 102){
    		return false;
    	}
    	else if(122 < distance && distance < 140){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	//setDefaultCommand(new MySpecialCommand());
    }
}
	 

