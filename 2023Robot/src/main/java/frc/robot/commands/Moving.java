package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Moving extends CommandBase {
    private final DriveTrain drivetain;
    private final Joystick thestick;

    // variables for calculating move speed
    private double lastmoveSpeed;
    private double currentmovespeed;
    private double lastrotatespeed;
    private double currentrotatespeed;

    private final double movementNerf = 1.5;

    public Moving(DriveTrain drivetain, Joystick thestick) {
        this.drivetain = drivetain;
        this.thestick = thestick;
        addRequirements(drivetain);
    }
    
    @Override
    public void initialize() {
        drivetain.arcadeDrive(0, 0);
        lastmoveSpeed = 0;
        currentmovespeed = 0;
        lastrotatespeed = 0;
        currentrotatespeed = 0;
    }

    @Override
    public void execute() {
        lastmoveSpeed = currentmovespeed;
        lastrotatespeed = currentrotatespeed;
        currentmovespeed = -thestick.getRawAxis(Constants.Controllers.JOYSTICKY);
        currentrotatespeed = -thestick.getRawAxis(Constants.Controllers.JOYSTICKZ);

        // Acceleration modifier, The lower this is the faster we wil accelerate, 
        // But we do not want to be too low or else we will cause motor brown out.
        double acceleration = 0.05;

        // for the moving speed
        if (Math.abs(currentmovespeed - lastmoveSpeed) > acceleration) {
            if (currentmovespeed > lastmoveSpeed) {
                currentmovespeed = lastmoveSpeed + acceleration;
            } else {
                currentmovespeed = lastmoveSpeed - acceleration;
            }
        } else {
            lastmoveSpeed = currentmovespeed;
        }

        // rotate speed
        if (Math.abs(currentrotatespeed - lastrotatespeed) > acceleration) {
            if (currentrotatespeed > lastrotatespeed) {
                currentrotatespeed = lastrotatespeed + acceleration;
            } else {
                currentrotatespeed = lastrotatespeed - acceleration;
            }
        } else {
            lastrotatespeed = currentrotatespeed;
        }

        if (RobotContainer.isInverted) {
            drivetain.arcadeDrive(-(currentmovespeed / movementNerf), currentrotatespeed / movementNerf);
            SmartDashboard.putString("Control State", "Inverted");
        } else {
            drivetain.arcadeDrive(currentmovespeed / movementNerf, currentrotatespeed / movementNerf);
            SmartDashboard.putString("Control State", "Normal");
        }
    }

    @Override
    public void end(boolean interuppted) {
        drivetain.arcadeDrive(0, 0);
        lastmoveSpeed = 0;
        currentmovespeed = 0;
        lastrotatespeed = 0;
        currentrotatespeed = 0;
    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
