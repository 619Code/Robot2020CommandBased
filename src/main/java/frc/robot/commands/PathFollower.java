package frc.robot.commands;

import com.team2363.commands.HelixFollower;
import com.team2363.controller.PIDController;
import com.team319.trajectory.Path;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.helpers.HelixFollowerAltBase;
import frc.robot.subsystems.ShiftingWCD;


public class PathFollower extends HelixFollowerAltBase {
    private ShiftingWCD drive;
    
    private PIDController headingController = new PIDController(RobotMap.HEADING_P, RobotMap.HEADING_I,
            RobotMap.HEADING_D, 0.001);
    private PIDController distanceController = new PIDController(RobotMap.DISTANCE_P, RobotMap.DISTANCE_I,
            RobotMap.DISTANCE_D, 0.001);

    public PathFollower(Path path, ShiftingWCD drive, boolean isReverse, boolean isMirror) {
        super(path);
        if (isReverse) reverse();
        if (isMirror) mirror();
        //mirror();
        this.drive = drive;        
    }

    @Override
    public void resetDistance() {
        drive.resetEncoders();
    }

    @Override
    public PIDController getHeadingController() {
        return headingController;
    }

    @Override
    public PIDController getDistanceController() {
        return distanceController;
    }

    @Override
    public double getCurrentDistance() {
        return (drive.getRightEncoderFeet() + drive.getLeftEncoderFeet()) / 2.0;
    }

    @Override
    public double getCurrentHeading() {
        return Math.toRadians(drive.getHeadingDegrees());
    }

    @Override
    public void useOutputs(double left, double right) {
        drive.setRawPercentOutput(left / RobotMap.MAX_AUTO_SPEED, right / RobotMap.MAX_AUTO_SPEED);
    }
}