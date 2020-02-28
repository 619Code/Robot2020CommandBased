package frc.robot.commands;

import javax.swing.Timer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.States;
import frc.robot.helpers.EShootState;
import frc.robot.helpers.IterativeDelay;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {

    private final IntakeMagazineSubsystem imSubsystem;
    private final Limelight limelight;
    private final ShooterSubsystem shooterSubsystem;
    private final double goodVelocity = 0;
    private IterativeDelay speedupDelay;
    private IterativeDelay stopDelay;
    private final int delayStopIterations = 150;
    private int stopIterations = 0;
    private final int shooterSpeedUpTime = 100;
    private final int shooterSpeedUpIterations = 0;

    public ShooterCommand(final IntakeMagazineSubsystem im, final Limelight ll, final ShooterSubsystem ss) {
        imSubsystem = im;
        limelight = ll;
        shooterSubsystem = ss;
        requires(imSubsystem);
        requires(ss);

        this.speedupDelay = new IterativeDelay(100);
        this.stopDelay = new IterativeDelay(150);
    }

    @Override
    protected void execute() {
        super.execute();

        if (imSubsystem.IsMagazineOccupied() && !imSubsystem.IsIntakePositionFilled())
        {
            System.out.println("Moving Balls In Magazine");
            States.ShooterState = EShootState.MovingBallsInMagazine;
        }
        else if (imSubsystem.IsIntakePositionFilled())
        {
            States.ShooterState = EShootState.MovingBallsInVertical;
        }

        
        this.speedupDelay.Cycle();
        shooterSubsystem.shoot(.8);
        if (this.speedupDelay.IsDone())
        {
            // Peter: Idealliy this code would look something like this, but
            // until we can figure out how to get a non zero velocity from 
            // our encoder we have to leave this logic out.
            //if (this.shooterSubsystem.getVelocity() > goodVelocity)
            
            imSubsystem.Loader(-0.8);

            if (States.ShooterState == EShootState.MovingBallsInMagazine)
            {
                System.out.println("Trying to move");
                imSubsystem.IntakeBelt(-1);
                imSubsystem.MagazineBelt(-0.5);
            }
        }

        // Peter: This is the pause code that we would use
        // if we can get the encoder working on the shooter.
        // else
        // {
        //     imSubsystem.Loader(0);
        //     imSubsystem.IntakeBelt(0);
        //     imSubsystem.MagazineBelt(0);
        // }
        
        // Once everything has been shot we want the shooter to 
        //  keep going for a few seconds.
        if (imSubsystem.isEmpty())
        {
            this.stopDelay.Cycle();
        }

        switch(States.ShooterState) {
            case Finished:
                imSubsystem.Loader(0);
                imSubsystem.IntakeBelt(0);
                imSubsystem.MagazineBelt(0);
                break;
            case MovingBallsInVertical:
                break;
            case MovingBallsInMagazine:
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        if (this.stopDelay.IsDone())
        {
            States.ShooterState = EShootState.Finished;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void end() {
        imSubsystem.Loader(0);
        imSubsystem.IntakeBelt(0);
        imSubsystem.MagazineBelt(0);
        shooterSubsystem.shoot(0);
    }
}

