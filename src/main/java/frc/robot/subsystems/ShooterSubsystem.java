package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax angleMotor;
  private TalonSRX shooterMotor;
  private PIDController shooterPID, anglePID;
  double targetInput;
  public ShooterSubsystem(){
    shooterMotor = new TalonSRX(20);
    shooterPID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    angleMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    shooterMotor.configFactoryDefault();
    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    shooterMotor.setSensorPhase(true);
    shooterMotor.configNominalOutputForward(0, 30);
		shooterMotor.configNominalOutputReverse(0, 30);
		shooterMotor.configPeakOutputForward(1, 30);
    shooterMotor.configPeakOutputReverse(-1, 30);
    shooterMotor.config_kF(0, (1023.0/7200.0), 30);
		shooterMotor.config_kP(0, 0.25, 30);
		shooterMotor.config_kI(0, 0.001, 30);
    shooterMotor.config_kD(0, 20, 30);

  }

  public void setVelocity(double targetRPM){
    targetInput = targetRPM * 4096 / 600;
    //shooterMotor.set(ControlMode.Velocity, targetInput);
    shooterMotor.set(ControlMode.PercentOutput, 0.83);
    double readRPM = (shooterMotor.getSelectedSensorVelocity() * 600)/4096;
    double readVelocity = (readRPM/60)*2*Math.PI*2;
    System.out.println(readRPM);
    System.out.println(readVelocity);
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
