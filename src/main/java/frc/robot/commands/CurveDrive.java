package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShiftingWCD;

public class CurveDrive extends Command {
  private ShiftingWCD drive;
  private Joystick joystick;
  public CurveDrive(ShiftingWCD drive, Joystick joystick) {
    this.drive = drive;
    this.joystick = joystick;
    requires(drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    drive.curve(joystick.getY(Hand.kRight), joystick.getX(Hand.kRight));
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
