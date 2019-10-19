package trap1.luphilip.dodgegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button moveLeft;
    Button moveRight;
    Button moveUp;
    Button moveDown;
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        moveLeft = findViewById(R.id.LEFT);
        moveRight = findViewById(R.id.RIGHT);
        moveUp = findViewById(R.id.UP);
        moveDown = findViewById(R.id.DOWN);
        drawView = findViewById(R.id.drawView);

        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.moveLeft();
            }
        });
        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.moveRight();
            }
        });
        moveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.moveUp();
            }
        });
        moveDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.moveDown();
            }
        });



    }
}
