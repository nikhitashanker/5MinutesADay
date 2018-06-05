package nikhi.a5_minutestressreliever;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class PickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        final RadioGroup buttonGroup = findViewById(R.id.radioGroup);
        final Button button1 = findViewById(R.id.penButton);
        final Button button2 = findViewById(R.id.exerciseButton);
        final Button button3 = findViewById(R.id.socialButton);
        final Button button4 = findViewById(R.id.meditationButton);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newScreen1(v);
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newScreen2(v);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newScreen3(v);
            }

        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newScreen4(v);
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
    public void newScreen1(View view){
        Intent startNewActivity = new Intent(this, StressActivity.class);
        startActivity(startNewActivity);
    }

    public void newScreen2(View view){
        Intent startNewActivity = new Intent(this, ExerciseActivity.class);
        startActivity(startNewActivity);
    }

    public void newScreen3(View view){
        Intent startNewActivity = new Intent(this, SocialActivity.class);
        startActivity(startNewActivity);
    }
    public void newScreen4(View view){
        Intent startNewActivity = new Intent(this, MindfulnessActivity.class);
        startActivity(startNewActivity);
    }


}
