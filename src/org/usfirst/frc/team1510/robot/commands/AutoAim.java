package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.*;


public class AutoAim extends Command{
	
	private NetworkTable table = NetworkTable.getTable("GRIP/Target");
	UltrasonicSubsystem sonic = Robot.ultrasonic;
	Shooter shooter = Robot.shooter;
	Drive drive = Robot.drive;
 	double distance;
	//private TargetLight targetLight = Robot.targetLight;
	private boolean complete = false;

	 public AutoAim() {
	        // Use requires() here to declare subsystem dependencies
	        requires(Robot.drive);
	        requires(Robot.targetLight);
	        requires(Robot.shooter);
	    }

	    // Called just before this Command runs the first time
	    public void initialize() {
	    }

	    // Called repeatedly when this Command is scheduled to run
	    
	    //@SuppressWarnings("deprecation")
		protected void execute(){
	    	//turn light on
	    	//targetLight.on();
    		//Get values printed in network tables
	   
    		double[] defaultValue = {};
    		
    		
    		try {
		    	double height = table.getNumberArray("height",defaultValue)[0];
		    	double width = table.getNumberArray("width",defaultValue)[0];
		    	double xval = table.getNumberArray("centerX",defaultValue)[0];
		    	double yval = table.getNumberArray("centerY",defaultValue)[0];
		    	//Find the ratio of height to width
		    	double ratio = height/width;
		    	//Find vertical offset
		    	//double vertdiff = yval - 120;
		    	//Find horizontal offset
		    	double hzdiff = xval - 240;
		    	/*
		    	if(vertdiff > 10){
		    		System.out.println("Move shooter up");
		    	}
		    	else if(vertdiff < -10){
		    		System.out.println("Move shooter down");
		    	}*/
		    	if(hzdiff > 10){
		    		System.out.println("Move robot right");
		    		drive.turn(10,.5);
		    	}
		    	else if(hzdiff < -10){
		    		System.out.println("Move robot left");
		    		drive.turn(10,-.5);
		    	}
		    	if(ratio > .7){
		    		System.out.println("The target is angled too much, cannot shoot");
		    		complete = true;
		    	}
		    	
		    	if (Math.abs(hzdiff) < 100) {
		    	 	distance = sonic.getMillimeters()/1000;
		    		System.out.println("You are clear to shoot");
		    		shooter.fire(distance);
		    		complete = true;
		    	}
    		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
    			System.out.println("no target");
    		}
	    	

    	}
    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	
	        return complete;
	        
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	//targetLight.off();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    }
}
