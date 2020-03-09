/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.IntakeMagazineSubsystem;

public class GatherBallsV2Command extends Command {
  private IntakeMagazineSubsystem imSubsystem;
  private boolean latch = false;
  private boolean cyclelatch = false; 

  public GatherBallsV2Command(IntakeMagazineSubsystem imSubsystem) {
    this.imSubsystem = imSubsystem;
    requires(imSubsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double loaderSpeed = 0.8;
    double intakeBeltSpeed = 0.8;
    double intakeSpeed = 0.55;
    if (!imSubsystem.IsMagazineFilled() || cyclelatch) {
      //if mag not full or cycle latch not done
      imSubsystem.isCycleBackDone();
      //update position
      if (imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW) || cyclelatch) {
        //if at low pos or cycle still in progress
        cyclelatch = !imSubsystem.isCycleBackDone();
        //update latch if not done make true
        imSubsystem.cycleback();
        //cycle mag back
      }

    } else if (!imSubsystem.isFilled()) {
      // if entire system not full
      cyclelatch = false;
      // fallback if cycle never resets
      if (imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_HIGH)) {
        // if ball is at top pos load bottom
        loaderSpeed = 0;
        intakeBeltSpeed = 0.8;
        intakeSpeed = 0.55;
        latch = false;

      } else {
        // if mag ball not at top
        if ((imSubsystem.HasBallAtIndex(RobotMap.MAG_POS_LOW) || latch)) {
          // if the low pos is true load ball up and set latch.
          latch = true;
          loaderSpeed = -0.8;
          intakeBeltSpeed = 0.8;
          intakeSpeed = 0.55;
        } else {
          //load ball in until the low pos is triggered
          loaderSpeed = 0.8;
          intakeBeltSpeed = 0.8;
          intakeSpeed = 0.55;
        }
      }
    } else if(imSubsystem.isFilled()){
      // stop system if full
      loaderSpeed = 0.0;
      intakeBeltSpeed = 0.0;
      intakeSpeed = 0.0;
      this.imSubsystem.RaiseIntake();
    }
    //set speeds
    imSubsystem.Loader(loaderSpeed);
    imSubsystem.IntakeBelt(intakeBeltSpeed);
    imSubsystem.SpinIntake(intakeSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
