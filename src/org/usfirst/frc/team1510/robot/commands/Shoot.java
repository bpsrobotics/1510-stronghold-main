package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import org.usfirst.frc.team1510.robot.subsystems.UltrasonicSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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

    public static final TARGET_WIDTH_FT = (1+2/3);
    public static final CAMERA_FOV = 0.652753 / 2;
    public static final CAMERA_FOV_PIXELS = 320 / 2;
    
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
	requires(Robot.ballCollector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	String tPixel = NetworkTable.getTable("CameraInfo").getString("XWidth", "-1");

	double TPIXEL = Double.toDouble(tPixel);
	
	distance = TARGET_WIDTH_FT * CAMERA_FOV_PIXELS / (2*TPIXEL*Math.tan(CAMERA_FOV);
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
