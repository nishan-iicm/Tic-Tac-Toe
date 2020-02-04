package com.example.addition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button[][] buttons=new Button[3][3];

        private boolean player1turn = true;

        private  int roundCount;

        private int player1Points;
        private  int player2Points;

        private  TextView textViewPlayer1;
        private  TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        int i;
        for (i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i +j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(!((Button) view).getText().toString().equals(""))
        {
            return;

        }
        if(player1turn)
        {
            ((Button)view).setText("X");

        }
        else
        {
            ((Button)view).setText("O");

        }

        roundCount++;
        if(checkForWin())
        {
            if(player1turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();
            }

        }
        else if(roundCount==9)
        {
            draw();

        }
        else{
            player1turn=!player1turn;
        }

    }
    private boolean checkForWin() {
        String[][] feild = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                feild[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (feild[i][0].equals(feild[i][1])
                    && feild[i][0].equals(feild[i][2])
                    && !feild[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (feild[0][i].equals(feild[1][i])
                    && feild[0][i].equals(feild[2][i])
                    && !feild[0][i].equals("")) {
                return true;
            }
        }
        if (feild[0][0].equals(feild[1][1])
                && feild[0][0].equals(feild[2][2])
                && !feild[0][0].equals("")) {
            return true;
        }
        if (feild[0][2].equals(feild[1][1])
                && feild[0][2].equals(feild[2][0])
                && !feild[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private  void player1Wins()
    {
        player1Points++;
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }



    private  void player2Wins()
    {
        player2Points++;
        Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }
    private void draw()
    {
        Toast.makeText(this,"DRAW!",Toast.LENGTH_SHORT).show();
    }
    private void updatePointsText()
    {
        textViewPlayer1.setText("Player 1: "+player1Points);
        textViewPlayer2.setText("Player 2: "+player2Points);

    }


    private void resetBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1turn=true;
    }
    private void resetGame()
    {
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }
}
