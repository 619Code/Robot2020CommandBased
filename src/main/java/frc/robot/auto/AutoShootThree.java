package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.*;
import frc.robot.commands.*;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.*;

public class AutoShootThree extends CommandGroup {
    public AutoShootThree(AimingSubsystem aimingSubsystem, ShiftingWCDSubsystem drive, IntakeMagazineSubsystem intakeMagazineSubsystem, Limelight limelight, ShooterSubsystem shooter) {
       addSequential(new PathFollower(new SixFeet(), drive, true, true));
       addSequential(new TurnToVisionTarget(drive, limelight, aimingSubsystem), 3.0);
       addSequential(new ShooterCommand(intakeMagazineSubsystem, shooter));
    }
}