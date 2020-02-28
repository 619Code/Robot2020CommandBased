package frc.robot.helpers;

import edu.wpi.first.networktables.*;

public class Limelight {

    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private NetworkTableEntry light;
    private NetworkTable table;

    public Limelight() {

        table = NetworkTableInstance.getDefault().getTable("limelight");
        this.tx = table.getEntry("tx");
        this.ty = table.getEntry("ty");
        this.ta = table.getEntry("ta");
        this.tv = table.getEntry("tv");
        this.light = table.getEntry("ledMode");
    }

    public TargetInfo GetTargetInfo() {
        var targetInfo = new TargetInfo(this.tx.getDouble(0), this.ty.getDouble(0), this.ta.getDouble(0),
                this.tv.getDouble(0));
        return targetInfo;
    }

    public void TurnLightOff() {
        this.light.setNumber(1);
    }

    public void TurnLightOn() {
        this.light.setNumber(3);
    }
}