package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.auto.AutoShootThree;
import frc.robot.commands.AimingSetZeroCommand;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.GatherBallsCommand;
import frc.robot.commands.IntakeMagazineDefaultCommand;
import frc.robot.commands.ManualClimber;
import frc.robot.commands.ManualShootCommand;
import frc.robot.commands.UnloadBallsCommand;
import frc.robot.commands.TurnToVisionTarget;
import frc.robot.commands.UnjamCommand;
import frc.robot.helpers.JoystickAnalogButton;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.AimingSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

//This is the main class for robot subsystems and commands. All subsystems and commands should be accessed through this class.
public class RobotContainer {
    // All subsystems should be declared here
    private final ShiftingWCDSubsystem drive;
    private final ClimberSubsystem climber;
    private final ShooterSubsystem shooter;
    private final IntakeMagazineSubsystem imSubsystem;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;
    private final AimingSubsystem aimingSubsystem;

    // private final Compressor compressor;
    // Initialize all subsystems and set default commands
    public RobotContainer() {
        drive = new ShiftingWCDSubsystem();
        imSubsystem = new IntakeMagazineSubsystem();
        shooter = new ShooterSubsystem();
        climber = new ClimberSubsystem();

        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);

        limelight = new Limelight();
        aimingSubsystem = new AimingSubsystem();
        // compressor = new Compressor(RobotMap.PCM_CAN_ID);

        // compressor.setClosedLoopControl(true);
        limelight.TurnLightOff();
        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick));
        imSubsystem.setDefaultCommand(new IntakeMagazineDefaultCommand(imSubsystem, secondaryJoystick));
        climber.setDefaultCommand(new ManualClimber(climber, secondaryJoystick));
        aimingSubsystem.setDefaultCommand(new AimingSetZeroCommand(aimingSubsystem, limelight));
        shooter.setDefaultCommand(new ManualShootCommand(shooter, secondaryJoystick));
        ConfigureControllers();
    }

    // If a button triggers a command, it should be declared here
    public void ConfigureControllers() {
        var aimButton = new JoystickButton(primaryJoystick, XboxController.Button.kB.value);
        aimButton.whileHeld(new TurnToVisionTarget(drive, limelight, aimingSubsystem));

        var gatherBallsButton = new JoystickAnalogButton(secondaryJoystick, XboxController.Axis.kLeftTrigger.value, 0.5);
        gatherBallsButton.whileHeld(new GatherBallsCommand(imSubsystem, secondaryJoystick));

        var unloadMag = new JoystickAnalogButton(secondaryJoystick, XboxController.Axis.kRightTrigger.value, 0.5);
        unloadMag.whileHeld(new UnloadBallsCommand(this.imSubsystem));

        var unjamButton = new JoystickButton(secondaryJoystick, XboxController.Button.kX.value);
        unjamButton.whileHeld(new UnjamCommand(this.imSubsystem));

    }

    public void AllStop() {
        this.shooter.shoot(0);
    }

    // Auto command(s) should be accessed from this method
    public Command getAutoCommand() {
        return new AutoShootThree(this.aimingSubsystem, this.drive, this.imSubsystem, this.limelight, this.shooter);
        // return new TurnToVisionTarget(drive, limelight, aimingSubsystem);

    }
}