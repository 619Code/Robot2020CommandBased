package frc.robot.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.helpers.SparkMaxDriveMotors;

public class ShiftingWCDSubsystem extends SubsystemBase {

  //CANSparkMax leftMaster, leftSlavCatsparagus e0, leftSlave1, rightMaster, rightSlave0, rightSlave1;
  CavalierDrive drive;
  DoubleSolenoid shifter;
  SparkMaxDriveMotors leftMotors;
  SparkMaxDriveMotors rightMotors;

  AHRS navx;
  //CANEncoder leftEncoder, rightEncoder;
  DifferentialDriveKinematics m_kinematics;
  DifferentialDriveOdometry m_odometry;
  Pose2d pose;

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  public ShiftingWCDSubsystem() {
    initMotors();
    initDrive();
    initSensors();
    shifter = new DoubleSolenoid(0, 1);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////

  private void initMotors() {
    leftMotors = new SparkMaxDriveMotors(10, 11, 12);
    rightMotors = new SparkMaxDriveMotors(13, 14, 15);
  }
  

  private void initDrive() {
    drive = new CavalierDrive(this.leftMotors.getMasterMotor(), this.rightMotors.getMasterMotor());
    drive.setSafetyEnabled(false);
  }

  private void initSensors() {
    navx = new AHRS(SPI.Port.kMXP);
    //leftEncoder = leftMaster.getEncoder();
    //rightEncoder = rightMaster.getEncoder();
    resetGyro();
    resetEncoders();
    m_kinematics= new DifferentialDriveKinematics(Units.inchesToMeters(RobotMap.TRACKWIDTH_INCHES));
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

  public void curve(double speed, double rotation, boolean state) {
    drive.curvatureVelDrive(0.5*speed, 0.5*rotation, true);
    setShift(state);
  }

  public void arcade(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  public void setRawPercentOutput(double left, double right) {
    drive.tankDrive(-left, -right);
  }

  public void setShift(boolean state){
    if(state){
      shifter.set(Value.kForward);
    }
    else{
      shifter.set(Value.kReverse);
    }
  }
  
    
  //////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public void periodic() {
    pose = m_odometry.update(getAngle(), Units.inchesToMeters(getLeftEncoderInches()), Units.inchesToMeters(getRightEncoderInches()));
  } 

  //////////////////////////////////////////////////////////////////////////////////////////////////////

}
