package com.example.firstapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstapplication.MyContentProvider;
import com.example.firstapplication.OrderProvider;
import com.example.firstapplication.R;

public class CafeFragment extends Fragment {

    View view;
    Button snack, pastry, baverage, confirm;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cafe, container, false);

        snack = view.findViewById(R.id.snack);
        pastry = view.findViewById(R.id.pastry);
        baverage = view.findViewById(R.id.baverage);
        confirm = view.findViewById(R.id.confirm);

        pastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PastryFragment());
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SnackFragment());
            }
        });

        baverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BaverageFragment());
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                TextView resultView = (TextView) view.findViewById(R.id.ord);
                ContentResolver resolver = getActivity().getContentResolver();
                // creating a cursor object of the
                // content URI
                Cursor cursor = resolver.query(OrderProvider.CONTENT_URI,
                        null, null, null, null);
                // iteration of the cursor
                // to print whole table
                if (cursor.moveToFirst()) {
                    StringBuilder strBuild = new StringBuilder();
                    while (!cursor.isAfterLast()) {
                        strBuild.append("\n").append(cursor.getString(cursor.getColumnIndex(OrderProvider.food)));
                        cursor.moveToNext();
                    }
                    resultView.setText(strBuild);
                    getContext().getContentResolver().delete(OrderProvider.CONTENT_URI,null,null);

                } else {
                    resultView.setText("No Records Found");
                }
            }
        });

        return view;
    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.commit();
    }
}