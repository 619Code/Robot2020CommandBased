package frc.robot.helpers;

public class IterativeDelay {
    int targetCycles;
    int cyclesPassed;

    public IterativeDelay(int cycles) {
        this.targetCycles = cycles;
        this.cyclesPassed = 0;
    }

    public void Reset() {
        cyclesPassed = 0;
    }

    public void Cycle() {
        if (!IsDone())
            cyclesPassed++;
    }

    public int getCycle() {
        return cyclesPassed;
    }

    public boolean IsDone() {
        if (cyclesPassed >= targetCycles)
            return true;
        else
            return false;
    }
}