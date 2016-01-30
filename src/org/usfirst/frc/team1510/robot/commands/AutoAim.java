package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.*;


public class AutoAim extends Command{

	 public AutoAim() {
	        // Use requires() here to declare subsystem dependencies
	        requires(Robot.drive);
	    }

	    // Called just before this Command runs the first time
	    public void initialize() {
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute(){
	    	NetworkTable table = NetworkTable.getTable("GRIP/Target");
	    	while(true){
	    		//Get values printed in network tables
		    	double height = table.getNumber("height", 0);
		    	double width = table.getNumber("width", 0);
		    	double xval = table.getNumber("centerX", 0);
		    	double yval = table.getNumber("centerY", 0);
		    	//Find the ratio of height to width
		    	double ratio = height/width;
		    	//Find vertical offset
		    	double vertdiff = yval - 120;
		    	//Find horizontal offset
		    	double hzdiff = xval - 240;
		    	
		    	if(vertdiff > 10){
		    		System.out.println("Move shooter up");
		    	}
		    	else if(vertdiff < 10){
		    		System.out.println("Move shooter down");
		    	}
		    	if(hzdiff > 10){
		    		System.out.println("Move robot right");
		    	}
		    	else if(hzdiff < 10){
		    		System.out.println("Move robot left");
		    	}
		    	if(ratio > .7){
		    		System.out.println("The target is angled too much, cannot shoot");
		    	}

	    	}
	    	
	    	
	    
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	        return false;
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    }
}
