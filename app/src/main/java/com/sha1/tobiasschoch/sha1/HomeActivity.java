package com.sha1.tobiasschoch.sha1;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.ClipboardManager;

import com.example.tobiasschoch.sha1.R;

public class HomeActivity extends Activity implements OnClickListener {

    private Button button;
    private EditText editText;
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    ClipboardManager myClipboard;
    String password = "";
    String sub = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (view == button) {

            password = editText.getText().toString();
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String hashtext = no.toString(36);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                sub = hashtext.substring(0,23);
                textView.setText(sub);
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            ClipData myClip;
            myClip = ClipData.newPlainText("sub", sub);
            myClipboard.setPrimaryClip(myClip);
        } else if (view == imageView) {
            int einfachso = 0;
        }

    }
}
