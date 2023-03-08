// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Util.CameraState;
import frc.robot.commands.Moving;
import frc.robot.commands.SwitchCamera;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Cameras camera = new Cameras();
  private final DriveTrain driveTrain = new DriveTrain();

  // controllers
  private final Joystick joystick = new Joystick(Constants.Controllers.JOYSTICK_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Configure subsystems default command 
    configureDefault();
    // Configure the SmartDashboard
    configureDashboard();

    // starting the cameras
    camera.startCameras();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  /**
   * Configures subsystem's default commands
   */
  public void configureDefault() {
    SwitchCamera switchCamera = new SwitchCamera(camera,joystick, camera.getselectedCamera(),CameraState.FRONT,Constants.CameraConstants.FRONT_CAMERA_NAME);
    camera.setDefaultCommand(switchCamera);
    driveTrain.setDefaultCommand(new Moving(driveTrain, joystick));
  } 

  /**
   * Configures the SmartDashboard on Robot startup.
   * Call all ConfigureDashboard.configureDashboard() methods from a
   * Subsystem that implements the ConfigureDashboard interface here.
   * @see frc.robot.Util.DashboardConfiguration;
   */
  public void configureDashboard() {
    camera.configureDashboard();
  }
}
