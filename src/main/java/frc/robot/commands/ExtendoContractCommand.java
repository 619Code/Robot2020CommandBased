/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendoContractCommand extends CommandBase {

    private IntakeSubsystem intakeSubsystem;

    public ExtendoContractCommand(IntakeSubsystem intakeSubsytem) {
        this.intakeSubsystem = intakeSubsytem;
        this.addRequirements(this.intakeSubsystem);
    }

  @Override
  public void initialize() {
      if (this.intakeSubsystem.isExtended())
      {
          this.intakeSubsystem.contract();
          this.intakeSubsystem.stop();
      }    
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished()
  {
    if (this.intakeSubsystem.isExtended())
        return false;
    else
        return true;
  }

  @Override
  public void end(boolean interrupted) {

  }
}
