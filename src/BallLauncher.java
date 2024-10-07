import acm.graphics.*;
import acm.program.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BallLauncher extends GraphicsProgram {

    public static final int SIZE = 25;
    public static final int SPEED = 2;
    public static final int MS = 50;
    public static final int ENEMY_CREATION_INTERVAL = 40; //new constant 
    private ArrayList<GOval> balls;

    public void run() {
        balls = new ArrayList<>();  
        addMouseListeners();
        Timer timer = new Timer(MS, e -> moveBalls());
        timer.start();
    }

    public void mousePressed(MouseEvent e) {
        for (GOval ball : balls) {
            if (ball.getX() < 100) {
                return; 
            }
        }

        GOval newBall = makeBall(SIZE / 2, e.getY());
        balls.add(newBall);  
        add(newBall); 
    }

    public GOval makeBall(double x, double y) {
        GOval ball = new GOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        ball.setFilled(true);
        ball.setFillColor(Color.RED);
        return ball;
    }

    private void moveBalls() {
        for (GOval ball : balls) {
            ball.move(SPEED, 0);  
        }
    }
}

