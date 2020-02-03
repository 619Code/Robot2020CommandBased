/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;

public class AimCommand extends CommandBase {
  private ShiftingWCDSubsystem drive;
  private TargetInfo targetInfo;
  private ShooterSubsystem shooter;
  private PIDController targetPID;
  private Limelight limelight;
  public AimCommand(ShiftingWCDSubsystem drive, Limelight limelight, ShooterSubsystem shooter) {
    this.drive = drive;
    this.limelight = limelight;
    this.shooter = shooter;
    targetInfo = limelight.GetTargetInfo();
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);
    this.addRequirements(drive, shooter);
    }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    targetInfo = limelight.GetTargetInfo();
    drive.curve(0,targetPID.calculate(targetInfo.getTargetX(), 0), false);
    shooter.setVelocity(500);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
