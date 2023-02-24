package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    CANSparkMax frontRightMotor;
    CANSparkMax backRightMotor;
    CANSparkMax frontLeftMotor;
    CANSparkMax backLeftMotor;
    MotorControllerGroup RightDrive;
    MotorControllerGroup LeftDrive;
    DifferentialDrive differentialDrive;

    @Override
    public void periodic(){

    }
    
}
