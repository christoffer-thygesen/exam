package yellow.sausages.com.exam;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Christoffer on 20/01/2018.
 */

public class ExamListAdapter extends ArrayAdapter<String> {

    public ExamListAdapter(@NonNull Context context, ArrayList<String> data) {
        super(context, R.layout.exam_list_item, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.exam_list_item, parent, false);

        String line = getItem(position);
        TextView textView = rowView.findViewById(R.id.nameText);
        TextView colorView = rowView.findViewById(R.id.colorText);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        colorView.setBackgroundColor(color);
        textView.setText(line);

        return rowView;
    }
}
