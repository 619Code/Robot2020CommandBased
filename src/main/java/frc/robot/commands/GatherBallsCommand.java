package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.*;

public class GatherBallsCommand extends Command {

    private IntakeMagazineSubsystem imSubsystem;
    private boolean waiting = false;

    public GatherBallsCommand(IntakeMagazineSubsystem intakeMagazineSubsystem) {
        this.imSubsystem = intakeMagazineSubsystem;
        this.requires(this.imSubsystem);
    }

    @Override
    protected void initialize() {
        this.imSubsystem.LowerIntake();
    }

    private boolean finished = false;

    @Override
    protected void execute() {

        double intakeSpeed = 0;
        double intakeBeltSpeed = 0;
        double loaderSpeed = 0;
        double magSpeed = 0;

        if (this.imSubsystem.isFilled()) {
            // Base case: nothing moving
            // everything set to 0
            this.finished = true;
        } else if (!this.imSubsystem.isMagazineFilled()) {
            // intaking but empty
            // v
            // > | >>>
            intakeSpeed = RobotMap.INTAKE_IN;
            intakeBeltSpeed = RobotMap.INTAKE_BELT_IN;
            loaderSpeed = RobotMap.LOADER_DOWN;
            magSpeed = RobotMap.MAG_IN;
        } else if (this.imSubsystem.isMagazineFilled() && !this.imSubsystem.oneInTheChamber() && !this.waiting) {
            // 3 balls in mag but stil moving in
            // v
            // > | OOO
            // loader has to go down till ball vectored, then up
            intakeSpeed = RobotMap.INTAKE_IN;
            intakeBeltSpeed = RobotMap.INTAKE_BELT_IN;
            loaderSpeed = RobotMap.LOADER_DOWN;
        } else if (this.waiting) {
            // 3 balls in mag and loading chamber
            intakeSpeed = RobotMap.INTAKE_IN;
            intakeBeltSpeed = RobotMap.INTAKE_BELT_IN;
            loaderSpeed = RobotMap.LOADER_DOWN;
            this.waiting = !this.imSubsystem.oneInTheChamber();
        } else {
            // last ball
            // X
            // > | OOO
            intakeSpeed = RobotMap.INTAKE_IN;
            intakeBeltSpeed = RobotMap.INTAKE_BELT_IN;
        }

        this.imSubsystem.Load(loaderSpeed);
        this.imSubsystem.MagazineBelt(magSpeed);
        this.imSubsystem.IntakeBelt(intakeBeltSpeed);
        this.imSubsystem.SpinIntake(intakeSpeed);
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    public void end() {
        this.finished = true;
        super.end();
    }
}