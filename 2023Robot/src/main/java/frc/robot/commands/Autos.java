// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static void configureAutos(SendableChooser<Command> menu, DriveTrain driveTrain){
    menu.addOption("Do Nothing", null);
    menu.addOption("Move Forward", moveForwardAuto(driveTrain));
  }
  
  private static SequentialCommandGroup moveForwardAuto(DriveTrain train){
    // use startEndCommand for runninng a command for x seconds
    return new SequentialCommandGroup( new StartEndCommand(
      () -> train.forward(0.25), () -> train.forward(0), train
      ).withTimeout(3));
  }
}
