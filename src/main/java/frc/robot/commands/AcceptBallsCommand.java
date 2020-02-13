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
  protected boolean isFinished()
  {
    if (this.magazineSubsystem.isLoaded())
        return false;
    else
        return true;
  }

  @Override
  public void end() {
    super.end();
  }
}