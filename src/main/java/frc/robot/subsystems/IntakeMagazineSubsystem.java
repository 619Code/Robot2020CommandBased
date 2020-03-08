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
        magazine.setIdleMode(IdleMode.kCoast);
        
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
        // [3]
        // [4][2][1][0]
        // Position sensors detecting balls
        this.positions = new BallIndex[] { new BallIndex(RobotMap.MAG_POS_FIRST),
                new BallIndex(RobotMap.MAG_POS_SECOND), new BallIndex(RobotMap.MAG_POS_LAST),
                new BallIndex(RobotMap.SHOOTER_POS), new BallIndex(RobotMap.FEEDER_POS),
                new BallIndex(RobotMap.PRE_MAG) };

        // It needs to be noted that a sensor that sees the ball reads 0 and a sensor
        // that does not see a ball reads
    }

    public void LogDigitalInputs() {

    }

    public int nextEmptyIndex() {
        for (int i = 0; i < 4; i++) {
            if (this.positions[i].hasBall() == false)
                return i;
        }
        // Indicates no free slots
        return -1;
    }

    public boolean HasBallAtIndex(int index) {
        return this.positions[index].hasBall();
    }

    // Load chamber. Provice negative values to to
    public void Loader(double speed) {
        loading.set(speed);
    }

    public void MagazineBelt(double speed) {
        magazine.set(speed);
    }

    public boolean isFilled() {

        // Return false if ball is not in position 0,1,2,3
        for (int i = 0; i < 4; i++) {
            if (!positions[i].hasBall()) {
                return false;
            }
        }

        // If either the intake position sensors are reading positive consider the
        // system
        // full
        if (positions[4].hasBall() || positions[5].hasBall())
            return true;
        else
            return false;
    }

    public void cycleback(){
        magazine.set(magazinePID.calculate(magazine.getEncoder().getPosition(), 7*RobotMap.ROTATIONS_PER_INCH));
    }

    public boolean isCycleBackDone(){
        if(Math.abs(7*RobotMap.ROTATIONS_PER_INCH-magazine.getEncoder().getPosition())>0.1){
            isDone = false;
        }
        else {
            magazine.getEncoder().setPosition(0);
            isDone = true;
        }
        return isDone;
    }

    public boolean IsMagazineFilled() {
        for (int i = 0; i < 3; i++) {
            if (!positions[i].hasBall()) {
                return false;
            }
        }
        return true;
    }

    public boolean IsMagazineOccupied() {
        /* for (int i = 0; i < 3; i++) {
            if (positions[i].hasBall()) {
                return true;
            }
        }
        return false; */
        if (positions[3].hasBall()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean IsIntakePositionFilledForShooting() {
        return positions[5].hasBall();
    }

    public boolean IsIntakePositionFilled() {
        return positions[4].hasBall() || positions[5].hasBall();
    }

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
        isIntakeDown= false;
        wrist.set(false);
    }

    public void LowerIntake() {
        isIntakeDown= true;
        wrist.set(true);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public boolean isEmpty() {
        for (int i = 0; i < 6; i++) {
            if (positions[i].hasBall()) {
                return false;
            }
        }
        return true;
    }
}
