package com.example.firstapplication.Fragments;

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

public class SnackFragment extends Fragment {

    View view;
    Button confirm, extend;

    CardView s1, s2, s3;

    TextView t1, t2, t3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_snack, container, false);
        confirm = view.findViewById(R.id.confirm);
        extend = view.findViewById(R.id.extend);
        s1 = view.findViewById(R.id.punugu);
        s2 = view.findViewById(R.id.pakoda);
        s3 = view.findViewById(R.id.bajji);
        t1 = view.findViewById(R.id.P);
        t2 = view.findViewById(R.id.OP);
        t3 = view.findViewById(R.id.B);

        extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CafeFragment());
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "Punugu selected", Toast.LENGTH_SHORT).show();
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "OnionPakoda selected", Toast.LENGTH_SHORT).show();
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(getContext(), "Bajji selected", Toast.LENGTH_SHORT).show();
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