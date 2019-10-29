package trap1.luphilip.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Bar extends RectF {
    private int x = 0;
    private int y = 0;
    private int length = 0;
    private int dx;
    private Paint barpaint = new Paint();

    public Bar(int x1, int y1, int length1, int color) {
        super(x1, y1, x1, y1 + length1);
        this.dx = -5;
        barpaint.setColor(color);
        barpaint.setStrokeWidth(8);
    }

    public void drawBar(Canvas canvas) {
        canvas.drawLine(left, bottom, right, top, barpaint);
        offset(dx, 0);
        if(left <= 0) {
            left = canvas.getWidth();
            right = canvas.getWidth();
        }
    }

    public void setPaintColor(int c) {
        this.barpaint.setColor(c);
    }


}
