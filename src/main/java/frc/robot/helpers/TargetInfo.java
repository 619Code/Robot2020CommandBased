package frc.robot.helpers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetInfo {

    public TargetInfo(){}

    public double X;
    public double Y;
    public double Area;
    public boolean HasTarget;

    public void Show() {
        SmartDashboard.putNumber("LimeLight-X", X);
        SmartDashboard.putNumber("LimeLight-Y", Y);
        SmartDashboard.putNumber("LimeLight-Area", Area);
        SmartDashboard.putString("Target Present", HasTarget ? "Yes" : "No");
    }
    public double getTargetX(){
        return X;
    }
    public double getTargetY(){
        return Y;
    }
}