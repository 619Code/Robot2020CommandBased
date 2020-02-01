package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.helpers.Limelight;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Command m_autonomousCommand;
  NetworkTableEntry pVel, iVel, dVel;
  ShuffleboardTab tab;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    tab = Shuffleboard.getTab("2020 Settings");
    pVel = tab.add("P Velocity", 0).getEntry();
    iVel = tab.add("I Velocity", 0).getEntry();
    dVel = tab.add("D Velocity", 0).getEntry();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutoCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    // RobotMap.VEL_P = pVel.getDouble(0);
    // RobotMap.VEL_I = iVel.getDouble(0);
    // RobotMap.VEL_D = dVel.getDouble(0);
  }

  @Override
  public void teleopPeriodic() {
    double distanceX = robotContainer.limelight.GetTargetInfo().getDistanceX();
    System.out.println(distanceX);
  }

  @Override
  public void testPeriodic() {
  }
}
