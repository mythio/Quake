package com.example.mythio.quake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeHolder> {

    private Context context;
    private List<Earthquake> earthquakes;

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        this.context = context;
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public EarthquakeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.earthquake_list_item, viewGroup, false);
        return new EarthquakeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeHolder earthquakeHolder, int i) {

        GradientDrawable drawable = (GradientDrawable) earthquakeHolder.mTextViewMagnitude.getBackground();
        Earthquake earthquake = earthquakes.get(i);
        drawable.setColor(context.getResources().getColor(R.color.magnitude6));

        switch ((int) Math.floor(Double.valueOf(earthquake.getmMagnitude()))) {
            case 1:
                drawable.setColor(context.getResources().getColor(R.color.magnitude1));
                break;
            case 2:
                drawable.setColor(context.getResources().getColor(R.color.magnitude2));
                break;
            case 3:
                drawable.setColor(context.getResources().getColor(R.color.magnitude3));
                break;
            case 4:
                drawable.setColor(context.getResources().getColor(R.color.magnitude4));
                break;
            case 5:
                drawable.setColor(context.getResources().getColor(R.color.magnitude5));
                break;
            case 6:
                drawable.setColor(context.getResources().getColor(R.color.magnitude6));
                break;
            case 7:
                drawable.setColor(context.getResources().getColor(R.color.magnitude7));
                break;
            case 8:
                drawable.setColor(context.getResources().getColor(R.color.magnitude8));
                break;
            case 9:
                drawable.setColor(context.getResources().getColor(R.color.magnitude9));
                break;
            default:
                drawable.setColor(context.getResources().getColor(R.color.magnitude4));
                break;
        }

        earthquakeHolder.mTextViewMagnitude.setText(earthquake.getmMagnitude());
        earthquakeHolder.mTextViewLocationOffset.setText(earthquake.getmLocationOffset());
        earthquakeHolder.mTextViewLocation.setText(earthquake.getmLocation());
        earthquakeHolder.mTextViewDate.setText(earthquake.getmDate());
        earthquakeHolder.mTextViewTime.setText(earthquake.getmTime());
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public class EarthquakeHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewMagnitude;
        private TextView mTextViewLocationOffset;
        private TextView mTextViewLocation;
        private TextView mTextViewDate;
        private TextView mTextViewTime;

        public EarthquakeHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewMagnitude = itemView.findViewById(R.id.text_view_magnitude);
            mTextViewLocationOffset = itemView.findViewById(R.id.text_view_location_offset);
            mTextViewLocation = itemView.findViewById(R.id.text_view_location);
            mTextViewDate = itemView.findViewById(R.id.text_view_date);
            mTextViewTime = itemView.findViewById(R.id.text_view_time);
        }
    }
}
