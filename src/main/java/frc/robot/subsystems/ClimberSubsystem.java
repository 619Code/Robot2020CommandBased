package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class ClimberSubsystem {
    CANSparkMax liftOne, liftTwo;

    public ClimberSubsystem() {
        liftOne = new CANSparkMax(RobotMap.CLIMB1, MotorType.kBrushless);
        liftTwo = new CANSparkMax(RobotMap.CLIMB2, MotorType.kBrushless);
    }

    public void extend() {
        while(true) {
            //liftOne.set();
            //liftTwo.set();
        }
    }

    public void ascend() {

    }
}