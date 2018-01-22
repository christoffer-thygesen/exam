package yellow.sausages.com.exam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Christoffer on 19/01/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String[] items;

    public RecyclerViewAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recyclerview_item, parent, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textView.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    //viewholder
    public class Item extends RecyclerView.ViewHolder {
        TextView textView;
        public Item(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }
    }
}
