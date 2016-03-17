package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.WheelArms;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunWheels extends Command {
	double speed;
	WheelArms wheelArms = Robot.wheelArms;
	
    public RunWheels(double reqSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.wheelArms);
    	speed = reqSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	wheelArms.move(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	wheelArms.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
