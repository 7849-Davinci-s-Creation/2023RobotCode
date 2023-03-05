package frc.robot.commands;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Cameras;

public class SwitchCamera extends CommandBase {
    private final Joystick remote;
    private final UsbCamera front;
    private final UsbCamera back;
    private final NetworkTableEntry camerselected;

    public SwitchCamera(Cameras cameras,Joystick remote, UsbCamera front, UsbCamera back, NetworkTableEntry camerselected) {
        this.remote = remote;
        this.front = front;
        this.back = back;
        this.camerselected = camerselected;
        addRequirements(cameras);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // When remote is pressed down show the back camera. Else show front.
        // We need to switch this to be based upon the Y position of the joystick instead of a button.
        if (remote.getTriggerPressed()) {
            camerselected.setString(Constants.CameraConstants.BACK_CAMERA_NAME);
            SmartDashboard.putString("CurrentView","Back");
        } else if (remote.getTriggerReleased()) {
            camerselected.setString(Constants.CameraConstants.FRONT_CAMERA_NAME);
            SmartDashboard.putString("CurrentView","Front");
        }
    }

    @Override
    public void end(boolean interuppted) {
    }

    @Override 
    public boolean isFinished() {
        return false;
    }

}