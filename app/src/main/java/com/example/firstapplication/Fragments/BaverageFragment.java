package com.example.firstapplication.Fragments;

import static com.example.firstapplication.OrderProvider.food;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
import android.widget.Toast;

import com.example.firstapplication.MyContentProvider;
import com.example.firstapplication.OrderProvider;
import com.example.firstapplication.R;

public class BaverageFragment extends Fragment {

    View view;

    Button confirm, extend;

    CardView b1, b2, b3;

    TextView t1, t2, t3;

    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_baverage, container, false);

        confirm = view.findViewById(R.id.confirm);
        extend = view.findViewById(R.id.extend);
        b1 = view.findViewById(R.id.butmilk);
        b2 = view.findViewById(R.id.coke);
        b3 = view.findViewById(R.id.grape);
        t1 = view.findViewById(R.id.BM);
        t2 = view.findViewById(R.id.CO);
        t3 = view.findViewById(R.id.GJ);

        extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CafeFragment());
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getActivity().getContentResolver();
                String ct1 = t1.getText().toString();
                ContentValues order = new ContentValues();
                // fetching text from user
                order.put(OrderProvider.food, ct1);
                // inserting into database through content URI
                resolver.insert(OrderProvider.CONTENT_URI, order);
                // displaying a toast message
                Toast.makeText(getContext(), "ButterMilk selected", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getActivity().getContentResolver();
                String ct2 = t2.getText().toString();
                ContentValues order = new ContentValues();
                // fetching text from user
                order.put(OrderProvider.food, ct2);
                // inserting into database through content URI
                resolver.insert(OrderProvider.CONTENT_URI, order);
                // displaying a toast message
                Toast.makeText(getContext(), "Coke selected", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getActivity().getContentResolver();
                String ct3 = t3.getText().toString();
                ContentValues order = new ContentValues();
                // fetching text from user
                order.put(OrderProvider.food, ct3);
                // inserting into database through content URI
                resolver.insert(OrderProvider.CONTENT_URI, order);
                // displaying a toast message
                Toast.makeText(getContext(), "Grape Juice selected", Toast.LENGTH_SHORT).show();
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