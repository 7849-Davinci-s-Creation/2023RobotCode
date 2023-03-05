// 7849 Davincis Creation Dashboard Configuration Functional Interface
// This interface should be implemented if you want to configure a subsystem's
// SmartDashboard with a consistent method across classes on launch.
package frc.robot.Util;

/**
 * Interface for configuring a subsystem's SmartDashboard configuration.
 */
@FunctionalInterface
public interface DashboardConfiguration {
     /**
      * Method for configuring a Subsystem's Dashboard.
      */
     void configureDashboard();
}
