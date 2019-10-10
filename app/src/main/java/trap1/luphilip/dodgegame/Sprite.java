package trap1.luphilip.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite extends RectF {

        private int dx;
        private int dy;
        private Paint paint;
        DrawView drawView;

        public Sprite(int left, int top, int right, int bottom) {
            super(left, top, right, bottom);
            this.dx = 50;
            this.dy = 50;
            this.paint = new Paint();
            this.paint.setColor(Color.BLUE);

        }

        public Sprite() {
            super(0,0, DrawView.getGridSpacing(), DrawView.getGridSpacing());
            this.dx = 50;
            this.dy = 50;
            this.paint = new Paint();
            this.paint.setColor(Color.BLUE);
        }

        public void drawBall(Canvas canvas) {
            canvas.drawCircle(centerX(), centerY(), width()/2, this.paint);
        }
        public void moveRight() {
            offset(dx, 0);
        }
        public void moveLeft() {
            offset(-dx,0);
        }
        public void moveUp() {
            offset(0, -dy);
        }
        public void moveDown() {
            offset(0,dy);
        }

        public void setCoordinates(int leftX, int topY, int rightX, int bottomY) {
            left = leftX; top = topY; right = rightX; bottom = bottomY;
        }
}
