package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;
import frc.robot.hardware.LimitSwitch;

public class AimingSubsystem extends Subsystem {
  private CANSparkMax angleMotor;
  private PIDController anglePID;
  private LimitSwitch zeroSwitch;

  public AimingSubsystem() {

    angleMotor = new CANSparkMax(RobotMap.ANGLE_MOTOR, MotorType.kBrushless);
    angleMotor.restoreFactoryDefaults();
    angleMotor.setSmartCurrentLimit(35);
    angleMotor.setInverted(false);
    angleMotor.setIdleMode(IdleMode.kCoast);
    anglePID = new PIDController(RobotMap.ANGLE_P, RobotMap.ANGLE_I, RobotMap.ANGLE_D);
    angleMotor.getEncoder().setPosition(0);

    zeroSwitch = new LimitSwitch(RobotMap.ZEROSWITCH);
  }

  public void setAngle(double angle) {
    double targetAngle = ((angle) * RobotMap.ANGLE_TO_TICK_RATIO);
    // System.out.println("ABSANGLE" + angleMotor.getEncoder().getPosition() + " " +
    // targetAngle);
    angleMotor.set(anglePID.calculate(angleMotor.getEncoder().getPosition(), targetAngle));
  }

  public double getAngle() {
    return angleMotor.getEncoder().getPosition() / RobotMap.ANGLE_TO_TICK_RATIO;
  }

  public void setZero() {
    if (zeroSwitch.get()) {
      angleMotor.set(0);
    } else {
      angleMotor.set(-0.2);
    }
  }

  @Override
  public void initDefaultCommand() {
  }
}