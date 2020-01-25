/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.paths.TestPath;
import frc.robot.commands.CurveDrive;
import frc.robot.commands.PathFollower;
import frc.robot.subsystems.ShiftingWCD;

public class RobotContainer {
    private final ShiftingWCD drive;
    private final Joystick primaryJoystick, secondaryJoystick;
    public RobotContainer(){
        drive = new ShiftingWCD();
        primaryJoystick = new Joystick(0);
        secondaryJoystick = new Joystick(1);

        drive.setDefaultCommand(new CurveDrive(drive, primaryJoystick));

    }
    public Command getAutoCommand(){
        return new PathFollower(new TestPath(), drive);
    }
}
