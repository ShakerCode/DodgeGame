package trap1.luphilip.dodgegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DrawView extends View {
    Sprite ball = new Sprite(Color.BLUE);
    Sprite checker = new Sprite(Color.BLACK);
    Paint paint = new Paint();
    ArrayList<Bar> bars = new ArrayList<Bar>();
    ArrayList<Sprite> obstacles = new ArrayList<Sprite>();

    private boolean first = true;
    private boolean addObst = true;
    private boolean end = false;
    private static int gridSize = 0;
    private static int boardSize = 0;
    private static int gridSpacing = 0;
    private int score = 0;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        int height = getHeight();
        int width = getWidth();
        gridSize = 6;
        gridSpacing = Math.min(width, height) / gridSize;
        boardSize = gridSize * gridSpacing;
        paint.setTextSize(50);

        //Set up
        if (first) {
            System.out.println("here");
            //Radius of checker is gridSpacing/4.
            //Spacing between bars and board is gridSpacing/2
            checker.setCoordinates(getWidth() / 2 - gridSpacing / 4, boardSize + gridSpacing / 2 + gridSpacing / 4,
                    getWidth() / 2 + gridSpacing / 4, boardSize + gridSpacing / 2 + gridSpacing / 4 + gridSpacing / 2);
            ball.setCoordinates(0, 0, gridSpacing, gridSpacing);
            addBars();
            ball.setHorBound(boardSize);
            ball.setVertBound(boardSize);
            first = false;
        }

        checker.drawBall(canvas);
        ball.drawBall(canvas);

//        System.out.println(obstacles);


        // FIX
        if (addObst) {
            addObstacle();
            addObst = false;
        }

        //Horizontal
        for (int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(0, i * gridSpacing, boardSize, i * gridSpacing, paint);
        }

        //Vertical
        for (int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(i * gridSpacing, 0, i * gridSpacing, boardSize, paint);
        }
        //Bars

        for (Bar b : bars) {
            b.drawBar(canvas);
        }

        for (Bar b : bars) {
            if (RectF.intersects(checker, b)) {
                checker.setPaintColor(Color.WHITE);
                ball.setCanMove(true);
                break;
            } else {
                checker.setPaintColor(Color.BLACK);
                ball.setCanMove(false);
            }
        }

        //Obstacles
        for (Sprite s : obstacles) {
            s.drawBall(canvas);
        }

        for(Sprite s: obstacles) {
            if(RectF.intersects(ball, s)) {
                end = true;
                break;
            }
        }

        if(end) {
            canvas.drawText("Game Over!", getWidth() - 400, getHeight() - 350, paint);
            canvas.drawText("Score: " + score, getWidth() - 400, getHeight() - 300, paint);
        } else {
            canvas.drawText("Score: " + score, getWidth() - 400, getHeight() - 300, paint);
            invalidate();

        }

    }

    public void moveLeft() {
        score += ball.moveLeft();
        checkAddObst();
        randomMove();
    }

    public void moveRight() {
        score += ball.moveRight();
        checkAddObst();
        randomMove();
    }

    public void moveUp() {
        score += ball.moveUp();
        checkAddObst();
        randomMove();
    }

    public void moveDown() {
        score += ball.moveDown();
        checkAddObst();
        randomMove();
    }

    public void addBars() {
        for (int i = 0; i < gridSize; i++) {
            bars.add(new Bar(i * gridSpacing, boardSize + gridSpacing / 2, gridSpacing, Color.WHITE));
        }
    }


    public static int getGridSpacing() {
        return gridSpacing;
    }

    public static int getBoardSize() {
        return boardSize;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public void randomMove() {
        for (Sprite s : obstacles) {
            s.randomMove();
        }
    }

    public void addObstacle() {
        Sprite newObstacle = new Sprite(boardSize - gridSpacing, 0, boardSize, gridSpacing, Color.RED);
        newObstacle.setHorBound(boardSize);
        newObstacle.setVertBound(boardSize);
        obstacles.add(newObstacle);
    }

    public void checkAddObst() {
        if (score >= 5 && score % 5 == 0)
            addObst = true;
    }


}
