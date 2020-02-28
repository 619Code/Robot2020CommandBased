package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetZeroCommand extends Command {
  
  private ShooterSubsystem shooterSubsystem;
  
  public ShooterSetZeroCommand(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    requires(shooterSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    this.shooterSubsystem.setZero();
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
