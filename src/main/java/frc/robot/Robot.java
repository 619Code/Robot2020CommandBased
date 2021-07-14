package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.helpers.states.ERobotState;

//This is the main robot class. All mode behaviors are set and run in this class.
public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Scheduler scheduler;
  private Command autoCommand;

  // Sets up the robot
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    scheduler = Scheduler.getInstance();
  }

  @Override
  public void robotPeriodic() {
    if (autoCommand != null) {
      // System.out.println("Interrupted:" + autoCommand.isInterruptible());
      // System.out.println("Canceled:" + autoCommand.isCanceled());
      // System.out.println("Complete:" + autoCommand.isCompleted());
      // System.out.println("Running:" + autoCommand.isRunning());
    }
  }

  // The disabled state shouldn't do anything other than cancelling any commands
  // that are currently running
  @Override
  public void disabledInit() {
    States.RobotState = ERobotState.Disabled;
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    this.robotContainer.AllStop();
  }

  @Override
  public void disabledPeriodic() {
  }

  // get auto command, start the command, and run the command
  @Override
  public void autonomousInit() {
    States.RobotState = ERobotState.Auto;
    autoCommand = robotContainer.getAutoCommand();
    if (autoCommand != null) {
      autoCommand.start();
      System.out.println("Running Auto");
    }
  }

  @Override
  public void autonomousPeriodic() {
    scheduler.run();
  }

  // Cancel all auto commands and run the default drive command (located in
  // RobotContainer)
  @Override
  public void teleopInit() {
    States.RobotState = ERobotState.Teleop;
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    Scheduler.getInstance().removeAll();
  }

  @Override
  public void teleopPeriodic() {
    scheduler.run();
  }

  // This section should be configured to whatever is needed at the moment
  @Override
  public void testInit() {
    States.RobotState = ERobotState.Test;
    super.testInit();
  }

  @Override
  public void testPeriodic() {
    scheduler.run();
  }
}
