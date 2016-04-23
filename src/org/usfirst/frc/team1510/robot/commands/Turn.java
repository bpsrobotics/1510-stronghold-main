package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Turn extends Command {

    // Drive code
    private Drive drive = Robot.drive;

    // Degrees to turn
    private double degrees;

    // Direction to turn
    private Turn.Direction direction;

    // Records if complete or not
    private boolean isComplete = false;
    private double power;
    public Turn(double encCounts , double reqPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);

    	degrees = encCounts;
    	power = reqPower;
    	//this.direction = direction;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.enable();
    	drive.resetEncoders();
    	drive.rightMotors[0].setEncPosition(0);
    	drive.leftMotors[0].enableBrakeMode(true);
    	drive.leftMotors[1].enableBrakeMode(true);
    	drive.rightMotors[0].enableBrakeMode(true);
    	drive.rightMotors[1].enableBrakeMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Math.abs(drive.rightMotors[0].getEncPosition()) <= degrees){
    		drive.leftMotors[0].set(power);
        	drive.leftMotors[1].set(power);
        	drive.rightMotors[0].set(power);
        	drive.rightMotors[1].set(power);
    	}
    	else{
    		drive.move(0, 0);
    		isComplete = true;
    	}
    	/*switch (direction) {
    	case LEFT:
    		isComplete = drive.turn(this.degrees,-0.85);
    		break;
    	case RIGHT:
    		isComplete = drive.turn(this.degrees,0.85);
    		break;
    	default:
    		break;
    	}*/
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isComplete;
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

    public enum Direction {
        LEFT, RIGHT
    }
}
