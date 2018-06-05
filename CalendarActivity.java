package nikhi.a5_minutestressreliever;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



//event recorder for this week
public class CalendarActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private SQLiteDatabase newDB;
    private String tableName = FeedReaderContract.FeedEntry.TABLE_NAME;
    Switch switchPandP;
    Switch switchExercise;
    Switch switchSocial;
    Switch switchMindfulness;
    RecyclerView mRecyclerView;


    private ListView list;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        int tint1 = Color.parseColor("#3F51B5"); // R.color.blue;
        int tint2 = Color.parseColor("#E91E63"); // R.color.blue;
        int tint3 = Color.parseColor("#FFA000"); // R.color.blue;
        int tint4 = Color.parseColor("#8BC34A"); // R.color.blue;

        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
        // add your drawable resources you wish to tint to the drawables array...
        int drawables[] = { R.drawable.runner, R.drawable.draw, R.drawable.mind, R.drawable.social};
        for (int id : drawables) {
            Drawable icon = getResources().getDrawable(id);
            if (id == R.drawable.runner)
                icon.setColorFilter(tint3, mode);
            if (id == R.drawable.draw)
                icon.setColorFilter(tint1, mode);
            if (id == R.drawable.mind)
                icon.setColorFilter(tint4, mode);
            if (id == R.drawable.social)
                icon.setColorFilter(tint2, mode);
        }

        switchPandP = findViewById(R.id.switchPandP);
        switchExercise = findViewById(R.id.switchExercise);
        switchSocial = findViewById(R.id.switchSocial);
        switchMindfulness = findViewById(R.id.switchMindfulness);
        switchPandP.setChecked(true);
        switchExercise.setChecked(true);
        switchSocial.setChecked(true);
        switchMindfulness.setChecked(true);
        //adapter.notifyDataSetChanged();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener()

                {
                    @Override
                    public boolean onNavigationItemSelected (MenuItem item){

                        switch (item.getItemId()) {
                            case R.id.action_history:
                                Intent startNewActivity1 = new Intent(getApplicationContext(), CalendarActivity.class);
                                startActivity(startNewActivity1);
                                break;

                            case R.id.action_today:
                                Intent startNewActivity2 = new Intent(getApplicationContext(), PickerActivity.class);
                                startActivity(startNewActivity2);
                                break;
                            case R.id.About_app:
                                Intent startNewActivity3 = new Intent(getApplicationContext(), AboutActivity.class);
                                startActivity(startNewActivity3);
                                break;

                        }
                        return true;
                    }
                });
        //list = (ListView) findViewById(R.id.listView);


        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data

        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
        //list.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Context context = getApplicationContext();
            CharSequence text = "Your activity has been recorded!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);

        }
        //}




        update();

        switchPandP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                update();
            }
        });
        switchExercise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                update();
            }
        });
        switchSocial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                update();
            }
        });
        switchMindfulness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                update();
            }
        });





    }



    private void openAndQueryDatabase() {
        try {
            FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
            newDB = mDbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("select * from " + tableName, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String startLocation = c.getString(c.getColumnIndex("date"));
                        String endLocation = c.getString(c.getColumnIndex("activity"));



                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            //if (newDB != null)
            //newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }


    }

    public void newScreen(View view){
            Intent startNewActivity = new Intent(this, StressActivity.class);
            startActivity(startNewActivity);
            }
    public void update() {
        try {
            arrayList.clear();
            FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
            newDB = mDbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("select * from " + FeedReaderContract.FeedEntry.TABLE_NAME, null);
            Object value1 = "", value2 = "", value3 = "", value4 = "";
            List<Object> values2;

            boolean pandp = switchPandP.isChecked();
            boolean exercise = switchExercise.isChecked();
            boolean social = switchSocial.isChecked();
            boolean mindfulness = switchMindfulness.isChecked();

            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        value1 = c.getString(c.getColumnIndex("date"));
                        value2 = c.getString(c.getColumnIndex("activity"));
                        value3 = c.getString(c.getColumnIndex("category"));
                        value4 = c.getString(c.getColumnIndex("rating"));

                        System.out.println(value1);


                        values2 = Arrays.asList(

                                value1, value2, value3, value4
                                // Cell values ....
                                // Additional rows ...
                        );
                        if ((value3.equals("Paper and Pencil") && pandp) || (value3.equals("Exercise") && exercise) || (value3.equals("Social") && social) || (value3.equals("Mindfulness") && mindfulness)) {
                            {
                                String temp = value3 + (String) values2.get(0) + "  " + values2.get(1);
                                String addition = "";
                                if (value4.equals("1"))
                                    addition = getResources().getString(R.string.onestar);
                                if (value4.equals("2"))
                                    addition = getResources().getString(R.string.twostar);

                                if (value4.equals("3"))
                                    addition = getResources().getString(R.string.threestar);

                                if (value4.equals("4"))
                                    addition = getResources().getString(R.string.fourstar);
                                arrayList.add(temp + " " + addition);
                            }

                        }

                    } while (c.moveToNext());
                    Collections.reverse(arrayList);



                }

            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            //if (newDB != null)
            //newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }













        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter<PlanetViewHolder>() {

            @Override
            public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1,
                        parent,
                        false);
                PlanetViewHolder vh = new PlanetViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(PlanetViewHolder vh, int position) {
                TextView tv = (TextView) vh.itemView;
                String temp = arrayList.get(position);
                if (temp.contains("Paper and Pencil")) {
                    tv.setText("  " + temp.substring(16));
                    tv.setTextSize(13);
                    tv.setTextColor(getResources().getColor(R.color.art));
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.draw, 0, 0, 0);
                }
                if (temp.contains("Exercise")) {
                    tv.setText("  " + temp.substring(8));
                    tv.setTextColor(getResources().getColor(R.color.exercise));
                    tv.setTextSize(13);
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.runner, 0, 0, 0);
                }
                if (temp.contains("Social")) {
                    tv.setText("  " + temp.substring(6));
                    tv.setTextColor(getResources().getColor(R.color.social));
                    tv.setTextSize(13);
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.social, 0, 0, 0);
                }
                if (temp.contains("Mindfulness")) {
                    tv.setText("  " + temp.substring(11));
                    tv.setTextColor(getResources().getColor(R.color.mindfulness));
                    tv.setTextSize(13);
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mind, 0, 0, 0);
                }
            }

            @Override
            public int getItemCount() {
                return arrayList.size();
            }
        });

        // this line adds the data of your EditText and puts in your array
        // next thing you have to do is check if your adapter has changed
    }
    private class PlanetViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public PlanetViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(),
                    //"You have clicked " + ((TextView) v).getText(),
                    //Toast.LENGTH_LONG).show();
        }
    }

}

