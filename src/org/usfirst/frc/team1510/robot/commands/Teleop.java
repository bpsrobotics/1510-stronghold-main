package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.*;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import org.usfirst.frc.team1510.robot.subsystems.WheelArms;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Teleop extends Command {

    Drive drive = Robot.drive;
    WheelArms wheelArms = Robot.wheelArms;
    Shooter shooter = Robot.shooter;

    private OI oi = Robot.oi;

    private DeployRoller deployRoller = new DeployRoller();
    private RetractRoller retractRoller = new RetractRoller();
    private DeployWheels deployWheels = new DeployWheels(10);
    private RetractWheels retractWheels = new RetractWheels(10);
    private BallPickup pickupBall = new BallPickup();
    private BallRelease releaseBall = new BallRelease();
    
    public Teleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooter);
    	requires(wheelArms);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.oi.btnA.whenPressed(new DeployRoller());
    	//Robot.oi.btnB.whenPressed(new RetractRoller());
    	//Robot.oi.btnX.whenPressed(new DeployWheels(135));
    	//Robot.oi.btnY.whenPressed(new RetractWheels(135));
    	//Robot.oi.rightBumper.whenPressed(new BallPickup());
    	//Robot.oi.leftBumper.whenPressed(new BallRelease());

    	//Need to make command to reset distance
    	//Robot.oi.lStick.whenPressed(new ResetDistance);
    	//Robot.oi.rStick.whenPressed(new AutoAim());
    	
    	//Robot.oi.btnY.whenPressed(new RunWheels(drive.getAverageDistance()));
    	Robot.drive.setDefault();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//wheelArms.move(Robot.oi.gamepad2.getRawAxis(2));
    	//if (Robot.oi.gamepad2.getRawAxis(3) > 50) ;
    	shooter.changeDistance(Robot.oi.gamepad2.getY());
    	System.out.println(Robot.oi.gamepad2.getY());
    	shooter.changeHeight(Robot.oi.gamepad2.getRawAxis(5));
    	System.out.println(Robot.oi.gamepad2.getRawAxis(5));

	if (oi.btnA.get() && !deployRoller.isRunning()) {
	    // End commands requiring roller
	    retractRoller.cancel();
	    pickupBall.cancel();
	    releaseBall.cancel();
	    // Start command
	    deployRoller.start();
	} else if (oi.btnB.get() && !retractRoller.isRunning()) {
	    // End commands requiring roller
	    deployRoller.cancel();
	    pickupBall.cancel();
	    releaseBall.cancel();
	    // Start command
	    retractRoller.start();
	} else if (oi.rightBumper.get() && !pickupBall.isRunning()) {
	    // End commands requiring roller
	    deployRoller.cancel();
	    retractRoller.cancel();
	    releaseBall.cancel();
	    // Start command
	    pickupBall.start();
	} else if (oi.leftBumper.get() && !releaseBall.isRunning()) {
	    // End commands requiring roller
	    deployRoller.cancel();
	    retractRoller.cancel();
	    pickupBall.cancel();
	    // Start command
	    releaseBall.start();
	}

	if (oi.btnX.get() && !deployWheels.isRunning()) {
	    // End commands requiring wheel arms
	    retractWheels.cancel();
	    // Start command
	    deployWheels.start();
	} else if (oi.btnY.get() && !retractWheels.isRunning()) {
	    // End commands requiring wheel arms
	    deployWheels.cancel();
	    // Start command
	    retractWheels.start();
	}

	if (oi.start.get()) {
	    // Cancel all commands
	    deployRoller.cancel();
	    retractRoller.cancel();
	    pickupBall.cancel();
	    releaseBall.cancel();
	    deployWheels.cancel();
	    retractWheels.cancel();
	}
    	
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
