package home.ferh.tictactoe.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ferh on 17.04.14.
 */
public class TitleActivity extends Activity implements View.OnTouchListener {
    ImageView newGameButton, settingsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        newGameButton = (ImageView) findViewById(R.id.NewGame);
        settingsButton = (ImageView) findViewById(R.id.Settings);

        newGameButton.setOnTouchListener(this);
        settingsButton.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (v.getId() == R.id.NewGame)
                    newGameButton.setBackgroundResource(R.drawable.menu_new_game_pressed);
                else {
                    settingsButton.setBackgroundResource(R.drawable.menu_settings_pressed);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (v.getId() == R.id.NewGame) {
                    newGameButton.setBackgroundResource(R.drawable.menu_new_game);
                    intent = new Intent("game");
                    startActivity(intent);
                } else {
                    settingsButton.setBackgroundResource(R.drawable.menu_settings);
                    //intent = new Intent("settings");
                    //startActivity(intent);
                }
                break;
        }
        return true;
    }
}