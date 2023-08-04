package com.example.proyecto2_parcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private Button buttonAddUser;
    private ListView listViewUsers;
    private ArrayList<String> userList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextUsername = findViewById(R.id.editTextUsername);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        listViewUsers = findViewById(R.id.listViewUsers);


        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listViewUsers.setAdapter(adapter);
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = userList.get(position);
                String[] userDetails = selectedUser.split(" - ");
                if (userDetails.length == 2) {
                    String username = userDetails[0];
                    String profile = userDetails[1];
                    openMovieListActivity(username, profile);
                }
            }
        });


        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });
    }
    private void showAddUserDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);

        final EditText editTextUsernameDialog = dialogView.findViewById(R.id.editTextUsernameDialog);
        final Spinner spinnerProfile = dialogView.findViewById(R.id.spinnerProfile);


        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.profiles_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(spinnerAdapter);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Agregar Usuario")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUsernameDialog.getText().toString().trim();
                        String selectedProfile = spinnerProfile.getSelectedItem().toString();

                        if (!username.isEmpty()) {
                            String userEntry = username + " - " + selectedProfile;
                            userList.add(userEntry);
                            adapter.notifyDataSetChanged();
                            editTextUsername.setText("");
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }

    private void openMovieListActivity(String username, String profile) {
        Intent intent = new Intent(this, Peliculas2.class);
        intent.putExtra("username", username);
        intent.putExtra("profile", profile);
        startActivity(intent);
    }
}