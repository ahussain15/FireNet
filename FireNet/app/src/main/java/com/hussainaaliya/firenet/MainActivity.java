package com.hussainaaliya.firenet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private FirebaseListAdapter<Message> listAdapter;

    //Register
    private Button register;
    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private Button login_fr;

    //Login
    private Button login;
    private EditText email_old;
    private EditText password_old;
    private Button register_fl;

    //Main
    private Button logout;
    private EditText type;
    private FloatingActionButton send;
    ListView show_messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
            register_setup();
        else {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            main_setup();
        }
    }

    private void register_setup(){
        setContentView(R.layout.register_activity);
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm);
        login_fr = findViewById(R.id.login_fr);
    }

    private void login_setup(){
        setContentView(R.layout.login_activity);
        login = findViewById(R.id.login);
        email_old = findViewById(R.id.email_old);
        password_old = findViewById(R.id.password_old);
        register_fl = findViewById(R.id.register_fl);
    }

    private void main_setup(){
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.logout);
        type = findViewById(R.id.type_field);
        send = findViewById(R.id.send);
        show_messages = findViewById(R.id.list);
        display();
        listAdapter.startListening();
    }

    public void register(View view) {
        String e = email.getText().toString();
        String p = password.getText().toString();
        String c = confirm_password.getText().toString();
        if(p.equals(c)) {
            mAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        main_setup();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
            Toast.makeText(getBaseContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
    }

    public void go_login(View view) {
        login_setup();
    }

    public void login(View view) {
        String e = email_old.getText().toString();
        String p = password_old.getText().toString();
        mAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            main_setup();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void go_register(View view) {
        register_setup();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        login_setup();
    }

    public void send_message(View view) {
        Message message = new Message(type.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail());
        FirebaseDatabase.getInstance().getReference().push().setValue(message);
        type.setText("");
    }

    public void display(){
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>().setQuery(FirebaseDatabase.getInstance().getReference(), Message.class).setLayout(R.layout.text).build();
        listAdapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView messageText = v.findViewById(R.id.message_body);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessage_text());
                messageUser.setText(model.getMessage_user());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", (long)model.getMessage_time()));
            }
        };
        show_messages.setAdapter(listAdapter);
    }
}