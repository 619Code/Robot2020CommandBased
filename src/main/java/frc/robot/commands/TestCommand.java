package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.IterativeDelay;

public class TestCommand extends Command {

  private String Name;
  private IterativeDelay delay;
  private int cyclesToComplete;

  public TestCommand(String name, int cyclesToComplete) {
    this.Name = name;
    this.cyclesToComplete = cyclesToComplete;
   }

  @Override
  protected void initialize() {
    this.delay = new IterativeDelay(this.cyclesToComplete);
    System.out.println("Initialize: " + this.Name);
  }

  @Override
  protected void execute() {
    this.delay.Cycle();
    System.out.println("Execute(" + this.delay.getCycle() +"): " + this.Name); 
  }

  @Override
  protected boolean isFinished() {
    if (this.delay.IsDone())
    {
      System.out.println("Finished: " + this.Name);
      return true;
    }
    else
      return false;
  }

  @Override
  protected void end() {
    System.out.println("Ended: " + this.Name);
  }

  @Override
  protected void interrupted() {
    System.out.println("Interrupted: " + this.Name);
  }

}
