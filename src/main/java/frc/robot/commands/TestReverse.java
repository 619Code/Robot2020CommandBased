package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.DistanceCalib;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ShiftingWCDSubsystem;

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
