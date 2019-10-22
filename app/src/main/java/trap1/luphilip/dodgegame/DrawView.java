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
    Paint barpaint = new Paint();
    ArrayList<Bar> bars = new ArrayList<Bar>();
    ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    Sprite firstObstacle;

    private boolean first = true;
    private boolean addObst = true;
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
        paint.setTextSize(80);
        int height = getHeight();
        int width = getWidth();
        gridSize = 6;
        gridSpacing = Math.min(width, height) / gridSize;
        boardSize = gridSize * gridSpacing;

        //Set up
        if (first) {
            checker.setCoordinates(gridSpacing * 3 - gridSpacing / 2 + 60, boardSize + 140,
                    gridSpacing * 3 + gridSpacing / 2 - 60, boardSize + 140 + gridSpacing / 2 - 60);
            ball.setCoordinates(0,0, gridSpacing, gridSpacing);
            addBars();
            firstObstacle = new Sprite(boardSize-gridSpacing,0,boardSize,gridSpacing, Color.RED);
            firstObstacle.setHorBound(boardSize);
            firstObstacle.setVertBound(boardSize);
            ball.setHorBound(boardSize);
            ball.setVertBound(boardSize);
            obstacles.add(firstObstacle);
            first = false;
        }

        checker.drawBall(canvas);
        ball.drawBall(canvas);

        System.out.println(obstacles);


        // FIX
        if(addObst && score >= 5 && score % 5 == 0) {
            addObstacle();
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
            b.drawBars(canvas, checker);
        }
        for(Bar b: bars) {
            if(RectF.intersects(checker, b)) {
                checker.setPaintColor(Color.WHITE);
                ball.setCanMove(true);
                break;
            } else {
                checker.setPaintColor(Color.BLACK);
                ball.setCanMove(false);
            }
        }

        //Obstacles
        for(Sprite s: obstacles) {
            s.drawBall(canvas);
        }

        canvas.drawText("Score: " + score,920, 2050, paint);
        invalidate();
    }

    public void moveLeft() {
        score += ball.moveLeft();
        randomMove();
    }

    public void moveRight() {
        score += ball.moveRight();
        randomMove();
    }

    public void moveUp() {
        score += ball.moveUp();
        randomMove();
    }

    public void moveDown() {
        score += ball.moveDown();
        randomMove();
    }

    public void addBars() {
        for (int i = 0; i < gridSize; i++) {
//            if(i != 3) {
                bars.add(new Bar(i * gridSpacing, boardSize + 90, gridSpacing, Color.WHITE));
//            }

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
        for(Sprite s: obstacles) {
            s.randomMove();
        }
    }

    public void addObstacle() {
        Sprite newObstacle = new Sprite(boardSize-gridSpacing,0,boardSize,gridSpacing, Color.RED);
        newObstacle.setHorBound(boardSize);
        newObstacle.setVertBound(boardSize);
        obstacles.add(newObstacle);
    }


}
