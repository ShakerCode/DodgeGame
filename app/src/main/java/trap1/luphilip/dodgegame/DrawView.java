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

    private boolean first = true;
    private static int gridSize = 0;
    private static int boardSize = 0;
    private static int gridSpacing = 0;
    private int score = 0;
    int x = 0;
    int y = 0;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        paint.setTextSize(30);
        int height = getHeight();
        int width = getWidth();
        gridSize = 6;
        gridSpacing = Math.min(width, height) / gridSize;
        boardSize = gridSize * gridSpacing;

        //Set up
        if (first) {
            checker.setCoordinates(gridSpacing * 3 - gridSpacing / 2 + 30, boardSize + 130,
                    gridSpacing * 3 + gridSpacing / 2 - 30, boardSize + 130 + gridSpacing / 2 - 30);
            ball.setCoordinates(x, y, gridSpacing, gridSpacing);
            addBars();
            ball.setHorBound(boardSize);
            ball.setVertBound(boardSize);
            first = false;
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
//                checker.setCanMove(true);
                break;
            } else {
                checker.setPaintColor(Color.BLACK);
//                checker.setCanMove(false);
            }
        }
        System.out.println(ball.getPaintColor());
        checker.drawBall(canvas);
        ball.drawBall(canvas);
        canvas.drawText("Score: " + score, 750, 1000, paint);
        invalidate();
    }

    public void moveLeft() {
        score += ball.moveLeft();
    }

    public void moveRight() {
        score += ball.moveRight();
    }

    public void moveUp() {
        score += ball.moveUp();
    }

    public void moveDown() {
        score += ball.moveDown();
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


}
