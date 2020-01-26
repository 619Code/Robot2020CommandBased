package frc.robot.helpers;

import java.util.HashSet;
import java.util.Set;

import com.team2363.commands.HelixFollower;
import com.team319.trajectory.Path;

import edu.wpi.first.wpilibj2.command.Subsystem;

public abstract class HelixFollowerAltBase extends HelixFollower
{
    public Set<Subsystem> m_requirements = new HashSet<>(); 

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
    
   /**
   * Adds the specified requirements to the command.
   *
   * @param requirements the requirements to add
   */
    public final void addRequirements(Subsystem... requirements) {
        m_requirements.addAll(Set.of(requirements));
    }
}