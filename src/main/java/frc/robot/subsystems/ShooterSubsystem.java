package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

public class ShooterSubsystem extends Subsystem {
  private CANSparkMax shooterMotor, angleMotor;
  private PIDController shooterPID, anglePID;

  public ShooterSubsystem(){
    shooterMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    shooterPID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    angleMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);
  }

  public void setVelocity(double targetRPM){
    double currentRPM = shooterMotor.getEncoder().getVelocity();
    shooterMotor.set(shooterPID.calculate(currentRPM, targetRPM));
  }
  public void setAngle(double targetAngle){
    double currentAngle = calculateAngle(shooterMotor.getEncoder().getPosition());
    angleMotor.set(anglePID.calculate(currentAngle, targetAngle));
  }

  public double calculateAngle(double position){
    //calculate position, can't yet because build
    return position;
  }

  @Override
  public void initDefaultCommand() {
  }
}
