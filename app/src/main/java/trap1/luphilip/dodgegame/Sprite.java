package trap1.luphilip.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.DrawableWrapper;

public class Sprite extends RectF {

        private int dx;
        private int dy;
        private int horBound;
        private int vertBound;
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
//            super(0,0, DrawView.getGridSpacing(), DrawView.getGridSpacing());
//            super(0,0,250,250);
            this.dx = 20;
            this.dy = 20;
            this.paint = new Paint();
            this.paint.setColor(Color.BLUE);
        }

        public void drawBall(Canvas canvas) {
                canvas.drawCircle(centerX(), centerY(), width() / 2, this.paint);
        }


//        public void projUpdate(Canvas canvas) {
//            drawBall(canvas);
//            offset(dx, dy);
//            if(right + dx >= canvas.getWidth()) {
//
//            }
//            if(left + dx <= width()/2 ) {
//
//            }
//            if(top + dy >= canvas.getHeight() - width()/2) {
//
//            }
//            if(bottom + dx >= width()/2) {
//
//            }
//        }

        public void moveRight() {
            if(right < horBound) {
                offset(DrawView.getGridSpacing(), 0);
            }
        }
        public void moveLeft() {
            if(left > 0) {
                offset(-DrawView.getGridSpacing(), 0);
            }
        }
        public void moveUp() {
            if(top > 0) {
                offset(0, -DrawView.getGridSpacing());
            }
        }
        public void moveDown() {
            if(bottom < vertBound) {
                offset(0, DrawView.getGridSpacing());
            }
        }





        public void setCoordinates(int leftX, int topY, int rightX, int bottomY) {
            left = leftX; top = topY; right = rightX; bottom = bottomY;
        }

        public void setdx(int newdx) {
            this.dx = newdx;
        }

        public void setdy(int newdy) {
            this.dy = newdy;
        }
        public void setHorBound(int newHorBound) {
            this.horBound = newHorBound;
        }
        public void setVertBound(int newVertBound) {
            this.vertBound = newVertBound;
        }

}
