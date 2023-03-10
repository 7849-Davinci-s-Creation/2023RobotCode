// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.autos.MoveFowardSeconds;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static void configureAutos(SendableChooser<Command> menu, DriveTrain driveTrain){
    menu.addOption("Move Forward", moveForwardAuto(driveTrain));
  }
  
  public static SequentialCommandGroup moveForwardAuto(DriveTrain train){
    return new SequentialCommandGroup(new MoveFowardSeconds(train, 1.5, Robot.getAutoTime(), 0.25));
  }
}
