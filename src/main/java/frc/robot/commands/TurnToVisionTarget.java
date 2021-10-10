/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.AimingSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;

public class TurnToVisionTarget extends Command {
  private ShiftingWCDSubsystem drive;
  private AimingSubsystem aimingSubsystem;
  private TargetInfo targetInfo;
  private PIDController targetPID;
  private Limelight limelight;
  private double lastShooterAngle, lastDriveAngle;

  public TurnToVisionTarget(ShiftingWCDSubsystem drive, Limelight limelight, AimingSubsystem aimingSubsystem) {
    this.drive = drive;
    this.limelight = limelight;
    this.aimingSubsystem = aimingSubsystem;
    // this.imSubsystem = imSubsystem;
    
    targetInfo = limelight.GetTargetInfo();
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);
    lastShooterAngle = 0;

    requires(drive);
    requires(aimingSubsystem);
  }

  @Override
  protected void initialize() {
    limelight.TurnLightOn();
  }

  @Override
  protected void execute() {

    targetInfo = limelight.GetTargetInfo();
    targetInfo.Show();

    if (States.isShooting == false) {
      double currentDriveAngle = targetInfo.getTargetX();
      double lastDriveAngle = currentDriveAngle;
      drive.resetGyro();
      drive.curve(0, -targetPID.calculate(currentDriveAngle, 0), false); //temp
      
      if (targetInfo.HasTarget) {
        double currentShooterAngle = this.aimingSubsystem.getAngle() + targetInfo.getTargetY();
        //System.out.println("Current angle: " + this.aimingSubsystem.getAngle());

        lastShooterAngle = currentShooterAngle;
        currentShooterAngle = currentShooterAngle > 50 ? 50 : currentShooterAngle;
        aimingSubsystem.setAngle(currentShooterAngle);
      } else {
        aimingSubsystem.setAngle(30);
      }

    } else {
      drive.curve(0, -targetPID.calculate(drive.getHeadingDegrees(), 0), false); //temp
      aimingSubsystem.setAngle(lastShooterAngle);
    }
  }

  @Override
  protected boolean isFinished() {
    return RobotState.isDisabled();
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
  }
}
