package eps.somah2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.category, null);
        Category category = getItem(position);

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        final TextView textView = (TextView) view.findViewById(R.id.textView);

        imageView.setBackgroundResource(category.getImage());
        textView.setText(category.getName());

        return view;
    }
}
