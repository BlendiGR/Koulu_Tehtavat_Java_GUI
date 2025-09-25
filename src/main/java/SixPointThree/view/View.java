package SixPointThree.view;

import SixPointThree.controller.Controller;
import SixPointThree.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View extends Application {
    private static final int W = 600, H = 400;
    private Canvas canvas;
    private GraphicsContext g;
    private Image dog;

    @Override
    public void start(Stage stage) {
        dog = new Image("dog.png");

        canvas = new Canvas(W, H);
        g = canvas.getGraphicsContext2D();

        Model model = new Model(W/2.0, H/2.0);
        Controller controller = new Controller(model, this);

        Scene scene = new Scene(new StackPane(canvas));
        stage.setScene(scene);
        stage.setTitle("Dog MVC");
        stage.show();

        controller.attach(scene);
        draw(model.getX(), model.getY(), null);
    }

    public void draw(double x, double y, double[] target) {
        g.setFill(Color.BEIGE);
        g.fillRect(0, 0, W, H);

        if (target != null) {
            g.setStroke(Color.RED);
            g.strokeOval(target[0]-6, target[1]-6, 12, 12);
        }

        g.drawImage(dog, x-20, y-20, 40, 40);
    }

}
