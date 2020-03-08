package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class IntakeMagazineDefaultCommand extends Command {
  private IntakeMagazineSubsystem imSubsystem;
  private XboxController joystick;

  public IntakeMagazineDefaultCommand(IntakeMagazineSubsystem imSubsystem, XboxController joystick) {
    this.imSubsystem = imSubsystem;
    this.joystick = joystick;
    requires(imSubsystem);
  }

  @Override
  public void initialize() {
    
  }
  

  protected void execute() {
    imSubsystem.MagazineBelt(0);
    imSubsystem.Loader(0);
    imSubsystem.IntakeBelt(0);
    imSubsystem.SpinIntake(0);
    /*
     * if(joystick.getBumper(Hand.kRight)) { imSubsystem.Loader(-0.8); }
     * if(joystick.getBumper(Hand.kLeft)){ imSubsystem.MagazineBelt(-0.5);
     * imSubsystem.IntakeBelt(-0.5); }
     */
    if (joystick.getPOV(0) == 0) {
      imSubsystem.RaiseIntake();
    }
    if (joystick.getPOV(0) == 180) {
      imSubsystem.LowerIntake();
    }
    if (joystick.getPOV(0) == 90) {
      imSubsystem.SpinIntake(0.55);
    }
    if (joystick.getPOV(0) == 270) {
      imSubsystem.SpinIntake(-0.55);
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