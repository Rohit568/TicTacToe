package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.session.MediaController;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn[][];
    Button resetBtn;
    boolean PLAYER  = true;
    int TURN_CNT = 0;
    int boardStatus[][] = null;
    TextView mDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = new Button[3][3];
        btn[0][0] = (Button)findViewById(R.id.button1);
        btn[0][1] = (Button)findViewById(R.id.button2);
        btn[0][2] = (Button)findViewById(R.id.button3);
        btn[1][0] = (Button)findViewById(R.id.button4);
        btn[1][1] = (Button)findViewById(R.id.button5);
        btn[1][2] = (Button)findViewById(R.id.button6);
        btn[2][0] = (Button)findViewById(R.id.button7);
        btn[2][1] = (Button)findViewById(R.id.button8);
        btn[2][2] = (Button)findViewById(R.id.button9);
        mDisplay = (TextView)findViewById(R.id.display);
        boardStatus = new int[3][3];
        for(int i = 0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                btn[i][j].setOnClickListener(this);
            }
        }
        initializeBoardStatus();
       resetBtn = (Button) findViewById(R.id.reset);

       resetBtn.setOnClickListener(this);

    }

    public void initializeBoardStatus()
    {
        TURN_CNT = 0;
        for(int i=0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                boardStatus[i][j] = -1;
                btn[i][j].setEnabled(true);
                btn[i][j].setText("");
            }
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.reset : initializeBoardStatus();
                               PLAYER = true;
                               updateDisplay("Player X turn");
                               break;
            case R.id.button1 : updateUI(0, 0, PLAYER);
                              break;
            case R.id.button2 : updateUI(0, 1, PLAYER);
                            break;
            case R.id.button3 :  updateUI(0, 2, PLAYER);
                            break;
            case  R.id.button4 :  updateUI(1, 0, PLAYER);
                            break;
            case  R.id.button5 :  updateUI(1, 1, PLAYER);
                            break;
            case R.id.button6 :  updateUI(1, 2, PLAYER);
                            break;
            case  R.id.button7 :  updateUI(2, 0, PLAYER);
                            break;
            case R.id.button8 :  updateUI(2, 1, PLAYER);
                             break;
            case  R.id.button9 :  updateUI(2, 2, PLAYER);
                           break;

        }
        PLAYER = !PLAYER;
        TURN_CNT++;


            if (PLAYER)
                updateDisplay("Player X Turn");
            else
                updateDisplay("Player Y Turn");
            if (TURN_CNT == 9)
                updateDisplay("Match Draw");

        int status = checkWinner(boardStatus);
        if(status!= -2)
        {
            if(status == 1)
                updateDisplay("Player Y won");
            else
                updateDisplay("Player X Won");
            for(int i =0; i<3; i++)
                for(int j = 0; j<3; j++)
                {
                    btn[i][j].setEnabled(false);
                }

        }


    }

    private int checkWinner(int[][] boardStatus) {

        if(boardStatus[0][0] != -1 && boardStatus[0][0] == boardStatus[0][1] && boardStatus[0][1] == boardStatus[0][2])
            return PLAYER?1:0;
        else if(boardStatus[1][0] != -1 && boardStatus[1][0] == boardStatus[1][1] && boardStatus[0][1] == boardStatus[1][2])
            return PLAYER?1:0;

        else if(boardStatus[2][0] != -1 && boardStatus[2][0] == boardStatus[2][1] && boardStatus[2][1] == boardStatus[2][2])
            return PLAYER?1:0;

        else if(boardStatus[1][0] != -1 && boardStatus[0][0] == boardStatus[1][0] && boardStatus[1][0] == boardStatus[2][0])
            return PLAYER?1:0;

       else if(boardStatus[1][0] != -1 && boardStatus[0][1] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][1])
            return PLAYER?1:0;

        else if(boardStatus[1][2] != -1 && boardStatus[0][2] == boardStatus[1][2] && boardStatus[1][2] == boardStatus[2][2])
            return PLAYER?1:0;

        else if(boardStatus[1][1] != -1 && boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2])
            return PLAYER?1:0;
        else if(boardStatus[1][1] != -1 && boardStatus[0][2] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[1][1])
            return PLAYER?1:0;
        return -2;
    }

    private void updateDisplay(String str) {
        mDisplay.setText(str);
    }

    private void updateUI(int i, int i1, boolean player) {
        String str = player? "X" : "O";
         int val = player ? 1: 0;
        btn[i][i1].setText(str);
        btn[i][i1].setEnabled(false);
        boardStatus[i][i1] = val;
    }
}