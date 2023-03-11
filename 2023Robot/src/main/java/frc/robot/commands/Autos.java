// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autocommands.MoveForward;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static void configureAutos(SendableChooser<Command> menu, DriveTrain driveTrain){
    menu.addOption("Do Nothing", null);
    menu.addOption("Move Forward", moveForwardAuto(driveTrain));
  }
  
  private static Command moveForwardAuto(DriveTrain train){
    return new MoveForward(train, 3, 0.5);
  }
}