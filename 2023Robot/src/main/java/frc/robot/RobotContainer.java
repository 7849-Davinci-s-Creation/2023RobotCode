// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Util.CameraState;
import frc.robot.commands.Autos;
import frc.robot.commands.Boost;
import frc.robot.commands.Creep;
import frc.robot.commands.Invertedmovement;
import frc.robot.commands.MoveGuardDown;
import frc.robot.commands.MoveGuardUp;
import frc.robot.commands.Moving;
import frc.robot.commands.SwitchCamera;
import frc.robot.commands.autocommands.MoveBackwards;
import frc.robot.commands.autocommands.MoveForward;
import frc.robot.commands.autocommands.MoveLeft;
import frc.robot.commands.autocommands.MoveRight;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Guard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // Subsystems
  private final Cameras camera = new Cameras();
  private final DriveTrain driveTrain = new DriveTrain();
  private final Guard guard = new Guard();

  // controllers
  private final Joystick joystick = new Joystick(Constants.Controllers.JOYSTICK_PORT);
  private final CommandJoystick anotherstick = new CommandJoystick(Constants.Controllers.JOYSTICK_PORT);

  // misc
  private final SendableChooser<Command> autoMenu = new SendableChooser<>();
  public static boolean isInverted = false;

    // Commands
    private SwitchCamera switchCamera = new SwitchCamera(camera,joystick, camera.getselectedCamera(),CameraState.FRONT,Constants.CameraConstants.FRONT_CAMERA_NAME);
    private Moving moving = new Moving(driveTrain, joystick);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // Configure subsystems default command 
    configureDefault();
    // Configure autos
    Autos.configureAutos(autoMenu, driveTrain);
    // starting the cameras
    camera.startCameras();
    // Configure the SmartDashboard
    configureDashboard();
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
    anotherstick.button(6).whileTrue(new Boost(driveTrain, joystick));
    anotherstick.button(5).whileTrue(new Creep(driveTrain, joystick));
    anotherstick.button(9).whileTrue(new Invertedmovement(driveTrain, joystick));
    anotherstick.povUp().onTrue(new MoveForward(driveTrain, .1 ,.1));
    anotherstick.povDown().onTrue(new MoveBackwards(driveTrain, .1 ,.1));
    anotherstick.povLeft().onTrue(new MoveLeft(driveTrain, .1 ,.1));
    anotherstick.povRight().onTrue(new MoveRight(driveTrain, .1 ,.1));
    anotherstick.button(1).onTrue(new MoveGuardDown(guard));
    anotherstick.button(1).onFalse(new MoveGuardUp(guard));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoMenu.getSelected();
  }

  /**
   * Configures subsystem's default commands
   */
  public void configureDefault() {
    camera.setDefaultCommand(switchCamera);
    driveTrain.setDefaultCommand(moving);
  } 

  /**
   * Configures the SmartDashboard on Robot startup.
   * Call all ConfigureDashboard.configureDashboard() methods from a
   * Subsystem that implements the ConfigureDashboard interface here.
   * @see frc.robot.Util.DashboardConfiguration;
   */
  public void configureDashboard() {
    camera.configureDashboard();
    SmartDashboard.putData(autoMenu);
    driveTrain.configureDashboard();
  }

  public void robotInit(){
    // to make sure we are not braked at all when we start up robot
    driveTrain.setBreakMode(false);
  }

  public void autonomousInit(){
    // to make sure we are not braked at all when we start up auto
    driveTrain.setBreakMode(false);
  }

  public void autonomousPeriodic(){
  }

  public void teleoperatedInit(){
    // to make sure we are not braked at all when we start up teleop
    driveTrain.setBreakMode(false);
  }

  public void teleoperatedPeriodic(){
    if (joystick.getRawButtonPressed(9)) {
      isInverted = true;
    } else {
      isInverted = false;
    }
  }

  public void disabledInit(){
    // to make sure we are not braked at all when we disable robot
    driveTrain.setBreakMode(false);
  }

  // command utility
  public static boolean getInverted() {
    return isInverted;
  }
}
