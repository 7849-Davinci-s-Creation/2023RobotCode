package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    CANSparkMax frontRightMotor;
    CANSparkMax backRightMotor;
    CANSparkMax frontLeftMotor;
    CANSparkMax backLeftMotor;

    @Override
    public void periodic(){

    }
    
}
