
package org.firstinspires.ftc.teamcode;
import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name = "State Machine Auto", group = "Autonomous")
@Configurable // Panels
public class StateMachineOpMode extends OpMode {

    States CurrentState = States.NONE;
    States TargetState = States.DRIVE_TO_SHOOT;

    @Override
    public void init() {
        // Not Implemented
        throw new Exception("Do not use this OpMode! Please read the note in the file on line 34.");
        // Do not use this OpMode, it has no actual logic, use this OpMode to understand and write your own!

    }

    public void Path_Follower_Drive_To_Shoot(){}
    public boolean Path_Follower_Done(){ return true; }

    public int Get_Flywheel_RPM(){ return 6000; };

    public void Activate_Launching_Transfer(){};

    public void Update_Sensors(){};

    @Override
    public void loop() {
        // -- Sensor reading and updates, do this first
        Update_Sensors();

        // -- Init Block, fires once on state change
        if (CurrentState != TargetState){
            CurrentState = TargetState;

            if (CurrentState == States.DRIVE_TO_SHOOT){
                Path_Follower_Drive_To_Shoot();
                // Send the robot off on its path, in PedroPathing this would be:
                // follower.FollowPath(ShootingPath);
            }

            if (CurrentState == States.LAUNCHING){
                Activate_Launching_Transfer();
            }

        }

        // -- Transition Block, checks every loop to see if we need to advance the state
        if (CurrentState == States.DRIVE_TO_SHOOT){
            if (Path_Follower_Done()){ // In PedroPathing this would be follower.isBusy()

                // Tell the State Machine to proceed to the next state
                TargetState = States.FLYWHEEL_SPIN_UP;
            }
        }

        if (CurrentState == States.FLYWHEEL_SPIN_UP){
            if (Get_Flywheel_RPM() > 6000){
                // Tell the robot to launch the artifacts
                TargetState = States.LAUNCHING;
            }
        }  

    }


}
    