private class PIDcontroller {

    private static double prevError = 0.0; // Used for calculating Ki

    private static double goalSpeed = 0.0, // For left and right motors, respectively
                          currentSpeed = 0.0;

    private static double returnSpeed = 0.0; // speed to pass to motor controlling class (the direct one, not through PID)

    private static double integrator = 0.0, // for left and right motors, respectively
                          derivator = 0.0;
    
    private static double integratorMax = 0.0, // Max and min values of integrator, to prevent it from being stupid
                          integratorMin = 0.0; 

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

    private double UpdateIntegrator(double error) {
        integrator += error;
        CheckIntegratorMaxMin();
    }

    private void SetGoal(double goal) {
        goalSpeed = goal;
    }

    private void CheckIntegratorMaxMin() {
        //EXPERIMENTAL - thanks to https://www.reddit.com/r/FRC/comments/44zy05/pi_loops/czuc2g1
        //Essentially makes it so I term can be max of what can command 1.0 (hence 1.0/Ki)
        if (integrator > (1.0/Ki))
            integrator = (1.0/Ki); // sorry i'm a python programmer
        else if (integrator < (-1*(1.0/Ki)))
            integrator = (-1 * (1.0/Ki)); // AT LEAST I REMEMBERED THE SEMICOLON
    }
    
    private double PIDRun() {
        double error = CalcError(currentSpeed, goalSpeed);
        UpdateIntegrator(error);
        double pValue = CalcKp(error);
        double iValue = CalcKi();
        double power = pValue + iValue;
        return power;
    }
    
}
