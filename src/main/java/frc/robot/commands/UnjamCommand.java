package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.EGatheringState;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class UnjamCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private IterativeDelay magazineStageOne;
    //private IterativeDelay magazineStageTwo;


    public UnjamCommand(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        magazineStageOne = new IterativeDelay(10);
        //magazineStageTwo = new IterativeDelay(10);
    }

    @Override
    protected void execute() {
        magazineStageOne.Cycle();    
        this.imSubsystem.MagazineBelt(.3);
        this.imSubsystem.IntakeBelt(.3);
        this.imSubsystem.Loader(-.3);
        this.imSubsystem.SpinIntake(-.3);
    }


    @Override
    protected boolean isFinished() {
        return this.magazineStageOne.IsDone();
    }

    @Override
    public void end() {
        this.imSubsystem.MagazineBelt(0);
        this.imSubsystem.IntakeBelt(0);
        super.end();
    }
}