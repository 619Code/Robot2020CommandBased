package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.States;
import frc.robot.helpers.IterativeDelay;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {

    private final IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;
    private final Limelight limelight;
    private final ShooterSubsystem shooterSubsystem;
    private final double goodVelocity = 0;
    private IterativeDelay speedupDelay;
    private IterativeDelay stopDelay;
    private Timer speedupDelayTimer;

    public ShooterCommand(final IntakeMagazineSubsystem im, final Limelight ll, final ShooterSubsystem ss, XboxController joystick) {
        imSubsystem = im;
        limelight = ll;
        shooterSubsystem = ss;
        this.joystick = joystick;
        requires(imSubsystem);
        requires(ss);

        //this.speedupDelayTimer = new Timer();
                
        this.speedupDelay = new IterativeDelay(150);
        this.stopDelay = new IterativeDelay(150);

        //this.speedupDelayTimer.start();
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
        double speedAdjust = 0;
        double speed = 0.9;

        if (this.joystick != null)
        {
            if(Math.abs(joystick.getY(Hand.kLeft)) > 0.1) {
                speedAdjust = joystick.getY(Hand.kLeft);
            } 
            else {
                speedAdjust = 0;
            }
        }

        speed = speed + (speedAdjust/10);
        shooterSubsystem.shoot(speed);
        
        if (this.speedupDelay.IsDone())
        {
            imSubsystem.Loader(-0.6);
            if (!(imSubsystem.HasBallAtIndex(3)||imSubsystem.HasBallAtIndex(4)||imSubsystem.HasBallAtIndex(5))){
                imSubsystem.MagazineBelt(-0.4);
            }
            else {
                imSubsystem.MagazineBelt(0);
            }
            if (!(imSubsystem.HasBallAtIndex(5))){
                imSubsystem.IntakeBelt(-1);
            }
            else{
                imSubsystem.IntakeBelt(0.3);
            }
        }

        if (imSubsystem.isEmpty())
        {
            this.stopDelay.Cycle();
        }

    }

    @Override
    protected boolean isFinished() {
        if(RobotState.isDisabled())
            return true;

        if (this.stopDelay.IsDone())
        {
            //States.ShooterState = EShootState.Finished;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void end() {
        States.isShooting = false;
        imSubsystem.Loader(0);
        imSubsystem.IntakeBelt(0);
        imSubsystem.MagazineBelt(0);
        shooterSubsystem.shoot(0);
    }
}

