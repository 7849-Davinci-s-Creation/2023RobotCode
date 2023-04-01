package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {

    private DriveTrain dtl;
    private ADXRS450_Gyro gyrl;
    private boolean isUp;
    private boolean isFlat;

    public AutoBalance(DriveTrain dt, ADXRS450_Gyro gyr){
        this.dtl = dt;
        this.gyrl = gyr;
        isUp = false;
        isFlat = false;
        addRequirements(this.dtl);
    }

    @Override
    public void initialize() {
        gyrl.reset();
        dtl.forward(0);
    }

    @Override
    public void execute() {
        if (!isUp){
            dtl.forward(0.2);
            if (gyrl.getAngle() > 5){
                isUp = true;
            }
        } else {
            dtl.forward(0.15);
            if(gyrl.getAngle() < 5){
                dtl.setBreakMode(true);
                isFlat = true;
            }
        }
    }

    @Override
    public void end(boolean interuppted) {
        dtl.forward(0);
        gyrl.reset();
        isFlat = false;
        isUp = false;
        dtl.setBreakMode(true);
    }

    @Override 
    public boolean isFinished() {
        return isFlat;
    }
    
}
