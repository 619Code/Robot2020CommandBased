package frc.robot;

import com.team2363.controller.PIDController;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Command m_autonomousCommand;
  PIDController velPID;
  NetworkTableEntry pVel, iVel, dVel;
  ShuffleboardTab tab;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    tab = Shuffleboard.getTab("2020 Settings");
    pVel = tab.add("P Velocity", 0).getEntry();
    iVel = tab.add("I Velocity", 0).getEntry();
    dVel = tab.add("D Velocity", 0).getEntry();
    velPID = new PIDController(0.0000005, 0, 0);
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
  public void testInit(){
    
  }

  public double targetVelocity = 31.3457 * 12;
  public double targetRPM; //60*(targetVelocity/(4*Math.PI));

  @Override
  public void testPeriodic() {
    
  }
}
