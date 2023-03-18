package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Util.DashboardConfiguration;

public class DriveTrain extends SubsystemBase implements DashboardConfiguration{
    // create motor objects
    CANSparkMax frontRightMotor;
    CANSparkMax backRightMotor;
    CANSparkMax frontLeftMotor;
    CANSparkMax backLeftMotor;
    // create motor control group
    MotorControllerGroup rightDrive;
    MotorControllerGroup leftDrive;
    // create differential drive
    DifferentialDrive differentialDrive;

    public DriveTrain() {
        frontRightMotor = new CANSparkMax( Constants.Motors.FRONTRIGHTMOTOR_PORT, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(Constants.Motors.BACKRIGHTMOTOR_PORT, MotorType.kBrushless);
        frontLeftMotor = new CANSparkMax(Constants.Motors.FRONTLEFTMOTOR_PORT, MotorType.kBrushless);
        backLeftMotor = new CANSparkMax(Constants.Motors.BACKLEFTMOTOR_PORT, MotorType.kBrushless);
        
        leftDrive = new MotorControllerGroup(frontLeftMotor,backLeftMotor);
        leftDrive.setInverted(true);
        rightDrive = new MotorControllerGroup(frontRightMotor,backRightMotor);
        
        differentialDrive = new DifferentialDrive(rightDrive, leftDrive);
    }

    public void  arcadeDrive(double moveSpeed, double rotateSpeed) { 
        differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    public void forward(double power){
        leftDrive.set(power);
        rightDrive.set(power);
    }

    public void backwards(double power){
        leftDrive.set(-power);
        rightDrive.set(-power);
    }

    public void right(double power){
        rightDrive.set(power);
        leftDrive.set(-power);
    }

    public void left(double power){
        leftDrive.set(power);
        rightDrive.set(-power);
    }

    public void setBreakMode(boolean isBraked) {
        if (isBraked) {
            frontLeftMotor.setIdleMode(IdleMode.kBrake);
            frontRightMotor.setIdleMode(IdleMode.kBrake);
            backRightMotor.setIdleMode(IdleMode.kBrake);
            backLeftMotor.setIdleMode(IdleMode.kBrake);
            return;
        }
        frontLeftMotor.setIdleMode(IdleMode.kCoast);
        frontRightMotor.setIdleMode(IdleMode.kCoast);
        backLeftMotor.setIdleMode(IdleMode.kCoast);
        backRightMotor.setIdleMode(IdleMode.kCoast);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void configureDashboard() {
        SmartDashboard.putString("Control State", "Normal");
    }
    
}
