package eps.somah2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by rernande on 21/10/2016.
 */

public class CategoryAdapter extends ArrayAdapter<Category>
{
    public CategoryAdapter(Context context, int resource, List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.category, null);
        Category category = getItem(position);

        int [] arrayColors={Color.parseColor("#00bfff"), Color.parseColor("#ff6600"), Color.parseColor("#00cc99"), Color.parseColor("#ffcc00"), Color.parseColor("#0066cc"), Color.parseColor("#ff0066")};

        final ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        final TextView textView = (TextView) view.findViewById(R.id.textView);
        final ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);

        imageView1.setBackgroundResource(category.getImage());
        textView.setText(category.getName());
        imageView2.setBackgroundColor(arrayColors[position]);
        //category.setImage(getResources().getIdentifier("image"+(i+1), "drawable", getPackageName()));

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("setOnClickListener", Integer.toString(position));
                getContext().startActivity(new Intent(view.getContext(), WelcomeActivity.class));
            }
        });

        return view;
    }


}
