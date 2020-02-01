package frc.robot.helpers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;

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

    public double getDistanceX() {
        double h1 = 39;
        double h2 = 63;
        double a1 = Math.toRadians(6.9);
        double a2 = Math.toRadians(Y);
        double d = ((h2-h1) / Math.tan(a1+a2));
        return d;
    }
}