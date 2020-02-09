/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.RobotMap;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;

public class TurnToVisionTarget extends Command {
  private ShiftingWCDSubsystem drive;
  private TargetInfo targetInfo;
  private PIDController targetPID;
  private Limelight limelight;
  public TurnToVisionTarget(ShiftingWCDSubsystem drive, Limelight limelight) {
    this.drive = drive;
    this.limelight = limelight;
    targetInfo = limelight.GetTargetInfo();
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);
    requires(drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    targetInfo = limelight.GetTargetInfo();
    drive.curve(0,targetPID.calculate(targetInfo.getTargetX(), 0), true);
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
