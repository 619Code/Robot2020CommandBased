package frc.robot.commands;

//import javax.swing.Timer;

import org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.States;
import frc.robot.helpers.EShootState;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private Limelight limelight;
    private ShooterSubsystem shooterSubsystem;
    private double goodVelocity = 0;
    private int delayStopIterations = 150;
    private int stopIterations = 0;
    private int shooterSpeedUpTime = 100;
    private int shooterSpeedUpIterations = 0;
    private boolean finished = false;
    private boolean shootNow = false;
    private Timer shootTimer;

    public ShooterCommand(IntakeMagazineSubsystem im, Limelight ll, ShooterSubsystem ss) {
        imSubsystem = im;
        limelight = ll;
        shooterSubsystem = ss;
        requires(imSubsystem);
    }

    @Override
    protected void execute() {
        super.execute();
        /*if(imSubsystem.HasBallAtIndex(3) || shootNow) {
            shootNow = true;
            States.ShooterState = EShootState.Shoot;
            if(shootTimer == null) {
                shootTimer = new Timer();
                shootTimer.start();
            } else if(shootTimer.hasPeriodPassed(1)) {
                shootNow = false;
                shootTimer = null;
            }
        } else*/ 
        if(imSubsystem.HasBallAtIndex(3) || imSubsystem.HasBallAtIndex(4)) {
            States.ShooterState = EShootState.MovingBallsInVertical;
        } else {
            States.ShooterState = EShootState.MovingBallsInMagazine;
        }

        switch(States.ShooterState) {
            case Finished:
                imSubsystem.Loader(0);
                imSubsystem.IntakeBelt(0);
                imSubsystem.MagazineBelt(0);
                shooterSubsystem.shoot(0);
                break;
            case Shoot:
                imSubsystem.Loader(-1);
                imSubsystem.IntakeBelt(0);
                imSubsystem.MagazineBelt(0);
                shooterSubsystem.shoot(1);
                break;
            case MovingBallsInVertical:
                System.out.println("vert");
                imSubsystem.Loader(-1);
                imSubsystem.IntakeBelt(0);
                imSubsystem.MagazineBelt(0);
                shooterSubsystem.shoot(1);
                break;
            case MovingBallsInMagazine:
                System.out.println("mag");
                imSubsystem.Loader(-1);
                imSubsystem.IntakeBelt(-0.5);
                imSubsystem.MagazineBelt(-1);
                shooterSubsystem.shoot(1);
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        finished = true;
        /*imSubsystem.Loader(0);
        imSubsystem.IntakeBelt(0);
        imSubsystem.MagazineBelt(0);
        shooterSubsystem.shoot(0);
        States.ShooterState = EShootState.Finished;
        super.end();*/
    }
}

