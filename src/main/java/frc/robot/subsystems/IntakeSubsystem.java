package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class IntakeSubsystem extends SubsystemBase {
    private int intakeBeltId = 0;
    private TalonSRX roller;
    private CANSparkMax intakeBelt;
    private DoubleSolenoid wrist;

    public IntakeSubsystem() {
        roller = new TalonSRX(RobotMap.INTAKE_MOTOR);
        wrist = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_WRIST_CHANNEL[0], RobotMap.INTAKE_WRIST_CHANNEL[1]);
        intakeBelt = new CANSparkMax(intakeBeltId, MotorType.kBrushless);
    }

    public boolean isExtended() {
        return wrist.get() == Value.kForward;
    }

    public boolean isStopped() {
        return roller.getMotorOutputPercent() == 0;
    }
    
    public void spin(double speed){
        roller.set(ControlMode.PercentOutput, speed);        
    }

    public void intakeBeltOn(double speed)
    {
        this.intakeBelt.set(speed);
    }

    public void contract() {
        wrist.set(Value.kReverse);
    }

    public void extend() {
        wrist.set(Value.kForward);
    }

    public void stop() {
        wrist.set(Value.kOff);
        roller.set(ControlMode.PercentOutput, 0);
        intakeBelt.set(0);
    }
}
