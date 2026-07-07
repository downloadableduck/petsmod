package com.jeff.pets.mob;

public class WalkAnimationState {
    private float speed;
    private int pos;

    public WalkAnimationState() {
        this.speed = 0;
        this.pos = 0;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getPosition() {
        return this.pos;
    }
    public boolean isMoving() {
        return this.speed > 1.0E-5F;
    }

    public float speed() {
        return this.getSpeed();
    }

    public float position() {
        return this.getPosition();
    }
}
