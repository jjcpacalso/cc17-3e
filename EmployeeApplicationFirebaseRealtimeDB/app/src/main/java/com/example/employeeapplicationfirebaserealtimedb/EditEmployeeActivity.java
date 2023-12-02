package com.example.employeeapplicationfirebaserealtimedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.employeeapplicationfirebaserealtimedb.model.Employee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditEmployeeActivity extends AppCompatActivity {

    TextView textViewId;
    EditText editTextName;
    EditText editTextAddress;
    Button buttonSaveRecord;

    DatabaseReference databaseReference;
    DatabaseReference employeesReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        employeesReference = databaseReference.child("employees");

        textViewId = findViewById(R.id.text_view_id);
        editTextName = findViewById(R.id.edit_text_name);
        editTextAddress = findViewById(R.id.edit_text_address);
        buttonSaveRecord = findViewById(R.id.button_save_record);

        Employee originalEmployee = getIntent().getParcelableExtra("employee");
        textViewId.setText(originalEmployee.getId());
        editTextName.setText(originalEmployee.getName());
        editTextAddress.setText(originalEmployee.getAddress());

        buttonSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee(
                        originalEmployee.getId(),
                        String.valueOf(editTextName.getText()),
                        String.valueOf(editTextAddress.getText())
                );
                employee.setKey(originalEmployee.getKey());
                employeesReference.child(employee.getKey()).setValue(employee);
                finish();
            }
        });
    }
}