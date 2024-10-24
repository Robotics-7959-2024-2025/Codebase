package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Summer OpMode")
public class Teleop_7959 extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotorEx liftR;
    private DcMotorEx liftL;

    //32 bit decimals are floats, while 64 bit are doubles, thus, doubles are better.
    private double maxVelocity = 1.0;

    private int ctrlPow = 3  ;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        liftL = hardwareMap.get(DcMotorEx.class, "liftL");
        liftR = hardwareMap.get(DcMotorEx.class, "liftR");

        //as long as lifts are the different direction the individual direction doesnt matter
        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {

            frontRight.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            frontLeft.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            backRight.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y-gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));
            backLeft.setPower(Math.pow(Math.max(Math.min(gamepad1.left_stick_y+gamepad1.left_stick_x,1),-maxVelocity),ctrlPow));

            frontRight.setPower(gamepad1.right_stick_x);
            frontLeft.setPower(-gamepad1.right_stick_x);
            backRight.setPower(gamepad1.right_stick_x);
            backLeft.setPower(-gamepad1.right_stick_x);

            if(gamepad1.left_trigger > 0 && !liftL.isBusy() && !liftR.isBusy()) {
                //moves the arm up
                telemetry.addData("ARM UP", "true");
                telemetry.update();
                liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //tickcount is 537
                //1460 is about 2.5 revolutions or whatever 1460/537 is
                //537 encoder ticks = 360 degrees on a motor
                liftR.setTargetPosition(1460);
                liftR.setDirection(DcMotor.Direction.REVERSE);
                liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftR.setPower(1);

                liftL.setTargetPosition(1460);
                liftL.setDirection(DcMotor.Direction.REVERSE);
                liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftL.setPower(1);
            }

            if(gamepad1.right_trigger > 0 && !liftL.isBusy() && !liftR.isBusy()){
                //moves the arm down
                telemetry.addData("rt working", true);
                telemetry.update();
                telemetry.update();
                liftL.setTargetPosition(0);
                liftL.setDirection(DcMotor.Direction.FORWARD);
                liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftL.setPower(-0.5);

                liftR.setTargetPosition(0);
                liftR.setDirection(DcMotor.Direction.REVERSE);
                liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                liftR.setPower(1);
            }





        }
    }
}
