package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;

public class GatherBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;

    public GatherBallsCommand(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    private boolean finished = false;

    @Override
    protected void execute() {
        
        if (this.imSubsystem.isFilled())
        {
            this.imSubsystem.RaiseIntake();
            this.imSubsystem.SpinIntake(0);
            this.imSubsystem.IntakeBelt(0);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.ChamberCrazyness(0);
            this.finished = true;
        }
        else if (this.imSubsystem.IsMagazineFilled() 
            && !this.imSubsystem.IsChamberFilled())
        {
            this.imSubsystem.SpinIntake(.5);
            this.imSubsystem.ChamberCrazyness(-.6);
            this.imSubsystem.IntakeBelt(-1);
            this.imSubsystem.MagazineBelt(0);
        }
        else if (!this.imSubsystem.IsMagazineFilled())
        {
            this.imSubsystem.ChamberCrazyness(.6);
            this.imSubsystem.MagazineBelt(.4);
            this.imSubsystem.IntakeBelt(-1);
            this.imSubsystem.SpinIntake(.5);
        }
        else 
        {
            this.imSubsystem.ChamberCrazyness(0);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(-1);
            this.imSubsystem.SpinIntake(.5);
        }
    }

    @Override
    protected boolean isFinished() {
       return finished;
    }

    @Override
    public void end() {
        finished = true;
        super.end();
    }
}