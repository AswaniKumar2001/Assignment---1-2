package com.example.firstapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
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

import com.example.firstapplication.OrderProvider;
import com.example.firstapplication.R;

public class PastryFragment extends Fragment {

    View view;

    Button confirm, extend;

    CardView p1,p2, p3;

    TextView t1, t2, t3;
    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pastry, container, false);

        confirm = view.findViewById(R.id.confirm);
        extend = view.findViewById(R.id.extend);
        p1 = view.findViewById(R.id.red);
        p2 = view.findViewById(R.id.dark);
        p3 = view.findViewById(R.id.black);
        t1 = view.findViewById(R.id.RV);
        t2 = view.findViewById(R.id.DC);
        t3 = view.findViewById(R.id.BC);

        extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CafeFragment());
            }
        });

        p1.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "RedVelvet selected", Toast.LENGTH_SHORT).show();
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "BlackCurrent selected", Toast.LENGTH_SHORT).show();
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "DarkChoclate selected", Toast.LENGTH_SHORT).show();
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