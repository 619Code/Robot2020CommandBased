/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;

public class AcceptBallsCommand extends CommandBase {

    private MagazineSubsystem magazineSubsystem;

    public AcceptBallsCommand(MagazineSubsystem magazineSubsystem) {
        this.magazineSubsystem = magazineSubsystem;
        this.addRequirements(this.magazineSubsystem);
    }

  @Override
  public void initialize() {      
  }

  @Override
  public void execute() {
      if (!this.magazineSubsystem.isLoaded() && this.magazineSubsystem.nextEmptyIndex() == 1)
      {
          this.magazineSubsystem.LoadChamber();
      }
      else if (this.magazineSubsystem.isLoaded())
      {
          if (!this.magazineSubsystem.isFilled())
          {
              if (this.magazineSubsystem.HasBallAtIndex(0))
              {
                  this.magazineSubsystem.LoadMagazine();
              }
          }
      }
  }

  @Override
  public boolean isFinished()
  {
    if (this.magazineSubsystem.isLoaded())
        return false;
    else
        return true;
  }

  @Override
  public void end(boolean interrupted) {

  }
}
