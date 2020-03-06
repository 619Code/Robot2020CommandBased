package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.EGatheringState;
import frc.robot.helpers.IterativeDelay;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class GatherBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;
    public boolean loaderUp = false;
    public boolean magLoaded = false;
    private boolean currentState = false;
    private boolean lastState = false;
    private boolean finished = false;
    private boolean latch = false;
    //private Timer tweakMagazine;
    private int magazineTweakInSeconds = 1;
    private IterativeDelay tightenMagazine;

    public GatherBallsCommand(IntakeMagazineSubsystem intakeMagazineSubsystem, XboxController joystick) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.joystick = joystick;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
        tightenMagazine = new IterativeDelay(25);
    }

    @Override
    protected void execute() {
        //System.out.println(latch);
        currentState = this.imSubsystem.HasBallAtIndex(4);
        if (getRisingEdge()) {
            RobotMap.ballCount++;
        }
        // System.out.println(States.GatheringState);
        if (!imSubsystem.IsMagazineFilled()) {
            latch = false;
            if (!imSubsystem.HasBallAtIndex(0)) {
                States.GatheringState = EGatheringState.FullIntake;
            }
            else {
                if (imSubsystem.HasBallAtIndex(4)) {
                    States.GatheringState = EGatheringState.FullIntake;
                } else {
                    States.GatheringState = EGatheringState.PartialIntake;
                }
            }
        } 
        else {
            if (!imSubsystem.isFilled()) {
                if (imSubsystem.HasBallAtIndex(3)) {
                    latch = false;
                    States.GatheringState = EGatheringState.LoadLast;
                } else {
                    if ((imSubsystem.HasBallAtIndex(4)) || latch) {
                        States.GatheringState = EGatheringState.LoadChamber;
                        latch = true;
                    } else {
                        States.GatheringState = EGatheringState.PartialIntake;
                    }
                }
            } else {
                this.imSubsystem.RaiseIntake();
                States.GatheringState = EGatheringState.Stop;
            }
        }

        System.out.println("Gath State:" + States.GatheringState.toString());
        switch (States.GatheringState) {
        case FullIntake:
            // POSITIVE IS DOWN
            this.imSubsystem.Loader(.8);
            // POSITIVE IS IN
            this.imSubsystem.MagazineBelt(.4);
            // POSITIVE IS IN
            this.imSubsystem.IntakeBelt(0.8);
            // POSITIVE IS IN
            this.imSubsystem.SpinIntake(.5);
            break;
        case PartialIntake:
            this.imSubsystem.Loader(.8);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(0.8); 
            this.imSubsystem.SpinIntake(.5);
            break;
        case LoadChamber:
            tightenMagazine();            
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.Loader(-.8);            
            this.imSubsystem.IntakeBelt(0.8);
            this.imSubsystem.SpinIntake(.5);
            break;
        case LoadLast:
            //tightenMagazine();
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.Loader(0); 
            this.imSubsystem.IntakeBelt(0.8);
            this.imSubsystem.SpinIntake(.5);
            break;
        case Stop:
            this.tightenMagazine();            
            this.imSubsystem.Loader(0);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(0);
            this.imSubsystem.SpinIntake(0);            
            break;
        }
        lastState = currentState;
    }

    private void tightenMagazine() {
        this.tightenMagazine.Cycle();
        // Tweek the magazine to tighten up the magazine a little
        if (this.tightenMagazine.IsDone())
        {
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(0);
            finished = true;
        }
        else
        {
            this.imSubsystem.IntakeBelt(.2);
            this.imSubsystem.MagazineBelt(.2);
        }
    }

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