package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.helpers.BallIndex;

public class IntakeMagazineSubsystem extends Subsystem {
    CANSparkMax indexing;
    CANSparkMax loading;
    private VictorSPX roller;
    private CANSparkMax intakeBelt;
    private Solenoid wrist;

    BallIndex[] positions;
    
    public IntakeMagazineSubsystem() {

        // Back magazine belt holding 3 balls
        indexing = new CANSparkMax(RobotMap.INDEXING_MOTOR, MotorType.kBrushless);
        indexing.restoreFactoryDefaults();
        indexing.setSecondaryCurrentLimit(35);
        
        // Vertical loader
        loading = new CANSparkMax(RobotMap.LOADING_MOTOR, MotorType.kBrushless);
        loading.restoreFactoryDefaults();
        loading.setSecondaryCurrentLimit(35);
        loading.setIdleMode(IdleMode.kCoast);

        roller = new VictorSPX(RobotMap.INTAKE_MOTOR);
        roller.configFactoryDefault();
        roller.setNeutralMode(NeutralMode.Brake);

        wrist = new Solenoid(RobotMap.INTAKE_SOLENOID);

        intakeBelt = new CANSparkMax(RobotMap.BELT_MOTOR, MotorType.kBrushless);
        intakeBelt.restoreFactoryDefaults();
        intakeBelt.setSecondaryCurrentLimit(50);
        
        // Magazine index diagram
        // [3]
        // [4][2][1][0]
        // Position sensors detecting balls
        this.positions = new BallIndex[] {
            new BallIndex(RobotMap.MAG_POS_FIRST),
            new BallIndex(RobotMap.MAG_POS_SECOND),
            new BallIndex(RobotMap.MAG_POS_LAST),
            new BallIndex(RobotMap.SHOOTER_POS),
            new BallIndex(RobotMap.FEEDER_POS),
            new BallIndex(RobotMap.PRE_MAG)
        };

        // It needs to be noted that a sensor that sees the ball reads 0 and a sensor that does not see a ball reads
    }

    public void LogDigitalInputs()
    {

    }

    public int nextEmptyIndex() {
        for (int i = 0; i < 4; i++) {
            if (this.positions[i].hasBall() == false)
                return i;
        }
        //Indicates no free slots
        return -1;
    }

    public boolean HasBallAtIndex(int index)
    {
        return this.positions[index].hasBall();
    }

    // Load chamber.  Provice negative values to to 
    public void Loader(double speed) {
        loading.set(speed);
    }

    public void MagazineBelt(double speed) {
        indexing.set(speed);
    }

    public boolean isFilled() {
        for(int i = 0; i < 5; i++)
        {
            if(!positions[i].hasBall()) {
                return false;
            }
        }
        return true;
    }

    public boolean IsMagazineFilled() {        
        for(int i = 0; i < 3; i++)
        {
            if(!positions[i].hasBall()) {
                return false;
            }
        }
        return true;
    }

    public boolean IsMagazineOccupied() {        
        for(int i = 0; i < 3; i++)
        {
            if(positions[i].hasBall()) {
                return true;
            }
        }
        return false;
    }

    public boolean IsIntakePositionFilled() {
        return positions[4].hasBall();
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
        wrist.set(false);        
    }

    public void LowerIntake() 
    {
        wrist.set(true);
    }


    @Override
    protected void initDefaultCommand() {
    }

	public boolean isEmpty() {
		for(int i = 0; i < 5; i++)
        {
            if(positions[i].hasBall()) {
                return false;
            }
        }
        return true;
	}
}
