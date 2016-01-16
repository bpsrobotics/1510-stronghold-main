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
	
	public Joystick leftStick = new Joystick(1);
	public Joystick rightStick = new Joystick(2);
	
	public Button[] leftStickButtons = {null,
		new JoystickButton(leftStick,1),
		new JoystickButton(leftStick,2),
		new JoystickButton(leftStick,3),
		new JoystickButton(leftStick,4),
		new JoystickButton(leftStick,5),
		new JoystickButton(leftStick,6)
	};
	
	public Button[] rightStickButtons = {null,
		new JoystickButton(rightStick,1),
		new JoystickButton(rightStick,2),
		new JoystickButton(rightStick,3),
		new JoystickButton(rightStick,4),
		new JoystickButton(rightStick,5),
		new JoystickButton(rightStick,6)
	};
	
	public OI () {
		
	}
}
