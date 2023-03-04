package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util.ConfigureDashboard;
public class Cameras extends SubsystemBase implements ConfigureDashboard{

    // Camera class variables
    
    private final UsbCamera frontCamera;
    private final UsbCamera backCamera;
    private final CvSink cvSink;
    private final CvSource backOutputstream;
    private final CvSource frontOutputstream;

    NetworkTableEntry selectedcamera;

    private final Mat source;
    private final Mat output;
    private final int width = 1000;
    private final int height = 1000;

    // Constructor sets up camera and camera output
    public Cameras(){
        frontCamera = CameraServer.startAutomaticCapture(0);
        frontCamera.setResolution(width,height);
        backCamera = CameraServer.startAutomaticCapture(1);
        backCamera.setResolution(width,height);
        frontCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        backCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        cvSink = CameraServer.getVideo();
        frontOutputstream = CameraServer.putVideo("Front", width,height);
        backOutputstream = CameraServer.putVideo("Back", width,height);

        source = new Mat();
        output = new Mat();
        
        selectedcamera = NetworkTableInstance.getDefault().getTable("").getEntry("selectedcamera");
    }

    // outputs all frames from camera to dashboard and makes a new thread.
    public void startCameras() {
        new Thread(
            () -> {
                while(!Thread.interrupted()){
                    if ( cvSink.grabFrame(source) == 0 ) {
                        continue;
                    }
                    Imgproc.cvtColor(source,output,Imgproc.COLOR_BGR2GRAY);
                    frontOutputstream.putFrame(output);
                    backOutputstream.putFrame(output);
                }
            }

        ).start();
    }
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    }

    @Override
    public void configureDashboard() {
        SmartDashboard.putString("CurrentView","Front");
    }

    public UsbCamera getfrontCamera(){
        return this.frontCamera;
    }

    public UsbCamera getbackCamera(){
        return this.backCamera;
    }

    public NetworkTableEntry getselectedCamera(){
        return this.selectedcamera;
    }
    
}