package yellow.sausages.com.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String[] items = {"item 0", "item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7", "item 8", "item 9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(this, items));
    }
}
