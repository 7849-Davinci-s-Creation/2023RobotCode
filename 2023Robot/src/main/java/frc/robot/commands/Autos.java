// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.autocommands.Brake;
import frc.robot.commands.autocommands.MoveBackwards;
import frc.robot.commands.autocommands.MoveForward;
import frc.robot.commands.autocommands.MoveLeft;
import frc.robot.commands.autocommands.MoveRight;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static void configureAutos(SendableChooser<Command> menu, DriveTrain driveTrain){
    menu.addOption("Do Nothing", null);
    menu.addOption("Move Forward", moveForwardAuto(driveTrain));
    menu.addOption("Test All Auto Commands\n(WARNING THIS WILL MAKE THE ROBOT PROBABLY GO SOMEWHERE WE DONT WANT IT SO DO IT PROPPED UP)", testAutos(driveTrain));
  }
  
  private static Command moveForwardAuto(DriveTrain train){
    return new MoveForward(train, 0.75, 0.5);
  }

  private static SequentialCommandGroup testAutos(DriveTrain train){
    return new SequentialCommandGroup(
    new MoveForward(train, 1, 0.5),
    new Brake(train, 0.1),
    new WaitCommand(0.5),
    new MoveBackwards(train, 1, 0.5),
    new Brake(train, 0.1),
    new WaitCommand(0.5),
    new MoveLeft(train, 1, 0.5),
    new Brake(train, 0.1),
    new WaitCommand(0.5),
    new MoveRight(train, 1, 0.5),
    new Brake(train, 0.1)
    );
  }
}