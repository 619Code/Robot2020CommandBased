package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;

public class CurveDriveCommand extends CommandBase {
  private ShiftingWCDSubsystem drive;
  private XboxController joystick;
  private Limelight limelight;
  private ShooterSubsystem shooter;
  private Command turnCommand;
  private double speed, rotation;
  private boolean state;

  public CurveDriveCommand(ShiftingWCDSubsystem drive, XboxController joystick) { //, Limelight limelight, ShooterSubsystem shooter) {
    this.drive = drive;
    this.joystick = joystick;
    // this.limelight = limelight;
    //this.shooter = shooter;
    this.addRequirements(drive);
    // this.turnCommand = new AimCommand(drive, limelight, shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    speed = joystick.getY(Hand.kLeft);
    rotation = -joystick.getX(Hand.kRight);
    state = joystick.getXButton();
    setVals();
    drive.curve(speed, rotation, state);
  }

  public void setVals() {
    if (Math.abs(speed) < 0.075) {
      speed = 0;
    }
    if (Math.abs(rotation) < 0.075) {
      rotation = 0;
    }
    if (joystick.getTriggerAxis(Hand.kRight) < 0.5) {
      speed = speed * 0.5;
      rotation = rotation * 0.5;
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
