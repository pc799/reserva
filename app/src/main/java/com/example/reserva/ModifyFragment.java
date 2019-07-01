package com.example.reserva;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyFragment extends Fragment {

    String _id;
    DatabaseManager myDb;
    EditText mobile, email, username, password, upi;
    Button update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify, container, false);

        MainActivity activity = (MainActivity) getActivity();
        _id = activity.getMyData();

        myDb = DatabaseManager.getInstance(getActivity());

        update = (Button) view.findViewById(R.id.update);
        mobile = (EditText) view.findViewById(R.id.mobile);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        email = (EditText) view.findViewById(R.id.email);
        upi = (EditText) view.findViewById(R.id.upi);

        viewAll();
        UpdateData();

        return view;
    }

    public void UpdateData() {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(_id,
                                mobile.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString(),
                                upi.getText().toString()
                        );
                        if (isUpdate) {
                            Toast.makeText(getActivity(), "Account updated!", Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                        }
                        else
                            Toast.makeText(getActivity(), "Update failed. Try again!", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        Cursor res = myDb.getData(_id);

        String[] data = new String[5];
        while (res.moveToNext()) {
            res.getString(0);
            data[0] = res.getString(1);
            data[1] = res.getString(2);
            data[2] = res.getString(3);
            data[3] = res.getString(4);
            data[4] = res.getString(5);
        }

        // Show all data
        showMessage(data);
    }

    public void showMessage(String[] data){
        username.setText(data[0]);
        password.setText(data[1]);
        email.setText(data[2]);
        mobile.setText(data[3]);
        upi.setText(data[4]);
    }
}
