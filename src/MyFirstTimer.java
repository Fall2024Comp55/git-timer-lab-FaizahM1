import acm.graphics.*;
import acm.program.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFirstTimer extends GraphicsProgram implements ActionListener {
    public static final int PROGRAM_HEIGHT = 600;
    public static final int PROGRAM_WIDTH = 800;

    private GLabel myLabel;
    private TimerBall ball;
    private TimerBall ball2;
    private Timer timer;
    private int steps;
    private final int DELAY = 500; //delay of 500ms between each tick 
    private final int MAX_STEPS = 10; //stop timer after 10 steps

    public void init() {
        setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        requestFocus();
    }

    public void run() {
        myLabel = new GLabel("times called: 0", 0, 100);
        add(myLabel);

        ball = new TimerBall(20, 30, 20, 20); 
        add(ball);

        ball2 = new TimerBall(20, 60, 30, 30); 
        add(ball2);

        //create a timer with initial delay of 3000 ms
        timer = new Timer(DELAY, this);
        timer.setInitialDelay(3000); //set a 3-second delay before starting
        steps = 0; //initialize step count
        timer.start(); // start the timer
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        steps++; //increment step count
        myLabel.setLabel("times called: " + steps);
        ball.move(5, 0); 
        ball2.move(5, 0);

        //stop the timer after MAX_STEPS times
        if (steps >= MAX_STEPS) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        new MyFirstTimer().start();
    }
}
