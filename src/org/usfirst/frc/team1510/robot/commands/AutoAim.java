package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.*;


public class AutoAim extends Command{
	
	private NetworkTable table = NetworkTable.getTable("GRIP/Target");
	UltrasonicSubsystem sonic = Robot.ultrasonic;
	Shooter shooter = Robot.shooter;
	TargetLight targetLight = Robot.targetLight;
	Drive drive = Robot.drive;
 	double distance;
	//private TargetLight targetLight = Robot.targetLight;
	private boolean complete = false;

    public static final int CENTERED_X = 166;
    public static final int ADJUSTED_WIDTH = CENTERED_X * 2;
    public static final double kP = 2;

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

		/*
    		double[] defaultValue = {};
    		
		double[] xValues = table.getNumberArray("centerX", defaultValue);
		double[] areas = table.getNumberArray("area", defaultValue);

		if (xValues.length <= 0) return;

		double largestIndex = 0;

		for (int i = 0; i < xValues.length; i++) {
		    if (areas[i] > largestArea) {
			largestIndex = i;
		    }
		}

		*/

		double xValue = shooter.getTargetInfo()[3];
		
		double alignmentRatio = (xValue / ADJUSTED_WIDTH - 0.5) * 2;

		if (Math.abs(alignmentRatio) <= 0.05) {
		    complete = true;
		}

		    drive.move(0, alignmentRatio * kP);
		    
		
	    }
    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	
	        return complete;
	        
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	targetLight.off();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }
}
