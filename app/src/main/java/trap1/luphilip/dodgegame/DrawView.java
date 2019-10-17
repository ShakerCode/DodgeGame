package trap1.luphilip.dodgegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DrawView extends View {
    Sprite ball = new Sprite();
    Sprite checker = new Sprite();
    Paint paint = new Paint();
    Paint barpaint = new Paint();
    ArrayList<Bar> bars = new ArrayList<Bar>();

    private boolean first = true;
    private static int gridSize = 0;
    private static int boardSize = 0;
    private static int gridSpacing = 0;
    int x = 0; int y = 0;
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

//        canvas.drawCircle(gridSpacing * 3, boardSize + 175, 100, paint);

        //Horizontal
        for(int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(0, i * gridSpacing, boardSize, i * gridSpacing, paint);
        }

        //Vertical
        for(int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(i * gridSpacing, 0, i * gridSpacing, boardSize, paint);
        }

        //Bars
        for(Bar b: bars) {
            b.drawBars(canvas);
        }

        if(first) {
            checker.setCoordinates(gridSpacing * 3, boardSize + 175, gridSpacing * 3, boardSize + 275);
            ball.setCoordinates(x, y, gridSpacing, gridSpacing);
            addBars();
            ball.setHorBound(boardSize);
            ball.setVertBound(boardSize);
            first = false;
        }
        checker.drawBall(canvas);
        ball.drawBall(canvas);
        invalidate();
    }

    public void moveLeft() {
        ball.moveLeft();
    }
    public void moveRight() {
        ball.moveRight();
    }
    public void moveUp() {
        ball.moveUp();
    }
    public void moveDown() {
        ball.moveDown();
    }

    public void addBars() {
        for(int i = 0; i < gridSize; i++) {
            bars.add(new Bar(i * gridSpacing, boardSize + 90, gridSpacing ));
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
