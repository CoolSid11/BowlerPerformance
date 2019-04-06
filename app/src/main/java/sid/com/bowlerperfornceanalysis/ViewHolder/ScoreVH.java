package sid.com.bowlerperfornceanalysis.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sid.com.bowlerperfornceanalysis.R;

public class ScoreVH extends RecyclerView.ViewHolder {
    public TextView coordinatetv,runs;

    public ScoreVH(@NonNull View itemView) {
        super(itemView);

        coordinatetv = itemView.findViewById(R.id.Coordtv);
        runs = itemView.findViewById(R.id.Runtv);


    }
}
