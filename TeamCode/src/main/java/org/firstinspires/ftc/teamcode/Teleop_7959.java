package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Summer OpMode")
public class Teleop_7959 extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

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
        }
    }
}
