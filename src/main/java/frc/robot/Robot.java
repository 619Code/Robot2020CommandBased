package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;
import com.team2363.controller.PIDController;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Command m_autonomousCommand;
  PIDController velPID;
  /*NetworkTableEntry pVel, iVel, dVel;
  ShuffleboardTab tab;*/

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    /* tab = Shuffleboard.getTab("2020 Settings");
    pVel = tab.add("P Velocity", 0).getEntry();
    iVel = tab.add("I Velocity", 0).getEntry();
    dVel = tab.add("D Velocity", 0).getEntry(); */
    velPID = new PIDController(0.0000005, 0, 0);
  }
  
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {

    this.robotContainer.drive.setIdleMode(IdleMode.kCoast);

    if (m_autonomousCommand != null) {
      m_autonomousCommand.end(true);
    }    
  }

  @Override
  public void disabledPeriodic() {
    // CommandScheduler.getInstance().cancelAll();
    // CommandScheduler.getInstance().cancel(m_autonomousCommand);
  }

  @Override
  public void autonomousInit() {
    this.robotContainer.drive.setIdleMode(IdleMode.kBrake);
    m_autonomousCommand = robotContainer.getAutoCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    //CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    this.robotContainer.drive.setIdleMode(IdleMode.kBrake);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.end(true);
    }    

    /*if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }*/
    /*CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().cancel(m_autonomousCommand);*/
    
    
    //CommandScheduler.getInstance().disable();

    // RobotMap.VEL_P = pVel.getDouble(0);
    // RobotMap.VEL_I = iVel.getDouble(0);
    // RobotMap.VEL_D = dVel.getDouble(0);
  }

  @Override
  public void teleopPeriodic() {
    //double distanceX = robotContainer.limelight.GetTargetInfo().getDistanceX();
    //System.out.println(distanceX);
    //CommandScheduler.getInstance().run();
    
  }

  @Override
  public void testInit(){
    this.robotContainer.drive.setIdleMode(IdleMode.kBrake);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.end(true);
    }
  }

  public double targetVelocity = 31.3457 * 12;
  public double targetRPM; //60*(targetVelocity/(4*Math.PI));

  @Override
  public void testPeriodic() {
  }
}
