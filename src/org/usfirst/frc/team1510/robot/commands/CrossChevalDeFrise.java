package org.usfirst.frc.team1510.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

    public CrossChevalDeFrise() {
    	/*
    	//Move forward to the defense
    	addSequential(new Move(400, Move.MoveDirection.FORWARD));
    	//Deploy wheel arms to stabilize seesaws
    	addSequential(new DeployWheels(135));
    	//Move forward with all 8 wheels
    	addSequential(new Move(40, Move.MoveDirection.FORWARD));
    	addParallel(new RunWheels(.85));
    	*/
    	
    	
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
