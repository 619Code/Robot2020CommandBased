package frc.robot.auto;

import com.team2363.commands.HelixFollower;
import com.team2363.controller.PIDController;
import com.team319.trajectory.Path;

import frc.robot.RobotMap;
import frc.robot.States;
import frc.robot.helpers.states.ERobotState;
import frc.robot.subsystems.ShiftingWCDSubsystem;

public class PathFollower extends HelixFollower {
    private ShiftingWCDSubsystem drive;
    
    private PIDController headingController = new PIDController(RobotMap.HEADING_P, RobotMap.HEADING_I,
            RobotMap.HEADING_D, 0.001);
    private PIDController distanceController = new PIDController(RobotMap.DISTANCE_P, RobotMap.DISTANCE_I,
            RobotMap.DISTANCE_D, 0.001);

    public PathFollower(Path path, ShiftingWCDSubsystem drive, boolean isReverse, boolean isMirror) {
        super(path);
        if (isReverse) reverse();
        if (isMirror) mirror();
        //mirror();
        this.drive = drive;
        requires(drive);
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
    
    @Override
    public boolean isFinished() {
        boolean baseResult = super.isFinished();
        return baseResult || !(States.RobotState == ERobotState.Auto);
    }
}