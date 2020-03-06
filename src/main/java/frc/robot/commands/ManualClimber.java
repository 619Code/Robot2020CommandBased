/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class ManualClimber extends Command {
  /**
   * Creates a new ManualShootCommand.
   */
  private ClimberSubsystem climberSubsystem;
  private XboxController joystick;

  public ManualClimber(ClimberSubsystem climberSubsystem, XboxController joystick) {
    this.climberSubsystem = climberSubsystem;
    this.joystick = joystick;
    requires(climberSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = joystick.getY(Hand.kRight);
    if (Math.abs(speed) > 0.1) {
      speed *= 0.3;
      climberSubsystem.setHookPosition(speed);
      // System.out.println("Raw Speed: " + speed);
    } else {
      climberSubsystem.setHookPosition(0.05);
    }
    if (joystick.getBButton()) {
      climberSubsystem.extendLift(-1);
    } else {
      climberSubsystem.extendLift(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
