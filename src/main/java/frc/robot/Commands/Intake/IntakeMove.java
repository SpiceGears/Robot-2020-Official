/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Intake.Flag;

public class IntakeMove extends CommandBase {

  Constants constants;
  double startT;

  public IntakeMove() {
    addRequirements(Robot.intake);

    constants = new Constants().getConstants();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startT = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Constants.getConstants().intakeSwitch == 0){
      Robot.intake.intakeUp();
      Robot.intake.changeIntakeFlag(Flag.STOP);
    }else if(Constants.getConstants().intakeSwitch == 1){
      Robot.intake.intakeDown();
      Robot.intake.changeIntakeFlag(Flag.START);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(Constants.getConstants().intakeSwitch == 0){
      Constants.getConstants().intakeSwitch = 1;
    }else if(Constants.getConstants().intakeSwitch == 1){
      Constants.getConstants().intakeSwitch = 0;
    }
    Robot.intake.intakeOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double newT = Timer.getFPGATimestamp();
    SmartDashboard.putBoolean("Delta", (startT - newT) > 2);
    return false;
  }
}
