package frc.robot.helpers;

import com.team2363.commands.HelixFollower;
import com.team319.trajectory.Path;

public abstract class HelixFollowerAltBase extends HelixFollower
{
    public HelixFollowerAltBase(Path path) {
        super(path);
    }

    public void initializedPublicAccess() {
        this.initialize();
    }

    public void executePublicAccess() {
        this.execute();
    }
    
    public boolean isFinishedPublicAccess() {
        return this.isFinished();
    }

    public void interruptedPulbicAccess() {
        this.interrupted();
    }    
}