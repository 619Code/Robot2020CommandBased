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
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Add your docs here.
 */
public class IntakeCommand extends Command {
  private IntakeSubsystem intake;
  private XboxController joystick;
  private double speed;
  public IntakeCommand(IntakeSubsystem intake, XboxController joystick) {
    this.intake = intake;
    this.joystick = joystick;
    requires(intake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
  }
  @Override
  protected void execute() {
    speed = 0.5*(joystick.getTriggerAxis(Hand.kLeft)-joystick.getTriggerAxis(Hand.kRight));
    if(Math.abs(speed) < 0.075){
      speed=0;
    }
    intake.spin(speed);
  }
  
  
  
  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

}
