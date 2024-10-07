import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
//neaten imports

public class DodgeBall extends GraphicsProgram implements ActionListener {
    private ArrayList<GOval> balls;
    private ArrayList<GRect> enemies;
    private GLabel text;
    private Timer movement;
    private RandomGenerator rgen;

    public static final int SIZE = 25;
    public static final int SPEED = 2;
    public static final int MS = 50;
    public static final int MAX_ENEMIES = 10;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 300;

    public void run() {
        rgen = RandomGenerator.getInstance();
        balls = new ArrayList<>();
        enemies = new ArrayList<>();
        text = new GLabel("" + enemies.size(), 0, WINDOW_HEIGHT);
        add(text);

        movement = new Timer(MS, this);
        movement.start();

        addMouseListeners();
    }

    public void actionPerformed(ActionEvent e) {
        moveAllBallsOnce();
        moveAllEnemiesOnce();
        checkCollisions();

        if (enemies.size() < MAX_ENEMIES) {
            addAnEnemy();
        }
    }

    public void mousePressed(MouseEvent e) {
        for (GOval b : balls) {
            if (b.getX() < SIZE * 2.5) {
                return;  
            }
        }
        addABall(e.getY());  
    }

    private void addABall(double y) {
        GOval ball = makeBall(SIZE / 2, y);
        add(ball);
        balls.add(ball);
    }

    public GOval makeBall(double x, double y) {
        GOval temp = new GOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        temp.setColor(Color.RED);
        temp.setFilled(true);
        return temp;
    }

    private void addAnEnemy() {
        double randomY = rgen.nextDouble(SIZE, WINDOW_HEIGHT - SIZE);
        GRect newEnemy = new GRect(WINDOW_WIDTH - SIZE, randomY - SIZE / 2, SIZE, SIZE);
        newEnemy.setFilled(true);
        newEnemy.setFillColor(rgen.nextColor());  //assigns random colors to the enemy
        enemies.add(newEnemy);
        add(newEnemy);
        text.setLabel("" + enemies.size());  
    }

    private void moveAllBallsOnce() {
        for (GOval ball : balls) {
            ball.move(SPEED, 0);
        }
    }

    private void moveAllEnemiesOnce() {
        for (GRect enemy : enemies) {
            int deltaY = rgen.nextInt(-2, 2);
            enemy.move(0, deltaY);
        }
    }

    private void checkCollisions() {
        ArrayList<GRect> toRemove = new ArrayList<>();
        for (GOval ball : balls) {
            double checkX = ball.getX() + SIZE;  
            double checkY = ball.getY() + SIZE / 2;
            GObject obj = getElementAt(checkX, checkY);
            if (obj instanceof GRect) {
                toRemove.add((GRect) obj);
            }
        }
        for (GRect enemy : toRemove) {
            remove(enemy);
            enemies.remove(enemy);
            text.setLabel("" + enemies.size());  
        }
    }

    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public static void main(String[] args) {
        new DodgeBall().start();
    }
}

