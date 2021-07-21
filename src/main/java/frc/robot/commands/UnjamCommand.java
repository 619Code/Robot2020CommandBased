package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.RobotMap;

public class UnjamCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    //private IterativeDelay magazineStageOne;

    public UnjamCommand(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        //magazineStageOne = new IterativeDelay(10);
        this.imSubsystem.LowerIntake();
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        //return this.magazineStageOne.IsDone();
        return true;
    }

    @Override
    public void end() {
        this.imSubsystem.Loader(0);
        this.imSubsystem.MagazineBelt(0);
        this.imSubsystem.IntakeBelt(0);
        super.end();
    }
}