
package org.usfirst.frc.team1510.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1510.robot.commands.*;
import org.usfirst.frc.team1510.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    ///////////////////////////////////////////
    //////////////// IMPORTANT ////////////////
    //// DO NOT INITIALIZE SUBSYSTEMS FROM ////
    /////////// OUTSIDE CONSTRUCTOR ///////////
    ///////////////////////////////////////////
    
    public static Drive drive;
    public static OI oi;
    public static TargetLight targetLight;
    public static Shooter shooter;
    public static BallCollector ballCollector;
    public static WheelArms wheelArms;
    
    
    Command autonomousCommand;
    Command teleopCommand;
    SendableChooser chooser;
    
    // Autonomous settings
    SendableChooser startingPosition;
    SendableChooser defense;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	oi = new OI();
    	drive = new Drive();
    	targetLight = new TargetLight(1);
    	
    	startingPosition = new SendableChooser();
    	startingPosition.addDefault("1", Autonomous.StartingPosition.POS1);
    	startingPosition.addObject("2", Autonomous.StartingPosition.POS2);
    	startingPosition.addObject("3", Autonomous.StartingPosition.POS3);
    	startingPosition.addObject("4", Autonomous.StartingPosition.POS4);
    	startingPosition.addObject("5", Autonomous.StartingPosition.POS5);

	defense = new SendableChooser();
    	defense.addDefault("Portcullis", Autonomous.Defense.PORTCULLIS);
    	defense.addObject("Cheval de Frise", Autonomous.Defense.CHEVAL_DE_FRISE);
    	defense.addObject("Moat", Autonomous.Defense.MOAT);
    	defense.addObject("Ramparts", Autonomous.Defense.RAMPARTS);
    	//defense.addObject("Drawbridge", Autonomous.Defense.DRAWBRIDGE);
    	//defense.addObject("Sally Port", Autonomous.Defense.SALLY_PORT);
    	defense.addObject("Rock Wall", Autonomous.Defense.ROCK_WALL);
    	defense.addObject("Rough Terrain", Autonomous.Defense.ROUGH_TERRAIN);
    	defense.addObject("Low Bar", Autonomous.Defense.LOW_BAR);

	SmartDashboard.putData("Starting Position",startingPosition);
	SmartDashboard.putData("Defense to Cross",defense);
	/*
	  chooser = new SendableChooser();
	  chooser.addDefault("Default Auto", new ExampleCommand());
	  //        chooser.addObject("My Auto", new MyAutoCommand());
	  SmartDashboard.putData("Auto mode", chooser);
        */
    }
    
    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit(){
	
    }
    
    public void disabledPeriodic() {
	Scheduler.getInstance().run();
    }
    
    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
     * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
     * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
     * below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
     * or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit() {
    	if (teleopCommand != null) teleopCommand.cancel();
        autonomousCommand = new AutoAim();
        
	/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
	   switch(autoSelected) {
	   case "My Auto":
	   autonomousCommand = new MyAutoCommand();
	   break;
	   case "Default Auto":
	   default:
	   autonomousCommand = new ExampleCommand();
	   break;
	   } */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        teleopCommand = new TeleopDrive();
        teleopCommand.start();
        
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
