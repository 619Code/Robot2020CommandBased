package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShiftingWCDSubsystem;

public class CurveDriveCommand extends Command {
  private ShiftingWCDSubsystem drive;
  private XboxController joystick;
  private double speed, rotation;
  private boolean isLowGear;

  public CurveDriveCommand(ShiftingWCDSubsystem drive, XboxController joystick) {
    this.drive = drive;
    this.joystick = joystick;
    requires(drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    speed = -joystick.getY(Hand.kLeft); //currently reversed
    rotation = joystick.getX(Hand.kRight);
    setVals();
    drive.curve(speed, rotation, isLowGear);
  }

  public void setVals() {
    if (Math.abs(speed) < 0.075) {
      speed = 0;
    }
    if (Math.abs(rotation) < 0.075) {
      rotation = 0;
    }
    rotation = rotation * 0.6;

    // left down = low gear
    // left up + right down = high gear, high speed
    // left up + right up = high gear, low speed
    if (joystick.getTriggerAxis(Hand.kLeft) > 0.5) {
      isLowGear = true;
    } else if (joystick.getTriggerAxis(Hand.kRight) < 0.5) {
      speed = speed * 0.5;
      rotation = rotation * 0.5;
      isLowGear = false;
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
