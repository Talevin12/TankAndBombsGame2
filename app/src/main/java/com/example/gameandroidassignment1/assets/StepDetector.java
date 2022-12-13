package com.example.gameandroidassignment1.assets;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepDetector {
    public interface CallBack_movementStep {
        void oneStepRight();
        void oneStepLeft();
    }

    public interface CallBack_speedStep {
        void increaseSpeed();
        void decreaseSpeed();
    }


    private SensorManager mSensorManager;
    private Sensor sensor;

    long timeStampMovement = 0;
    long timeStampSpeed = 0;

    private CallBack_movementStep callBack_movementStep;
    private CallBack_speedStep callBack_speedStep;

    /**
     * Step detector constructor
     * @param context the context of the activity or application or service
     * @param _callBack_movementSteps the listener to steps
     * @param _callBack_speedSteps the listener to steps
     */
    public StepDetector(Context context, CallBack_movementStep _callBack_movementSteps, CallBack_speedStep _callBack_speedSteps) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.callBack_movementStep = _callBack_movementSteps;
        this.callBack_speedStep = _callBack_speedSteps;
    }

    /**
     * register to the sensors
     */
    public void start() {
        mSensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * unregister to the sensors
     */
    public void stop() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float z = event.values[2];

            calculateMovementStep(x);
            calculateSpeedStep(z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void calculateMovementStep(float x) {
        if (x > 5.0) {
            if (System.currentTimeMillis() - timeStampMovement > 50) {
                timeStampMovement = System.currentTimeMillis();
                if (callBack_movementStep != null) {
                    callBack_movementStep.oneStepLeft();
                }
            }
        }

        if (x < -5.0) {
            if (System.currentTimeMillis() - timeStampMovement > 50) {
                timeStampMovement = System.currentTimeMillis();
                if (callBack_movementStep != null) {
                    callBack_movementStep.oneStepRight();
                }
            }
        }
    }

    private void calculateSpeedStep(float x) {
        if (x > 1.0) {
            if (System.currentTimeMillis() - timeStampMovement > 100) {
                timeStampMovement = System.currentTimeMillis();
                if (callBack_speedStep != null) {
                    callBack_speedStep.increaseSpeed();
                }
            }
        }

        if (x < -1.0) {
            if (System.currentTimeMillis() - timeStampMovement > 100) {
                timeStampMovement = System.currentTimeMillis();
                if (callBack_speedStep != null) {
                    callBack_speedStep.decreaseSpeed();
                }
            }
        }
    }
}
