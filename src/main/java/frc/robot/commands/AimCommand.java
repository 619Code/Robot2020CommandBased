/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;

public class AimCommand extends CommandBase {
  private ShiftingWCDSubsystem drive;
  private TargetInfo targetInfo;
  private ShooterSubsystem shooter;
  private PIDController targetPID;
  private Limelight limelight;

  /*public static NetworkTableEntry pVel, iVel, dVel;
  public static ShuffleboardTab tab;*/

  public AimCommand(ShiftingWCDSubsystem drive, Limelight limelight, ShooterSubsystem shooter) {
    this.drive = drive;
    this.limelight = limelight;
    this.shooter = shooter;
    targetInfo = limelight.GetTargetInfo();

    /* var p = SmartDashboard.putNumber("LimelightTarget P", RobotMap.TARGET_P);
    var i = SmartDashboard.putNumber("LimelightTarget I", RobotMap.TARGET_I);
    var d = SmartDashboard.putNumber("LimelightTarget D", RobotMap.TARGET_D); */
    
    targetPID = new PIDController(RobotMap.TARGET_P, RobotMap.TARGET_I, RobotMap.TARGET_D);    
    this.addRequirements(drive, shooter);
    
  }

  /*public static void initializeShuffleBoard() {
    tab = Shuffleboard.getTab("Aim Command");
    
    pVel = tab.add("vP", RobotMap.TARGET_P).getEntry();
    iVel = tab.add("vI", RobotMap.TARGET_I).getEntry();
    dVel = tab.add("vD", RobotMap.TARGET_D).getEntry();
  }*/

  @Override
  public void execute() {
    targetInfo = limelight.GetTargetInfo();

    //targetPID.setPID(this.pVel.getDouble(0), this.iVel.getDouble(0), this.dVel.getDouble(0));
    drive.curve(0,targetPID.calculate(targetInfo.getTargetX(), 0), false);
    shooter.setVelocity(500);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
