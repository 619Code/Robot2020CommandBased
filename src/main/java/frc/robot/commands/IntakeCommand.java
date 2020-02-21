/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
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
    if(joystick.getTriggerAxis(Hand.kLeft) > 0.5) {
      intake.spin(0.5);
    } else if(joystick.getBumper(Hand.kLeft)) {
      intake.spin(-0.5);
    } else {
      intake.spin(0);
    }
  }
  
  
  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

}
