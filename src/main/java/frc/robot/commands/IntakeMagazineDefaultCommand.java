package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class IntakeMagazineDefaultCommand extends Command {
    private IntakeMagazineSubsystem imSubsystem;
    private XboxController joystick;

    public IntakeMagazineDefaultCommand(IntakeMagazineSubsystem imSubsystem, XboxController joystick) {
        this.imSubsystem = imSubsystem;
        this.joystick = joystick;
        requires(imSubsystem); 
    }

    protected void execute() {
        RobotMap.ballCount = 0;
        if(joystick.getTriggerAxis(Hand.kLeft) > 0.5) {
            imSubsystem.Loader(.6);
            imSubsystem.IntakeBelt(1);
            imSubsystem.SpinIntake(0.5);
          } else if(joystick.getBumper(Hand.kLeft)) {
            imSubsystem.Loader(-1);
            imSubsystem.IntakeBelt(-1);
            imSubsystem.SpinIntake(-0.5);
          } else {
            imSubsystem.Loader(0);
            imSubsystem.IntakeBelt(0);
            imSubsystem.SpinIntake(0);
          }
          if(joystick.getTriggerAxis(Hand.kRight) > 0.5) {
            imSubsystem.MagazineBelt(0.5);
          } else if(joystick.getBumper(Hand.kRight)) {
            imSubsystem.MagazineBelt(-0.5);
          } else {
            imSubsystem.MagazineBelt(0);
          }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}