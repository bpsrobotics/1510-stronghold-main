package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.WheelArms;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeDeployWheels extends Command {
	
	WheelArms wheelArms = Robot.wheelArms;
	private double reqPower;
    public TimeDeployWheels(double time, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.wheelArms);
    	setTimeout(time);
    	reqPower = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	wheelArms.moveArm(reqPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	wheelArms.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
