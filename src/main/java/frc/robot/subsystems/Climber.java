package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class Climber {
    CANSparkMax liftOne, liftTwo;

    public Climber() {
        liftOne = new CANSparkMax(RobotMap.CLIMB1, MotorType.kBrushless);
        liftTwo = new CANSparkMax(RobotMap.CLIMB2, MotorType.kBrushless);
    }

    public void extend() {
        //liftOne.set();
        //liftTwo.set();
    }

    public void ascend() {

    }
}