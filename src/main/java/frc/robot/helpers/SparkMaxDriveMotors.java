package frc.robot.helpers;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotMap;

public class SparkMaxDriveMotors 
{
    public CANSparkMax[] motors;  
    public CANEncoder encoder;
    public PIDController motorPID;
    public boolean isLowGear = false;

    public CANSparkMax getMasterMotor() {
        return motors[0];
    } 

    public SparkMaxDriveMotors(final int canId1, final int canId2, final int canId3) {
        motors = new CANSparkMax[3];
        motors[0] = CreateNeoSparkMax(canId1);
        motors[1] = CreateNeoSparkMax(canId2);
        motors[2] = CreateNeoSparkMax(canId3);

        motors[1].follow(motors[0]);
        motors[2].follow(motors[0]);

        motorPID = new PIDController(RobotMap.VEL_P, RobotMap.VEL_P, RobotMap.VEL_D);

        this.encoder = this.motors[0].getEncoder();
    }

    private CANSparkMax CreateNeoSparkMax(final int canId) {
        CANSparkMax sparkMax = new CANSparkMax(canId, MotorType.kBrushless);
        sparkMax.setIdleMode(IdleMode.kBrake);
        sparkMax.setSmartCurrentLimit(RobotMap.NEO_LIMIT);
        return sparkMax;
    }

    public void setIdleMode(IdleMode mode)
    {
        for (CANSparkMax canSparkMax : motors) {
            canSparkMax.setIdleMode(mode);            
        }                
    }

    public void velControl(double targetVel) {

    }

    public CANEncoder getEncoder() {
        return this.encoder;
    }

    public double getDriveRatio() {
        return this.isLowGear ? RobotMap.LG_DRIVE_RATIO : RobotMap.HG_DRIVE_RATIO;
     }

    public double getWheelSpeedInInchesPerSecond() {
        return this.encoder.getVelocity()/this.getDriveRatio() * RobotMap.WHEEL_DIAMETER/60;
    }

    public double getEncoderDistanceInInches(boolean inverse) {
        var modifier = inverse ? -1 : 1;
        return modifier * (encoder.getPosition()/this.getDriveRatio())*(RobotMap.WHEEL_DIAMETER*Math.PI);
    }

    public double getEncoderDistanceInFeet(boolean inverse) {
        return this.getEncoderDistanceInInches(inverse);
    }

    public void ResetEncoder() {
        this.encoder.setPosition(0);
    }
} 