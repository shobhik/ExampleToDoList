package shobhik.exampletodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private TextView tv2;
    private Context mContext;
//    private ArrayList<String> values = new ArrayList<String>();
    private ArrayList<ListItemModel> values2 = new ArrayList<ListItemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = (TextView) findViewById(R.id.main_text2);
        tv2 = (TextView) findViewById(R.id.main_text);
        Firebase myFirebaseRef = new Firebase("https://vivid-torch-8283.firebaseio.com/");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Go to the List!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(mContext, ListActivity.class));
            }
        });
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        Log.v("Ex", "Preset Item!");

//        values.add("Item 1");
//        values.add("Item 2");
        for(int i = 1; i < 11; i++) {
            ListItemModel li = new ListItemModel("Title "+i, "Text " + i);
            values2.add(li);
        }
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                tv.setText("hello");
                tv2.setText("hi");
                Log.v("Ex", "Set Item!");
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
//        myFirebaseRef.child("message").setValue("Data!");
        myFirebaseRef.child("message").setValue(values2);
        Toast.makeText(mContext, "Added items to list online!", Toast.LENGTH_SHORT).show();
        Log.v("Ex", "PostSet Item!");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
