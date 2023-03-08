package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cameras;

public class SwitchCamera extends CommandBase {
    private final Joystick remote;
    private final NetworkTableEntry camerselected;
    private String cameraState = null;
    private int toggle = 0;

    public SwitchCamera(Cameras cameras,Joystick remote, NetworkTableEntry camerselected) {
        this.remote = remote;
        this.camerselected = camerselected;
        addRequirements(cameras);
    }

    @Override
    public void initialize() {
        if (cameraState == null){
            cameraState = Constants.CameraConstants.FRONT_CAMERA_NAME;
        }
        if (toggle >= 1){
            toggle = 0;
        }
    }

    @Override
    public void execute() {
        // When remote is pressed down show the back camera. Else show front.
        // We need to switch this to be based upon the Y position of the joystick instead of a button.
        if (toggle >= 1){
            cameraState = Constants.CameraConstants.BACK_CAMERA_NAME;
        }
        if (remote.getRawButtonPressed(7)) {
            camerselected.setString(cameraState);
            SmartDashboard.putString("CurrentView", cameraState);
            toggle ++;
        } 
        // else if (remote.getRawButtonPressed(7)) {
        //     camerselected.setString(Constants.CameraConstants.FRONT_CAMERA_NAME);
        //     SmartDashboard.putString("CurrentView","Front");
        // }
    }

    @Override
    public void end(boolean interuppted) {
        if (toggle >= 1){
            toggle = 0;
        }
    }

    @Override 
    public boolean isFinished() {
        return false;
    }

}