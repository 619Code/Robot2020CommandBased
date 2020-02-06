/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.ReverseCommand;
import frc.robot.commands.AimCommand;
import frc.robot.helpers.Limelight;
import frc.robot.helpers.TargetInfo;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;

public class RobotContainer {
    private final ShiftingWCDSubsystem drive;
    private final ClimberSubsystem climber;
    private final XboxController primaryJoystick, secondaryJoystick;
    public final Limelight limelight;

    public RobotContainer() {

        drive = new ShiftingWCDSubsystem();
        climber = new ClimberSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        limelight = new Limelight();

        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick, limelight));

        ConfigureControllers();
        AimCommand.initializeShuffleBoard();
    }

    public void ConfigureControllers() {
        var turnButton = new JoystickButton(primaryJoystick, XboxController.Button.kA.value);
        turnButton.toggleWhenPressed(new AimCommand(drive, limelight));
        //var intakeButton = new JoystickButton(secondaryJoystick, XboxController.Axis.kLeftTrigger.value);
        //intakeButton.toggleWhenPressed(new ExtendoExtendCommand(intakeSubsystem));
        //intakeButton.whenReleased(new ExtendoContractCommand(intakeSubsystem));
    }

    public Command getAutoCommand(){
        return new AimCommand(drive, limelight); //new TestReverse(drive, limelight);
    }
}
