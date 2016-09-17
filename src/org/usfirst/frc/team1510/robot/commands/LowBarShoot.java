package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarShoot extends CommandGroup {
    Drive drive = Robot.drive;
    public  LowBarShoot() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	drive.resetEncoders();
    	addSequential(new TimeDeployWheels(.5,.5));
    	addSequential(new Move(20000, .5));
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	addSequential(new TimeDeployWheels (.1, -.5));
    	addSequential(new TimeTurn(.75,-.5));
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	addSequential(new Move(2000, .5));
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	addSequential(new TimeTurn(.05,-.5));
    	//addSequential(new AA2());
    	addSequential(new ShootHigh());
    }
}
