package frc.robot.commands;

import javax.swing.Timer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.States;
import frc.robot.helpers.EShootState;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private Limelight limeLight;
    private ShooterSubsystem shooterSubsystem;
    private double goodVelocity = 0;
    private int delayStopIterations = 150;
    private int stopIterations = 0;

    public ShooterCommand(IntakeMagazineSubsystem im, Limelight ll, ShooterSubsystem ss) {
        imSubsystem = im;
        limeLight = ll;
        shooterSubsystem = ss;
    }

    @Override
    protected void execute() {
        super.execute();

        if (imSubsystem.IsMagazineOccupied() && !imSubsystem.IsIntakePositionFilled())
        {   
            States.ShooterState = EShootState.MovingBallsInMagazine;
        }
        else if (imSubsystem.IsIntakePositionFilled())
        {
            States.ShooterState = EShootState.MovingBallsInVertical;
        }

        shooterSubsystem.shoot(1);

        if (this.shooterSubsystem.getVelocity() > goodVelocity)
        {
            imSubsystem.Loader(-0.8);

            if (States.ShooterState == EShootState.MovingBallsInMagazine)
            {
                imSubsystem.IntakeBelt(-0.5);
                imSubsystem.MagazineBelt(-0.5);
            }
        }
        else
        {
            imSubsystem.Loader(0);
            imSubsystem.IntakeBelt(0);
            imSubsystem.MagazineBelt(0);
        }
        
        if (imSubsystem.isEmpty())
        {
            this.stopIterations++;
        }
    }

    @Override
    protected boolean isFinished() {
        if (this.stopIterations >= this.delayStopIterations)
        {
            States.ShooterState = EShootState.Finished;
            return true;
        }
        else
        {
            return false;
        }
    }

     
}

