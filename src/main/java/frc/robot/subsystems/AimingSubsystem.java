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
public class AimingSubsystem extends Subsystem {
  private CANSparkMax angleMotor;
  private PIDController anglePID;

  public AimingSubsystem() {
           
    angleMotor = new CANSparkMax(RobotMap.ANGLE_MOTOR, MotorType.kBrushless);
    angleMotor.restoreFactoryDefaults();
    angleMotor.setSmartCurrentLimit(35);
    anglePID = new PIDController(RobotMap.SHOOTER_P, RobotMap.SHOOTER_I, RobotMap.SHOOTER_D);
    angleMotor.getEncoder().setPosition(0);
  }  

  public void setAngle(double angle) {
    double targetAngle = ((angle) * 10.5 / 90.0) + angleMotor.getEncoder().getPosition();
    System.out.println("ANGLE:" + angleMotor.getEncoder().getPosition() + " " + targetAngle);
    angleMotor.set(anglePID.calculate(angleMotor.getEncoder().getPosition(), targetAngle));
  }

  public void setAbsAngle(double angle) {
    double targetAngle = ((angle) * 10.5 / 90.0);
    System.out.println("ABSANGLE" + angleMotor.getEncoder().getPosition() + " " + targetAngle);
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
