import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Controller{
    private final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");

    public final double radius=64;
    private double x=radius,y=radius;
    private double dx=1,dy=1;
    private Circle ball=new Circle(x,y,radius);

    @FXML
    public Label timestamp;

    @FXML
    public Pane thePane;

    @FXML
    private void initialize(){
        ball.setFill(Color.rgb(64,64,0,0.5));
        thePane.getChildren().add(ball);

        //Each timeline should be a thread.
        Timeline updaterClock=new Timeline(new KeyFrame(Duration.millis(5),event->updateClock()));
        updaterClock.setCycleCount(Timeline.INDEFINITE);
        updaterClock.play();

        Timeline aniBall=new Timeline(new KeyFrame(Duration.millis(5),event->moveBall()));
        aniBall.setCycleCount(Timeline.INDEFINITE);
        aniBall.play();
    }

    public void updateClock(){
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        timestamp.setText(sdf.format(ts));
    }

    public void moveBall(){
        // Check boundaries
        if (x<radius||x>thePane.getWidth()-radius){
            dx *= -1;//Change ball move direction
        }
        if (y<radius||y>thePane.getHeight()-radius){
            dy *= -1;//Change ball move direction
        }

        //Adjust ball position
        x+=dx;
        y+=dy;
        ball.setCenterX(x);
        ball.setCenterY(y);
    }
}