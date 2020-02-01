package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;
import frc.robot.hardware.LimitSwitch;

public class MagazineSubsystem {
    CANSparkMax indexing, loading, feeder1, feeder2;
    LimitSwitch feederSwitch;
    int ballsInMag, ballsInFeeder, ballsInShooter;

    public MagazineSubsystem() {
        indexing = new CANSparkMax(RobotMap.INDEXING, MotorType.kBrushless);
        loading = new CANSparkMax(RobotMap.LOADING, MotorType.kBrushless);
        feeder1 = new CANSparkMax(RobotMap.FEEDER1, MotorType.kBrushless);
        feeder2 = new CANSparkMax(RobotMap.FEEDER2, MotorType.kBrushless);
    }

    public void index() {
        //indexing.set();
        if(ballsInShooter == 0) {
            //call feed without loading
        } else if(ballsInFeeder == 0) {
            //stop the ball
        } else {
            //go to mag
        }
    }

    public void feed() {
        if(feederSwitch.get() == false) {
            //loading.set();
        } else {
            //feeder1.set();
            //feeder2.set();
        }
    }
}