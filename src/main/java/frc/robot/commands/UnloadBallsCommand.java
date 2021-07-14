package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class UnloadBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;

    private ShooterSubsystem shooterSubsystem;
    private IterativeDelay speedupDelay;
    private IterativeDelay loadingDelay;
    private boolean loadingDelayDone;
    private IterativeDelay stopDelay;

    public UnloadBallsCommand(IntakeMagazineSubsystem imSubsystem, ShooterSubsystem shooterSubsystem) {
        this.imSubsystem = imSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        requires(imSubsystem);
        requires(shooterSubsystem);

        this.speedupDelay = new IterativeDelay(100);
        this.loadingDelay = new IterativeDelay(20);
        this.loadingDelayDone = false;
        this.stopDelay = new IterativeDelay(50);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        super.execute();

        this.speedupDelay.Cycle();
        shooterSubsystem.shoot(0.95);

        if (this.speedupDelay.IsDone()) {
            unloadBalls();
        }
      
        if (imSubsystem.isEmpty()) {
            System.out.println("Emptied");
            //this.speedupDelay.Reset();
            //this.loadingDelay.Reset();
            this.loadingDelayDone = false;
            if(RobotState.isAutonomous()) {
                this.stopDelay.Cycle();
            }
        }
    }

    public void unloadBalls() {
        System.out.println("Unloading started");
        imSubsystem.Loader(-0.6);

        if (!((imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_HIGH) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_PRE)))) {
            if(!loadingDelayDone) {
                //System.out.println(this.loadingDelay.getCycle());
                this.loadingDelay.Cycle();
            }
            if(this.loadingDelay.IsDone()) {
                loadingDelayDone = true;
                imSubsystem.MagazineBelt(-0.4);
            }
        } else {
            imSubsystem.MagazineBelt(0);
        }

        if (!(imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_PRE))) {
            imSubsystem.IntakeBelt(-1);
        } else {
            imSubsystem.IntakeBelt(0.3);
        }
    }

    @Override
    protected boolean isFinished() {
        if (RobotState.isDisabled())
        {
            System.out.println("isFinished #1");
            shooterSubsystem.shoot(0);
            return true;
        }

        if (this.stopDelay.IsDone()) {
            System.out.println("isFinished #2");
            shooterSubsystem.shoot(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void end() {
        System.out.println("end");
        imSubsystem.Loader(0);
        imSubsystem.IntakeBelt(0);
        imSubsystem.MagazineBelt(0);
        shooterSubsystem.shoot(0);
    }
}
