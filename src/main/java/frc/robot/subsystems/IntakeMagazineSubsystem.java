package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeMagazineSubsystem extends Subsystem {
    CANSparkMax indexing;
    CANSparkMax loading;
    private VictorSPX roller;
    private CANSparkMax intakeBelt;
    private DoubleSolenoid wrist;

    DigitalInput[] positions;
    
    public IntakeMagazineSubsystem() {

        // Back magazine belt holding 3 balls
        indexing = new CANSparkMax(RobotMap.INDEXING_MOTOR, MotorType.kBrushless);
        indexing.setSmartCurrentLimit(35);
        
        // Vertical loader
        loading = new CANSparkMax(RobotMap.LOADING_MOTOR, MotorType.kBrushless);
        loading.setSmartCurrentLimit(35);
        loading.setIdleMode(IdleMode.kCoast);
        
        // Magazine index diagram
        // [3]
        // [4][2][1][0]
        // Position sensors detecting balls
        this.positions = new DigitalInput[] {
            new DigitalInput(RobotMap.MAG_POS_FIRST),
            new DigitalInput(RobotMap.MAG_POS_SECOND),
            new DigitalInput(RobotMap.MAG_POS_LAST),
            new DigitalInput(RobotMap.SHOOTER_POS),
            new DigitalInput(RobotMap.FEEDER_POS)};
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

    // Load chamber.  Provice negative values to to 
    public void ChamberCrazyness(double speed) {
        loading.set(speed);
    }

    public void MagazineBelt(double speed) {
        indexing.set(speed);
    }

    public boolean isFilled() {
        for(DigitalInput pos : positions) {
            if(!pos.get()) {
                return false;
            }
        }
        return true;
    }

    public boolean IsMagazineFilled() {        
        for(int i = 0; i < 3; i++)
        {
            if(!positions[i].get()) {
                return false;
            }
        }
        return true;
    }

    public boolean IsChamberFilled() {
        return positions[3].get();
    }

    public boolean IsIntakePositionFilled() {
        return positions[4].get();
    }

    public void SpinIntake(double speed)
    {
        roller.set(ControlMode.PercentOutput, speed);
    }

    public void IntakeBelt(double speed)
    {        
        intakeBelt.set(speed);       
    }

    public void RaiseIntake() 
    {
        wrist.set(Value.kReverse);        
    }

    public void LowerIntake() 
    {
        wrist.set(Value.kForward);
    }


    @Override
    protected void initDefaultCommand() {
    }
}
