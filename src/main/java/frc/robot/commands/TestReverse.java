package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.TestPath;
import frc.robot.subsystems.ShiftingWCD;
import frc.robot.helpers.*;

public class TestReverse extends SequentialCommandGroup {
  private ShiftingWCD drive;
  public TestReverse(ShiftingWCD drive) {
    this.drive = drive;
    this.addRequirements(drive);
    this.addCommands(HelixFollowerCommandWrapper.wrap(new PathFollower(new TestPath(), drive, false, false)),
                     HelixFollowerCommandWrapper.wrap(new PathFollower( new TestPath(), drive, true, false)));
  }
}
