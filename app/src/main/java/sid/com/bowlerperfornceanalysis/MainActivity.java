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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import sid.com.bowlerperfornceanalysis.Adapter.ScoreAdapter;
import sid.com.bowlerperfornceanalysis.Common.Common;
import sid.com.bowlerperfornceanalysis.Model.Score;

public class MainActivity extends AppCompatActivity {
    List<Score> scoreList =  new ArrayList<>();
    RecyclerView scoreRecyclerView;
    ScoreAdapter scoreAdapter;
    FloatingActionButton addScoreButton,clearScoreButton;
    EditText CoordEtv,runsEtv;
    Button ChangeBowlerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeBowlerButton = findViewById(R.id.ChangeBowlerButton);
        scoreRecyclerView = (RecyclerView)findViewById(R.id.BowlerRecyclerView);

        ChangeBowlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changemyDialog();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        scoreRecyclerView.setLayoutManager(layoutManager);

        scoreRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));




        scoreAdapter =  new ScoreAdapter(scoreList, MainActivity.this);

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
                if(scoreList.size() != 0)
                scoreList.remove(scoreList.size()-1);
                scoreRecyclerView.setAdapter(scoreAdapter);
            }
        });


    }

    private void changemyDialog() {
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Enter Name:");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_comment = inflater.inflate(R.layout.changebowlerlayout,null);

         final EditText ChangebwlrEdtv = (EditText) order_address_comment.findViewById(R.id.ChangeBwlrEdit);

        alertDialog.setView(order_address_comment);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!ChangebwlrEdtv.getText().toString().trim().isEmpty()) {
                    ChangeBowlerButton.setText(ChangebwlrEdtv.getText().toString());
                    Common.currentBowlerName = ChangebwlrEdtv.getText().toString();
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
//        alertDialog.setMessage("");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_comment = inflater.inflate(R.layout.dialoglayout,null);

         final EditText DialogCoord = (EditText) order_address_comment.findViewById(R.id.DialogCoord);
         final EditText DialogRuns = (EditText) order_address_comment.findViewById(R.id.DialogRuns);

        alertDialog.setView(order_address_comment);
//        alertDialog.setIcon(R.drawable.ic_add_black_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Create new Request
//                Toast.makeText(MainActivity.this, DialogCoord.getText().toString(), Toast.LENGTH_SHORT).show();
                addScores(DialogCoord.getText().toString(),DialogRuns.getText().toString());
//                Common.currentBowlerName
                
                sendRecords();
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

    private void sendRecords() {
        String url="http://mysqlauthentication.000webhostapp.com/chintan/Histdataretrieval.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
//                params.put("dbname", Paper.book().read(Common.CLIENT_KEY).toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void addScores(String coord,String runs) {
        CoordEtv = (EditText)findViewById(R.id.DialogCoord);
        runsEtv = (EditText)findViewById(R.id.DialogRuns);

        scoreList.add(new Score(coord,runs));
        scoreRecyclerView.setAdapter(scoreAdapter);
    }
    
    
}
