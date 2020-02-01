package frc.robot.helpers;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.helpers.HelixFollowerAltBase;

public class HelixFollowerCommandWrapper extends CommandBase {

    private HelixFollowerAltBase follower;

    public HelixFollowerCommandWrapper(HelixFollowerAltBase follower)
    {
        this.follower = follower;
        this.addRequirements(this.follower.m_requirements.toArray(new Subsystem[this.follower.m_requirements.size()]));
    }

    @Override
    public void execute() {
        this.follower.executePublicAccess();
    }

    @Override
    public void initialize() {
        this.follower.initializedPublicAccess();
    }

    @Override
    public boolean isFinished() {
        return this.follower.isFinishedPublicAccess();
    }

    @Override 
    public void end(boolean interrupted) {
        this.follower.interruptedPulbicAccess();       
    }

    public static HelixFollowerCommandWrapper wrap(HelixFollowerAltBase follower)
    {
        return new HelixFollowerCommandWrapper(follower);
    }
}
    
