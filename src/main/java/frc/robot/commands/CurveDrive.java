package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drive.ShiftingWCD;

public class CurveDrive extends Command {
  private ShiftingWCD drive;
  private XboxController joystick;
  private double speed, rotation;
  public CurveDrive(ShiftingWCD drive, XboxController joystick) {
    this.drive = drive;
    this.joystick = joystick;
    requires(drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    speed = joystick.getY(Hand.kLeft);
    rotation = -joystick.getX(Hand.kRight);
    setVals();
    drive.curve(speed, rotation);
  }

  public void setVals() {
    if(Math.abs(speed) < 0.075) {
      speed = 0;
    }
    if(Math.abs(rotation) < 0.075) {
      rotation = 0;
    }
    if(joystick.getTriggerAxis(Hand.kRight) < 0.5) {
      speed *= 0.5;
      rotation *= 0.5;
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

  public void adjustVals() {
    
  }

}
