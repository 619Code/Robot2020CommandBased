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
  public static final int CLIMB1 = -1;
  public static final int CLIMB2 = -1;
  public static final int INDEXING = -1;
  public static final int LOADING = -1;
  public static final int FEEDER1 = -1;
  public static final int FEEDER2 = -1;
  public static final int SHOOTER_MOTOR = -1;

  //Solenoids
  public static final int PCM_CAN_ID = -1;
  public static final int[] INTAKE_WRIST_CHANNEL = {-1, -1};

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

  // PATHS
  
  // pid values
  public static final double HEADING_P = 0.01; //0.64
  public static final double HEADING_I = 0;
  public static final double HEADING_D = 0;

  public static final double DISTANCE_P = 0.5; //5
  public static final double DISTANCE_I = 0; //8
  public static final double DISTANCE_D = 0;

  public static final double SHOOTER_P = -1;
  public static final double SHOOTER_I = -1;
  public static final double SHOOTER_D = -1;

  public static final double ANGLE_P = -1;
  public static final double ANGLE_I = -1;
  public static final double ANGLE_D = -1;

  public static final double TARGET_P = 0.12; //0.64
  public static final double TARGET_I = 0;
  public static final double TARGET_D = 1.2;

  // DifferentialDrive Kinematics
  public static final double kTrackwidthInches = 25;

  // Max Trajectory Velocity/Acceleration
  public static final double kMaxAccelerationFeetPerSecondSquared = 1.5;
}
