package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

//This is the shooter subsystem. All methods or information related to the shooter should be accessed through this class.
public class ShooterSubsystem extends Subsystem {
  private CANSparkMax shooterMotor, angleMotor;
  private PIDController shooterPID, anglePID;

  public ShooterSubsystem(){
    shooterMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    shooterPID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);

    angleMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);
  }

  //Uses a PID to adjust the current angle/velocity to the target angle/velocity
  public void setAngle(double targetAngle){
    double currentAngle = getAngle(shooterMotor.getEncoder().getPosition());
    angleMotor.set(anglePID.calculate(currentAngle, targetAngle));
  }

  public void setVelocity(double targetRPM) {
    double currentRPM = shooterMotor.getEncoder().getVelocity();
    shooterMotor.set(shooterPID.calculate(currentRPM, targetRPM));
  }

  //Calculate the current angle of the shooter
  public double getAngle(double position){
    return -1;
  }

  @Override
  public void initDefaultCommand() {
  }
}
