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
    private boolean firstCycle;
    private IterativeDelay stopDelay;

    public UnloadBallsCommand(IntakeMagazineSubsystem imSubsystem, ShooterSubsystem shooterSubsystem) {
        this.imSubsystem = imSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        requires(imSubsystem);
        requires(shooterSubsystem);

        this.speedupDelay = new IterativeDelay(100); //lower?
        this.loadingDelay = new IterativeDelay(10); //formerly 20
        this.loadingDelayDone = false;
        this.firstCycle = true;
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
            this.speedupDelay.Reset();
            this.loadingDelay.Reset();
            this.loadingDelayDone = false;
            this.firstCycle = true;
            if(RobotState.isAutonomous()) {
                this.stopDelay.Cycle();
            }
        }
    }

    public void unloadBalls() {
        imSubsystem.Loader(-0.45);

        if (!((imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_HIGH) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_PRE)))) {
            if(firstCycle) {
                loadingDelayDone = true;
            } else if(this.loadingDelay.IsDone()) {
                loadingDelayDone = true;
            }
            
            if(!loadingDelayDone) {
                this.loadingDelay.Cycle();
            } else {
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

        firstCycle = false;
    }

    @Override
    protected boolean isFinished() {
        if (RobotState.isDisabled())
        {
            shooterSubsystem.shoot(0);
            return true;
        }

        if (this.stopDelay.IsDone()) {
            shooterSubsystem.shoot(0);
            return true;
        } else {
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
