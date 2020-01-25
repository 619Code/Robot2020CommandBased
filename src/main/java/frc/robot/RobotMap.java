/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  //Talon ID
  public static final int LEFTMASTER = 10;
  public static final int RIGHTMASTER = 13;
  public static final int LEFTSLAVE0 = 11;
  public static final int LEFTSLAVE1 = 12;
  public static final int RIGHTSLAVE0 = 14;
  public static final int RIGHTSLAVE1 = 15;

  // DRIVE
  public static final double WHEEL_DIAMETER = 4;
  public static final double ENCODER_TICK_PER_REV = 6;
  public static final double MAX_AUTO_SPEED = 6;

  public static final int NEO_LIMIT = 45;
  public static final double DRIVE_RATIO = 6.87;

  // PATHS
  // pid values
  public static final double HEADING_P = 0.01; //0.64
  public static final double HEADING_D = 0;
  public static final double HEADING_I = 0;

  public static final double DISTANCE_P = 0.5; //5
  public static final double DISTANCE_D = 0; //8
  public static final double DISTANCE_I = 0;

  // DifferentialDrive Kinematics
  public static final double kTrackwidthInches = 21;

  // Max Trajectory Velocity/Acceleration
  public static final double kMaxAccelerationFeetPerSecondSquared = 1.5;
}
