package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.AimingSubsystem;

public class AimingSetZeroCommand extends Command {

  private AimingSubsystem aimingSubsystem;
  private Limelight limelight;

  public AimingSetZeroCommand(AimingSubsystem aimingSubsystem, Limelight limelight) {
    this.aimingSubsystem = aimingSubsystem;
    this.limelight = limelight;
    requires(aimingSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    this.aimingSubsystem.setZero();
    limelight.TurnLightOff();
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
