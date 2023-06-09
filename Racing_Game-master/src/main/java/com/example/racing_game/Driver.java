package com.example.racing_game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Driver extends Application {


    private static final double W = 1500, H = 700;
    private static final String carImageLoc = "C:\\Users\\Chris\\Desktop\\#Schule\\3AHITN\\itp2\\javafx\\Racing_Game-master\\Racing_Game-master\\images\\03_RedBull.png";
    private Image carImage;
    private Node car;

    boolean faster, goUp, goDown, goRight, goLeft;

    @Override
    public void start(Stage stage) throws Exception{
        carImage = new Image(carImageLoc, 125, 200, false, false);
        car = new ImageView(carImage);

        Group track = new Group(car);
        moveCarTo(W / 2, H/2);
        Scene scene = new Scene(track, W, H, Color.LIMEGREEN);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    //case UP: goUp = true; break;
                    //case DOWN: goDown = true; break;
                    case LEFT: goLeft = true; break;
                    case RIGHT: goRight = true; break;
                    case SHIFT: faster = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    //case UP: goUp = false; break;
                    //case DOWN: goDown = false; break;
                    case LEFT: goLeft = false; break;
                    case RIGHT: goRight = false; break;
                    case SHIFT: faster = false; break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                //if(goUp) dy = dy - 1;
                //if(goDown) dy = dy + 1;
                if(goLeft) dx = dx - 3;
                if(goRight) dx = dx + 3;
                if(faster){
                    dx = dx * 3;
                    dy = dy * 3;
                }
                moveCarBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveCarBy(int dx, int dy){
        if(dx == 0 && dy == 0)return;

        final double cx = car.getBoundsInLocal().getWidth() / 2;
        final double cy = car.getBoundsInLocal().getHeight() / 2;

        double x = cx + car.getLayoutX() + dx;
        double y = cy + car.getLayoutY() + dy;

        moveCarTo(x, y);
    }

    private void moveCarTo(double x, double y){
        final double cx = car.getBoundsInLocal().getWidth() / 2;
        final double cy = car.getBoundsInLocal().getHeight() / 2;

        // Position prüfen
        if (x - cx >= 0 && x + cx <= W && y - cy >= 0 && y + cy <= H){
            car.relocate(x - cx, y - cy);
        }
    }

    /*
    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);


        stage.setScene(scene);
        stage.show();



    }

     */

    public static void main(String[] args) {
        launch();
    }
}

