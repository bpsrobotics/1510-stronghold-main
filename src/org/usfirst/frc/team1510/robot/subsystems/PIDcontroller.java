package org.usfirst.frc.team1510.robot.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.RobotDrive;
// import edu.wpi.first.wpilibj.GenericHID;

class PIDcontroller {

    public double Kp = 0.5, // K constants, gotta tune them. Kp is multiplier of error. Should be between .5 and 1.5 GENERALLY ---- needs testing
                  Ki = 0.1, // Ki is multiplier of the integral. Should be pretty small (less than 1)
                  Kd = -0.1; // Kd is multiplier of derivivitve. Should be <0 iirc
                  //see? it spells pid!
    public double speedMulti = 1;

    private double previousError = 0,
                   previousSpeed = 0;

    public CANTalon[] encoders;

    // private  double prevError = 0.0; // Used for calculating Ki or something

    private double goalSpeed = 0.0,  // Goal speed that PID will work towards
                   currentSpeed = 0.0; // Current PID speed

    private double returnSpeed = 0.0; // speed to pass to motor controlling class (the direct one, not through PID)

    private double integrator = 0.0,
                   derivator = 0.0;
    
    //private static double integratorMax = 0.0, // Max and min values of integrator, to prevent it from being stupid. Depricated, cause i'm using the cool variable thingy. 
    //                      integratorMin = 0.0; 
    
    public PIDcontroller(double Kp, double Ki, double Kd, CANTalon[] encoder) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.encoders = encoder;
    }

    private double CalcError(double speed, double goalspeed) { // Calculates error from input values
        return goalspeed - speed;
    }

    private double CalcKp(double error) {
        return error * Kp;
    }

    private double CalcKi() { // Calculates Ki value
        return integrator * Ki;
    }

    private double CalcKd(double error, double previousSpeed) { // Calculates Kd value -- TODO
        return Kd * (error - derivator);
    }

    private double UpdateIntegrator(double error) {
        integrator += error;
        integrator = CheckIntegratorMaxMin(integrator);
        return integrator;
    }

    private double UpdateDerivator(double goalSpeed, double previousSpeed) {
        derivator = currentSpeed - previousSpeed;
        return derivator;
    }

    public void SetGoal(double goal) { // USE FOR SETTING GOAL SPEED
        this.goalSpeed = goal;
    }


    private double CheckIntegratorMaxMin(double integrator) {
        //EXPERIMENTAL - thanks to https://www.reddit.com/r/FRC/comments/44zy05/pi_loops/czuc2g1
        //Essentially makes it so I term can be max of what can command 1.0 (hence 1.0/Ki)
        if (integrator > (1.0/Ki))
            integrator = (1.0/Ki); // sorry i'm a python programmer
        else if (integrator < (-1.0/Ki))
            integrator = (-1.0/Ki); // AT LEAST I REMEMBERED THE SEMICOLON
        return integrator;
    }
    public double GetSpeed() {
        currentSpeed = encoders[0].getSpeed() * speedMulti;
        return currentSpeed;
    }

    public double PIDRun() {
        if (encoders[0] != null && encoders[1] != null && (Kp != 0 ||  Ki != 0 || Kd != 0)) { // making sure encoder and at least one K variable is assigned
            this.currentSpeed = GetSpeed();

            double error = CalcError(currentSpeed, goalSpeed);
            this.integrator = UpdateIntegrator(error);
            this.derivator = UpdateDerivator(this.goalSpeed, this.previousSpeed);

            double pValue = CalcKp(error);
            double iValue = CalcKi();
            double dValue = CalcKd(error, previousError);


            this.previousError = error;
            this.previousSpeed = currentSpeed;
            
            this.returnSpeed = pValue + iValue + dValue;

            return this.returnSpeed; // what will be doing motor driving iirc

        } else return 0;
    }
}
