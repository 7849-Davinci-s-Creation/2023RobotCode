package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Guard;

public class GuardToggle extends CommandBase {
    private final Guard guard;

    public GuardToggle(Guard guard) {
        this.guard = guard;
        addRequirements(guard);
    }

    @Override
    public void initialize() {
      this.guard.changeState();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interuppted) {

    }

    @Override 
    public boolean isFinished() {
        return true;
    }
}

