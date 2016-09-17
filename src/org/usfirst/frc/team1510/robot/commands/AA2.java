package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import org.usfirst.frc.team1510.robot.subsystems.UltrasonicSubsystem;
/**
 *
 */
public class AA2 extends Command {
	
	Drive drive = Robot.drive;
	Shooter shooter = Robot.shooter;
	double offset;
	double reqX = 170;
	boolean isComplete = false;
	public AA2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double offset = reqX - shooter.getTargetInfo()[3];
    	if(Math.abs(offset) <= 3){
    		drive.move(0, 0);
    		isComplete= true;
    	}
    	else if (offset > 3){
    		drive.resetEncoders();
    		if(Math.abs(drive.rightMotors[0].getEncPosition()) <= 1){
    			drive.leftMotors[0].set(.75);
    			drive.leftMotors[1].set(.75);
    			drive.rightMotors[0].set(.75);
    			drive.rightMotors[1].set(.75);
    		}
    		/*else{
    			drive.stop();
    		}*/
    	}
    	else if (offset < -3){
    		drive.resetEncoders();
    		if(Math.abs(drive.rightMotors[0].getEncPosition()) <= 1){
    		drive.leftMotors[0].set(-.75);
    		drive.leftMotors[1].set(-.75);
    		drive.rightMotors[0].set(-.75);
    		drive.rightMotors[1].set(-.75);
    		}
    		/*
    		else{
    			drive.stop();
    		}*/
    	}
    	else{
    		drive.stop();
    	}
    	System.out.println(offset);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
