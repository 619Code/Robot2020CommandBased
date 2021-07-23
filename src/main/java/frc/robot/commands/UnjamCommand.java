package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.RobotMap;

public class UnjamCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;

    public UnjamCommand(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    @Override
    protected void execute() {
        System.out.println("Unjam");
        this.imSubsystem.IntakeBelt(-0.8);
        this.imSubsystem.SpinIntake(-0.55);

        if(!imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_HIGH)) {
            this.imSubsystem.Loader(0.4);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        this.imSubsystem.SpinIntake(0);
        this.imSubsystem.Loader(0);
        this.imSubsystem.MagazineBelt(0);
        this.imSubsystem.IntakeBelt(0);
        super.end();
    }
}