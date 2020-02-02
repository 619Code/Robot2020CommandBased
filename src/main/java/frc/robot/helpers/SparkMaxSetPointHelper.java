package frc.robot.helpers;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SparkMaxSetPointHelper {

    public String Name;
    private int deviceId;;
    private CANSparkMax motor;
    public CANPIDController pidController;
    private CANEncoder encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    boolean tuning;
    ControlType controlType;
    
    /*
    * The second parameter is the control type can be set to one of four 
    * parameters:
    *  com.revrobotics.ControlType.kDutyCycle
    *  com.revrobotics.ControlType.kPosition
    *  com.revrobotics.ControlType.kVelocity
    *  com.revrobotics.ControlType.kVoltage
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

    public SparkMaxSetPointHelper(int deviceId, ControlType controlType, double kP, double kI, double kD)
    {
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

    public void SetMaxOutput(double kMinOutput, double kMaxOutput)
    {
        this.kMinOutput = kMinOutput;
        this.kMaxOutput = kMaxOutput;
        this.pidController.setOutputRange(this.kMinOutput, this.kMaxOutput);
    }

    public void Init() {
        // initialize motor
        this.motor = new CANSparkMax(deviceId, MotorType.kBrushless);

        /**
         * The restoreFactoryDefaults method can be used to reset the configuration parameters
         * in the SPARK MAX to their factory default state. If no argument is passed, these
         * parameters will not persist between power cycles
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

    public void ShowOnDashboard() {
        this.tuning = true;
        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber(this.getDisplayName("P Gain"), kP);
        SmartDashboard.putNumber(this.getDisplayName("I Gain"), kI);
        SmartDashboard.putNumber(this.getDisplayName("D Gain"), kD);
        SmartDashboard.putNumber(this.getDisplayName("I Zone"), kIz);
        SmartDashboard.putNumber(this.getDisplayName("Feed Forward"), kFF);
        SmartDashboard.putNumber(this.getDisplayName("Max Output"), kMaxOutput);
        SmartDashboard.putNumber(this.getDisplayName("Min Output"), kMinOutput);
        SmartDashboard.putNumber(this.getDisplayName("Set " + this.controlType.toString()), 0);
    }

    private String getDisplayName(String itemName)
    {
        return Name + " / " + itemName;
    }

    public void UpdateFromDashboard(boolean includeMovement) {
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber(this.getDisplayName("P Gain"), 0);
        double i = SmartDashboard.getNumber(this.getDisplayName("I Gain"), 0);
        double d = SmartDashboard.getNumber(this.getDisplayName("D Gain"), 0);
        double iz = SmartDashboard.getNumber(this.getDisplayName("I Zone"), 0);
        double ff = SmartDashboard.getNumber(this.getDisplayName("Feed Forward"), 0);
        double max = SmartDashboard.getNumber(this.getDisplayName("Max Output"), 0);
        double min = SmartDashboard.getNumber(this.getDisplayName("Min Output"), 0);
        double rotations = SmartDashboard.getNumber("Set " + this.controlType.toString(), 0);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { 
            pidController.setP(p); 
            kP = p;        
        }

        if((i != kI)) { 
            pidController.setI(i); 
            kI = i;        
        }

        if((d != kD)) { 
            pidController.setD(d); 
            kD = d; 
        }
        if((iz != kIz)) { 
            pidController.setIZone(iz); 
            kIz = iz; 
        }

        if((ff != kFF)) { 
            pidController.setFF(ff); kFF = ff; 
        }
        
        if((max != kMaxOutput) || (min != kMinOutput)) { 
            pidController.setOutputRange(min, max); 
            kMinOutput = min; kMaxOutput = max; 
        }

         /**
         * PIDController objects are commanded to a set point using the 
         * SetReference() method.
         * 
         * The first parameter is the value of the set point, whose units vary
         * depending on the control type set in the second parameter.
         **/
        pidController.setReference(rotations, this.controlType);
    }

    public void setReference(double value)
    {
        pidController.setReference(value, this.controlType);
    }
}

