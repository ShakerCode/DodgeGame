package trap1.luphilip.dodgegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class DrawView extends View {
    Sprite ball = new Sprite();
    Paint paint = new Paint();
    public static int gridSpacing = 0;
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
        int gridSize = 6;
        gridSpacing = Math.min(width, height) / gridSize;
        int boardSize = gridSize * gridSpacing;

        //Horizontal
        for(int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(0, i * gridSpacing, boardSize, i * gridSpacing, paint);
        }

        //Vertical
        for(int i = 0; i < gridSize + 1; i++) {
            canvas.drawLine(i * gridSpacing, 0, i * gridSpacing, boardSize, paint);
        }


        ball.setCoordinates(x, y, gridSpacing, gridSpacing);
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

    public static int getGridSpacing() {
        return gridSpacing;
    }


}
