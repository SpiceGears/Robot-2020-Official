/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands.Aiming;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Subsystems.LEDState.StateLedFlag;

public class SetAimingPosition extends CommandBase {

  private PIDController controller;
  private double currentAimValue;
  private double target;
  boolean up;
  private double startT;


  public SetAimingPosition(double pos) {
    this.target = pos;
    addRequirements(Robot.aiming);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startT = Timer.getFPGATimestamp();
    SmartDashboard.putNumber("AimAndShoot", 1);
    Robot.aiming.setMode(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.aiming.setpositionToHold2(target);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.aiming.setpositionToHold2(target);
    Robot.aiming.setMode(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return  Math.abs(Robot.aiming.getPotentometerPosition() - target) < Constants.getConstants().aimMinYAllowError;
  }
}
