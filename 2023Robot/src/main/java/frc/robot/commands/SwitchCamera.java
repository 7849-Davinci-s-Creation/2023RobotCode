package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Util.CameraState;
import frc.robot.subsystems.Cameras;

public class SwitchCamera extends CommandBase {
    private final Joystick remote;
    private final NetworkTableEntry camerselected;

    private CameraState cameraState;
    private String currentCamera;

    public SwitchCamera(Cameras cameras,Joystick remote, NetworkTableEntry camerselected, CameraState defaultState, String defaultCamera) {
        this.remote = remote;
        this.camerselected = camerselected;
        this.cameraState = defaultState;
        this.currentCamera = defaultCamera;
        addRequirements(cameras);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // When remote is pressed down show the back camera. Else show front.
        // We need to switch this to be based upon the Y position of the joystick instead of a button.
        if (remote.getRawButtonPressed(7)) {
            camerselected.setString(currentCamera);
            SmartDashboard.putString("CurrentView", currentCamera);
        } else if (remote.getRawButtonReleased(7)) {
            if(cameraState == CameraState.FRONT) {
                setCameraState(CameraState.BACK);
                setCurrentCamera(Constants.CameraConstants.BACK_CAMERA_NAME);
            } else {
                setCameraState(CameraState.FRONT);
                setCurrentCamera(Constants.CameraConstants.FRONT_CAMERA_NAME);
            }
        }
        // else if (remote.getRawButtonPressed(7)) {
        //     camerselected.setString(Constants.CameraConstants.FRONT_CAMERA_NAME);
        //     SmartDashboard.putString("CurrentView","Front");
        // }
    }

    @Override
    public void end(boolean interuppted) {

    }

    @Override 
    public boolean isFinished() {
        return false;
    }

    private void setCameraState(CameraState state) {
        this.cameraState = state;
    }

    private void setCurrentCamera(String camera) {
        this.currentCamera = camera;
    }

}