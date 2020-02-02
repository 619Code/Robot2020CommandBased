package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CurveDriveCommand extends CommandBase {
  private ShiftingWCDSubsystem drive;
  private XboxController joystick;
  private double speed, rotation;
  private boolean state;
  public CurveDriveCommand(ShiftingWCDSubsystem drive, XboxController joystick) {
    this.drive = drive;
    this.joystick = joystick;
    this.addRequirements(drive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    speed = joystick.getY(Hand.kLeft);
    rotation = -joystick.getX(Hand.kRight);
    state = joystick.getXButtonPressed();
    setVals();
    drive.curve(speed, rotation);
    drive.setShift(state);
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
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
  
  public void adjustVals() {
    
  }

}
