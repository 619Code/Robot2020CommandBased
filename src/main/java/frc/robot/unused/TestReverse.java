package frc.robot.unused;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.DistanceCalib;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.auto.PathFollower;

public class TestReverse extends CommandGroup {
  private ShiftingWCDSubsystem drive;
  private Limelight limelight;
  public TestReverse(ShiftingWCDSubsystem drive, Limelight limelight) {
    this.drive = drive;
    this.limelight = limelight;
    requires(drive);
    addSequential(new PathFollower(new DistanceCalib(), drive, false, false));
    addSequential(new PathFollower(new DistanceCalib(), drive, true, false));
  }
}