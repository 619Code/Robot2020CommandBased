package frc.robot.helpers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// For velocity 4096 Units/Rev * 500 RPM / 600 100ms

public class TalonSRXClosedLoopControllerHelper {

    public String name;
    private int deviceId;
    private TalonSRX motor;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    boolean tuning;
    ControlMode controlMode;
    private int kPIDLoopIdx;
    
    /**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;
    
    public TalonSRXClosedLoopControllerHelper(String name, int deviceId, ControlMode controlMode) {
        this.deviceId = deviceId;
        this.controlMode = controlMode;
        this.name = name; 
        // PID default coefficients
        kP = 0; 
        kI = 0;
        kD = 0; 
        //kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        Init();        
    }

    public TalonSRXClosedLoopControllerHelper(String name, int deviceId, ControlMode controlMode, double kP, double kI, double kD)
    {
        this.name = name;
        this.deviceId = deviceId;
        this.controlMode = controlMode;
        // PID default coefficients
        this.kP = kP; 
        this.kI = kI;
        this.kD = kD; 
        //kIz = 0; 
        kFF = 0; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        Init();
    }

    public void setMaxOutput(double kMinOutput, double kMaxOutput)
    {
        this.kMinOutput = kMinOutput;
        this.kMaxOutput = kMaxOutput;
        motor.configPeakOutputForward(this.kMaxOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		motor.configPeakOutputReverse(this.kMinOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);        
    }

    public void Init() {
        // initialize motor
        this.motor = new TalonSRX(this.deviceId);
        this.motor.configFactoryDefault();

        /* Config sensor used for Primary PID [Velocity] */
        this.motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                                            this.kPIDLoopIdx, 
                                            TalonSRXClosedLoopControllerHelper.kTimeoutMs);

        /**
		 * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
		this.motor.setSensorPhase(true);

		/* Config the peak and nominal outputs */
		this.motor.configNominalOutputForward(0, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configNominalOutputReverse(0, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configPeakOutputForward(this.kMaxOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configPeakOutputReverse(this.kMinOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		this.motor.config_kF(this.kPIDLoopIdx, this.kFF, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kP(this.kPIDLoopIdx, this.kP, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kI(this.kPIDLoopIdx, this.kI, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kD(this.kPIDLoopIdx, this.kD, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
    }    

    public void showOnDashboard() {
        this.tuning = true;
        // display PID coefficients on SmartDashboard
        SmartDashboard.putNumber(this.getDisplayName("P Gain"), kP);
        SmartDashboard.putNumber(this.getDisplayName("I Gain"), kI);
        SmartDashboard.putNumber(this.getDisplayName("D Gain"), kD);
        //SmartDashboard.putNumber(this.getDisplayName("I Zone"), kIz);
        SmartDashboard.putNumber(this.getDisplayName("Feed Forward"), kFF);
        SmartDashboard.putNumber(this.getDisplayName("Max Output"), kMaxOutput);
        SmartDashboard.putNumber(this.getDisplayName("Min Output"), kMinOutput);
        SmartDashboard.putNumber(this.getDisplayName("Set " + this.controlMode.toString()), 0);
    }

    private String getDisplayName(String itemName)
    {
        return this.name + " / " + itemName;
    }

    public void updateFromDashboard(boolean includeMovement) {
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber(this.getDisplayName("P Gain"), 0);
        double i = SmartDashboard.getNumber(this.getDisplayName("I Gain"), 0);
        double d = SmartDashboard.getNumber(this.getDisplayName("D Gain"), 0);
        //double iz = SmartDashboard.getNumber(this.getDisplayName("I Zone"), 0);
        double ff = SmartDashboard.getNumber(this.getDisplayName("Feed Forward"), 0);
        double max = SmartDashboard.getNumber(this.getDisplayName("Max Output"), 0);
        double min = SmartDashboard.getNumber(this.getDisplayName("Min Output"), 0);
        double rotations = SmartDashboard.getNumber("Set " + this.controlMode.toString(), 0);


        /* Config the peak and nominal outputs */
		this.motor.configNominalOutputForward(0, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configNominalOutputReverse(0, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configPeakOutputForward(this.kMaxOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.configPeakOutputReverse(this.kMinOutput, TalonSRXClosedLoopControllerHelper.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		this.motor.config_kF(this.kPIDLoopIdx, this.kFF, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kP(this.kPIDLoopIdx, this.kP, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kI(this.kPIDLoopIdx, this.kI, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
		this.motor.config_kD(this.kPIDLoopIdx, this.kD, TalonSRXClosedLoopControllerHelper.kTimeoutMs);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { 
            this.motor.config_kP(this.kPIDLoopIdx, this.kP, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
            kP = p;        
        }

        if((i != kI)) { 
            this.motor.config_kI(this.kPIDLoopIdx, this.kI, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
            kI = i;        
        }

        if((d != kD)) { 
            this.motor.config_kD(this.kPIDLoopIdx, this.kD, TalonSRXClosedLoopControllerHelper.kTimeoutMs);
            kD = d; 
        }
        // if((iz != kIz)) { 
        //     pidController.setIZone(iz); 
        //     kIz = iz; 
        // }

        if((ff != kFF)) { 
            this.motor.config_kF(this.kPIDLoopIdx, this.kFF, TalonSRXClosedLoopControllerHelper.kTimeoutMs); 
        }
        
        if((max != kMaxOutput) || (min != kMinOutput)) { 
            this.setMaxOutput(min, max); 
            kMinOutput = min; kMaxOutput = max; 
        }
       
        if (includeMovement)
            this.motor.set(this.controlMode, rotations);
    }

    public void setPoint(double value)
    {
        //For velocity value is UnitsPer100ms
        this.motor.set(this.controlMode, value);
    }

}

