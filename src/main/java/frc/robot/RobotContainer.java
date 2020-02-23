package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.GatherBallsCommandV2;
import frc.robot.commands.IntakeMagazineDefaultCommand;
import frc.robot.commands.ManualShootCommand;
import frc.robot.commands.TurnToVisionTarget;
import frc.robot.helpers.JoystickAnalogButton;
import frc.robot.helpers.Limelight;
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
    private final IntakeMagazineSubsystem imSubsystem;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;

    //Initialize all subsystems and set default commands
    public RobotContainer() {
        drive = new ShiftingWCDSubsystem();
        imSubsystem = new IntakeMagazineSubsystem();
        shooter = new ShooterSubsystem();
        climber = new ClimberSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        limelight = new Limelight();

        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick));
        imSubsystem.setDefaultCommand(new IntakeMagazineDefaultCommand(imSubsystem, secondaryJoystick));
        shooter.setDefaultCommand(new ManualShootCommand(shooter, secondaryJoystick));
        ConfigureControllers();
    }

    //If a button triggers a command, it should be declared here
    public void ConfigureControllers() {
        var turnButton = new JoystickButton(primaryJoystick, XboxController.Button.kB.value);
        turnButton.whileHeld(new TurnToVisionTarget(drive, limelight, shooter));
        var gatherBalls = new JoystickAnalogButton(secondaryJoystick, XboxController.Axis.kLeftTrigger.value , 0.5);
        gatherBalls.whileHeld(new GatherBallsCommandV2(imSubsystem, secondaryJoystick));
    }

    //Auto command(s) should be accessed from this method
    public Command getAutoCommand(){
        return new TurnToVisionTarget(drive, limelight, shooter);
    }
}