package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import org.usfirst.frc.team1510.robot.subsystems.UltrasonicSubsystem;
/**
 *
 */
public class Shoot extends Command {
	
    Shooter shooter = Robot.shooter;

    BallCollector pickup = Robot.ballCollector;
    
    UltrasonicSubsystem sonic = Robot.ultrasonic;

    private double distance;
    private int cycles = 0;
    private boolean complete = false;
    
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
	requires(Robot.ballCollector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	distance = sonic.getRange()/1000;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	if (cycles > 150) {
	    pickup.off();
	    complete = true;
	}
	else if (cycles > 100) {
	    pickup.forward();
	}
	shooter.fire(distance);
	cycles++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
