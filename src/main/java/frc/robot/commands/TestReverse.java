package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.TestPath;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ShiftingWCD;
import frc.robot.helpers.*;

public class TestReverse extends SequentialCommandGroup {
  private ShiftingWCD drive;
  private Limelight limelight;
  public TestReverse(ShiftingWCD drive, Limelight limelight) {
    this.drive = drive;
    this.addRequirements(drive);
    this.addCommands(HelixFollowerCommandWrapper.wrap(new PathFollower(new TestPath(), drive, false, false)),
                     HelixFollowerCommandWrapper.wrap(new PathFollower( new TestPath(), drive, true, false)));
    this.limelight = limelight;
    
    // addSequential(new PathFollower(new TestPath(), drive, false, false));
    // addSequential(new TurnToVisionTarget(drive, limelight));
  }
}
