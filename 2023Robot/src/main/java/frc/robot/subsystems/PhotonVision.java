package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util.DashboardConfiguration;

public class PhotonVision extends SubsystemBase implements DashboardConfiguration{
    private final PhotonCamera camera;

    public PhotonVision(){
        camera = new PhotonCamera("USB_Video_Device");
    }

    public PhotonPipelineResult getLatestResult() {
        return this.camera.getLatestResult();
    }

    @Override
    public void configureDashboard() {
        SmartDashboard.putBoolean("Has Target", false);
    }
}
