package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

//This is the shooter subsystem. All methods or information related to the shooter should be accessed through this class.
public class ShooterSubsystemV2 extends Subsystem {
  private CANSparkMax shooterMotor;
  private PIDController shooterPID;

  public ShooterSubsystemV2(){
    shooterMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
    shooterPID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);
  }

  //Uses a PID to adjust the current velocity to the target velocity
  public void setVelocity(double targetRPM) {
    double currentRPM = shooterMotor.getEncoder().getVelocity();
    shooterMotor.set(shooterPID.calculate(currentRPM, targetRPM));
  }

  @Override
  public void initDefaultCommand() {
  }
}
