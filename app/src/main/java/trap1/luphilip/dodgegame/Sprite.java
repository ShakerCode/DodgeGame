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
        private Paint paint = new Paint();
        private boolean canMove;

        public Sprite(int left, int top, int right, int bottom, int c) {
            super(left, top, right, bottom);
            this.dx = 50;
            this.dy = 50;
            this.paint.setColor(c);

        }

        public Sprite(int c) {
//            super(0,0, DrawView.getGridSpacing(), DrawView.getGridSpacing());
//            super(0,0,250,250);
            this.dx = 20;
            this.dy = 20;
            this.paint.setColor(c);
        }

        public void drawBall(Canvas canvas) {
                canvas.drawCircle(centerX(), centerY(), width() / 2, this.paint);
        }

        public void randomMove() {
            int randomNum = (int)(Math.random() * 4) + 1;
//            System.out.println(randomNum);
            switch(randomNum) {
                case 1:
                    if(right < horBound)
                        offset(DrawView.getGridSpacing(),0);
                    break;
                case 2:
                    if(left > 0)
                        offset(-DrawView.getGridSpacing(), 0);
                    break;
                case 3:
                    if(top > 0)
                        offset(0, -DrawView.getGridSpacing());
                    break;
                case 4:
                    if(bottom < vertBound)
                        offset(0, DrawView.getGridSpacing());
                    break;
                default:
                    offset(0,0);
                    break;
            }
        }

        public int moveRight() {
            if(right < horBound) {
                offset(DrawView.getGridSpacing(), 0);
                if(canMove) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
        public int moveLeft() {
            if(left > 0) {
                offset(-DrawView.getGridSpacing(), 0);
                if(canMove) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
        public int moveUp() {
            if(top > 0) {
                offset(0, -DrawView.getGridSpacing());
                if(canMove) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
        public int moveDown() {
            if(bottom < vertBound) {
                offset(0, DrawView.getGridSpacing());
                if(canMove) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
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

        public void setPaintColor(int c) {
            this.paint.setColor(c);
        }
        public int getPaintColor() {
            return this.paint.getColor();
        }
        public void setCanMove(boolean b) {
            canMove = b;
        }


}
