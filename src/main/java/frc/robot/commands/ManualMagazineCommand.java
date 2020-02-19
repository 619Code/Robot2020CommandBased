package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.MagazineSubsystem;

public class ManualMagazineCommand extends Command {
  private MagazineSubsystem magazine;
  private XboxController joystick;
  private double speed,speed2;
  public ManualMagazineCommand(MagazineSubsystem magazine, XboxController joystick) {
    this.magazine = magazine;
    this.joystick = joystick;
    requires(magazine);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    System.out.println(magazine.HasBallAtIndex(0));
    System.out.println(magazine.HasBallAtIndex(1));
    System.out.println(magazine.HasBallAtIndex(2));
    System.out.println(magazine.HasBallAtIndex(3));
    System.out.println("---------------");
    if(joystick.getTriggerAxis(Hand.kLeft) > 0.5) {
      magazine.LoadChamber(0.6);
    } else if(joystick.getBumper(Hand.kLeft)) {
      magazine.LoadChamber(-1);
    } else {
      magazine.LoadChamber(0);
    }
    //try {
    if(joystick.getTriggerAxis(Hand.kRight) > 0.5) {
      //Thread.sleep(5000);
      magazine.LoadMagazine(0.5);
    } else if(joystick.getBumper(Hand.kRight)) {
      //Thread.sleep(5000);
      magazine.LoadMagazine(-0.5);
    } else {
      magazine.LoadMagazine(0);
    }
    /*} catch(InterruptedException ie) {
    }*/
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
