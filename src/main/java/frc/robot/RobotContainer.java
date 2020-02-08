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
import frc.paths.CirclePath;
import frc.paths.TestPath;
import frc.robot.commands.AimCommand;
import frc.robot.commands.CurveDriveCommand;
import frc.robot.commands.ExtendoContractCommand;
import frc.robot.commands.ExtendoExtendCommand;
import frc.robot.commands.PathFollowerCommand;
import frc.robot.helpers.HelixFollowerCommandWrapper;
import frc.robot.helpers.Limelight;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drive.ShiftingWCDSubsystem;

public class RobotContainer {
    private final ShiftingWCDSubsystem drive;
    //private final ClimberSubsystem climber;
    //private final ShooterSubsystem shooter;
    private final XboxController primaryJoystick, secondaryJoystick;
    //public final Limelight limelight;
    //private final IntakeSubsystem intakeSubsystem;

    public RobotContainer() {

        drive = new ShiftingWCDSubsystem();
        //climber = new ClimberSubsystem();
        //shooter = new ShooterSubsystem();
        primaryJoystick = new XboxController(0);
        secondaryJoystick = new XboxController(1);
        //limelight = new Limelight();
        //intakeSubsystem = new IntakeSubsystem();
        //intakeSubsystem = null;

        drive.setDefaultCommand(new CurveDriveCommand(drive, primaryJoystick)); //, limelight, shooter));

        ConfigureControllers();
        //AimCommand.initializeShuffleBoard();
    }

    public void ConfigureControllers() {
        // var turnButton = new JoystickButton(primaryJoystick, XboxController.Button.kA.value);
        // turnButton.toggleWhenPressed(new AimCommand(drive, limelight, shooter));
        // var intakeButton = new JoystickButton(secondaryJoystick, XboxController.Axis.kLeftTrigger.value);
        // intakeButton.toggleWhenPressed(new ExtendoExtendCommand(intakeSubsystem));
        // intakeButton.whenReleased(new ExtendoContractCommand(intakeSubsystem));
        // intakeSubsystem.setDefaultCommand(new ExtendoContractCommand(intakeSubsystem));
    }

    public Command getAutoCommand(){
        //return new AimCommand(drive, limelight, shooter); //new TestReverse(drive, limelight);
        return HelixFollowerCommandWrapper.wrap(new PathFollowerCommand(new TestPath(), this.drive, false, false));
    }
}