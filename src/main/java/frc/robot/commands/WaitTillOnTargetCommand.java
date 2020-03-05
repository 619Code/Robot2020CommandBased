/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;

public class WaitTillOnTargetCommand extends Command {

  private double xThreshold = 1;
  private double yThreshold = 1;
  private Limelight limelight;
  private TargetInfo targetInfo;

  public WaitTillOnTargetCommand(Limelight limelight) {
    this.limelight = limelight;        
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    targetInfo = limelight.GetTargetInfo();    
  }

  @Override
  protected boolean isFinished() {

    if (RobotState.isDisabled())
      return true;

    if (Math.abs(this.targetInfo.getTargetX()) < 1 && Math.abs(this.targetInfo.getTargetY()) < 1)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  @Override
  protected void end() {
    
  }

  @Override
  protected void interrupted() {
  }
}
