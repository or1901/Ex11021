package com.example.ex11021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The main activity:
 * Reads text from a raw file and displays it on the screen when a button is clicked.
 * Also displays text from edit text which is in the activity.
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 9/11/2023
 */
public class MainActivity extends AppCompatActivity {
    TextView tV;
    EditText eT;
    private final String FILENAME = "rawtext.txt";
    String fileName, line;
    int resourceId;
    InputStream iS;
    InputStreamReader iSR;
    BufferedReader bR;
    StringBuilder sB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = (TextView) findViewById(R.id.tV);
        eT = (EditText) findViewById(R.id.eT);

        fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw",
                this.getPackageName());
    }

    /**
     * This function reads the text from the file, and displays it to the text view.
     * @param view The button that was clicked to read the file.
     */
    public void readFile(View view) {
        try {
            // Initiates the variables using to read
            iS = this.getResources().openRawResource(resourceId);
            iSR = new InputStreamReader(iS);
            bR = new BufferedReader(iSR);
            sB = new StringBuilder();

            // Reads from the file
            line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tV.setText(sB.toString());
        }
        catch (IOException e){
            Toast.makeText(this, "Error reading file", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "Error reading file");
        }
    }

    /**
     * This function displays the input of the edit text in the text view.
     * @param view The button that was clicked to read the edit text.
     */
    public void readEt(View view) {
        tV.setText(eT.getText().toString());
    }
}