package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Move extends Command {

    // Drive code
    private Drive drive = Robot.drive;
    public enum MoveDirection {
    	FORWARD, BACKWARD
    }
    // Distance to move
    private double distance;
    private double power;
    // Records if complete or not
    private boolean isComplete = false;
    private int isReversed = 1;
    //private MoveDirection moveDirection = MoveDirection.FORWARD; 
    
    /**
     * Creates a Move command
     * @param distance The number of degrees on the encoder
     * @param reverse Whether or not to go forward or backward
     * 					1 goes forward, -1 goes backward
     */
    public Move(double reqDistance, double reqPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.drive);
    	//moveDirection = direction;
    	///this.distance = distance;
    	distance = reqDistance;
    	power = reqPower;
    	setTimeout(5);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.enable();
    	drive.resetEncoders();
    	drive.leftMotors[0].enableBrakeMode(true);
    	drive.leftMotors[1].enableBrakeMode(true);
    	drive.rightMotors[0].enableBrakeMode(true);
    	drive.rightMotors[1].enableBrakeMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//switch(moveDirection) {
    	//case FORWARD:
    	
    	if(Math.abs(drive.rightMotors[0].getEncPosition()) <= distance){
    		drive.leftMotors[0].set(-power);
        	drive.leftMotors[1].set(-power);
        	drive.rightMotors[0].set(power);
        	drive.rightMotors[1].set(power);
    	}
    	else{
    		drive.move(0, 0);
    		isComplete = true;
    	}
    		//isComplete = drive.driveDistance(this.distance,-0.85);
    	//case BACKWARD:
    		//isComplete = drive.driveDistance(this.distance,0.85);
    	//}
    	

    	if (isTimedOut()) {
    		isComplete = true;
    	}
	
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
}
