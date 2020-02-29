/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.RobotMap;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;

public class TurnToVisionTarget extends Command {
  private ShiftingWCDSubsystem drive;
  private ShooterSubsystem shooterSubsystem;
  private TargetInfo targetInfo;
  private PIDController targetPID;
  private Limelight limelight;
  private IntakeMagazineSubsystem imSubsystem;
  private XboxController primaryController, secondaryController;

  public TurnToVisionTarget(ShiftingWCDSubsystem drive, Limelight limelight, ShooterSubsystem shooterSubsystem, XboxController primaryController, XboxController secondaryController) {
    this.drive = drive;
    this.limelight = limelight;
    this.shooterSubsystem = shooterSubsystem;
    this.primaryController = primaryController;
    this.secondaryController = secondaryController;
    //this.imSubsystem = imSubsystem;
    targetInfo = limelight.GetTargetInfo();
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);
    requires(drive);
    requires(shooterSubsystem);
  }

  @Override
  protected void initialize() {
    limelight.TurnLightOn();
  }

  @Override
  protected void execute() {
    targetInfo = limelight.GetTargetInfo();
    drive.curve(0,-targetPID.calculate(targetInfo.getTargetX(), 0), true);
    if(targetInfo.HasTarget) {
      shooterSubsystem.setAngle(targetInfo.getTargetY());
    } else {
      shooterSubsystem.setAbsAngle(15);
    }
    
    double speed = secondaryController.getTriggerAxis(Hand.kRight);
    if(speed>0.075){
      shooterSubsystem.shoot(speed);
    }
  }

  @Override
  protected boolean isFinished() {
    //shooterSubsystem.setAbsAngle(0);
    return false;
  }

  @Override
  protected void end() {
    limelight.TurnLightOff();
  }

  @Override
  protected void interrupted() {
  }
}
