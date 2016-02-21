package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployRoller extends Command {
	
	BallCollector ballCollector = new BallCollector();
	
    public DeployRoller() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballCollector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ballCollector.extend();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ballCollector.isSwitch1Set();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ballCollector.retract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
