package com.example.employeeapplicationfirebaserealtimedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeapplicationfirebaserealtimedb.EditEmployeeActivity;
import com.example.employeeapplicationfirebaserealtimedb.R;
import com.example.employeeapplicationfirebaserealtimedb.model.Employee;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {

    List<Employee> employeeList;
    Context context;

    DatabaseReference employeesReference;

    public EmployeeAdapter(Context context, List<Employee> employeeList, DatabaseReference employeesReference){
        this.context = context;
        this.employeeList = employeeList;
        this.employeesReference = employeesReference;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_employee, parent, false);
        return new EmployeeViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) holder;
        employeeViewHolder.textViewId.setText(employee.getId());
        employeeViewHolder.textViewName.setText(employee.getName());
        employeeViewHolder.textViewAddress.setText(employee.getAddress());

        employeeViewHolder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editEmployeeActivity = new Intent(context, EditEmployeeActivity.class);
                editEmployeeActivity.putExtra("employee", employee);
                context.startActivity(editEmployeeActivity);
            }
        });

        employeeViewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeesReference.child(employee.getKey()).removeValue();
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        ImageButton buttonDelete;
        TextView textViewId;
        TextView textViewName;
        TextView textViewAddress;
        ImageButton buttonEdit;


        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewAddress = itemView.findViewById(R.id.text_view_address);
            buttonEdit = itemView.findViewById(R.id.button_edit);
        }
    }
}
