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
import frc.robot.commands.autocommands.MoveRight;
import frc.robot.subsystems.DriveTrain;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static void configureAutos(SendableChooser<Command> menu, DriveTrain driveTrain){
    menu.addOption("Do Nothing", null);
    menu.addOption("Move Forward", moveForwardAuto(driveTrain));
    menu.addOption("Score-Balance Red Bumps", scoreBalanceRedBumps(driveTrain));
    menu.addOption("Score-Balance Blue Bumps", scoreBalanceBlueBumps(driveTrain));
    menu.addOption("Score-Move", scoreMove(driveTrain));
    menu.addOption("Speed Bump", speedBumpScoreMove(driveTrain));
  }
  
  private static Command moveForwardAuto(DriveTrain train){
    return new MoveForward(train, 0.75, 0.5);
  }

  private static SequentialCommandGroup scoreBalanceRedBumps(DriveTrain train){
    return new SequentialCommandGroup(
      new MoveBackwards(train, 0.3, 0.3),
      new Brake(train, 0.2),
      new WaitCommand(0.5),
      new MoveForward(train, 1, 0.45),
      new Brake(train, 0.3),
      new WaitCommand(0.5),
      new MoveForward(train, 2.5, 0.1),
      new Brake(train, 0.2),
      new WaitCommand(0.5),
      new MoveBackwards(train, 1, 0.6),
      new Brake(train, 0.3),
      new WaitCommand(0.5),
      new MoveRight(train, 0.45, 0.3),
      new Brake(train, 1),
      new WaitCommand(0.5)
    );
  }

  private static SequentialCommandGroup scoreBalanceBlueBumps(DriveTrain train) {
    return new SequentialCommandGroup(
      new MoveBackwards(train, 0.3, 0.3),
      new Brake(train, 0.2),
      new WaitCommand(0.5),
      new MoveForward(train, 1, 0.5),
      new Brake(train, 0.5),
      new WaitCommand(0.5),
      new MoveForward(train, 2.5, 0.1),
      new Brake(train, 0.2),
      new WaitCommand(0.5),
      new MoveBackwards(train, 0.91, 0.6),
      new Brake(train, 0.5),
      new WaitCommand(0.5),
      new MoveRight(train, 0.47, 0.3),
      new Brake(train, 1),
      new WaitCommand(0.5)
    );
  }

  //TODO this needs to be made
  private static SequentialCommandGroup scoreMove(DriveTrain train) {
    return new SequentialCommandGroup(new MoveBackwards(train, 0.3, 0.3),
    new Brake(train, 0.2),
    new WaitCommand(0.5),
    new MoveForward(train, 1.2, 0.5),
    new Brake(train, 0.5),
    new WaitCommand(0.5));
  }

  private static SequentialCommandGroup speedBumpScoreMove(DriveTrain train){
    return new SequentialCommandGroup(new MoveBackwards(train, 0.3, 0.3),
    new Brake(train, 0.2),
    new WaitCommand(0.5),
    new MoveForward(train, 1.5, 0.5),
    new Brake(train, 0.5),
    new WaitCommand(0.5));
  }
}