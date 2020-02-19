package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.hardware.LimitSwitch;
import frc.robot.helpers.SparkMaxSetPointHelper;

public class MagazineSubsystem extends Subsystem {
    CANSparkMax indexing;
    CANSparkMax loading;
    
    LimitSwitch feederSwitch;

    public DigitalInput pos0;
    public DigitalInput pos1;
    public DigitalInput pos2;
    public DigitalInput pos3;
    public DigitalInput pos4;

    DigitalInput[] positions;
    
    boolean isLoaded;

    public MagazineSubsystem() {
        indexing = new CANSparkMax(RobotMap.INDEXING_MOTOR, MotorType.kBrushless);
        indexing.setSmartCurrentLimit(35);
        loading = new CANSparkMax(RobotMap.LOADING_MOTOR, MotorType.kBrushless);
        loading.setSmartCurrentLimit(35);
        loading.setIdleMode(IdleMode.kCoast);
        pos0 = new DigitalInput(RobotMap.MAG_POS_FIRST);
        pos1 = new DigitalInput(RobotMap.MAG_POS_SECOND);
        pos2 = new DigitalInput(RobotMap.MAG_POS_LAST);
        pos3 = new DigitalInput(RobotMap.SHOOTER_POS);
        pos4 = new DigitalInput(RobotMap.FEEDER_POS);
        this.positions = new DigitalInput[] {pos0, pos1, pos2, pos3, pos4};        
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
        return !this.positions[index].get();
    }

    public void LoadChamber(double speed) {
        loading.set(speed);     
    }

    public void LoadMagazine(double speed) {
        indexing.set(speed);
    }

    public boolean isFilled() {
        return this.pos0.get() && 
            this.pos1.get() &&
            this.pos2.get() &&
            this.pos3.get() &&
            this.pos4.get();        
    }

    @Override
    protected void initDefaultCommand() {
    }
}
