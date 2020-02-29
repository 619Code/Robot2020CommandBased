package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.team2363.controller.PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem {
    public CANSparkMax lift;
    public TalonSRX hook;
    PIDController hookPID;

    public ClimberSubsystem() {
        hook = new WPI_TalonSRX(RobotMap.HOOK);
        hook.configFactoryDefault();
        hook.setNeutralMode(NeutralMode.Brake);
        hook.configOpenloopRamp(0.4);
        lift = new CANSparkMax(RobotMap.LIFT, MotorType.kBrushless);
        // hookPID = new PIDController(RobotMap.HOOK_P, RobotMap.HOOK_I,
        // RobotMap.HOOK_D);
    }

    public void setHookPosition(double position) {
        // double targetPosition = position; // put calculations here
        // System.out.println("Hook Position: " + hook.getEncoder().getPosition() + " "
        // + targetPosition);
        // hook.set(hookPID.calculate(hook.getEncoder().getPosition(), targetPosition));
        hook.set(ControlMode.PercentOutput, position);
    }

    public void extendLift(double speed) {
        lift.set(speed);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}