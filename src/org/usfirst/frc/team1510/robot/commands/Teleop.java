package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team1510.robot.*;

/**
 *
 */
public class Teleop extends Command {

	Drive drive = Robot.drive;
	
    public Teleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.oi.leftTrigger.whenPressed(new AutoAim());
    	Robot.drive.setDefault();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.btnA.toggleWhenPressed(new DeployRoller());
    	Robot.oi.btnB.toggleWhenPressed(new BallPickup());
    	Robot.oi.btnX.toggleWhenPressed(new DeployWheels(30));
    	Robot.oi.btnY.toggleWhenPressed(new RunWheels(drive.getAverageDistance()));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.drive.resetDefault();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
