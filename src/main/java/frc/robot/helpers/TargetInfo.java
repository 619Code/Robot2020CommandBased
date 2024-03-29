package frc.robot.helpers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class TargetInfo {
    public double X;
    public double Y;
    public double Area;
    public boolean HasTarget;

    public TargetInfo(double tx, double ty, double ta, double tv) {
        this.X = tx;
        this.Y = ty;
        this.Area = ta;
        this.HasTarget = tv == 0 ? false : true;
    }

    public void Show() {
        System.out.println("LimeLight-X: " + X);
        System.out.println("LimeLight-Y: " + Y);
        System.out.println("LimeLight-Area: " + Area);
        System.out.println("Target Present: " + (HasTarget ? "Yes" : "No"));
        System.out.println();
    }

    public double getTargetX() {
        return X;
    }

    public double getTargetY() {
        return Y;
    }

    public double getDistanceX() {
        double h1 = 39;
        double h2 = 63;
        double a1 = Math.toRadians(6.9);
        double a2 = Math.toRadians(Y);
        double d = ((RobotMap.TARGET_HEIGHT - RobotMap.LIMELIGHT_HEIGHT) / Math.tan(RobotMap.LIMELIGHT_ANGLE + a2));
        return d;
    }
}