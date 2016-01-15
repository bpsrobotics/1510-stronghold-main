package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Drive extends Subsystem {
    
    private static CANTalon[] leftMotors	= {new CANTalon(1), new CANTalon(2), new CANTalon(3), new CANTalon(4)};
    private static CANTalon[] rightMotors	= {new CANTalon(5), new CANTalon(6), new CANTalon(7), new CANTalon(8)};
    
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

