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

    BallCollector ballCollector = Robot.ballCollector;
    
    UltrasonicSubsystem sonic = Robot.ultrasonic;

    private double distance;
    private int cycles = 0;
    private boolean complete = false;

    public static final double TARGET_WIDTH_FT = (1+2/3);
    public static final double CAMERA_FOV = 0.652753 / 2;
    public static final double CAMERA_FOV_PIXELS = 320 / 2;
    
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	requires(Robot.ballCollector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//String tPixel = NetworkTable.getTable("CameraInfo").getString("XWidth", "-1");

    	//double TPIXEL = String.toDouble(tPixel);
    	
    	//distance = TARGET_WIDTH_FT * CAMERA_FOV_PIXELS / (2*TPIXEL*Math.tan(CAMERA_FOV));
    	setTimeout(4);
    	shooter.fire();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	/*if (cycles > 150) {
	    pickup.off();
	    complete = true;
	}
	else if (cycles > 100) {
	    pickup.forward();
	}
	shooter.fire(distance);
	cycles++;
    }*/
    	if(timeSinceInitialized() >= 2){
			ballCollector.rollerMotor.set(1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ballCollector.rollerMotor.set(0);
    	shooter.changeDistance(0);
    	shooter.changeHeight(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
