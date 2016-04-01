package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossRockWall extends CommandGroup {

    public CrossRockWall() {
    	/*
    	//Move up to defense
    	addSequential(new Move(300, Move.MoveDirection.FORWARD));
    	//Deploy wheels over the rock wall
    	addSequential(new DeployWheels(150));
    	//Charge forward
    	addSequential(new Move(200, Move.MoveDirection.FORWARD));
    	addParallel(new RunWheels(.85));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		*/
    	//new Move();
    	addSequential(new TimeDeployWheels(.5,.5));
    	addSequential(new Move(7000, .8));
    	addSequential(new Turn(2200,-.8));
    	//addSequential(new Move(1000, -.5));
    	addSequential(new ShootLow());
    	//addSequential(new TimeDeployWheels(2, 0));
    	
    }
    protected void execute(){
    	
    	/*
    	if(Robot.drive.rightMotors[0].getEncPosition() <= 1000 ){
    		Robot.drive.move(.8, 0);
    	}
    	else{
    		Robot.drive.move(0, 0);
    	}
    	*/
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.rightMotors[0].setEncPosition(0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
