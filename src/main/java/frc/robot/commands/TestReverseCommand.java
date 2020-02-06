package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.TestPath;
import frc.robot.helpers.HelixFollowerCommandWrapper;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;

public class TestReverseCommand extends SequentialCommandGroup {
  private ShiftingWCDSubsystem drive;
  private Limelight limelight;
  public TestReverseCommand(ShiftingWCDSubsystem drive, Limelight limelight) {
    this.drive = drive;
    this.addRequirements(drive);
    this.addCommands(HelixFollowerCommandWrapper.wrap(new PathFollowerCommand(new TestPath(), drive, false, false)),
                     HelixFollowerCommandWrapper.wrap(new PathFollowerCommand( new TestPath(), drive, true, false)));
    this.limelight = limelight;
    
    // addSequential(new PathFollower(new TestPath(), drive, false, false));
    // addSequential(new TurnToVisionTarget(drive, limelight));
  }
}
