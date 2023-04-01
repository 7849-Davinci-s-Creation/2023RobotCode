package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalanceWithDriveBack extends CommandBase {

    private enum STATEMACHINE {
        DRIVEFORWARD, DRIVEUP, DRIVEBACK, END
    }
    private DriveTrain dtl;
    private ADXRS450_Gyro gyrl;
    private STATEMACHINE currentState;
    private int driveBackCounter;
    private int lastTime;

    public AutoBalanceWithDriveBack(DriveTrain dt, ADXRS450_Gyro gyr){
        this.dtl = dt;
        this.gyrl = gyr;
        addRequirements(this.dtl);
    }

    @Override
    public void initialize() {
        gyrl.reset();
        dtl.forward(0);
        currentState = STATEMACHINE.DRIVEFORWARD;
        driveBackCounter = 0;
        lastTime = 0;
    }

    @Override
    public void execute() {
        switch (currentState) {
            case DRIVEFORWARD: 
                dtl.forward(0.2);
                if (gyrl.getAngle() > 5){
                    currentState = STATEMACHINE.DRIVEUP;
                }
                break;

            case DRIVEUP:
                dtl.forward(0.15);
                if (gyrl.getAngle() < 5){
                    currentState = STATEMACHINE.DRIVEBACK;
                    lastTime = Utility.getFPGATime();
                }
                break;

            case DRIVEBACK:
                dtl.forward(-0.2)
                if (Utility.getFPGATime() - lastTime > 2000){
                    currentState = STATEMACHINE.END;
                }
                driveBackCounter ++;
                break;
            case END:
                break;
            default:
                break;
        }
    }

    @Override
    public void end(boolean interuppted) {
        dtl.forward(0);
        gyrl.reset();
        dtl.setBreakMode(true);
    }

    @Override 
    public boolean isFinished() {
        return currentState == STATEMACHINE.END;
    }
    
}
