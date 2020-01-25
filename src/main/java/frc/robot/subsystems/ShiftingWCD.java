package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.RobotMap;

public class ShiftingWCD extends Subsystem {

  CANSparkMax leftMaster, leftSlave0, leftSlave1, rightMaster, rightSlave0, rightSlave1;
  DifferentialDrive drive;
  DoubleSolenoid shifter;

  AHRS navx;
  CANEncoder leftEncoder, rightEncoder;
  DifferentialDriveKinematics m_kinematics;
  DifferentialDriveOdometry m_odometry;
  Pose2d pose;

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public ShiftingWCD() {
    initMotors();
    initDrive();
    initSensors();
    // shifter = new DoubleSolenoid(0, 1);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  private void initMotors() {
    leftMaster = new CANSparkMax(10, MotorType.kBrushless);
    leftSlave0 = new CANSparkMax(11, MotorType.kBrushless);
    leftSlave1 = new CANSparkMax(12, MotorType.kBrushless);

    leftSlave0.follow(leftMaster);
    leftSlave1.follow(leftMaster);

    rightMaster = new CANSparkMax(13, MotorType.kBrushless);
    rightSlave0 = new CANSparkMax(14, MotorType.kBrushless);
    rightSlave1 = new CANSparkMax(15, MotorType.kBrushless);

    rightSlave0.follow(rightMaster);
    rightSlave1.follow(rightMaster);

    leftMaster.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
    leftSlave0.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
    leftSlave1.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
    rightMaster.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
    rightSlave0.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
    rightSlave1.setSmartCurrentLimit(RobotMap.NEO_LIMIT);

    brakeMode(leftMaster);
    brakeMode(leftSlave0);
    brakeMode(leftSlave1);
    brakeMode(rightMaster);
    brakeMode(rightSlave0);
    brakeMode(rightSlave1);

  }

  private void initDrive() {
    drive = new DifferentialDrive(leftMaster, rightMaster);
    drive.setSafetyEnabled(false);
  }

  private void initSensors() {
    navx = new AHRS(SPI.Port.kMXP);
    leftEncoder = leftMaster.getEncoder();
    rightEncoder = rightMaster.getEncoder();
    resetGyro();
    resetEncoders();
    m_kinematics= new DifferentialDriveKinematics(Units.inchesToMeters(RobotMap.kTrackwidthInches));
    m_odometry = new DifferentialDriveOdometry(getAngle());
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public void resetGyro() {
    navx.reset();
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public double getLeftEncoderInches() {
    return -(leftEncoder.getPosition()/RobotMap.DRIVE_RATIO)*(RobotMap.WHEEL_DIAMETER*Math.PI);
  }

  public double getRightEncoderInches() {
    return (rightEncoder.getPosition()/RobotMap.DRIVE_RATIO)*(RobotMap.WHEEL_DIAMETER*Math.PI);
  }

  public double getLeftEncoderFeet() {
    return getLeftEncoderInches() / 12.0;
  }

  public double getRightEncoderFeet() {
    return getRightEncoderInches() / 12.0;
  }

  public double getHeadingDegrees() {
    return -navx.getAngle();
  }

  public double getHeadingRadians() {
    return Math.toRadians(getHeadingDegrees());
  }

  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(getHeadingDegrees());
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public void curve(double speed, double rotation) {
    drive.curvatureDrive(speed, rotation, true);
  }

  public void arcade(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  public void setRawPercentOutput(double left, double right) {
    drive.tankDrive(-left, -right);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void periodic() {
    pose = m_odometry.update(getAngle(), Units.inchesToMeters(getLeftEncoderInches()), Units.inchesToMeters(getRightEncoderInches()));
  } 

  @Override
  protected void initDefaultCommand() {
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  private void brakeMode(CANSparkMax mc) {
    mc.setIdleMode(IdleMode.kBrake);
  }

}
