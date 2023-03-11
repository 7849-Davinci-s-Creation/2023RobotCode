package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public  class MoveForward extends CommandBase {
    private DriveTrain fastplane;
    private double seconds;
    private double power;

    private double startingtime;

    public MoveForward(DriveTrain fastplane, double seconds, double power) {
       addRequirements(fastplane);
       this.fastplane = fastplane;
       this.seconds = seconds;
       this.power = power;  
    }

 @Override
    public void initialize() {
        startingtime = System.currentTimeMillis();

    }

    @Override
    public void execute() {
       fastplane.forward(power);
    }

    @Override
    public void end(boolean interuppted) {
      fastplane.forward(0);
    }

    @Override 
    public boolean isFinished() {
      return System.currentTimeMillis() - startingtime > (seconds * 1000);
    }
}
