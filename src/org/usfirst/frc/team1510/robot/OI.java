package org.usfirst.frc.team1510.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public Joystick gamepad1 = new Joystick(0);
    
    //
    public Joystick gamepad2 = new Joystick(1);

    // Buttons
    public JoystickButton leftTrigger = new JoystickButton(gamepad1,1);
    public JoystickButton  leftStickPress = new JoystickButton(gamepad1,9);
    public JoystickButton  g1btnA = new JoystickButton(gamepad1, 1);
    //public JoystickButton trigger1 = new JoystickButton(gamepad2, 1);
    //public JoystickButton trigger2 = new JoystickButton(gamepad2, 2);
    public JoystickButton btnA = new JoystickButton(gamepad2, 1);
    public JoystickButton btnB = new JoystickButton(gamepad2, 2);
    public JoystickButton btnX = new JoystickButton(gamepad2, 3);
    public JoystickButton btnY = new JoystickButton(gamepad2, 4);
    public JoystickButton leftBumper = new JoystickButton(gamepad2, 5);
    public JoystickButton rightBumper = new JoystickButton(gamepad2, 6);
    public JoystickButton start = new JoystickButton(gamepad2, 7);
    public JoystickButton back = new JoystickButton(gamepad2, 8);
    public JoystickButton lStick = new JoystickButton(gamepad2, 9);
    public JoystickButton rStick = new JoystickButton(gamepad2, 10);
    
    
    
    public static final double deadzoneThreshold = 0.35;
    
    public static double deadzone(double value) {
	if (Math.abs(value) < deadzoneThreshold) return 0;
	return deadzoneThreshold;
    }
    
}
