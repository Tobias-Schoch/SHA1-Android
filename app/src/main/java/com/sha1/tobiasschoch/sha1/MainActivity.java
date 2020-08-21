package com.sha1.tobiasschoch.sha1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        String hash = preferences.getString("hash", "");


        final Spinner dropdown = findViewById(R.id.spinner1);
        final TextView textView = findViewById(R.id.textView);
        Button deleteButton = findViewById(R.id.deleteButton);
        final EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);

        final Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.list)
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);

        if (hash.equals("MD5")) {
            spinner.setSelection(0);
        } else if (hash.equals("SHA-1")) {
            spinner.setSelection(1);
        } else if (hash.equals("SHA-256")) {
            spinner.setSelection(2);
        } else if (hash.equals("SHA-512")) {
            spinner.setSelection(3);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = dropdown.getSelectedItem().toString();
                if (text.equals("MD5")) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] messageDigest = md.digest((editTextTextPassword.getText().toString()).getBytes());
                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);
                        while (hashtext.length() < 32) {
                            hashtext = "0" + hashtext;
                        }
                        textView.setText(hashtext);
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("1", hashtext);
                        clipboard.setPrimaryClip(clip);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else if (text.equals("SHA-1")) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-1");
                        byte[] messageDigest = md.digest((editTextTextPassword.getText().toString()).getBytes());
                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);
                        while (hashtext.length() < 32) {
                            hashtext = "0" + hashtext;
                        }
                        textView.setText(hashtext);
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("1", hashtext);
                        clipboard.setPrimaryClip(clip);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else if (text.equals("SHA-256")) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] messageDigest = md.digest((editTextTextPassword.getText().toString()).getBytes());
                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);
                        while (hashtext.length() < 32) {
                            hashtext = "0" + hashtext;
                        }
                        textView.setText(hashtext);
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("1", hashtext);
                        clipboard.setPrimaryClip(clip);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else if (text.equals("SHA-512")) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-512");
                        byte[] messageDigest = md.digest((editTextTextPassword.getText().toString()).getBytes());
                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);
                        while (hashtext.length() < 32) {
                            hashtext = "0" + hashtext;
                        }
                        textView.setText(hashtext);
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("1", hashtext);
                        clipboard.setPrimaryClip(clip);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                }
                Toast.makeText(getApplicationContext(), "Hash copied to clipboard",
                        Toast.LENGTH_LONG).show();

                editor.putString("hash",text);
                editor.apply();

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                } catch (Exception e) {
                    String input = "";
                }
            }
        });


    }
}