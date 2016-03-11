package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
//import org.usfirst.frc.team1510.robot.commands.Teleop.ShooterStage;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootLow extends Command {
	private enum ShooterStage {
		STARTSPIN, SPIN, STARTSHOOT, SHOOT, STOP, OFF
	}
	private ShooterStage shooterStage = ShooterStage.OFF;
	private int timeCounter = 0;
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
		switch (shooterStage) {
		case OFF:
			shooterStage = ShooterStage.STARTSPIN;
		case STARTSPIN:
			// Begin spinning motors and reset time counter
			shooter.changeDistance(1);
			shooter.changeHeight(.25);
			timeCounter = 0;
			// Advance to next stage
			shooterStage = ShooterStage.SPIN;
			// Delay advancement to next runthrough
			break;
		case SPIN:
			// Count down to 0.25 seconds for motor spinup
			timeCounter += 20;
			if (timeCounter >= 250) {
				// Once time has been reached, advance to shoot
				shooterStage = ShooterStage.STARTSHOOT;
			} else {
				// If not time reached, exit switch
				break;
			}
		case STARTSHOOT:
			// Reset time counter
			timeCounter = 0;
			// Turn on collector
			ballCollector.forward();
			// Advance to next stage
			shooterStage = ShooterStage.SHOOT;
			// Delay advancement to next runthrough
			break;
		case SHOOT:
			// Advance time counter by 0.02 seconds
			timeCounter += 20;
			if (timeCounter >= 1000) {
				// If 1 second reached, advance to stop
				shooterStage = ShooterStage.STOP;
			} else {
				// If not time reached, exit switch
				break;
			}
		case STOP:
			// Turn off all motors
			shooter.stop();
			ballCollector.off();
			// Advance to next stage
			shooterStage = ShooterStage.OFF;
			break;
		}
    	/*
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
		*/
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
    	end();
    }
}
