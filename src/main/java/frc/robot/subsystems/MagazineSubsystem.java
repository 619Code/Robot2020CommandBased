package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.hardware.LimitSwitch;
import frc.robot.helpers.SparkMaxSetPointHelper;

public class MagazineSubsystem extends Subsystem {
    SparkMaxSetPointHelper indexing;
    SparkMaxSetPointHelper loading;
    
    LimitSwitch feederSwitch;

    DigitalInput positionLoaded;
    DigitalInput position0;
    DigitalInput position1;
    DigitalInput position2;
    DigitalInput position3;

    DigitalInput[] positions;
    
    boolean isLoaded;

    public MagazineSubsystem() {
        indexing = new SparkMaxSetPointHelper(RobotMap.INDEXING, ControlType.kPosition);
        loading = new SparkMaxSetPointHelper(RobotMap.LOADING, ControlType.kPosition);
        positionLoaded = new DigitalInput(RobotMap.MAG_POS_SENSOR_lOADED);
        position0 = new DigitalInput(RobotMap.MAG_POS_SENSOR_1);
        position1 = new DigitalInput(RobotMap.MAG_POS_SENSOR_2);
        position2 = new DigitalInput(RobotMap.MAG_POS_SENSOR_3);
        position3 = new DigitalInput(RobotMap.MAG_POS_SENSOR_4);
        this.positions = new DigitalInput[] {position0, position1, position2,position3};        
    }

    public int nextEmptyIndex() {
        for (int i = 0; i < 4; i++) {
            if (this.positions[i].get() == false)
                return i;
        }

        //Indicates no free slots
        return -1;
    }

    public boolean HasBallAtIndex(int index)
    {
        return this.positions[index].get();
    }

    public void LoadChamber() {
        //Advanced loader motor
    }

    public void LoadMagazine() {
        //Advanced magazine motor
    }

    public boolean isFilled() {
        return this.position0.get() && 
            this.position1.get() &&
            this.position2.get() &&
            this.position3.get() &&
            this.isLoaded();        
    }

    public boolean isLoaded() {
        return this.positionLoaded.get();
    }
    @Override
    protected void initDefaultCommand() {
    }
}
