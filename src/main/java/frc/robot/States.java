package frc.robot;

import frc.robot.helpers.states.EGatheringState;
import frc.robot.helpers.states.ERobotState;
import frc.robot.helpers.states.EShootState;

//This tracks the current state of each robot part
public class States {

    public static ERobotState RobotState;
    public static EGatheringState GatheringState;
    public static EShootState ShooterState;
    public static boolean isShooting = false;
    /* paste this in robotPeriodic()
    States.IS_AUTO = isAutonomous();
    States.IS_TELE = isOperatorControl();
    */
}