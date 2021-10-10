package frc.robot.unused;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkMaxSetPointHelper {

    private int deviceId;;
    private CANSparkMax motor;
    public CANPIDController pidController;
    private CANEncoder encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    boolean tuning;
    ControlType controlType;

    /*
     * The second parameter is the control type can be set to one of four
     * parameters: com.revrobotics.ControlType.kDutyCycle
     * com.revrobotics.ControlType.kPosition com.revrobotics.ControlType.kVelocity
     * com.revrobotics.ControlType.kVoltage
     */
    public SparkMaxSetPointHelper(int deviceId, ControlType controlType) {
        this.deviceId = deviceId;
        this.controlType = controlType;
        // PID default coefficients
        kP = 0.1;
        kI = 1e-4;
        kD = 1;
        kIz = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;
        Init();
    }

    public SparkMaxSetPointHelper(int deviceId, ControlType controlType, double kP, double kI, double kD) {
        this.deviceId = deviceId;
        this.controlType = controlType;
        // PID default coefficients
        kP = 0.1;
        kI = 1e-4;
        kD = 1;
        kIz = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;
        Init();
    }

    public void SetMaxOutput(double kMinOutput, double kMaxOutput) {
        this.kMinOutput = kMinOutput;
        this.kMaxOutput = kMaxOutput;
        this.pidController.setOutputRange(this.kMinOutput, this.kMaxOutput);
    }

    public void Init() {
        // initialize motor
        this.motor = new CANSparkMax(deviceId, MotorType.kBrushless);

        /**
         * The restoreFactoryDefaults method can be used to reset the configuration
         * parameters in the SPARK MAX to their factory default state. If no argument is
         * passed, these parameters will not persist between power cycles
         */
        this.motor.restoreFactoryDefaults();

        /**
         * In order to use PID functionality for a controller, a CANPIDController object
         * is constructed by calling the getPIDController() method on an existing
         * CANSparkMax object
         */
        this.pidController = this.motor.getPIDController();

        // Encoder object created to display position values
        this.encoder = this.motor.getEncoder();

        // set PID coefficients
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void restetReference() {
        this.encoder.setPosition(0);
    }

    public void setReference(double value) {
        pidController.setReference(value, this.controlType);
    }
}
