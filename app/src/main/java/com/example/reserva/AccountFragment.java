package com.example.reserva;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment {

    String _id;
    DatabaseManager myDb;
    TextView username, email, mobile, password, upi;
    Button modify, delete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        MainActivity activity = (MainActivity) getActivity();
        _id = activity.getMyData();

        myDb = DatabaseManager.getInstance(getActivity());

        modify = view.findViewById(R.id.modify);
        delete = view.findViewById(R.id.delete);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        mobile = view.findViewById(R.id.mobile);
        password = view.findViewById(R.id.password);
        upi = view.findViewById(R.id.upi);

        viewAll();

        modify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModifyFragment()).commit();
                    }
                }
        );

        DeleteData();

        return view;

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

    public void DeleteData() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(_id);
                        if(deletedRows > 0) {
                            Toast.makeText(getActivity(), "Account Deleted", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getActivity(),"Deletion failed. Try Again!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
