package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Guard extends SubsystemBase {
    private final Compressor compressor; 
    private final DoubleSolenoid solenoid; 

    public Guard() {
        this.compressor = new Compressor(6,PneumaticsModuleType.CTREPCM);
        this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);
    } 

    public void guardDown() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void guardUp() { 
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    @Override
    public void periodic() {
        // System.out.println(compressor.getPressure());
    }
}      