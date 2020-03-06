/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  // Talon ID
  public static final int INTAKE_MOTOR = 21;

  // Drive CANs
  public static final int LEFTMASTER = 10;
  public static final int RIGHTMASTER = 13;
  public static final int LEFTSLAVE0 = 11;
  public static final int LEFTSLAVE1 = 12;
  public static final int RIGHTSLAVE0 = 14;
  public static final int RIGHTSLAVE1 = 15;

  // CAN ID
  public static final int HOOK = 51;
  public static final int LIFT = 50;
  public static final int MAGAZINE_MOTOR = 30;
  public static final int BELT_MOTOR = 22;
  public static final int LOADING_MOTOR = 31;
  public static final int FEEDER1 = -1;
  public static final int FEEDER2 = -1;

  // Shooter
  public static final int SHOOTER_MOTOR_RIGHT = 41;
  public static final int SHOOTER_MOTOR_LEFT = 40;
  public static final int ANGLE_MOTOR = 42;

  public static final int ZEROSWITCH = 6;

  public static final double DEFAULT_ANGLE = 5.0;

  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;

  public static final double kGains_VelocitkF = 0.0;
  public static final double kGains_VelocitkP = 0.0;
  public static final double kGains_VelocitkI = 0.0;
  public static final double kGains_VelocitkD = 0.0;

  /**
   * PID Gains may have to be adjusted based on the responsiveness of control
   * loop. kF: 1023 represents output value to Talon at 100%, 7200 represents
   * Velocity units at 100% output
   * 
   * kP kI kD kF Iz PeakOut public final static Gains kGains_Velocit = new Gains(
   * 0.25, 0.001, 20, 1023.0/7200.0, 300, 1.00);
   */

  // Solenoids
  public static final int PCM_CAN_ID = 0;
  public static final int INTAKE_SOLENOID = 0;
  public static final int DRIVE_SOLENOID_FORWARD = 1;
  public static final int DRIVE_SOLENOID_BACK = 4;


  // Climb Constants
  public static final double PULLEY_RATIO = -1;
  public static final double GEAR_RATIO = -1;
  public static final double DISTANCE = -1;

  // DRIVE
  public static final double WHEEL_DIAMETER = 6;
  public static final double ENCODER_TICK_PER_REV = 6;
  public static final double MAX_AUTO_SPEED = 6;

  public static final int NEO_LIMIT = 45;
  public static final double DRIVE_RATIO = 8;

  public static final double SPEED_ADJUST = 1;

  // PATHS

  // pid values
  public static final double HEADING_P = 0.01; // 0.64
  public static final double HEADING_I = 0;
  public static final double HEADING_D = 0;

  public static final double DISTANCE_P = 0.5; // 5
  public static final double DISTANCE_I = 0; // 8
  public static final double DISTANCE_D = 0;

  // public static final double SHOOTER_P = 0.08;
  // public static final double SHOOTER_I = 0.025;
  // public static final double SHOOTER_D = 0.001;

  public static final double SHOOTER_P = 0.04;
  public static final double SHOOTER_I = 0.0025;
  public static final double SHOOTER_D = 0.001;

  public static final double ANGLE_P = -1;
  public static final double ANGLE_I = -1;
  public static final double ANGLE_D = -1;

  public static final double HOOK_P = -1;
  public static final double HOOK_I = -1;
  public static final double HOOK_D = -1;

  public static final double TARGET_P = 0.03; // 0.64 0.12
  public static final double TARGET_I = 0.0;
  public static final double TARGET_D = 0.001;

  // DifferentialDrive Kinematics
  public static final double kTrackwidthInches = 25;

  // Max Trajectory Velocity/Acceleration
  public static final double kMaxAccelerationFeetPerSecondSquared = 1.5;

  public static final double ANGLE_TO_TICK_RATIO = 1.0 / 2.0;
  public static final double TARGET_HEIGHT = 0;
  public static final double LIMELIGHT_HEIGHT = 0;
  public static final double LIMELIGHT_ANGLE = 0;


  // Magazine
  public static final int MAG_POS_FIRST = 2;
  public static final int MAG_POS_SECOND = 1;
  public static final int MAG_POS_LAST = 0;
  public static final int SHOOTER_POS = 4;
  public static final int FEEDER_POS = 3;
  public static final int PRE_MAG = 5;

  public static int ballCount = 0;

  // Field Elements
}
