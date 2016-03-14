package shobhik.exampletodolist;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private Context mContext;
    private ListView lv;
    private ArrayList<String> values = new ArrayList<String>();
    private ArrayList<String> detailvalues = new ArrayList<String>();

    private ArrayAdapter listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        values = new ArrayList<String>();

        lv = (ListView) findViewById(R.id.main_list);
        Log.v("ExampleToDoList", "Testing ListView" + lv.getId());
        listadapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, values);
        Log.v("ExampleToDoList", "Testing Adapter" + listadapter.getItemId(0));
        lv.setAdapter(listadapter);
        Log.v("ExampleToDoList", "Tested ListView!");

        Firebase myFirebaseRef = new Firebase("https://vivid-torch-8283.firebaseio.com/");
        Query queryRef = myFirebaseRef.orderByChild("title");

        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                String title = (String) snapshot.child("message").getValue();
                ArrayList<String> title2 = (ArrayList<String>) snapshot.getValue();
//                String newPost = snapshot.getValue(String.class);
//                values.add(title);
                Log.v("Ex", "Set Item! valueenventlistener:" + title);
                Log.v("Ex", "Set Item! valueenventlistener2:" + title2.toString());
                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        queryRef.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
//                ListItemModel item = snapshot.child("message").getValue(ListItemModel.class);
//                ListItemModel item = snapshot.getValue(ListItemModel.class);
//                String title = (String) snapshot.child("message").getValue();
//                List<String> title2 = (List<String>) snapshot.getValue();
//                String newPost = snapshot.getValue(String.class);
                Log.v("Ex", "onChildAdded: retrieved item");
                String title = snapshot.getKey();
                ArrayList<HashMap<String, String>> title2 = (ArrayList<HashMap<String, String>>) snapshot.getValue();
                Log.v("Ex", "Got Items for onChildAdded:" + title2.toString());
                for (HashMap<String, String> t : title2) {
                    String xtext = t.get("text");
                    String xtitle = t.get("title");

                    values.add(xtitle);
                    detailvalues.add(xtext);
                }

                Log.v("Ex", "onChildAdded: set item: " + title + ", " + title2.toString());
                listadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
                ad.setTitle(values.get(position));
                ad.setMessage(detailvalues.get(position));
                ad.create().show();
            }
        });

    }

}
