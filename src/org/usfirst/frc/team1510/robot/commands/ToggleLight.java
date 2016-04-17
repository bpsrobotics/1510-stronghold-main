package org.usfirst.frc.team1510.robot.commands;

import org.usfirst.frc.team1510.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1510.robot.subsystems.TargetLight;

public class ToggleLight extends Command {
	TargetLight targetLight = Robot.targetLight;
    public void initialize() {
	Robot.targetLight.on();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
	return false;
    }

    protected void end() {
	targetLight.off();
    }

    protected void interrupted() {
	end();
    }
    
}
