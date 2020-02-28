package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.*;
import frc.robot.helpers.JoystickAnalogButton;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.AimingSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

//This is the main class for robot subsystems and commands. All subsystems and commands should be accessed through this class.
public class RobotContainer {
    //All subsystems should be declared here
    private final ShiftingWCDSubsystem drive;
    private final ClimberSubsystem climber;
    private final ShooterSubsystem shooter;
    private final AimingSubsystem aimingSubsystem;
    private final IntakeMagazineSubsystem imSubsystem;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;

    //Initialize all subsystems and set default commands
    public RobotContainer() {
        drive = new ShiftingWCDSubsystem();
        imSubsystem = new IntakeMagazineSubsystem();
        shooter = new ShooterSubsystem();
        climber = new ClimberSubsystem();
        aimingSubsystem = new AimingSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        limelight = new Limelight();

        limelight.TurnLightOff();
        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick));
        imSubsystem.setDefaultCommand(new IntakeMagazineDefaultCommand(imSubsystem, secondaryJoystick));
        climber.setDefaultCommand(new ManualClimber(climber, secondaryJoystick));

        //Peter: Seperated out elevation motor from shooter subsystem to support aim zero default command
        // behavior.
        aimingSubsystem.setDefaultCommand(new AimingSetZeroCommand(this.aimingSubsystem));
        ConfigureControllers();
    }

    //If a button triggers a command, it should be declared here
    public void ConfigureControllers() {
        var turnButton = new JoystickButton(primaryJoystick, XboxController.Button.kB.value);
        turnButton.whileHeld(new TurnToVisionTarget(drive, limelight, aimingSubsystem));

        var gatherBalls = new JoystickAnalogButton(secondaryJoystick, XboxController.Axis.kLeftTrigger.value , 0.5);
        gatherBalls.whileHeld(new GatherBallsCommandV2(imSubsystem));

        var shootBalls = new JoystickAnalogButton(secondaryJoystick, XboxController.Axis.kRightTrigger.value , 0.5);
        shootBalls.whileHeld(new ManualShootCommand(shooter, secondaryJoystick));

        // Peter: Added Y button on controller joystick to test out new ShooterCommand feed and shoot code.
        var shooterButtonTest = new JoystickButton(secondaryJoystick, XboxController.Button.kY.value);
        shooterButtonTest.whileHeld(new ShooterCommand(this.imSubsystem, this.limelight, this.shooter));
    }

    public void AllStop() {
        this.shooter.shoot(0);
    }

    //Auto command(s) should be accessed from this method
    public Command getAutoCommand(){
        return new TurnToVisionTarget(drive, limelight, aimingSubsystem);
    }
}