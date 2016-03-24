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
	private double multiplierTurn = 1;
	public boolean isCoast = true;
	public boolean isBrake = false;
    public TeleopDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.enable();
    	drive.setCoast();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Post whether or not the drive is in brake mode or not
    	if(isBrake){
    		SmartDashboard.putString("Drive Mode", "Brake");
    	}
    	if(isCoast){
    		SmartDashboard.putString("Drive Mode", "Coast");
    	}
    	//Hold right bumper for slow mode
    	if (Robot.oi.g1rightBumper.get()){
    		multiplier = .5;
    		multiplierTurn = .75;
    	}
    	else{
    		multiplier = .9;
    		multiplierTurn = 1;
    		
    	}
    	//Move robot with joystick input
    	drive.move(OI.deadzone(Robot.oi.gamepad1.getY())*multiplier, OI.deadzone(Robot.oi.gamepad1.getRawAxis(4))*multiplierTurn);
    	double[] encoderValues = drive.getEncoderValues();
    	//Post encoder values
    	//SmartDashboard.putNumber("Left Encoder", encoderValues[0]);
    	//SmartDashboard.putNumber("Right Encoder", encoderValues[1]);
    	//Press A and B to switch between coast and brake modes
    	if (Robot.oi.g1btnA.get()){
    		isBrake = drive.setBrake();
    	}
    	if(Robot.oi.g1btnB.get()){
    		isCoast = drive.setCoast();
    	}
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
