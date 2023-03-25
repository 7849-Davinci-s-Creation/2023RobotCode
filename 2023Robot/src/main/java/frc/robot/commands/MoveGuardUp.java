package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Guard;

public class MoveGuardUp extends CommandBase {
    private final Guard guard;  

    public MoveGuardUp(Guard guard){
        this.guard = guard;
        addRequirements(guard);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        guard.guardUp();
    }

    @Override
    public void end(boolean interuppted) {

    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
    
