package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossChevalDeFrise;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossLowBar;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossMoat;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossPortcullis;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossRamparts;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossRockWall;
import org.usfirst.frc.team1510.robot.commands.crossers.CrossRoughTerrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
	
	
    public  Autonomous() {
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
	if(Robot.defense.getSelected().equals(Defense.PORTCULLIS)){
	    addSequential(new CrossPortcullis() );
	} else if(Robot.defense.getSelected().equals(Defense.CHEVAL_DE_FRISE) ){
	    addSequential(new CrossChevalDeFrise() );
	} else if(Robot.defense.getSelected().equals(Defense.MOAT) ){
	    addSequential(new CrossMoat() );
	} else if(Robot.defense.getSelected().equals(Defense.RAMPARTS)){
	    addSequential(new CrossRamparts() );
	} else if(Robot.defense.getSelected().equals(Defense.ROCK_WALL)){
	    addSequential(new CrossRockWall() );
    	} else if(Robot.defense.getSelected().equals(Defense.ROUGH_TERRAIN)){
	    addSequential(new CrossRoughTerrain() );
	} else if(Robot.defense.getSelected().equals(Defense.LOW_BAR)){
	    addSequential(new CrossLowBar() );
    	}
    }
    
    protected void execute(){

    }
    
    public enum StartingPosition {
    	POS1, POS2, POS3, POS4, POS5
    }
    
    public enum Defense {
    	PORTCULLIS, CHEVAL_DE_FRISE, MOAT, RAMPARTS, DRAWBRIDGE, SALLY_PORT, ROCK_WALL, ROUGH_TERRAIN, LOW_BAR
    }
}
