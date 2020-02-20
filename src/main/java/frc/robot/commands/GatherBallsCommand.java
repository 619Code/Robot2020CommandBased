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
            // Shut everything down
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
            // Keep taking in balls
            this.imSubsystem.SpinIntake(.5);

            // Move the chamber wheels up
            this.imSubsystem.ChamberCrazyness(-.6);

            // Move the intake belt back
            this.imSubsystem.IntakeBelt(-1);

            // Shut the magazine belt down
            this.imSubsystem.MagazineBelt(0);
        }
        else if (!this.imSubsystem.IsMagazineFilled())
        {
            // Move the chamber down to help balls go into magazine
            this.imSubsystem.ChamberCrazyness(.6);

            // Move the magazine belt back to make room for more balls
            this.imSubsystem.MagazineBelt(.4);

            // Keep the intake belt moving back to get balls to the magazine
            this.imSubsystem.IntakeBelt(-1);

            // Keep taking in balls
            this.imSubsystem.SpinIntake(.5);
        }
        else // We now need to try to fill the last postion
        {
            // Shut down the chamber wheels, hopefully the balls 
            //  stays up with compression
            this.imSubsystem.ChamberCrazyness(0);

            // Make sure the magazine belt is not running
            this.imSubsystem.MagazineBelt(0);

            // Keep the intake belt moving to get 
            //  the ball in the intake position
            this.imSubsystem.IntakeBelt(-1);

            // Keep more balls coming
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