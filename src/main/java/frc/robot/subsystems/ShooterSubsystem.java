package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ShooterSubsystem extends Subsystem {
  private CANSparkMax shooterMotorLeft, shooterMotorRight;
  private CANEncoder speedEncoder;
  private CANPIDController speedPID;

  public ShooterSubsystem() {
    shooterMotorLeft = new CANSparkMax(RobotMap.SHOOTER_MOTOR_LEFT, MotorType.kBrushed);

    shooterMotorLeft.restoreFactoryDefaults();
    // speedEncoder =
    // shooterMotorLeft.getAlternateEncoder(AlternateEncoderType.kQuadrature, 8192);
    // speedEncoder = shooterMotorLeft.getEncoder(EncoderType.kQuadrature, 8192);
    speedPID = shooterMotorLeft.getPIDController();
    // speedPID.setFeedbackDevice(speedEncoder);

    shooterMotorRight = new CANSparkMax(RobotMap.SHOOTER_MOTOR_RIGHT, MotorType.kBrushed);
    shooterMotorRight.restoreFactoryDefaults();
    // shooterMotorRight.getEncoder(EncoderType.kNoSensor, 0);
    shooterMotorRight.setInverted(false);
    shooterMotorRight.follow(shooterMotorLeft);

    shooterMotorRight.setSmartCurrentLimit(35);
    shooterMotorLeft.setSmartCurrentLimit(35);
  }

  public void shoot(double rpm) {
    // double targetVelocity_UnitsPer100ms = rpm * 4096 / 600;
    // shooterMotorLeft.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
    // System.out.println(shooterMotorLeft.getAlternateEncoder(AlternateEncoderType.kQuadrature,
    // 8192).getVelocity());
    shooterMotorLeft.set(rpm);
    // shooterMotorRight.set(rpm);
  }

  public double getVelocity() {
    return 0.0;
  }

  @Override
  public void initDefaultCommand() {
  }
}
