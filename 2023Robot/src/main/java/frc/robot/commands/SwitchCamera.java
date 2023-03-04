package frc.robot.commands;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Cameras;

public class SwitchCamera extends CommandBase {

    Joystick remote;
    UsbCamera front;
    UsbCamera back;
    NetworkTableEntry camerselected;

    public SwitchCamera( Cameras cameras,Joystick remote, UsbCamera front, UsbCamera back, NetworkTableEntry camerselected ){
        this.remote = remote;
        this.front = front;
        this.back = back;
        this.camerselected = camerselected;
        addRequirements(cameras);
    }

    @Override
    public void initialize( ){
    }

    @Override
    public void execute( ){
        if ( remote.getTriggerPressed()) {
            camerselected.setString(back.getName());
            SmartDashboard.putString("CurrentView","Back");
        } else if (remote.getTriggerReleased()){
            camerselected.setString(front.getName());
            SmartDashboard.putString("CurrentView","Front");
        }
    }

    @Override
    public void end(boolean interuppted) {
    }

    @Override 
    public boolean isFinished( ){
        return false;
    }


}