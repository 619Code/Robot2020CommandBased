/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendoExtend extends CommandBase {

    private IntakeSubsystem intakeSubsystem;

    public ExtendoExtend(IntakeSubsystem intakeSubsytem) {
        this.intakeSubsystem = intakeSubsytem;
        this.addRequirements(this.intakeSubsystem);
    }

  @Override
  public void initialize() {
      if (!this.intakeSubsystem.isExtended())
      {
          this.intakeSubsystem.extend();
          this.intakeSubsystem.spin(RobotMap.INTAKE_MOTOR_PERCENTAGE);
          this.intakeSubsystem.intakeBeltOn(RobotMap.INTAKE_MOTOR_BELT);
      }    
  }

  @Override
  public void execute() {
      if (this.intakeSubsystem.isExtended())
      {
        this.intakeSubsystem.spin(RobotMap.INTAKE_MOTOR_PERCENTAGE);
        this.intakeSubsystem.intakeBeltOn(RobotMap.INTAKE_MOTOR_BELT);
      }
  }

  @Override
  public boolean isFinished()
  {
    if (this.intakeSubsystem.isExtended())
        return true;
    else
        return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
