package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.TestPath;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ShiftingWCD;

public class TestReverse extends CommandGroup {
  private ShiftingWCD drive;
  private Limelight limelight;
  public TestReverse(ShiftingWCD drive, Limelight limelight) {
    this.drive = drive;
    this.limelight = limelight;
    requires(drive);
    addSequential(new PathFollower(new TestPath(), drive, false, false));
    addSequential(new TurnToVisionTarget(drive, limelight));
  }
}
