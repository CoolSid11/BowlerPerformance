package sid.com.bowlerperfornceanalysis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import sid.com.bowlerperfornceanalysis.Adapter.ScoreAdapter;
import sid.com.bowlerperfornceanalysis.Common.Common;
import sid.com.bowlerperfornceanalysis.Model.Score;

public class MainActivity extends AppCompatActivity {
    List<Score> scoreList = new ArrayList<>();
    List<Score> scorelist0 = new ArrayList<>();
    List<Score> scorelist1 = new ArrayList<>();
    RecyclerView scoreRecyclerView;
    ScoreAdapter scoreAdapter;
    FloatingActionButton addScoreButton, clearScoreButton;
    EditText CoordEtv, runsEtv;
    Button ChangeBowlerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeBowlerButton = findViewById(R.id.ChangeBowlerButton);
        scoreRecyclerView = (RecyclerView) findViewById(R.id.BowlerRecyclerView);

        ChangeBowlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changemyDialog();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        scoreRecyclerView.setLayoutManager(layoutManager);

        scoreRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        scoreAdapter = new ScoreAdapter(scoreList, MainActivity.this);

        scoreRecyclerView.setAdapter(scoreAdapter);

        addScoreButton = findViewById(R.id.AddScore);
        clearScoreButton = findViewById(R.id.ClearList);

        addScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(MainActivity.this);
            }
        });

        clearScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreList.size() != 0)
                    scoreList.remove(scoreList.size() - 1);
                scoreRecyclerView.setAdapter(scoreAdapter);
            }
        });

    }

    private void changemyDialog() {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Enter Name:");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_comment = inflater.inflate(R.layout.changebowlerlayout, null);

        final EditText ChangebwlrEdtv = (EditText) order_address_comment.findViewById(R.id.ChangeBwlrEdit);

        alertDialog.setView(order_address_comment);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!ChangebwlrEdtv.getText().toString().trim().isEmpty()) {
                    ChangeBowlerButton.setText(ChangebwlrEdtv.getText().toString());
                    Common.currentBowlerName = ChangebwlrEdtv.getText().toString();

                } else {
                    Toast.makeText(MainActivity.this, "Please enter fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ChangeBowlerButton.setText("Bowler Name");
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();

    }

    private void openDialog(final Context context) {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Enter Details:");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_comment = inflater.inflate(R.layout.dialoglayout, null);

        final EditText DialogCoord = (EditText) order_address_comment.findViewById(R.id.DialogCoord);
        final EditText DialogRuns = (EditText) order_address_comment.findViewById(R.id.DialogRuns);

        alertDialog.setView(order_address_comment);
//        alertDialog.setIcon(R.drawable.ic_add_black_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!DialogCoord.getText().toString().isEmpty() || !DialogRuns.getText().toString().isEmpty()){
                    addScores(DialogCoord.getText().toString(), DialogRuns.getText().toString());

                    if(ChangeBowlerButton.getText().toString().equals(String.valueOf(0))){
                        scorelist0.add(new Score(DialogCoord.getText().toString(),DialogRuns.getText().toString()));
                    }
                    else if(ChangeBowlerButton.getText().toString().equals(String.valueOf(1))){
                        scorelist1.add(new Score(DialogCoord.getText().toString(),DialogRuns.getText().toString()));
                    }
                    else {
                        Toast.makeText(context, "Bowler Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(context, "Blamk fields not allowed!", Toast.LENGTH_SHORT).show();
                }



            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resultmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        startActivity(new Intent(MainActivity.this,ResultActivity.class));
        calculate();
        Toast.makeText(this, Common.staticArea0, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,Common.staticArea1,Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    private void calculate() {
        int counter = 0;


        float[] arrayArea0 = new float[28];
        float[] arrayCount0 = new float[28];

        for (Score scor0 : scorelist0) {
            arrayArea0[Integer.parseInt(scor0.getCoordinates()) - 1] = arrayArea0[Integer.parseInt(scor0.getCoordinates()) - 1]
                    + Float.parseFloat(scor0.getRuns());

            arrayCount0[Integer.parseInt(scor0.getCoordinates()) - 1]++;
        }
        for (Float area0 : arrayArea0) {
            System.out.println(counter + ":" + area0);
            counter++;
        }
        System.out.println("--------------");
        counter = 0;
        for (Float count : arrayCount0) {
            System.out.println(counter + ":" + count);
            counter++;
        }
        float smallest0 = arrayArea0[0] / arrayCount0[0];
        int area0 = 0;
        int bowlerno0 = 0;
        float sumRuns0 = 0;
        float sumCount0 = 0;
        System.out.println("Avg0:----------------");
        if (bowlerno0 == 0) {
            for (int i = 0; i < 28; i++) {

                sumRuns0 = sumRuns0 + arrayArea0[i];
                sumCount0 = sumCount0 + arrayCount0[i];

                System.out.println(arrayArea0[i] / arrayCount0[i]);
                if (smallest0 > arrayArea0[i] / arrayCount0[i]) {
                    smallest0 = arrayArea0[i] / arrayCount0[i];
                    area0 = i + 1;
                }

            }
        }
        System.out.println("The best area0 is " + area0 + " with economy of " + smallest0);
        System.out.println("The bowler economy0 is " + sumRuns0 / sumCount0);

        Common.staticArea0 = "Area0: "+String.valueOf(area0);
        Common.staticSmallest0 = "Smallest0: "+String.valueOf(smallest0);
        Common.staticAvg0="Avg0:"+String.valueOf(sumRuns0 / sumCount0);

        float[] arrayArea1 = new float[28];
        float[] arrayCount1 = new float[28];

        for (Score scor1 : scorelist1) {
            arrayArea1[Integer.parseInt(scor1.getCoordinates()) - 1] = arrayArea1[Integer.parseInt(scor1.getCoordinates()) - 1]
                    + Float.parseFloat(scor1.getRuns());

            arrayCount1[Integer.parseInt(scor1.getCoordinates()) - 1]++;
        }
        counter = 0;
        for (Float area1 : arrayArea1) {
            System.out.println(counter + ":" + area1);
            counter++;
        }
        System.out.println("--------------");
        counter = 0;
        for (Float count : arrayCount1) {
            System.out.println(counter + ":" + count);
            counter++;
        }
//-------------------------------------------------------------------------------------------------------------------------------------
        float smallest1 = arrayArea1[0] / arrayCount1[0];
        int area1 = 0;
        int bowlerno1 = 1;
        float sumRuns1 = 0;
        float sumCount1 = 0;
        System.out.println("Avg1:----------------");
        if (bowlerno1 == 1) {
            for (int i = 0; i < 28; i++) {

                sumRuns1 = sumRuns1 + arrayArea1[i];
                sumCount1 = sumCount1 + arrayCount1[i];

                System.out.println(arrayArea1[i] / arrayCount1[i]);
                if (smallest1 > arrayArea1[i] / arrayCount1[i]) {
                    smallest1 = arrayArea1[i] / arrayCount1[i];
                    area1 = i + 1;
                }

            }

            System.out.println("The best area1 is " + area1 + " with economy of " + smallest1);
            System.out.println("The bowler economy1 is " + sumRuns1 / sumCount1);

            Common.staticArea1 = "Area1: "+String.valueOf(area1);
            Common.staticSmallest1 = "Smallest1: "+String.valueOf(smallest1);
            Common.staticSmallest1="Avg1:"+String.valueOf(sumRuns0 / sumCount1);
        }

        float[] totalArea = new float[28];
        float[] totalCount = new float[28];
        int cou = 0;
        for (int j = 0; j < totalArea.length; j++) {
            totalArea[j] = arrayArea0[j] + arrayArea1[j];
            System.out.println("SumArea:" + cou + ":" + totalArea[j]);
            cou++;
        }

        cou = 0;
        for (int j = 0; j < totalCount.length; j++) {
            totalCount[j] = arrayCount0[j] + arrayCount1[j];
            System.out.println("SumCount:" + cou + ":" + totalArea[j]);
            cou++;
        }

        float totalsmallest = totalArea[0] / totalCount[0];
        int totalare = 0;
        float totalsumRuns = 0;
        float totalcoun = 0;
        System.out.println("--------------");
        System.out.println("Avg1:----------------");
        for (int i = 0; i < arrayArea1.length; i++) {

            totalsumRuns = totalsumRuns + totalArea[i];
            totalcoun = totalcoun + totalCount[i];

            System.out.println(totalArea[i] / totalCount[i]);
            if (totalsmallest > totalArea[i] / totalCount[i]) {
                totalsmallest = totalArea[i] / totalCount[i];
                totalare = i + 1;
            }

        }
        System.out.println("The best areaT is " + totalare + " with economy of " + totalsmallest);
        System.out.println("The bowler economyT is " + totalsumRuns / totalcoun);

        Log.d("*areaT",String.valueOf(totalare));
    }

    private void addScores(String coord, String runs) {
        CoordEtv = (EditText) findViewById(R.id.DialogCoord);
        runsEtv = (EditText) findViewById(R.id.DialogRuns);

        scoreList.add(new Score(coord, runs));
        scoreRecyclerView.setAdapter(scoreAdapter);
    }


}
