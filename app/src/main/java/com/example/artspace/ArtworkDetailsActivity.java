
package com.example.artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private TextView detailsTitle, detailsArtist, detailsYear;
    private Button buttonWebsite, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        // Connect Java with XML views
        detailsTitle = findViewById(R.id.detailsTitle);
        detailsArtist = findViewById(R.id.detailsArtist);
        detailsYear = findViewById(R.id.detailsYear);

        buttonWebsite = findViewById(R.id.buttonWebsite);
        buttonBack = findViewById(R.id.buttonBack);

        // Receive artwork information
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        String year = intent.getStringExtra("year");

        // Display artwork information
        detailsTitle.setText(title);
        detailsArtist.setText(artist);
        detailsYear.setText(year);

        // Open website using Implicit Intent
        buttonWebsite.setOnClickListener(v -> {
            Intent websiteIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://artsandculture.google.com/")
            );

            try {
                startActivity(websiteIntent);
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this,
                        "No browser available",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Return to MainActivity
        buttonBack.setOnClickListener(v -> finish());
    }
}
