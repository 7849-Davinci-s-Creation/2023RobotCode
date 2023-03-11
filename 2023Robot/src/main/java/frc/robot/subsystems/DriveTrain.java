package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
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
    }

    public void left(double power){
        leftDrive.set(power);
    }

    @Override
    public void periodic() {

    }
    
}
