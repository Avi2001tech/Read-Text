package com.example.textread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class MainActivity extends AppCompatActivity {

    private Button fetch;
    private TextView heading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heading = findViewById(R.id.heading);
        fetch = findViewById(R.id.fetch);


        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to extract
                // data from PDF file.
                fetch.setVisibility(View.INVISIBLE);
                extractPDF();
            }
        });
    }

    private void extractPDF() {
        try {
            PdfReader reader = new PdfReader("res/raw/questions.pdf");
            int n = reader.getNumberOfPages();
            String extractedText = "";
            for (int i = 0; i < n; i++) {
                extractedText = extractedText + PdfTextExtractor.getTextFromPage(reader, i + 1).trim() + "\n";
            }

            heading.setText(extractedText);

            reader.close();
        } catch (Exception e) {
            heading.setText("Error found is : \n" + e);
        }
    }
}