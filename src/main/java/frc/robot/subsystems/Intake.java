package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem{
    private CANSparkMax roller;
    private DoubleSolenoid wrist;

    public Intake() {
        roller = new CANSparkMax(RobotMap.INTAKE_MOTOR, MotorType.kBrushless);
        wrist = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_WRIST_CHANNEL[0], RobotMap.INTAKE_WRIST_CHANNEL[1]);
    }

    public void spin(double speed){
        roller.set(speed);
    }

    private void raise() {
        wrist.set(Value.kReverse);
    }

    private void lower() {
        wrist.set(Value.kForward);
    }

    private void stop() {
        wrist.set(Value.kOff);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
