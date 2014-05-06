package home.ferh.tictactoe.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ferh on 17.04.14.
 */
public class GameActivity extends Activity implements View.OnClickListener {
    boolean isXTurn;
    ImageView imgViewButtons[][];
    TextView txtView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        imgViewButtons = new ImageView[3][3];
        txtView = (TextView) findViewById(R.id.textView);
        int i = 0;
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++) {
                imgViewButtons[j][k] = (ImageView) findViewById(R.id.imageView1 + (i++));
                imgViewButtons[j][k].setOnClickListener(this);
                imgViewButtons[j][k].setTag(i + 1);
            }
        isXTurn = true;
        txtView.setText("Waiting for 'x");
    }

    private String isEnd() {
        for (int i = 0; i < 3; i++) {
            if (imgViewButtons[i][0].getTag() == imgViewButtons[i][1].getTag() &&
                    imgViewButtons[i][1].getTag() == imgViewButtons[i][2].getTag())
                return imgViewButtons[i][0].getTag().toString();
            else if (imgViewButtons[0][i].getTag() == imgViewButtons[1][i].getTag() &&
                    imgViewButtons[1][i].getTag() == imgViewButtons[2][i].getTag())
                return imgViewButtons[0][i].getTag().toString();
        }
        if (imgViewButtons[0][0].getTag() == imgViewButtons[1][1].getTag() &&
                imgViewButtons[1][1].getTag() == imgViewButtons[2][2].getTag() ||
                imgViewButtons[2][0].getTag() == imgViewButtons[1][1].getTag() &&
                        imgViewButtons[1][1].getTag() == imgViewButtons[0][2].getTag())
            return imgViewButtons[1][1].getTag().toString();
        for (ImageView[] a : imgViewButtons)
            for (ImageView b : a)
                if (b.getTag() != "'X' wins!" && b.getTag() != "'O' wins!")
                    return null;
        return "Draw!";
    }

    @Override
    public void onClick(View view) {
        for (ImageView[] a : imgViewButtons)
            for (ImageView b : a)
                if (b.equals(view))
                    if (isXTurn) {
                        b.setBackgroundResource(R.drawable.x);
                        b.setTag("'X' wins!");
                        b.setClickable(false);
                        isXTurn = false;
                        txtView.setText("Waiting for 'o");
                    } else {
                        b.setBackgroundResource(R.drawable.o);
                        b.setTag("'O' wins!");
                        b.setClickable(false);
                        isXTurn = true;
                        txtView.setText("Waiting for 'x");
                    }
        if (isEnd() != null) {
            for (ImageView[] a : imgViewButtons)
                for (ImageView b : a)
                    if (b.getTag() != "'X' wins!" && b.getTag() != "'O' wins!")
                        b.setClickable(false);
            txtView.setText(isEnd());
        }
    }
}