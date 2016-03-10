package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootLow extends Command {

	Shooter shooter = Robot.shooter;
	BallCollector ballCollector= Robot.ballCollector;
	boolean isDone;
    public ShootLow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    	requires(ballCollector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Set speed of shooting motors
    	shooter.changeDistance(1);
		shooter.changeHeight(.25);
		//Wait till motors get up to speed
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Feed ball to shooter
		ballCollector.forward();
		//Wait till ball is shot
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Stop all motors before exiting
		shooter.stop();
		ballCollector.off();
		isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stop();
		ballCollector.off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
