package com.example.firstapplication;

import static com.example.firstapplication.MyContentProvider.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapplication.Fragments.CafeFragment;

public class MainActivity extends AppCompatActivity {

    Button login, newlogin, load;
    EditText id, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        newlogin = findViewById(R.id.newlogin);
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        load = findViewById(R.id.load);

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String cId = id.getText().toString();
                String cPassword = password.getText().toString();
                if (cId.isEmpty() || cPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                    String select = "id =? AND password=?";
                    String args[] = {cId, cPassword};
                    // creating a cursor object of the// content URI
                    Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                            null, select, args, null);
                    // iteration of the cursor
                    // to print whole table
                    if (cursor.moveToFirst()) {
                        String i, p;
                        while (!cursor.isAfterLast()) {
                            i = cursor.getString(cursor.getColumnIndex(MyContentProvider.id));
                            p = cursor.getString(cursor.getColumnIndex(MyContentProvider.password));
                            if (cId.equals(i) && cPassword.equals(p)) {
                                replaceFragment(new CafeFragment());
                                Toast.makeText(getApplicationContext(), "Welcome Back ", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                    } else {
                        android.widget.Toast.makeText(getApplicationContext(), "Invalid login Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cId = id.getText().toString();
                String cPassword = password.getText().toString();
                if (cId.isEmpty() || cPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    // fetching text from user
                    values.put(MyContentProvider.id, cId);
                    values.put(MyContentProvider.password, cPassword);
                    // inserting into database through content URI
                    getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
                    // displaying a toast message
                    android.widget.Toast.makeText(getApplicationContext(), "New Record Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                TextView resultView = (TextView) findViewById(R.id.res);

                // creating a cursor object of the
                // content URI
                Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                        null, null, null, null);

                // iteration of the cursor
                // to print whole table
                if (cursor.moveToFirst()) {
                    StringBuilder strBuild = new StringBuilder();
                    while (!cursor.isAfterLast()) {
                        strBuild.append("\n").
                                append(cursor.getString(cursor.getColumnIndex(MyContentProvider.id))).
                                append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.password)));
                        cursor.moveToNext();
                    }
                    resultView.setText(strBuild);
                } else {
                    resultView.setText("No Records Found");
                }
            }
        });
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout,fragment);
        fragmentTransaction.commit();
    }
}