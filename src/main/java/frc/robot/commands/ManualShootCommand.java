/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShootCommand extends Command {
  /**
   * Creates a new ManualShootCommand.
   */
  private ShooterSubsystem shooterSubsystem;
  private XboxController joystick;

  double speedAdjust;
  double speed;

  public ManualShootCommand(ShooterSubsystem shooterSubsystem, XboxController joystick) {
    this.shooterSubsystem = shooterSubsystem;
    this.joystick = joystick;
    requires(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!(this.joystick == null)) {
      if (Math.abs(joystick.getY(Hand.kRight)) > 0.1) {
        speedAdjust = joystick.getY(Hand.kRight);
      } else {
        speedAdjust = 0;
      }
      speed = 1.0 + (speedAdjust / 10.0);

      if (joystick.getBumper(Hand.kRight)) {
        shooterSubsystem.shoot(speed);
      } else {
        shooterSubsystem.shoot(0);
      }
    } else {
      shooterSubsystem.shoot(1);
    }

    // System.out.println(shooterSubsystem.getVelocity());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    shooterSubsystem.shoot(0);
  }
}
