package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveFowardSeconds extends CommandBase {
    
    private DriveTrain aTrain;
    private double seconds;
    private double timer;
    private double power;
    public MoveFowardSeconds(DriveTrain aTrain, double seconds, double timer, double power){
        this.aTrain = aTrain;
        this.seconds = seconds;
        this.timer = timer;
        this.power = power;
        addRequirements(aTrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (timer<seconds){
            aTrain.forward(power);
        }
    }

    @Override
    public void end(boolean interuppted) {
    }

    @Override 
    public boolean isFinished() {
        return true;
    }
}
