package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPortcullis extends CommandGroup {

    public CrossPortcullis() {
    	//Move forward
    	addSequential(new Move(40));
    	//Move second set of wheels down
    	addSequential(new DeployWheels(135));
    	//Drive underneath portcullis opening
    	addParallel(new RunWheels(.85));
    	addParallel(new Move(40));
    	//Stop secondary wheels
    	addSequential(new RunWheels(0));
    	//Move secondary wheels back while moving forward
    	//This will let the robot slide under
    	addParallel(new RetractWheels(135));
    	addParallel(new Move(180));
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
