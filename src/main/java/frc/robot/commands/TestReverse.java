package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.TestPath;
import frc.robot.subsystems.ShiftingWCD;

public class TestReverse extends CommandGroup {
  private ShiftingWCD drive;
  public TestReverse(ShiftingWCD drive) {
    this.drive = drive;
    requires(drive);
    addSequential(new PathFollower(new TestPath(), drive, false, false));
    addSequential(new PathFollower(new TestPath(), drive, true, false));
  }
}
