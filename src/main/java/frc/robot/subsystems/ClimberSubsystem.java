package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.team2363.controller.PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem {
    public CANSparkMax lift;
    public TalonSRX hook;
    public PIDController hookPID;

    public ClimberSubsystem() {
        hook = new TalonSRX(RobotMap.HOOK);
        lift = new CANSparkMax(RobotMap.LIFT, MotorType.kBrushless);
        hookPID = new PIDController(RobotMap.HOOK_P, RobotMap.HOOK_I, RobotMap.HOOK_D);
        hook.configFactoryDefault();
        lift.restoreFactoryDefaults();

        hook.setSelectedSensorPosition(0);
        hook.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    }

    public void setHookPosition(double position) {
        double targetPosition = hookPID.calculate(hook.getSelectedSensorPosition(), RobotMap.CLIMB_TARGET); // put
                                                                                                            // calculations
                                                                                                            // here

        System.out.println("Hook Position: " + hook.getSelectedSensorPosition());
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