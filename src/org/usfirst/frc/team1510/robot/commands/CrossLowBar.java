package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1510.robot.commands.Move;
/**
 *
 */
public class CrossLowBar extends CommandGroup {

    public CrossLowBar() {
    	//Move wheels down so they don't hit the low bar
    	addSequential(new TimeDeployWheels(.4,.5));
    	//Move all 8 wheels forward
    	addSequential(new TimeMove(1.5, .75));
    	//addSequential(new Move(500, Move.MoveDirection.BACKWARD));
    	//addSequential(new Move(500, Move.MoveDirection.FORWARD));
    	//addParallel(new RunWheels(.85));
    	
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
