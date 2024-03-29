package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;
import frc.robot.helpers.BallIndex;

public class IntakeMagazineSubsystem extends Subsystem {
    CANSparkMax magazine;
    CANSparkMax loading;
    private VictorSPX roller;
    private CANSparkMax intakeBelt;
    private Solenoid wrist;
    private PIDController magazinePID;

    BallIndex[] positions;
    boolean isIntakeDown;
    boolean isDone = false;

    public IntakeMagazineSubsystem() {
        // Back magazine belt holding 3 balls
        magazine = new CANSparkMax(RobotMap.MAGAZINE_MOTOR, MotorType.kBrushless);
        magazine.restoreFactoryDefaults();
        magazine.setSecondaryCurrentLimit(35);
        magazine.setIdleMode(IdleMode.kBrake);
        
        magazinePID = new PIDController(RobotMap.MAG_P, RobotMap.MAG_I, RobotMap.MAG_D);

        // Vertical loader
        loading = new CANSparkMax(RobotMap.LOADING_MOTOR, MotorType.kBrushless);
        loading.restoreFactoryDefaults();
        loading.setSecondaryCurrentLimit(35);
        loading.setIdleMode(IdleMode.kCoast);

        roller = new VictorSPX(RobotMap.INTAKE_MOTOR);
        roller.configFactoryDefault();
        roller.setNeutralMode(NeutralMode.Coast);

        wrist = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_SOLENOID);

        intakeBelt = new CANSparkMax(RobotMap.BELT_MOTOR, MotorType.kBrushless);
        intakeBelt.restoreFactoryDefaults();
        intakeBelt.setSecondaryCurrentLimit(50);
        intakeBelt.setIdleMode(IdleMode.kCoast);

        // Magazine index diagram
        //   [4]
        // [5][3][2][1][0]
        // Position sensors detecting balls
        this.positions = new BallIndex[] { 
            new BallIndex(RobotMap.MAG_POS_END),
            new BallIndex(RobotMap.MAG_POS_MIDDLE),
            new BallIndex(RobotMap.MAG_POS_FIRST),
            new BallIndex(RobotMap.MAG_POS_LOW),
            new BallIndex(RobotMap.MAG_POS_HIGH), 
            new BallIndex(RobotMap.MAG_POS_PRE) };
    }

    //belts
    public void Loader(double speed) {
        loading.set(speed);
    }

    public void MagazineBelt(double speed) {
        magazine.set(speed);
    }

    //intake
    public void SpinIntake(double speed) {
        if (isIntakeDown)
            roller.set(ControlMode.PercentOutput, speed);
        else{
            roller.set(ControlMode.PercentOutput, 0);
        }
    }

    public void IntakeBelt(double speed) {
        intakeBelt.set(speed);
    }

    public void RaiseIntake() {
        isIntakeDown = false;
        wrist.set(false);
    }

    public void LowerIntake() {
        isIntakeDown = true;
        wrist.set(true);
    }

    //information
    public boolean HasBallAtIndex(int index) {
        return this.positions[index].hasBall();
    }

    public boolean isFilled() {
        if(!IsMagazineFilled()) {
            return false;
        } else if(!positions[RobotMap.MAG_POS_HIGH].hasBall()) {
            return false;
        } else if(positions[RobotMap.MAG_POS_LOW].hasBall() || positions[RobotMap.MAG_POS_PRE].hasBall()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean IsMagazineFilled() {
        /*for (int i = 0; i < 2; i++) {
            if (!positions[i].hasBall()) {
                return false;
            }
        }
        return true;*/
        return positions[RobotMap.MAG_POS_END].hasBall();
    }

    public boolean IsIntakePositionFilled() {
        return positions[4].hasBall() || positions[5].hasBall();
    }

    public boolean isEmpty() {
        for (int i = 0; i < 6; i++) {
            if (positions[i].hasBall()) {
                return false;
            }
        }
        return true;
    }

    //cycleback
    public void cycleback(){
        //magazine.set(magazinePID.calculate(magazine.getEncoder().getPosition(), 7*RobotMap.ROTATIONS_PER_INCH));
        magazine.set(0.8);
    }

    public boolean isCycleBackDone(){
        if(7*RobotMap.ROTATIONS_PER_INCH > magazine.getEncoder().getPosition()){
            isDone = false;
        }
        else {
            magazine.set(0);
            magazine.getEncoder().setPosition(0);
            isDone = true;
        }
        return isDone;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
