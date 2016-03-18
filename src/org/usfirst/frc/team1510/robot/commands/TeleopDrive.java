package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.OI;
import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class TeleopDrive extends Command {

	Drive drive = Robot.drive;
	private double multiplier = .9;
    public TeleopDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.g1rightBumper.get()){
    		multiplier = .5;
    	}
    	else{
    		multiplier = .9;
    	}
    	drive.move(Robot.oi.gamepad1.getY()*multiplier, Robot.oi.gamepad1.getRawAxis(4)*multiplier);
    	double[] encoderValues = drive.getEncoderValues();
    	
    	SmartDashboard.putNumber("Left Encoder", encoderValues[0]);
    	SmartDashboard.putNumber("Right Encoder", encoderValues[1]);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
