package nikhi.a5_minutestressreliever;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import nikhi.a5_minutestressreliever.FeedReaderContract.FeedEntry;

public class RelaxActivity extends Activity {

    private RatingBar ratingBar;
    private TextView ratingValue;
    private Button button;
    private ContentValues values;
    private String date;
    private String desc;
    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Intent intent = getIntent();
            date = intent.getStringExtra("date");
            desc = intent.getStringExtra("desc");
            name = intent.getStringExtra("name");

        }

        addListenerOnRatingBar();
        addListenerOnButton();

    }

    public void addListenerOnRatingBar() {

        ratingBar = findViewById(R.id.ratingBar);
        ratingValue = findViewById(R.id.txtRatingValue);


    }

    public void addListenerOnButton() {

        ratingBar = findViewById(R.id.ratingBar);
        button = findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase newDB = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(FeedEntry.COLUMN1_NAME_TITLE, date);
                values.put(FeedEntry.COLUMN2_NAME_TITLE, desc);
                values.put(FeedEntry.COLUMN3_NAME_TITLE, name);
                values.put(FeedReaderContract.FeedEntry.COLUMN4_NAME_TITLE, String.valueOf((int)ratingBar.getRating()));

                System.out.println(values);


                // Insert the new row, returning the primary key value of the new row
                long newRowId = newDB.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

/*                  double previousCost = 0.00;

                  if (c != null) {
                      if (c.moveToFirst()) {
                          do {

                              previousCost = c.getDouble(c.getColumnIndex("total"));


                          } while (c.moveToNext());
                      }
                  }
                  /** ADDING THE NEW ROW **/

                // Create a new map of values, where column names are the keys
                Intent i = new Intent(RelaxActivity.this, CalendarActivity.class);
                i.putExtra("key",String.valueOf(ratingBar.getRating()));
                startActivity(i);
            }

        });

    }
}