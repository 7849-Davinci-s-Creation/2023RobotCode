package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Brake extends CommandBase {
    private DriveTrain fastplane;
    private double seconds;

    private double startingtime;

    public Brake(DriveTrain fastplane, double seconds) {
       addRequirements(fastplane);
       this.fastplane = fastplane;
       this.seconds = seconds;
    }

 @Override
    public void initialize() {
        startingtime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
       fastplane.setBreakMode(true);
    }

    @Override
    public void end(boolean interuppted) {
      fastplane.setBreakMode(false);
    }

    @Override 
    public boolean isFinished() {
      return System.currentTimeMillis() - startingtime > (seconds * 1000);
    }
}
