package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.MagazineSubsystem;

public class AcceptBallsCommand extends Command {

    private MagazineSubsystem magazineSubsystem;

    public AcceptBallsCommand(MagazineSubsystem magazineSubsystem) {
        this.magazineSubsystem = magazineSubsystem;
        this.requires(this.magazineSubsystem);
    }

  @Override
  protected void initialize() {      
  }

  @Override
  protected void execute() {
      if (!this.magazineSubsystem.HasBallAtIndex(2) && this.magazineSubsystem.nextEmptyIndex() == 1)
      {
          //this.magazineSubsystem.LoadChamber(0);
      }
      else if (this.magazineSubsystem.HasBallAtIndex(2))
      {
          if (!this.magazineSubsystem.isFilled())
          {
              if (this.magazineSubsystem.HasBallAtIndex(0))
              {
                  //this.magazineSubsystem.LoadMagazine(0);
              }
          }
      }
  }

  @Override
  protected boolean isFinished()
  {
    if (this.magazineSubsystem.HasBallAtIndex(2))
        return false;
    else
        return true;
  }

  @Override
  public void end() {
    super.end();
  }
}