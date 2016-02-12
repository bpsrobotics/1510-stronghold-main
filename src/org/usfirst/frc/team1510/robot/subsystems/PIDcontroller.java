package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.GenericHID;

class PIDcontroller {

    private static final double Kp = 0.5, // K constants, gotta tune them
                                Ki = 0.1,
                                Kd = 0.0;

    // private static double prevError = 0.0; // Used for calculating Ki or something

    private double goalSpeed = 0.0, // For left and right motors, respectively
                   currentSpeed = 0.0;

    private static double returnSpeed = 0.0; // speed to pass to motor controlling class (the direct one, not through PID)

    private static double integrator = 0.0, // for left and right motors, respectively
                          derivator = 0.0;
    
    //private static double integratorMax = 0.0, // Max and min values of integrator, to prevent it from being stupid. Depricated, cause i'm using the cool variable thingy. 
    //                      integratorMin = 0.0; 

    private double CalcError(double speed, double goalspeed) { // Calculates error from input values
        return goalspeed - speed;
    }

    private double CalcKp(double error) {
        return error * Kp;
    }

    private double CalcKi() { // Calculates Ki value
        return integrator * Ki;
    }

    private double CalcKd(double error) { // Calculates Kd value -- TODO
        return Kd * (error - derivator);
    }

    private void UpdateIntegrator(double error) {
        integrator += error;
        this.integrator = CheckIntegratorMaxMin(integrator);
    }

    public void SetGoal(double goal) { // USE FOR SETTING GOAL SPEED
        this.goalSpeed = goal;
    }

    private void CheckIntegratorMaxMin(double integrator) {
        //EXPERIMENTAL - thanks to https://www.reddit.com/r/FRC/comments/44zy05/pi_loops/czuc2g1
        //Essentially makes it so I term can be max of what can command 1.0 (hence 1.0/Ki)
        if (integrator > (1.0/Ki))
            integrator = (1.0/Ki); // sorry i'm a python programmer
        else if (integrator < (-1.0/Ki))
            integrator = (-1.0/Ki); // AT LEAST I REMEMBERED THE SEMICOLON
        this.integrator = integrator;
    }
    
    public double PIDRun() {
        double error = CalcError(currentSpeed, goalSpeed);
        UpdateIntegrator(error);
        double pValue = CalcKp(error);
        double iValue = CalcKi();
        this.returnSpeed = pValue + iValue;
        return this.returnSpeed;
    }
    
}
