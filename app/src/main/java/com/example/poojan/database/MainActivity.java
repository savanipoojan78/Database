package com.example.poojan.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;
EditText name,surname,marks,id;
Button sumit,show,update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.NAME);
        surname=(EditText)findViewById(R.id.SURNAME);
        marks=(EditText)findViewById(R.id.MARKS);
        sumit=(Button)findViewById(R.id.Submit);
        show=(Button)findViewById(R.id.View);
        update=(Button)findViewById(R.id.Update);
        delete=(Button)findViewById(R.id.DELETE);
        id=(EditText)findViewById(R.id.ID);



        mydb=new DatabaseHelper(this);
        Adddata();
        ShowData();
        updateData();
        DeleteData();
    }
    public void updateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=mydb.UpdateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
            if(isUpdated)
            {
                showMessage("Data","Data Sucessfully Updated");
            }
            }
        });
    }
    public void Adddata()
    {
        sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInsert= mydb.insertdata(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isInsert)
                {
                    Toast.makeText(getApplicationContext(),"Data inserte Sucessfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data inserte UnSucessfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void ShowData()
    {
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","No Data Found");
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())

                {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("SurName :"+ res.getString(2)+"\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());

            }
        });
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Integer deleteRows= mydb.DeleteData(id.getText().toString());
               if(deleteRows>0)
               {
                   Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Data Not Deleted",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}
