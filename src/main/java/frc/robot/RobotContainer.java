package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ManualMagazineCommand;
import frc.robot.commands.TestReverse;
import frc.robot.commands.TurnToVisionTarget;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;

//This is the main class for robot subsystems and commands. All subsystems and commands should be accessed through this class.
public class RobotContainer {
    //All subsystems should be declared here
    private final ShiftingWCDSubsystem drive;
    private final IntakeSubsystem intake;
    private final ClimberSubsystem climber;
    private final MagazineSubsystem magazine;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;

    //Initialize all subsystems and set default commands
    public RobotContainer() {
        drive = new ShiftingWCDSubsystem();
        intake = new IntakeSubsystem();
        climber = new ClimberSubsystem();
        magazine = new MagazineSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        limelight = new Limelight();

        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick));
        intake.setDefaultCommand(new IntakeCommand(intake, secondaryJoystick));
        magazine.setDefaultCommand(new ManualMagazineCommand(magazine, secondaryJoystick));

        ConfigureControllers();
    }

    //If a button triggers a command, it should be declared here
    public void ConfigureControllers() {
        var turnButton = new JoystickButton(primaryJoystick, XboxController.Button.kA.value);
        turnButton.whileHeld(new TurnToVisionTarget(drive, limelight));
    }

    //Auto command(s) should be accessed from this method
    public Command getAutoCommand(){
        return new TurnToVisionTarget(drive, limelight);
    }
}