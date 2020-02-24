/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Intake extends SubsystemBase {
  public WPI_VictorSPX intakeMotor;

  public DoubleSolenoid intakeSolenoid;
  public Compressor compressor;

  public enum Flag{
    START, STOP
  }

  public Flag state = Flag.START;

  public Intake(){
    intakeMotor = new WPI_VictorSPX(PortMap.kIntakeMotor);

    intakeMotor.configFactoryDefault();

    intakeSolenoid = new DoubleSolenoid(PortMap.kIntakeSolenoidA, PortMap.kIntakeSolenoidB);
    compressor = new Compressor();
    compressor.stop();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("intakeVolt", intakeMotor.getMotorOutputVoltage());
  }

  public void intakeBalls(){
    intakeMotor.set(ControlMode.PercentOutput, -0.6);
  }

  public void stopBalls(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  public void intakeUp(){
    intakeSolenoid.set(Value.kForward);
  }

  public void intakeDown(){
    intakeSolenoid.set(Value.kReverse);
  }

  public void intakeOff(){
    intakeSolenoid.set(Value.kOff);
  }

  public Flag getIntakeFlag(){
    return state;
  }

  public void changeIntakeFlag(Flag flag){
    state = flag;
  }
}