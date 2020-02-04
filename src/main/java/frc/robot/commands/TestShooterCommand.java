/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TestShooterCommand extends CommandBase {

    private ShooterSubsystem shooterSubsystem;

    public TestShooterCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.addRequirements(this.shooterSubsystem);
    }

  @Override
  public void initialize() {
      this.shooterSubsystem.shooterMotor.showOnDashboard();
  }

  @Override
  public void execute() {
      this.shooterSubsystem.shooterMotor.updateFromDashboard(true);
      //this.shooterSubsystem.shooterMotor.setPoint(100);
  }

  @Override
  public boolean isFinished()
  {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
