package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.OI;
import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class TeleopDrive extends Command {

	Drive drive = Robot.drive;
	Shooter shooter = Robot.shooter;
	private double multiplier = .9;
	private double multiplierTurn = 1;
	public boolean isCoast = true;
	public boolean isBrake = false;
	double offset;
	double reqX = 175;
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
    	SmartDashboard.putNumber("Left Encoder", encoderValues[0]);
    	SmartDashboard.putNumber("Right Encoder", encoderValues[1]);
    	//Press A and B to switch between coast and brake modes
    	if (Robot.oi.g1btnA.get()){
    		isBrake = drive.setBrake();
    	}
    	if(Robot.oi.g1btnB.get()){
    		isCoast = drive.setCoast();
    	}
    	else if(Robot.oi.gamepad1.getRawAxis(2) > .5 ){
    		/*
    		//Guide wheels will always be at full power
    		shooter.changeDistance(.6);
    		//Main shooter wheels are set to 25% power
    		shooter.changeHeight(.25);
    		
    	}else {
    		shooter.stop();
    		*/
    		double offset = reqX - shooter.getTargetInfo()[3];
        	if(Math.abs(offset) <= 3){
        		drive.move(0, 0);
        	}
        	else if (offset > 3){
        		drive.resetEncoders();
        		if(Math.abs(drive.rightMotors[0].getEncPosition()) <= 1){
        			drive.leftMotors[0].set(.35);
        			drive.leftMotors[1].set(.35);
        			drive.rightMotors[0].set(.35);
        			drive.rightMotors[1].set(.35);
        		}
        		/*else{
        			drive.stop();
        		}*/
        	}
        	else if (offset < -3){
        		drive.resetEncoders();
        		if(Math.abs(drive.rightMotors[0].getEncPosition()) <= 1){
        		drive.leftMotors[0].set(-.35);
        		drive.leftMotors[1].set(-.35);
        		drive.rightMotors[0].set(-.35);
        		drive.rightMotors[1].set(-.35);
        		}
        		/*
        		else{
        			drive.stop();
        		}*/
        	}
        	else{
        		drive.stop();
        	}
        	//System.out.println(offset);
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
