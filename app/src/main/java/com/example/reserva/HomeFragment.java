package com.example.reserva;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String[] res = {"Toy Story 4", "John Wick 3", "Godzilla", "Annabelle"};
        int[] cover = {R.drawable.toystory, R.drawable.johnwick, R.drawable.godzilla, R.drawable.annabelle};
        ListView simpleList;

        simpleList = (ListView) view.findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), res, cover);
        simpleList.setAdapter(customAdapter);

        return view;
    }
}
