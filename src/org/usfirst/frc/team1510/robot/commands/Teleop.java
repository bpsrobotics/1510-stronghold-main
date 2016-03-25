package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.*;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import org.usfirst.frc.team1510.robot.subsystems.WheelArms;
import org.usfirst.frc.team1510.robot.subsystems.Shooter;
import org.usfirst.frc.team1510.robot.subsystems.BallCollector;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.USBCamera;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Teleop extends Command {
	/**
	 * Enumerator for shooter system
	 */
	//Declare all necessary subsystems
    Drive drive = Robot.drive;
    WheelArms wheelArms = Robot.wheelArms;
    Shooter shooter = Robot.shooter;
    BallCollector ballCollector = Robot.ballCollector;
    //Create new commands
    private OI oi = Robot.oi;
    //Declare default speed of bottom motors in ShootHigh as .95
    private double speed = .95;
    //Declare default distance
    private double distance = 15;
    //Declare default recommended speed
    private double recSpeed = .85;
    private DeployRoller deployRoller = new DeployRoller();
    private RetractRoller retractRoller = new RetractRoller();
    private DeployWheels deployWheels = new DeployWheels(1);
    private RetractWheels retractWheels = new RetractWheels(1);
    private BallPickup pickupBall = new BallPickup();
    private BallRelease releaseBall = new BallRelease();
    private ShootHigh shootHigh = new ShootHigh();
    private ShootLow shootLow = new ShootLow();

    private boolean rightBumperPressedLast = false;
    private USBCamera currentCamera = Robot.forwardCamera;

    public Teleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(shooter);
    	//requires(wheelArms);
    	//requires(drive);
    	requires(ballCollector);
    	setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.oi.btnA.toggleWhenPressed(new SpinRoller());
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
    	wheelArms.stop();
    	ballCollector.off();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/**
    	  Post ultrasonic distance 
    	  Speed of shooter (varied by d-pad)
    	  Limit Switch values
    	*/
    	recSpeed =  shooter.getRecSpeed(distance);
    	//SmartDashboard.putNumber("Ultrasonic Distance", Robot.ultrasonic.getRange());
    	SmartDashboard.putNumber("Speed of shooter", speed);
    	SmartDashboard.putNumber("Estimated Distance", distance);
    	SmartDashboard.putNumber("Recommended Speed", recSpeed);
    	SmartDashboard.putBoolean("Home Limit", ballCollector.limitSwitch1.get());
    	SmartDashboard.putBoolean("Away Limit", ballCollector.limitSwitch2.get());

	// Controls for camera switching
	if (Robot.oi.g1rightBumper.get() && !rightBumperPressedLast && currentCamera == Robot.forwardCamera) {
	    Robot.camera.startAutomaticCapture(Robot.reverseCamera);
	    rightBumperPressedLast = true;
	}
	else if (Robot.oi.g1rightBumper.get() && !rightBumperPressedLast && currentCamera == Robot.reverseCamera) {
	    Robot.camera.startAutomaticCapture(Robot.forwardCamera);
	    rightBumperPressedLast = true;
	}
	else if (!Robot.oi.g1rightBumper.get()) {
	    rightBumperPressedLast = false;
	}
	
	
    	/**
    	 * 
    	 * Begin controls for manipulator
 		 * 
    	 **/
    	//If input from joystick is greater than deadzone 
    	if (Math.abs(oi.gamepad2.getRawAxis(1)) > .2){
    		//Cancel all commands requiring wheel arms
    		retractWheels.cancel();
    		deployWheels.cancel();
    		//Set the arm motor of the wheel arms to joystick value
    		wheelArms.moveArm(oi.gamepad2.getRawAxis(1));
    	}
    	else if (Math.abs(oi.gamepad2.getRawAxis(5)) > .2){
    		wheelArms.moveArm(oi.gamepad2.getRawAxis(5));
    	}
    	//Else stop arms from moving
    	else if (Math.abs(oi.gamepad2.getRawAxis(1)) <= .2 && Math.abs(oi.gamepad2.getRawAxis(5)) <= .2){
    		wheelArms.moveArm(0);
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
    	if(oi.gamepad2.getPOV(0) == 180 && speed > .5){
    		speed -= .001;
    	}
    	/*If the top of the d-pad is pressed and the current distance
		is less than 17 (max) then increase speed by increments of .1
    	 */
    	if(oi.gamepad1.getPOV(0) == 0 && distance < 17){
    		distance += .1;
    	}
    	/*If the bottom of the d-pad is pressed and the current speed
		is more than 0 (min) then decrease distance by increments of .1
    	 */
    	if(oi.gamepad1.getPOV(0) == 180 && distance > 0){
    		distance -= .1;
    	}
    	//While button A is held spin roller
    	//While button B is held reverse roller
    	if (oi.btnA.get()) {
    		ballCollector.rollerMotor.set(1);
    	}if (oi.btnB.get()) {
   	     	ballCollector.rollerMotor.set(-1);
       	}else if (!oi.btnB.get() && !oi.btnA.get()){
       		ballCollector.rollerMotor.set(0);
       	}
    	//While button X is held extend roller till limit
 		if (oi.btnX.get()) {
    		ballCollector.armMotor.set(.25);
    		if (!ballCollector.limitSwitch2.get()) {
    		    ballCollector.armMotor.set(0);
  		}
 		}
    	//While button Y is held retract roller till limit
    	else if (oi.btnY.get()) {
    		ballCollector.armMotor.set(-.25);	
    		if (!ballCollector.limitSwitch1.get()) {
    		    ballCollector.armMotor.set(0);
    		}
    	}
    	//If no button is pressed stop motors
    	else if (!oi.btnX.get() && !oi.btnY.get()) {
    		ballCollector.off();
    	} 
    	//If right trigger is pressed
    	if(oi.gamepad2.getRawAxis(3) > .5){
    		
    		//Set power for shooting motors
    		//Guide wheels will always be at full power
    		shooter.changeDistance(1);
    		//Main shooter wheels are set to double speed
    		shooter.changeHeight(speed);
    	} 
    	//If left trigger is pressed 
    	else if(oi.gamepad2.getRawAxis(2) > .5){
    		//Guide wheels will always be at full power
    		shooter.changeDistance(1);
    		//Main shooter wheels are set to 25% power
    		shooter.changeHeight(-.25);
    	}else {
    		shooter.stop();
    	}
    	
    	//Controls for Target Light
    	if(oi.rightBumper.get()){
    		toggleLight = -toggleLight;
    	}
    	if(toggleLight == 1){
    		Robot.targetLight.off();
    	}
    	else if(toggleCam == -1){
    		Robot.targetLight.on();
    	}
    	/**
    	 * 
    	 * Begin controls for main driver
    	 * 
    	 **/
	
    	//If start button is pressed on either joystick
    	/*if (oi.start.get() || oi.g1start.get()) {
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
    	}*/
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
