package sid.com.bowlerperfornceanalysis.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sid.com.bowlerperfornceanalysis.Model.Score;
import sid.com.bowlerperfornceanalysis.R;
import sid.com.bowlerperfornceanalysis.ViewHolder.ScoreVH;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreVH> {
    List<Score> scoreList;
    Context context;

    public ScoreAdapter(List<Score> scoreList, Context context) {
        this.scoreList = scoreList;
        this.context = context;
    }




    @NonNull
    @Override
    public ScoreVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bowlerperformancelayout,viewGroup,false);
        return new ScoreVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreVH scoreVH, int position) {
        Score scores = scoreList.get(position);
        scoreVH.coordinatetv.setText(scores.getCoordinates());
        scoreVH.runs.setText(scores.getRuns());
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }
}
