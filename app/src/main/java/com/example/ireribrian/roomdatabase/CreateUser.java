package com.example.ireribrian.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ireribrian on 10/19/2018.
 */

public class CreateUser extends AppCompatActivity{

    EditText firstName;
    EditText lastName;
    EditText email;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 10/19/2018 Save to Database

                String firstname = firstName.getText().toString();
                String lastname = lastName.getText().toString();
                String emailtext = email.getText().toString();

                if(TextUtils.isEmpty(firstname)){
                    Toast.makeText(CreateUser.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname)){
                    Toast.makeText(CreateUser.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
               if(TextUtils.isEmpty(emailtext)){
                    Toast.makeText(CreateUser.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                    User user = new User(firstName.getText().toString(),
                            lastName.getText().toString(),
                            email.getText().toString());
                    db.userDao().insertAll(user);

                    startActivity(new Intent(CreateUser.this, MainActivity.class));

            }
        });
    }
}
