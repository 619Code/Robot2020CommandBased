/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.AimingSubsystem;
import frc.robot.subsystems.IntakeMagazineSubsystem;
import frc.robot.subsystems.ShiftingWCDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;

public class TurnToVisionTarget extends Command {
  private ShiftingWCDSubsystem drive;
  private AimingSubsystem aimingSubsystem;
  private TargetInfo targetInfo;
  private PIDController targetPID;
  private Limelight limelight;
  private LinearFilter filter;

  public TurnToVisionTarget(ShiftingWCDSubsystem drive, Limelight limelight, AimingSubsystem aimingSubsystem) {
    this.drive = drive;
    this.limelight = limelight;
    this.aimingSubsystem = aimingSubsystem;
    //this.imSubsystem = imSubsystem;
    targetInfo = limelight.GetTargetInfo();
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);
    requires(drive);
    requires(aimingSubsystem);
  }

  @Override
  protected void initialize() {
    limelight.TurnLightOn();
    filter = LinearFilter.singlePoleIIR(.1, .02);
  }

  @Override
  protected void execute() {

    targetInfo = limelight.GetTargetInfo();
    double lastAngle = 0;
    //Temp always do this
    if(States.isShooting == false){      
      drive.curve(0,-targetPID.calculate(targetInfo.getTargetX(), 0), true);
      if(targetInfo.HasTarget) {      
        //double currentAngle = targetInfo.getTargetY();
        // Filtering data coming from the limelight
        double currentAngle = filter.calculate(targetInfo.getTargetY());
        System.out.println("Target Y:" + targetInfo.getTargetY());
        aimingSubsystem.setAngle(currentAngle);
        lastAngle = currentAngle;
      }
      else{
        aimingSubsystem.setAbsAngle(30);
      }
    }
    else{
      aimingSubsystem.setAngle(lastAngle);
    }
  }

  @Override
  protected boolean isFinished() {
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
