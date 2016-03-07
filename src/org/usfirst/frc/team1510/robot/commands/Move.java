package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Move extends Command {

    // Drive code
    private Drive drive = Robot.drive;

    // Distance to move
    private double distance;

    // Records if complete or not
    private boolean isComplete = false;
    
    public Move(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);

	this.distance = distance;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	isComplete = drive.driveDistance(this.distance,0.85);
	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
