package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.*;

public class AutoAim extends Command{

	 public AutoAim() {
	        // Use requires() here to declare subsystem dependencies
	        requires(Robot.drive);
	        requires(Robot.shooter);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	shooter.reset();
	    	table = NetworkTable.getTable("GRIP/Target");
	    	double[] defaultValue = new double[0];
	    	double[] y1 = table.getNumberArray("y1", defaultValue);
	    	double[] y2 = table.getNumberArray("y2", defaultValue);
	    	double[] x1 = table.getNumberArray("x1", defaultValue);
	    	double[] x2 = table.getNumberArray("x2", defaultValue);
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute(){
	    	
	    	
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
