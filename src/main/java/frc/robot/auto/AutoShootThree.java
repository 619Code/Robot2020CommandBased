package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.SixFeet;
import frc.robot.commands.TurnToVisionTarget;
import frc.robot.commands.UnloadBallsCommand;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.AimingSubsystem;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootThree extends CommandGroup {
    public AutoShootThree(AimingSubsystem aimingSubsystem, ShiftingWCDSubsystem drive, IntakeMagazineSubsystem imSubsystem, Limelight limelight, ShooterSubsystem shooter) {
       addSequential(new PathFollower(new SixFeet(), drive, true, true));
       addSequential(new TurnToVisionTarget(drive, limelight, aimingSubsystem), 2.5);
       addParallel(new UnloadBallsCommand(imSubsystem, shooter));
       //addSequential(new ManualShootCommand(shooter, null));
       //addSequential(new UnloadBallsCommand(imSubsystem, shooter));
    }
}