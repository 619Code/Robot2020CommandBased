package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.helpers.ERobotState;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Scheduler scheduler;
  private Command m_autonomousCommand;
  
  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    scheduler = Scheduler.getInstance();
  }

  @Override
  public void robotPeriodic() {    
    if (m_autonomousCommand != null) {
      System.out.println("Interupted:" + m_autonomousCommand.isInterruptible());
      System.out.println("Canceled:" + m_autonomousCommand.isCanceled());
      System.out.println("Complete:" + m_autonomousCommand.isCompleted());
      System.out.println("Running:" + m_autonomousCommand.isRunning());
    }
  }

  @Override
  public void disabledInit() {
    States.RobotState = ERobotState.Disabled;
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //Scheduler.getInstance().removeAll();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    States.RobotState = ERobotState.Auto;
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    // Scheduler.getInstance().removeAll();
    m_autonomousCommand = robotContainer.getAutoCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  }

  @Override
  public void autonomousPeriodic() {
    scheduler.run();
  }

  @Override
  public void teleopInit() {
    States.RobotState = ERobotState.Teleop;
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    Scheduler.getInstance().removeAll();
  }

  @Override
  public void teleopPeriodic() {
    scheduler.run();
  }

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
