package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.States;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class UnloadBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;

    private ShooterSubsystem shooterSubsystem;
    private double goodVelocity = 0;
    private IterativeDelay speedupDelay;
    private IterativeDelay stopDelay;
    private Timer speedupDelayTimer;

    public UnloadBallsCommand(IntakeMagazineSubsystem imSubsystem) {
        this.imSubsystem = imSubsystem;
        requires(imSubsystem);
        // this.speedupDelayTimer = new Timer();

        this.speedupDelay = new IterativeDelay(150);
        this.stopDelay = new IterativeDelay(150);

        // this.speedupDelayTimer.start();
    }

    @Override
    protected void initialize() {
        States.isShooting = true;
        this.speedupDelay = new IterativeDelay(100);
        this.stopDelay = new IterativeDelay(150);
    }

    boolean startedMagainzeEmptyProcess = false;

    @Override
    protected void execute() {
        super.execute();
        this.speedupDelay.Cycle();

        if (RobotState.isAutonomous()) {
            if (this.speedupDelay.IsDone()) {
                unloadBalls();
            }
        } else {
            unloadBalls();
        }

        if (imSubsystem.isEmpty()) {
            this.stopDelay.Cycle();
        }

    }

    public void unloadBalls() {
        imSubsystem.Loader(-0.6);
        if (!(imSubsystem.HasBallAtIndex(3) || imSubsystem.HasBallAtIndex(4) || imSubsystem.HasBallAtIndex(5))) {
            imSubsystem.MagazineBelt(-0.4);
        } else {
            imSubsystem.MagazineBelt(0);
        }
        if (!(imSubsystem.HasBallAtIndex(5))) {
            imSubsystem.IntakeBelt(-1);
        } else {
            imSubsystem.IntakeBelt(0.3);
        }
    }

    @Override
    protected boolean isFinished() {
        if (RobotState.isDisabled())
            return true;

        if (this.stopDelay.IsDone()) {
            // States.ShooterState = EShootState.Finished;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        States.isShooting = false;
        imSubsystem.Loader(0);
        imSubsystem.IntakeBelt(0);
        imSubsystem.MagazineBelt(0);
    }
}
