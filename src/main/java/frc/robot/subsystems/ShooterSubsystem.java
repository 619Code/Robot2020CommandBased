package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase {
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

  public double findVelocity(double targetA, double distanceX) {
    double time = Math.sqrt((2*(RobotMap.TARGET_HEIGHT-RobotMap.SHOOTER_Y_OFFSET))/-(32.2*12));
    double velocityX = (2*distanceX-RobotMap.SHOOTER_X_OFFSET)/time;
    double velocity = velocityX/Math.cos(Math.toRadians(targetA) + RobotMap.LIMELIGHT_ANGLE);
    return velocity;
  }
}
