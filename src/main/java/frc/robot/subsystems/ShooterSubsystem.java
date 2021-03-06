package frc.robot.subsystems;

import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

//This is the shooter subsystem. All methods or information related to the shooter should be accessed through this class.
public class ShooterSubsystem extends Subsystem {
  private CANSparkMax shooterMotorLeft, shooterMotorRight, angleMotor;
  private CANEncoder speedEncoder;
  private PIDController anglePID;
  private CANPIDController speedPID;

  public ShooterSubsystem() {
    shooterMotorLeft = new CANSparkMax(RobotMap.SHOOTER_MOTOR_LEFT, MotorType.kBrushed);
    speedEncoder = shooterMotorLeft.getAlternateEncoder(AlternateEncoderType.kQuadrature, 8192);
    shooterMotorLeft.restoreFactoryDefaults();
    speedPID = shooterMotorLeft.getPIDController();
    speedPID.setFeedbackDevice(speedEncoder);

    shooterMotorRight = new CANSparkMax(RobotMap.SHOOTER_MOTOR_RIGHT, MotorType.kBrushed);
    shooterMotorRight.restoreFactoryDefaults();
    shooterMotorRight.getEncoder(EncoderType.kNoSensor, 0);
    shooterMotorRight.setInverted(false);
    shooterMotorRight.follow(shooterMotorLeft);

    shooterMotorRight.setSmartCurrentLimit(35);
    shooterMotorLeft.setSmartCurrentLimit(35);
    
    angleMotor = new CANSparkMax(RobotMap.ANGLE_MOTOR, MotorType.kBrushless);
    angleMotor.restoreFactoryDefaults();
    angleMotor.setSmartCurrentLimit(35);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    angleMotor.getEncoder().setPosition(0);
    // shooterMotorLeft.getEncoder().setPosition(0);
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
    return shooterMotorLeft.getAlternateEncoder().getVelocity();
  }

  public void setAngle(double angle) {
    double targetAngle = ((angle) * 10.5 / 90.0) + angleMotor.getEncoder().getPosition();
    System.out.println("REEEEEE" + angleMotor.getEncoder().getPosition() + " " + targetAngle);
    angleMotor.set(anglePID.calculate(angleMotor.getEncoder().getPosition(), targetAngle));
  }

  public void setAbsAngle(double angle) {
    double targetAngle = ((angle) * 10.5 / 90.0);
    System.out.println("EEEEER" + angleMotor.getEncoder().getPosition() + " " + targetAngle);
    angleMotor.set(anglePID.calculate(angleMotor.getEncoder().getPosition(), targetAngle));
  }

  public void setZero(){
    angleMotor.set(0);
    angleMotor.getEncoder().setPosition(0);
  }

  @Override
  public void initDefaultCommand() {
  }
}
