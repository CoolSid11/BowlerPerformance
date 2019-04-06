package sid.com.bowlerperfornceanalysis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

        calculate();
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

    private void openDialog(Context context) {
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
                addScores(DialogCoord.getText().toString(), DialogRuns.getText().toString());
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

    private void calculate() {
        int counter = 0;

        List<Score> scorelist0 = new ArrayList<>();
        scorelist0.add(new Score("1", "7"));
        scorelist0.add(new Score("2", "10"));
        scorelist0.add(new Score("3", "5"));
        scorelist0.add(new Score("4", "-5"));
        scorelist0.add(new Score("2", "5"));

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

        List<Score> scoreList1 = new ArrayList<>();
        scoreList1.add(new Score("1", "3"));
        scoreList1.add(new Score("2", "13"));
        scoreList1.add(new Score("3", "5"));
        scoreList1.add(new Score("4", "-10"));
        scoreList1.add(new Score("2", "9"));

        float[] arrayArea1 = new float[28];
        float[] arrayCount1 = new float[28];

        for (Score scor1 : scoreList1) {
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
