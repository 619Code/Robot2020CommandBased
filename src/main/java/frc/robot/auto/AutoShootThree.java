package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.*;
import frc.robot.commands.*;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.*;

public class AutoShootThree extends CommandGroup {
    public AutoShootThree(AimingSubsystem aimingSubsystem, ShiftingWCDSubsystem drive, IntakeMagazineSubsystem im, Limelight limelight, ShooterSubsystem shooter) {
       addSequential(new PathFollower(new SixFeet(), drive, false, true ));
       addParallel(new TurnToVisionTarget(drive, limelight, aimingSubsystem));
       addSequential(new WaitTillOnTargetCommand(limelight));
       addSequential(new ShooterCommand(im, limelight, shooter));
    }
}