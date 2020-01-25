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
import frc.robot.helpers.SparkMaxDriveMotors;

public class ShiftingWCD extends Subsystem {

  //CANSparkMax leftMaster, leftSlave0, leftSlave1, rightMaster, rightSlave0, rightSlave1;
  DifferentialDrive drive;
  DoubleSolenoid shifter;
  SparkMaxDriveMotors leftMotors;
  SparkMaxDriveMotors rightMotors;

  AHRS navx;
  //CANEncoder leftEncoder, rightEncoder;
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

    leftMotors = new SparkMaxDriveMotors(10, 11, 12);
    rightMotors = new SparkMaxDriveMotors(13, 14, 15);

  }

  private void initDrive() {
    drive = new DifferentialDrive(this.leftMotors.getMasterMotor(), this.rightMotors.getMasterMotor());
    drive.setSafetyEnabled(false);
  }

  private void initSensors() {
    navx = new AHRS(SPI.Port.kMXP);
    //leftEncoder = leftMaster.getEncoder();
    //rightEncoder = rightMaster.getEncoder();
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
    this.leftMotors.ResetEncoder();
    this.rightMotors.ResetEncoder();
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public double getLeftEncoderInches() {

    return this.leftMotors.getEncoderDistanceInInches(true);
  }

  public double getRightEncoderInches() {
    return this.rightMotors.getEncoderDistanceInInches(false);
  }

  public double getLeftEncoderFeet() {
    return this.leftMotors.getEncoderDistanceInFeet(true);
  }

  public double getRightEncoderFeet() {
    return this.rightMotors.getEncoderDistanceInFeet(false);
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

}
