import acm.graphics.*;
import java.awt.Color;

public class TimerBall extends GOval {

    public TimerBall(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFilled(true);
        this.setColor(Color.RED); 
    }
}
