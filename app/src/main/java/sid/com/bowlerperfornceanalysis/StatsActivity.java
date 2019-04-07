package sid.com.bowlerperfornceanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import sid.com.bowlerperfornceanalysis.Common.Common;

public class StatsActivity extends AppCompatActivity {
    TextView area0,smallest0,avg0,area1,smallest1,avg1,totalarea,totalsmallest,totalavg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        area0 = findViewById(R.id.Area0);
        smallest0 = findViewById(R.id.Smallest0);
        avg0 = findViewById(R.id.Avg0);

        area1 = findViewById(R.id.Area1);
        smallest1 = findViewById(R.id.Smallest1);
        avg1 = findViewById(R.id.Avg1);

        totalarea = findViewById(R.id.totalArea);
        totalsmallest = findViewById(R.id.totalSmallest);
        totalavg = findViewById(R.id.totalAvg);

        //Set tet

        area0.setText(Common.staticArea0);
        smallest0.setText(Common.staticSmallest0);
        avg0.setText(Common.staticAvg0);

        area1.setText(Common.staticArea1);
        smallest1.setText(Common.staticSmallest1);
        avg1.setText(Common.staticAvg1);

        totalarea.setText(Common.staticTotalArea);
        totalsmallest.setText(Common.staticTotalSmallest);
        totalavg.setText(Common.staticTotalAvg);

        Log.d("*Smallest0",Common.staticSmallest0);
        Log.d("*Area0",Common.staticArea0);
        Log.d("*Avg0",Common.staticAvg0);

        Log.d("*Smallest1",Common.staticSmallest0);
        Log.d("*Area1",Common.staticArea1);
        Log.d("*Avg1",Common.staticAvg1);

        Log.d("*total area:",Common.staticTotalArea);
        Toast.makeText(this, Common.staticTotalSmallest, Toast.LENGTH_SHORT).show();
//        Log.d("*total smallest:",Common.staticTotalSmallest);
        Log.d("*Total avg:",Common.staticTotalAvg);



    }
}
