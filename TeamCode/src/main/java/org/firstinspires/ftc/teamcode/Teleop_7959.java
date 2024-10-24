package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Teleop 7959 (with outtake)")
public class Teleop_7959 extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotorEx liftL;

    //32 bit decimals are floats, while 64 bit are doubles, thus, doubles are better.
    private double maxVelocity = 1.0;

    private int ctrlPow = 3  ;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        liftL = hardwareMap.get(DcMotorEx.class, "liftL");

        liftL.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {

            rightFront.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            leftFront.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            rightBack.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            leftBack.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));

            rightFront.setPower(gamepad1.right_stick_x);
            leftFront.setPower(-gamepad1.right_stick_x);
            rightBack.setPower(gamepad1.right_stick_x);
            leftBack.setPower(-gamepad1.right_stick_x);


            if(gamepad1.left_trigger > 0 && !liftL.isBusy()) {
                //moves the arm up
                telemetry.addData("ARM UP", "true");
                telemetry.update();
                //tickcount is 537
                //1460 is about 2.5 revolutions or whatever 1460/537 is
                //537 encoder ticks = 360 degrees on a motor

                liftL.setTargetPosition(400);
                liftL.setDirection(DcMotor.Direction.FORWARD);
                liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftL.setPower(1);
            }

            if(gamepad1.right_trigger > 0 && !liftL.isBusy()){
                //moves the arm down
                telemetry.addData("ARM DOWN", "true");
                telemetry.update();


                liftL.setTargetPosition(0);
                liftL.setDirection(DcMotor.Direction.REVERSE);
                liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftL.setPower(-0.5);
            }





        }
    }
}
