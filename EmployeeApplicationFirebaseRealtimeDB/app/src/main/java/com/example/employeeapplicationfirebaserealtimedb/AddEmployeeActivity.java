package com.example.employeeapplicationfirebaserealtimedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeeapplicationfirebaserealtimedb.model.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText editTextId;
    EditText editTextName;
    EditText editTextAddress;
    Button buttonAddRecord;

    DatabaseReference databaseReference;
    DatabaseReference employeesReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        employeesReference = databaseReference.child("employees");

        editTextId = findViewById(R.id.edit_text_id);
        editTextName = findViewById(R.id.edit_text_name);
        editTextAddress = findViewById(R.id.edit_text_address);
        buttonAddRecord = findViewById(R.id.button_add_employee);

        buttonAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add in firebase realtime DB
                Employee employee = new Employee(
                        String.valueOf(editTextId.getText()),
                        String.valueOf(editTextName.getText()),
                        String.valueOf(editTextAddress.getText())
                );
                employeesReference.push().setValue(employee);
                finish();
            }
        });
    }
}