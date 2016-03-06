package org.usfirst.frc.team1510.robot.commands.crossers;

import org.usfirst.frc.team1510.robot.commands.DeployWheels;
import org.usfirst.frc.team1510.robot.commands.Move;
import org.usfirst.frc.team1510.robot.commands.RunWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWall extends CommandGroup {

    public CrossRockWall() {
    	//Move up to defense
    	addSequential(new Move(300));
    	//Deploy wheels over the rock wall
    	addSequential(new DeployWheels(150));
    	//Charge forward
    	addSequential(new Move(200));
    	addParallel(new RunWheels(.85));
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
