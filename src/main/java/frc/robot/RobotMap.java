/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  // Magazine
  public static int MAG_POS_SENSOR_lOADED = 0;
  public static int MAG_POS_SENSOR_1 = 1;
  public static int MAG_POS_SENSOR_2 = 2;
  public static int MAG_POS_SENSOR_3 = 3;
  public static int MAG_POS_SENSOR_4 = 4; 

  // Intake Stuff
  public static double INTAKE_MOTOR_PERCENTAGE = 50;
  public static double INTAKE_MOTOR_BELT = 0.3;

  // Talon ID
  public static final int INTAKE_MOTOR = -1;
  
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
  public static final double WHEEL_DIAMETER = 4;
  public static final double ENCODER_TICK_PER_REV = 6;
  public static final double MAX_AUTO_SPEED = 6;

  public static final int NEO_LIMIT = 45;
  public static final double DRIVE_RATIO = 6.87;

  public static final double MAX_VOLTAGE = 12;

  public static final double FEET_TO_V = 12.0/132.0;

  // PATHS
  
  // pid values
  public static double VEL_P = 0.2;
  public static double VEL_I = 0.0;
  public static double VEL_D = 0.0;

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
  public static final double TRACKWIDTH_INCHES = 21;

  // Max Trajectory Velocity/Acceleration
  public static final double MAX_ACCELERATION_FEET_PER_SECOND_SQUARED = 1.5;
  public static final double MAX_VELOCITY = 132; //i/s

  //Field constants
  public static final double TARGET_HEIGHT = 52;

  //Shooter constants
  public static final double MAX_SHOOTER_VELOCITY = -1;
  public static final double SHOOTER_X_OFFSET = -1;
  public static final double SHOOTER_Y_OFFSET = -1;
  public static final double LIMELIGHT_HEIGHT = 39;
  public static final double LIMELIGHT_ANGLE = Math.toRadians(-1);
}
