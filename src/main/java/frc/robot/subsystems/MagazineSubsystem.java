package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;
import frc.robot.hardware.LimitSwitch;

public class MagazineSubsystem {
    CANSparkMax indexing, loading;
    LimitSwitch feederSwitch;

    DigitalInput positionLoaded;
    DigitalInput position1;
    DigitalInput position2;
    DigitalInput position3;
    DigitalInput position4;
    
    boolean isLoaded;

    public MagazineSubsystem() {
        indexing = new CANSparkMax(RobotMap.INDEXING, MotorType.kBrushless);
        loading = new CANSparkMax(RobotMap.LOADING, MotorType.kBrushless);
        positionLoaded = new DigitalInput(RobotMap.MAG_POS_SENSOR_lOADED);
        position1 = new DigitalInput(RobotMap.MAG_POS_SENSOR_1);
        position2 = new DigitalInput(RobotMap.MAG_POS_SENSOR_2);
        position3 = new DigitalInput(RobotMap.MAG_POS_SENSOR_3);
        position4 = new DigitalInput(RobotMap.MAG_POS_SENSOR_4);        
    }

    public void indexerMoveBallIn() {

    }

    public void indexerMoveBallOut() {

    }

    public boolean isFilled() {
        //return position1.
        return false;
    }




    // public void index() {
    //     //indexing.set();
    //     if(ballsInShooter == 0) {
    //         //call feed without loading
    //     } else if(ballsInFeeder == 0) {
    //         //stop the ball
    //     } else {
    //         //go to mag
    //     }
    // }

    // public void feed() {
    //     if(feederSwitch.get() == false) {
    //         //loading.set();
    //     } else {
    //         //feeder1.set();
    //         //feeder2.set();
    //     }
    // }
}

