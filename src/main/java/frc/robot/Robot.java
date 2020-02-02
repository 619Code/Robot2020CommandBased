package frc.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2363.controller.PIDController;

import edu.wpi.first.networktables.NetworkTableEntry;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;
  private Command m_autonomousCommand;
  private TalonSRX shooterMotor;
  PIDController velPID;
  NetworkTableEntry pVel, iVel, dVel;
  ShuffleboardTab tab;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    shooterMotor = new TalonSRX(20);
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

  double out;
  final double maxVel = -17000.0;
  double targetRPM = 120;
  double targetVel;
  @Override
  public void testInit(){
    //targetVel = (targetRPM * 1024.0 * 7.0)/600.0;
    targetVel = -17000;
    out = targetVel/maxVel;
    //System.out.println("HEY A GAG SHKLFSJDKF SDKG: " + out);
  }

  @Override
  public void testPeriodic() {
    double change = velPID.calculate(shooterMotor.getSelectedSensorVelocity(), targetVel);
    out += -change;
    //shooterMotor.set(ControlMode.PercentOutput, out);
    shooterMotor.set(ControlMode.PercentOutput, 1.0);
    //double vel = shooterMotor.getSelectedSensorVelocity()*600 / 7 / 1024;
    //System.out.println("vel: " + shooterMotor.getSelectedSensorVelocity() + " targetVel: " + targetVel);
    //shooterMotor.set(ControlMode.PercentOutput, 1);
    //double targetVelocityRPM = 100;
    //double targetEnocoderTicksPer100ms = (targetVelocityRPM * 1024 * 7)/600;
    //double change = (velPID.calculate(vel, targetVelocityRPM)+1)/2;
    //System.out.println(change);
    //out += change;
    //shooterMotor.set(ControlMode.PercentOutput, out);
  }
}
