package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalance extends CommandBase {

    private enum STATEMACHINE {
        DRIVEFORWARD, DRIVEUP, DRIVEBACK
    }
    private DriveTrain dtl;
    private ADXRS450_Gyro gyrl;
    private STATEMACHINE currentState;

    public AutoBalance(DriveTrain dt, ADXRS450_Gyro gyr){
        this.dtl = dt;
        this.gyrl = gyr;
        addRequirements(this.dtl);
    }

    @Override
    public void initialize() {
        gyrl.reset();
        dtl.forward(0);
        currentState = STATEMACHINE.DRIVEFORWARD;
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
                dtl.forward(0.1*(gyrl.getAngle()/45) + 0.1);
                break;
            case DRIVEBACK:
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
        return gyrl.getAngle() < 5 && (currentState == STATEMACHINE.DRIVEUP);
    }
    
}
