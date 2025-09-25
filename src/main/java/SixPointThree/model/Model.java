package SixPointThree.model;

public class Model {
    private double x, y;     // current position
    private double targetX, targetY;
    private boolean hasTarget = false;

    public Model(double startX, double startY) {
        this.x = startX;
        this.y = startY;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getTargetX() { return targetX; }
    public double getTargetY() { return targetY; }
    public boolean hasTarget() { return hasTarget; }

    public void setTarget(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        this.hasTarget = true;
    }

    public void clearTarget() { this.hasTarget = false; }

    // move a bit toward target
    public void step(double stepSize) {
        if (!hasTarget) return;
        double dx = targetX - x;
        double dy = targetY - y;
        double dist = Math.hypot(dx, dy);
        if (dist <= stepSize) {
            x = targetX; y = targetY;
            hasTarget = false; // reached
        } else {
            x += dx / dist * stepSize;
            y += dy / dist * stepSize;
        }
    }
}
