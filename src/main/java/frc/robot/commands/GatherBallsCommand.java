package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.IterativeDelay;
import frc.robot.helpers.states.EGatheringState;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class GatherBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;
    public boolean loaderUp = false;
    public boolean magLoaded = false;
    private boolean finished = false;
    private boolean latch = false;
    private int magazineTweakInSeconds = 1;
    private IterativeDelay tightenMagazine;
    private IterativeDelay finalTighten;

    public GatherBallsCommand(IntakeMagazineSubsystem intakeMagazineSubsystem, XboxController joystick) {
        tightenMagazine = new IterativeDelay(10);
        finalTighten = new IterativeDelay(5);

        this.imSubsystem = intakeMagazineSubsystem;
        this.joystick = joystick;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    @Override
    protected void execute() {
        // A note on the latch: The latch is meant to make sure that the ball keeps going up
        // This means that when LoadChamber starts it will continue until the upper chamber has a ball

        if (!imSubsystem.IsMagazineFilled()) {
            latch = false;
            if (!imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_FIRST)) {
                States.GatheringState = EGatheringState.FullIntake;
            } else {
                if (imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_PRE)) {
                    States.GatheringState = EGatheringState.FullIntake;
                } else {
                    States.GatheringState = EGatheringState.PartialIntake;
                }
            }
        } else {
            if (!imSubsystem.isFilled()) {
                if (imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_HIGH)) {
                    latch = false;
                    States.GatheringState = EGatheringState.LoadLast;
                } else {
                    if ((imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW)) || latch) {
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

        printStatuses();
        switch (States.GatheringState) {
        case FullIntake:
            this.tightenMagazine.Reset();
            this.finalTighten.Reset();
            // POSITIVE IS DOWN
            this.imSubsystem.Loader(0.3); //wheels that move up/down
            // POSITIVE IS IN
            this.imSubsystem.MagazineBelt(0.4); //belt in magazine proper //0.6
            // POSITIVE IS IN
            this.imSubsystem.IntakeBelt(0.8); //belt right before magazine
            // POSITIVE IS IN
            this.imSubsystem.SpinIntake(0.45); //intake wheels //0.4
            break;
        case PartialIntake:
            this.tightenMagazineAction(this.tightenMagazine);
            if(this.tightenMagazine.IsDone()) {
                if(!imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_FIRST) && 
                (imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_MIDDLE) || imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_END))) {
                    this.imSubsystem.MagazineBelt(-0.6);
                } else {
                    this.imSubsystem.MagazineBelt(0);
                }

                //this.imSubsystem.MagazineBelt(0);
                this.imSubsystem.IntakeBelt(0.8);
            }
            this.imSubsystem.Loader(0.3);
            this.imSubsystem.SpinIntake(.45); //0.4
            break;
        case LoadChamber:
            this.tightenMagazineAction(this.finalTighten);
            if (this.finalTighten.IsDone()) {
                this.imSubsystem.MagazineBelt(0);
                this.imSubsystem.IntakeBelt(0.8);
            }
            this.imSubsystem.Loader(-0.8);
            this.imSubsystem.SpinIntake(0.55);
            break;
        case LoadLast:
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.Loader(0);
            this.imSubsystem.IntakeBelt(0.8);
            this.imSubsystem.SpinIntake(0.55);
            break;
        case Stop:
            this.tightenMagazine.Reset();
            this.finalTighten.Reset();

            this.imSubsystem.Loader(0);
            this.imSubsystem.MagazineBelt(0);
            this.imSubsystem.IntakeBelt(0);
            this.imSubsystem.SpinIntake(0);
            break;
        }
    }

    // Tweak the magazine to tighten up a little
    private void tightenMagazineAction(IterativeDelay delay) {
        delay.Cycle();
        if (delay.IsDone()) {
            this.imSubsystem.MagazineBelt(0); //note: are these two necessary?
            this.imSubsystem.IntakeBelt(0);
            finished = true; //note: figure out what's up with finished
        } else {
            System.out.println("Tightening: " + delay.getCycle());
            this.imSubsystem.IntakeBelt(.8);
            this.imSubsystem.MagazineBelt(.8);
        }
    }

    private void printStatuses() {
        System.out.println("State: " + States.GatheringState);
        System.out.print("Sensors: ");
        for(int i = 0; i < 6; i++) {
            if(imSubsystem.HasBallAtIndex(i)) System.out.print(i); else System.out.print("_");
        }
        System.out.println(); System.out.println();
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