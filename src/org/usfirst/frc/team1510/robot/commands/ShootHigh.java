package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
/**
 *
 */
public class ShootHigh extends Command {
	
	Shooter shooter = Robot.shooter;
	BallCollector ballCollector= Robot.ballCollector;
    public ShootHigh() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    	requires(ballCollector);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(4);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Set power of shooter wheels and guide wheels
    	shooter.changeDistance(2);
		shooter.changeHeight(.70);
		if(timeSinceInitialized() >= 1){
			ballCollector.rollerMotor.set(1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stop();
		ballCollector.rollerMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
