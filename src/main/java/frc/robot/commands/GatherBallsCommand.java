package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;

public class GatherBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;
    public boolean loaderUp = false;
    public boolean magLoaded = false;
    private boolean currentState = false;
    private boolean lastState = false;

    public GatherBallsCommand(IntakeMagazineSubsystem intakeMagazineSubsystem, XboxController joystick) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.joystick = joystick;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    private boolean finished = false;

    @Override
    protected void execute() {
        // System.out.println(imSubsystem.HasBallAtIndex(0));
        // System.out.println(imSubsystem.HasBallAtIndex(1));
        // System.out.println(imSubsystem.HasBallAtIndex(2));
        // System.out.println(imSubsystem.HasBallAtIndex(3));
        // System.out.println(imSubsystem.HasBallAtIndex(4));
        // System.out.println("---------------");
        currentState = this.imSubsystem.HasBallAtIndex(4);
        if (true) {

            if (this.imSubsystem.isFilled()) {
                System.out.println("Full");
                // Shut everything down
                this.imSubsystem.RaiseIntake();
                this.imSubsystem.SpinIntake(0);
                this.imSubsystem.IntakeBelt(0);
                this.imSubsystem.MagazineBelt(0);
                this.imSubsystem.Loader(0);
                this.finished = true;
            }

            /*
             * WANT TO : GIVEN MAG IS FULL: Loader up if : all 4 bttom sensors=True and top
             * sensor=false Loader down if everything else Loader stop if: top and mag full
             */
            else if (this.imSubsystem.IsMagazineFilled() && !getRisingEdge() && this.imSubsystem.HasBallAtIndex(4)) {
                if(!this.imSubsystem.HasBallAtIndex(4)) {
                    magLoaded = true;
                }
                if (loaderUp) { // All 4 bttom sensors
                    System.out.println("Load Chamber");
                    // Keep taking in balls
                    this.imSubsystem.SpinIntake(.4);

                    // Move the chamber wheels up
                    this.imSubsystem.Loader(-1);

                    // Move the intake belt back
                    this.imSubsystem.IntakeBelt(1);

                    // Shut the magazine belt down
                    this.imSubsystem.MagazineBelt(0);
                    if (imSubsystem.HasBallAtIndex(3)) {
                        loaderUp = false;
                    }
                } else if(magLoaded) {
                    System.out.println("Intake");
                    imSubsystem.Loader(.35);
                    imSubsystem.MagazineBelt(0);
                    imSubsystem.IntakeBelt(1);
                    imSubsystem.SpinIntake(.5);
                    if (imSubsystem.HasBallAtIndex(4)) {
                        loaderUp = true;
                    }
                } else {
                    System.out.println("Load Magazine");
                    this.imSubsystem.Loader(.35);
                    this.imSubsystem.MagazineBelt(.5);
                    this.imSubsystem.IntakeBelt(1);
                    this.imSubsystem.SpinIntake(.5);
                }
            } else if (!this.imSubsystem.IsMagazineFilled()) {
                if (!imSubsystem.HasBallAtIndex(0) || imSubsystem.HasBallAtIndex(4)) {
                    System.out.println("Load Magazine");
                    // Move the chamber down to help balls go into magazine
                    // POSITIVE IS DOWN
                    this.imSubsystem.Loader(.35);

                    // Move the magazine belt back to make room for more balls
                    // POSITIVE IS BACK
                    this.imSubsystem.MagazineBelt(.5);

                    // Keep the intake belt moving back to get balls to the magazine
                    // POSITIVE IS INTAKING
                    this.imSubsystem.IntakeBelt(1);

                    // Keep taking in balls
                    // POSITIVE IS IN
                    this.imSubsystem.SpinIntake(.5);
                } else {
                    System.out.println("Intake");
                    imSubsystem.Loader(.35);
                    imSubsystem.MagazineBelt(0);
                    imSubsystem.IntakeBelt(1);
                    imSubsystem.SpinIntake(.5);
                }
            } else // We now need to try to fill the last postion
            {
                System.out.println("Last");
                // Shut down the chamber wheels, hopefully the balls
                // stays up with compression
                this.imSubsystem.Loader(0);

                // Make sure the magazine belt is not running
                this.imSubsystem.MagazineBelt(0);

                // Keep the intake belt moving to get
                // the ball in the intake position
                this.imSubsystem.IntakeBelt(1);

                // Keep more balls coming
                this.imSubsystem.SpinIntake(.5);
            }
        } else {
            this.finished = true;
            this.imSubsystem.Loader(0);

            this.imSubsystem.MagazineBelt(0);

            this.imSubsystem.IntakeBelt(0);

            this.imSubsystem.SpinIntake(0);
        }
        lastState = currentState;
    }

    private boolean getRisingEdge(){
        return currentState && !lastState;
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