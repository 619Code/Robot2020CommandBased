package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
public class ShooterSubsystem extends Subsystem {
  private CANSparkMax angleMotor;
  private TalonSRX shooterMotor;
  private PIDController shooterPID, anglePID;

  public ShooterSubsystem(){
    shooterMotor = new TalonSRX(20);
    shooterPID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    angleMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);
  }

  public void setVelocity(double targetRPM){
    
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

  @Override
  public void initDefaultCommand() {
  }
}
