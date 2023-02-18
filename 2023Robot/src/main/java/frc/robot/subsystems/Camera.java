package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Camera extends SubsystemBase{

    // Camera class variables
    
    private final UsbCamera camera;
    private final CvSink cvSink;
    private final CvSource outputstream;

    private final Mat source;
    private final Mat output;

    private final int width = 640;
    private final int height = 480;

    // Constructor sets up camera and camera output
    public Camera(){
        camera = CameraServer.startAutomaticCapture();
        camera.setResolution(width,height);
        cvSink = CameraServer.getVideo();
        outputstream = CameraServer.putVideo("RobotPOV", width,height);

        source = new Mat();
        output = new Mat();
    }

    // outputs all frames from camera to dashboard and makes a new thread.
    public void startCamera() {
        new Thread(
            () -> {
                while(!Thread.interrupted()){
                    if ( cvSink.grabFrame(source) == 0 ) {
                        continue;
                    }
                    Imgproc.cvtColor(source,output,Imgproc.COLOR_BGR2GRAY);
                    outputstream.putFrame(output);
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
}