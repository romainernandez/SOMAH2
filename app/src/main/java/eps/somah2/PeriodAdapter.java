package eps.somah2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rernande on 09/11/2016.
 */

public class PeriodAdapter extends ArrayAdapter<Period> {

    public int actualID;

    public PeriodAdapter(Context context, int resource, List<Period> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.period, null);
        Period period = getItem(position);

        int [] arrayColors={Color.parseColor("#F05282"), Color.parseColor("#FFB84C"), Color.parseColor("#4284D2"), Color.parseColor("#785AB4"), Color.parseColor("#8BB8CD"), Color.parseColor("#FF5967"), Color.parseColor("#3ECCCD"), Color.parseColor("#68BF60"), Color.parseColor("#BF5FB6"), Color.parseColor("#3D3D3B"), Color.parseColor("#FF98A7"), Color.parseColor("#EDECD4"), Color.parseColor("#9EE3C5"), Color.parseColor("#86A6DA")};

        final ImageView imageView1 = (ImageView) view.findViewById(R.id.periodImageView1);
        final TextView textView = (TextView) view.findViewById(R.id.periodTextView);
        final ImageView imageView2 = (ImageView) view.findViewById(R.id.periodImageView2);

        imageView1.setBackgroundResource(period.getImage());
        textView.setText(period.getName());
        imageView2.setBackgroundColor(arrayColors[position]);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContext().startActivity(new Intent(view.getContext(), AllCategoriesActivity.class));
            }
        });
        return view;
    }
}