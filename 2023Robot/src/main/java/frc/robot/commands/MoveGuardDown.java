package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Guard;

public class MoveGuardDown extends CommandBase {
    private final Guard guard;  

    public MoveGuardDown(Guard guard){
        this.guard = guard;
        addRequirements(guard);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        guard.guardDown();
    }

    @Override
    public void end(boolean interuppted) {

    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
