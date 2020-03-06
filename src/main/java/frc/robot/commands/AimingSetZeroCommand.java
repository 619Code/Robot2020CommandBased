package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.AimingSubsystem;

public class AimingSetZeroCommand extends Command {
  
  private AimingSubsystem aimingSubsystem;
  
  public AimingSetZeroCommand(AimingSubsystem aimingSubsystem) {
    this.aimingSubsystem = aimingSubsystem;
    requires(aimingSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    this.aimingSubsystem.setZero();
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
