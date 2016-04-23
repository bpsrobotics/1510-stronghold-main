package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeTurn extends Command {
    private Drive drive = Robot.drive;
    private double reqThrottle = 0;
    private double reqTime = 0;
    public TimeTurn(double time, double throttle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	reqTime = time;
    	reqThrottle = throttle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(reqTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!isTimedOut()){
    		drive.leftMotors[0].set(reqThrottle);
    		drive.leftMotors[1].set(reqThrottle);
    		drive.rightMotors[0].set(reqThrottle);
    		drive.rightMotors[1].set(reqThrottle);
    	}
    	else{
    		drive.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.move(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
