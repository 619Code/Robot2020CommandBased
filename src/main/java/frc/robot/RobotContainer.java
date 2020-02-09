
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.TestReverse;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;

public class RobotContainer {
    private final ShiftingWCDSubsystem drive;
    private final IntakeSubsystem intake;
    private final ClimberSubsystem climber;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;
    private final TargetInfo targetInfo;

    public RobotContainer() {

        drive = new ShiftingWCDSubsystem();
        intake = new IntakeSubsystem();
        climber = new ClimberSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        targetInfo = new TargetInfo();
        limelight = new Limelight();

        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick));
        intake.setDefaultCommand(new IntakeCommand(intake, secondaryJoystick));

    }
    public Command getAutoCommand(){
        return new TestReverse(drive, limelight);
    }
}
