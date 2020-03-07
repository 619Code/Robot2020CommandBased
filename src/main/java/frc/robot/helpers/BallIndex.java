package frc.robot.helpers;

import edu.wpi.first.wpilibj.DigitalInput;

// Helps up identify if a ball is at an index or not.  This was done becaause of the 
// wacky reverse logic of our ball sensors.
public class BallIndex {

    DigitalInput input;

    public BallIndex(int channel) {
        input = new DigitalInput(channel);
    }

    public boolean hasBall() {

        // This is the oppisite logic of what you would think, but when
        // the sensor sees a ball it reads false and when the sensor
        // does not see the ball it reads 1.
        if (input.get())
            return false;
        else
            return true;
    }

}
