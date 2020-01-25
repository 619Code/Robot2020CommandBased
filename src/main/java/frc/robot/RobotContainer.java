/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.CurveDrive;
import frc.robot.commands.TestReverse;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ShiftingWCD;

public class RobotContainer {
    private final ShiftingWCD drive;
    private final Climber climber;
    private final XboxController primaryJoystick, secondaryJoystick;
    private final Limelight limelight;
    private final TargetInfo targetInfo;

    public RobotContainer() {

        drive = new ShiftingWCD();
        climber = new Climber();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        targetInfo = new TargetInfo();
        limelight = new Limelight();

        drive.setDefaultCommand(new CurveDrive(drive, primaryJoystick));

    }
    public Command getAutoCommand(){
        return new TestReverse(drive, limelight);
    }
}
