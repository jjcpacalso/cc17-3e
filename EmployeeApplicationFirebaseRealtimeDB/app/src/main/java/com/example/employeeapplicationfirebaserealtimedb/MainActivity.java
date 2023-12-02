package com.example.employeeapplicationfirebaserealtimedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.employeeapplicationfirebaserealtimedb.adapter.EmployeeAdapter;
import com.example.employeeapplicationfirebaserealtimedb.model.Employee;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MaterialToolbar materialToolbar;

    DatabaseReference databaseReference;
    DatabaseReference employeesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        employeesReference = databaseReference.child("employees");

        materialToolbar = findViewById(R.id.top_app_bar);
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_add){
                    Intent addMenu = new Intent(MainActivity.this, AddEmployeeActivity.class);
                    startActivity(addMenu);
                }
                return true;
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        //List<Employee> employeeList = new ArrayList<>();
        //employeeList.add(new Employee("10001", "Jason", "Address 1"));
        //employeeList.add(new Employee("10002", "Mark", "Address 2"));
        //employeeList.add(new Employee("10003", "David", "Address 1"));
        //employeeList.add(new Employee("10004", "Andrew", "Address 2"));
        //recyclerView.setAdapter(new EmployeeAdapter(MainActivity.this, employeeList));
        //recyclerView.setHasFixedSize(true);

        employeesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Employee> employeeList = new ArrayList<>();
                for(DataSnapshot employeeSnapshot: snapshot.getChildren()){
                    Employee employee = employeeSnapshot.getValue(Employee.class);
                    employee.setKey(employeeSnapshot.getKey());
                    employeeList.add(employee);
                }

                recyclerView.setAdapter(new EmployeeAdapter(MainActivity.this, employeeList, employeesReference));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}