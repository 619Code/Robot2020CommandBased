package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.paths.SixFeet;
import frc.robot.commands.*;
import frc.robot.helpers.*;
import frc.robot.subsystems.*;

public class ParellelVsSequential extends CommandGroup {

    public ParellelVsSequential() {
        this.addSequential(new TestCommand("S-One", 100));
        this.addSequential(new TestCommand("S-Two", 100));
        this.addParallel(new TestCommand("P-Three", 200));
        this.addSequential(new TestCommand("S-Four", 100));
        this.addParallel(new TestCommand("P-Five", 200));
        this.addSequential(new TestCommand("S-Six", 100));
    }

}