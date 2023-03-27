package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GuardState;

public class Guard extends SubsystemBase {
    // private final Compressor compressor; 
    private final DoubleSolenoid solenoid; 
    private GuardState currentState;

    public Guard() {
        // this.compressor = new Compressor(0,PneumaticsModuleType.CTREPCM);
        this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,4,3);
        // in case above can ids are wrong
        // this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);
        currentState = GuardState.UP;
        solenoid.set(DoubleSolenoid.Value.kForward);
    } 

    public void guardDown() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
       // this.currentState = GuardState.DOWN;
    }
    
    public void guardUp() { 
        solenoid.set(DoubleSolenoid.Value.kForward);
        this.currentState = GuardState.UP;
    }
    
    @Override
    public void periodic() {
        // System.out.println(compressor.getPressure());
    }

    public void changeState() {
        if (this.currentState == GuardState.UP) {
            this.currentState = GuardState.DOWN;
            guardDown();
        } else {
            this.currentState = GuardState.UP;
            guardUp();
        }
    }

    public GuardState getCurrentState() {
        return this.currentState;
    }
}      