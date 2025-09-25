package SixPointThree.controller;

import SixPointThree.model.Model;
import SixPointThree.view.View;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class Controller {
    private final Model model;
    private final View view;

    private long lastNs = 0;
    private static final double SPEED = 150; // pixels per second

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void attach(Scene scene) {
        scene.setOnMouseMoved(e -> model.setTarget(e.getX(), e.getY()));
        scene.setOnMouseExited(e -> model.clearTarget());

        new AnimationTimer() {
            @Override public void handle(long now) {
                if (lastNs == 0) { lastNs = now; return; }
                double dt = (now - lastNs) / 1_000_000_000.0;
                lastNs = now;

                model.step(SPEED * dt);
                view.draw(model.getX(), model.getY(),
                        model.hasTarget() ? new double[]{model.getTargetX(), model.getTargetY()} : null);
            }
        }.start();
    }
}
