package com.example.sipclient;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SIPManager sipManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sipManager = new SIPManager(this);
        sipManager.initPJSIP();

        EditText user = findViewById(R.id.editUser);
        EditText pass = findViewById(R.id.editPass);
        EditText server = findViewById(R.id.editServer);
        Button registerBtn = findViewById(R.id.btnRegister);
        TextView logView = findViewById(R.id.textLog);

        registerBtn.setOnClickListener(v -> {
            String username = user.getText().toString();
            String password = pass.getText().toString();
            String srv = server.getText().toString();
            sipManager.register(username, password, srv, logView);
        });
    }
}
