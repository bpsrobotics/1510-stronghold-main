package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.*;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import org.usfirst.frc.team1510.robot.subsystems.WheelArms;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Teleop extends Command {

    Drive drive = Robot.drive;
    WheelArms wheelArms = Robot.wheelArms;
    Shooter shooter = Robot.shooter;
    BallCollector ballCollector = Robot.ballCollector;
    private OI oi = Robot.oi;
    private double speed = .95; 
    private DeployRoller deployRoller = new DeployRoller();
    private RetractRoller retractRoller = new RetractRoller();
    private DeployWheels deployWheels = new DeployWheels(1);
    private RetractWheels retractWheels = new RetractWheels(1);
    private BallPickup pickupBall = new BallPickup();
    private BallRelease releaseBall = new BallRelease();
    private ShootHigh shootHigh = new ShootHigh();
    private ShootLow shootLow = new ShootLow();
    //private RunWheels runWheels = new RunWheels(oi.gamepad1.getY());
    
    public Teleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(shooter);
    	//requires(wheelArms);
    	//requires(drive);
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
    	new TeleopDrive().start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//wheelArms.move(Robot.oi.gamepad2.getRawAxis(2));
    if (Math.abs(Robot.oi.gamepad2.getRawAxis(5)) > .1){
    		retractWheels.cancel();
    		deployWheels.cancel();
    		wheelArms.moveArm(Robot.oi.gamepad2.getRawAxis(5));
    }
    else if (Math.abs(Robot.oi.gamepad2.getRawAxis(5)) <= .1){
    	wheelArms.moveArm(0);
    }
    if(oi.gamepad2.getPOV(0) == 0 && speed < 1){
   		speed += .001;
   	}
   	if(oi.gamepad2.getPOV(0) == 180 && speed > .7){
   		speed -= .001;
    }
    System.out.println(speed);
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

	if (oi.g1btnX.get() && !deployWheels.isRunning()) {
	    // End commands requiring wheel arms
	    retractWheels.cancel();
	    // Start command
	    deployWheels.start();
	} else if (oi.g1btnY.get() && !retractWheels.isRunning()) {
	    // End commands requiring wheel arms
	    deployWheels.cancel();
	    // Start command
	    retractWheels.start();
	} if (oi.g1btnA.get()) {
		deployWheels.cancel();
		retractWheels.cancel();
		wheelArms.stop();
	} 
	if(oi.gamepad2.getRawAxis(3) > .5){
	   	shooter.changeDistance(1);
	    shooter.changeHeight(speed);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ballCollector.forward();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		shooter.stop();
		ballCollector.off();
		
	}
	if(oi.gamepad2.getRawAxis(2) > .5){
		shootLow.start();
		
	}

	
	/*if(oi.g1btnA.get()){
		runWheels.start();
	}
	//else if (oi.leftStickPress.get() && runWheels.isRunning()){
		//runWheels.cancel();
	//}*/
	if (oi.start.get() || oi.g1start.get()) {
	    // Cancel all commands
	    deployRoller.cancel();
	    retractRoller.cancel();
	    pickupBall.cancel();
	    releaseBall.cancel();
	    deployWheels.cancel();
	    retractWheels.cancel();
	    wheelArms.stop();
	    //runWheels.cancel();
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
