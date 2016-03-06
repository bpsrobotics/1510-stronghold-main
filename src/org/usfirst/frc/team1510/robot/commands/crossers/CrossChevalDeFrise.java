package org.usfirst.frc.team1510.robot.commands.crossers;

import org.usfirst.frc.team1510.robot.commands.DeployWheels;
import org.usfirst.frc.team1510.robot.commands.Move;
import org.usfirst.frc.team1510.robot.commands.RunWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossChevalDeFrise extends CommandGroup {

    public CrossChevalDeFrise() {
    	//Move forward to the defense
    	addSequential(new Move(400));
    	//Deploy wheel arms to stabilize seesaws
    	addSequential(new DeployWheels(135));
    	//Move forward with all 8 wheels
    	addSequential(new Move(40));
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
