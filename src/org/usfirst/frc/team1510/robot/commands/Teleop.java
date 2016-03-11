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
	/**
	 * Enumerator for shooter system
	 */
	private enum ShooterStage {
		STARTSPIN, SPIN, STARTSHOOT, SHOOT, STOP, OFF
	}
	//Declare all necessary subsystems
    Drive drive = Robot.drive;
    WheelArms wheelArms = Robot.wheelArms;
    Shooter shooter = Robot.shooter;
    BallCollector ballCollector = Robot.ballCollector;
    //Create new commands
    private OI oi = Robot.oi;
    //Declare default speed of bottom motors in ShootHigh as .95
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
    
    // Controls for shooter systems
    private ShooterStage shooterStage = ShooterStage.OFF;
    private int timeCounter = 0;
    
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
    	//Set Teleop drive as default command for the drive subsystem
    	//This will allow other command to interrupt it (like AutoAim)
    	Robot.drive.setDefault();
    	new TeleopDrive().start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Post ultrasonic distance to ShitDashboard
    	SmartDashboard.putNumber("Ultrasonic Distance", Robot.ultrasonic.getInches());
    	/**
    	 * 
    	 * Begin controls for manipulator
    	 * 
    	 **/
    	//If input from joystick is greater than deadzone 
    	if (Math.abs(Robot.oi.gamepad2.getRawAxis(5)) > .1){
    		//Cancel all commands requiring wheel arms
    		retractWheels.cancel();
    		deployWheels.cancel();
    		//Set the arm motor of the wheel arms to joystick value
    		wheelArms.moveArm(Robot.oi.gamepad2.getRawAxis(5));
    	}
    	//Else stop arms from moving
    	else if (Math.abs(Robot.oi.gamepad2.getRawAxis(5)) <= .1){
    		wheelArms.moveArm(0);
    	}
    	//If input from joystick is greater than deadzone
    	if (Math.abs(Robot.oi.gamepad2.getRawAxis(1)) > .1){
    		//Cancel all commands requiring the ball collector
    		retractRoller.cancel();
    		deployRoller.cancel();
    		//Set the arm motor of the ball collector to joystick value
    		ballCollector.moveArm(Robot.oi.gamepad2.getRawAxis(1));
    	}
    	//Else stop arms from moving
    	else if (Math.abs(Robot.oi.gamepad2.getRawAxis(1)) <= .1){
    		ballCollector.moveArm(0);
    	}
    	/*If the top of the d-pad is pressed and the current speed
    		is less than 1 (max) then increase speed by increments of .001
    	 */
    	if(oi.gamepad2.getPOV(0) == 0 && speed < 1){
    		speed += .001;
    	}
    	/*If the bottom of the d-pad is pressed and the current speed
			is more than .7 (min) then decrease speed by increments of .001
    	 */
    	if(oi.gamepad2.getPOV(0) == 180 && speed > .7){
    		speed -= .001;
    	}
    	//Print the current value of speed for drivers to see
    	System.out.println(speed);
    	//If button A is pressed and the roller isn't being deployed
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
    	if (oi.btnX.get()) {
    		// End commands requiring roller
    		retractRoller.cancel();
    		pickupBall.cancel();
    		releaseBall.cancel();
    		deployRoller.cancel();
    		//start command
    		ballCollector.forward();
    	}else if (!oi.btnX.get()) {
    		// End commands requiring roller
    		deployRoller.cancel();
    		pickupBall.cancel();
    		releaseBall.cancel();
    		retractRoller.cancel();
    		//start command
    		ballCollector.off();	
    	} else if (oi.btnY.get()) {
    		// End commands requiring roller
    		deployRoller.cancel();
    		pickupBall.cancel();
    		releaseBall.cancel();
    		retractRoller.cancel();
    		//start command
    		ballCollector.reverse();
    	}else if (!oi.btnY.get()) {
    		// End commands requiring roller
    		deployRoller.cancel();
    		pickupBall.cancel();
    		releaseBall.cancel();
    		retractRoller.cancel();
    		//start command
    		ballCollector.off();
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
    	//If right trigger is pressed
    	if(oi.gamepad2.getRawAxis(3) > .5){
    		
    		
    		switch (shooterStage) {
    		case OFF:
    			shooterStage = ShooterStage.STARTSPIN;
    		case STARTSPIN:
    			// Begin spinning motors and reset time counter
    			shooter.changeDistance(1);
    			shooter.changeHeight(speed);
    			timeCounter = 0;
    			// Advance to next stage
    			shooterStage = ShooterStage.SPIN;
    			// Delay advancement to next runthrough
    			break;
    		case SPIN:
    			// Count down to 0.25 seconds for motor spinup
    			timeCounter += 20;
    			if (timeCounter >= 250) {
    				// Once time has been reached, advance to shoot
    				shooterStage = ShooterStage.STARTSHOOT;
    			} else {
    				// If not time reached, exit switch
    				break;
    			}
    		case STARTSHOOT:
    			// Reset time counter
    			timeCounter = 0;
    			// Turn on collector
    			ballCollector.forward();
    			// Advance to next stage
    			shooterStage = ShooterStage.SHOOT;
    			// Delay advancement to next runthrough
    			break;
    		case SHOOT:
    			// Advance time counter by 0.02 seconds
    			timeCounter += 20;
    			if (timeCounter >= 1000) {
    				// If 1 second reached, advance to stop
    				shooterStage = ShooterStage.STOP;
    			} else {
    				// If not time reached, exit switch
    				break;
    			}
    		case STOP:
    			// Turn off all motors
    			shooter.stop();
    			ballCollector.off();
    			// Advance to next stage
    			shooterStage = ShooterStage.OFF;
    			break;
    		}
    		
    		/*
    		//Set power for shooting motors
    		//Guide wheels will always be at full power
    		shooter.changeDistance(1);
    		//Main shooter wheels are set to double speed
    		shooter.changeHeight(speed);
    		//Wait for motors to get up to speed
    		try {
    			Thread.sleep(250);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		//Feed ball into shooter
    		ballCollector.forward();
    		//Wait till ball is shot
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		//Stop all motors
    		shooter.stop();
    		ballCollector.off();
    		*/
    	}
    	//If left trigger is pressed 
    	if(oi.gamepad2.getRawAxis(2) > .5){
    		//Start command
    		shootLow.start();
    	}
    	/**
    	 * 
    	 * Begin controls for main driver
    	 * 
    	 **/
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
    	} if(oi.g1btnA.get()) {
    		deployWheels.cancel();
    		retractWheels.cancel();
    		wheelArms.stop();
    	} 
	

	
	/*if(oi.g1btnA.get()){
		runWheels.start();
	}
	//else if (oi.leftStickPress.get() && runWheels.isRunning()){
		//runWheels.cancel();
	//}*/
	//If start button is pressed on either joystick
	if (oi.start.get() || oi.g1start.get()) {
	    // Cancel all commands
	    deployRoller.cancel();
	    retractRoller.cancel();
	    pickupBall.cancel();
	    releaseBall.cancel();
	    deployWheels.cancel();
	    retractWheels.cancel();
	    //Stop all subsystems
	    wheelArms.stop();
	    shooter.stop();
	    ballCollector.off();
	    //runWheels.cancel();
	}
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
