package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.EGatheringState;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class GatherBallsCommandV2 extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    public boolean loaderUp = false;
    public boolean magLoaded = false;
    private boolean currentState = false;
    private boolean lastState = false;
    private boolean finished = false;
    private boolean latch = false;
    // private Timer tweakMagazine;
    // private int magazineTweakInSeconds = 1;

    public GatherBallsCommandV2(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    @Override
    protected void execute() {
        System.out.println(latch);
        currentState = this.imSubsystem.HasBallAtIndex(4);
        if (getRisingEdge()) {
            RobotMap.ballCount++;
        }
        // System.out.println(States.GatheringState);
        if (!imSubsystem.IsMagazineFilled()) {
            latch = false;
            if (!imSubsystem.HasBallAtIndex(0)) {
                States.GatheringState = EGatheringState.FullIntake;
            } else {
                if (imSubsystem.HasBallAtIndex(4)) {
                    States.GatheringState = EGatheringState.FullIntake;
                } else {
                    States.GatheringState = EGatheringState.PartialIntake;
                }
            }
        } else {
            if (!imSubsystem.isFilled()) {
                if (imSubsystem.HasBallAtIndex(3)) {
                    latch = false;
                    States.GatheringState = EGatheringState.LoadLast;
                } else {
                    if ((imSubsystem.HasBallAtIndex(4) || latch) && !imSubsystem.HasBallAtIndex(5)) {
                        States.GatheringState = EGatheringState.LoadChamber;
                        latch = true;
                    } else {
                        States.GatheringState = EGatheringState.PartialIntake;
                    }
                }
            } else {
                States.GatheringState = EGatheringState.Stop;
            }
        }

        switch (States.GatheringState) {
        case FullIntake:
            // POSITIVE IS DOWN
            this.imSubsystem.Loader(.3);
            // POSITIVE IS IN
            this.imSubsystem.MagazineBelt(.5);
            // POSITIVE IS IN
            this.imSubsystem.IntakeBelt(1);
            // POSITIVE IS IN
            this.imSubsystem.SpinIntake(.5);
            break;
        case PartialIntake:
            this.imSubsystem.Loader(.3);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(1);
            this.imSubsystem.SpinIntake(.5);
            break;
        case LoadChamber:
            this.imSubsystem.Loader(-1);
            //tightenMagazine();
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(1);
            this.imSubsystem.SpinIntake(.4);
            break;
        case LoadLast:
            this.imSubsystem.Loader(0);
            //tightenMagazine();
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(1);
            this.imSubsystem.SpinIntake(.5);
            break;
        case Stop:
            this.imSubsystem.Loader(0);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(0);
            this.imSubsystem.SpinIntake(0);
            finished = true;
            break;
        }
        lastState = currentState;
    }

     // Peter: I am temporarily removing this code, I think overall
        //  it was having a negative effect on loading the magazine.  
        //  needs to be refined.
    // private void tightenMagazine() {
       

    //     // Tweek the magazine to tighten up the magazine a little
    //     if (this.tweakMagazine != null)
    //     {
    //         if (this.tweakMagazine.hasPeriodPassed(this.magazineTweakInSeconds))
    //             this.imSubsystem.MagazineBelt(0);
    //         else
    //             this.imSubsystem.MagazineBelt(0.3);
    //     }
    //     else {
    //         this.tweakMagazine = new Timer();
    //         this.tweakMagazine.start();
    //     }
    // }

    private boolean getRisingEdge() {
        return currentState && !lastState;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    public void end() {
        // RobotMap.ballCount = 0;
        finished = true;
        super.end();
    }
}