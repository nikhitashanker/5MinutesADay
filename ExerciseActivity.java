package nikhi.a5_minutestressreliever;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
public class ExerciseActivity extends AppCompatActivity {
    ArrayList<Suggest> suggestPenAndPaper;
    ArrayList<Suggest> suggestExercise;
    ArrayList<Suggest> suggest1; //suggestMeditation;
    ArrayList<Suggest> suggestSocial;
    ArrayList<Suggest> suggestLearn;
    private SQLiteDatabase newDB;
    private String tableName = FeedReaderContract.FeedEntry.TABLE_NAME;


    int currValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress);
        suggestPenAndPaper = new ArrayList<Suggest>();
        suggestExercise = new ArrayList<Suggest>();
        suggest1 = new ArrayList<Suggest>();
        suggestSocial = new ArrayList<Suggest>();
        suggestLearn = new ArrayList<Suggest>();

        //suggest1.add(new Suggest("Go on a walk", 1));
        //suggest1.add(new Suggest("Do as many jumping jacks as you can in a minute. ", 2));
        //suggest1.add(new Suggest("Draw a butterfly", 3));
        Resources res = getResources();
        //Drawable flowerpower = res.getDrawable(R.drawable.jumprope);
        //Drawable musicmixture = res.getDrawable(R.drawable.dance);
        //Drawable googlefordoodle = res.getDrawable(R.drawable.hulahoop);
        //Drawable card = res.getDrawable(R.drawable.run);
        //Drawable color = res.getDrawable(R.drawable.toes);

        suggest1.add(new Suggest("Jump rope for 5 minutes at a comfortable pace. If you are feeling adventurous, try some tricks like the crossover or the side swing.", "Can you do a crossover?", R.drawable.jumprope, "Exercise"));
        suggest1.add(new Suggest("Find your favorite dance song online and play it. Then dance to it for five minutes. Don't hold back.", "That's my jam!", R.drawable.dance, "Exercise"));
        suggest1.add(new Suggest("Hula hoop for 5 minutes at a comfortable pace. If you are feeling adventurous, try hula hooping with multiple hoops.", "Hula hoop it up!", R.drawable.hulahoop, "Exercise"));
        suggest1.add(new Suggest("Run outside or run on a treadmill for 5 minutes at a comfortable pace. Be sure to stay hydrated!", "Run small step for man", R.drawable.run, "Exercise"));
        suggest1.add(new Suggest("Stand up. Try to touch you toes while keeping your knees straight. Stay in that position for as long as you feel comfortable. Repeat for five minutes. If this is too easy, try to touch your head to your knees, without bending your knees, of course.", "Stretch it out", R.drawable.toes, "Exercise"));


        //suggest1.add(new Suggest("Do jumping jacks", 5));
        //suggest1.add(new Suggest("Read a book", 6));
        Random rand = new Random();
        currValue = rand.nextInt(5);


        TextView valueTV = findViewById(R.id.simpleTextView);
        TextView textName= findViewById(R.id.simpleTextViewSentence);

        ImageView icon = findViewById(R.id.icon);
        icon.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), suggest1.get(currValue).getImage(), 100, 100));        valueTV.setText(suggest1.get(currValue).getDesc());
        textName.setText(suggest1.get(currValue).getName());


        final Button button = findViewById(R.id.changeButton2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Suggest temp = suggest1.remove(currValue);
                Random rand = new Random();
                currValue = rand.nextInt(4);

                TextView valueTV2 = findViewById(R.id.simpleTextViewSentence);

                TextView valueTV = findViewById(R.id.simpleTextView);
                ImageView icon = findViewById(R.id.icon);
                icon.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), suggest1.get(currValue).getImage(), 100, 100));                valueTV.setText(suggest1.get(currValue).getDesc());
                valueTV2.setText(suggest1.get(currValue).getName());

                suggest1.add(temp);
            }

        });    // Code here executes on main thread after user presses button
        final Button button2 = findViewById(R.id.changeButton);


        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                newDB = mDbHelper.getWritableDatabase();


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

                //clearing has been commented out
                String value="Hello world";
                Intent i = new Intent(ExerciseActivity.this, RelaxActivity.class);
                i.putExtra("date",getDate());
                i.putExtra("desc",suggest1.get(currValue).getDesc());
                i.putExtra("name",suggest1.get(currValue).getCategory());

                startActivity(i);
            }

        });

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

    }


    public void newScreenStressActivity(View view) {

    }

    public void newScreenCalendarActivity(View view) {

    }
    public String getDate()
    {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }



}