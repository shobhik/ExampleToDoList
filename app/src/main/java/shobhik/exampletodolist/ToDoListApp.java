package shobhik.exampletodolist;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Shobhik Ghosh on 3/8/2016.
 */
public class ToDoListApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }


}
