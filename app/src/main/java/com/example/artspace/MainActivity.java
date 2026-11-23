
package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterText, artworkTitle, artworkInfo;
    private ImageView artworkImage;
    private Button buttonPrevious, buttonNext;

    private int currentIndex = 0;

    private final String[] titles = {
            "The Starry Night",
            "Mona Lisa",
            "The Scream",
            "Girl with a Pearl Earring",
            "The Kiss",
            "The Great Wave"
    };

    private final String[] artists = {
            "Vincent van Gogh",
            "Leonardo da Vinci",
            "Edvard Munch",
            "Johannes Vermeer",
            "Gustav Klimt",
            "Katsushika Hokusai"
    };

    private final String[] years = {
            "1889", "1503", "1893",
            "1665", "1908", "1831"
    };

    // Temporary images - we will replace them later


    private final int[] images = {
            R.drawable.starry_night,
            R.drawable.mona_lisa,
            R.drawable.the_scream,
            R.drawable.girl_pearl,
            R.drawable.the_kiss,
            R.drawable.great_wave
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
        artworkTitle = findViewById(R.id.artworkTitle);
        artworkInfo = findViewById(R.id.artworkInfo);
        artworkImage = findViewById(R.id.artworkImage);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("index", 0);
        }

        buttonNext.setOnClickListener(v -> {
            if (currentIndex < titles.length - 1) {
                currentIndex++;
                updateArtwork();
            }
        });

        buttonPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtwork();
            }
        });


        artworkImage.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            intent.putExtra("title", titles[currentIndex]);
            intent.putExtra("artist", artists[currentIndex]);
            intent.putExtra("year", years[currentIndex]);

            startActivity(intent);
        });

        updateArtwork();
    }

    private void updateArtwork() {
        counterText.setText((currentIndex + 1) + " / " + titles.length);

        artworkTitle.setText(titles[currentIndex]);

        artworkInfo.setText(
                artists[currentIndex] + " - " + years[currentIndex]
        );

        artworkImage.setImageResource(images[currentIndex]);

        buttonPrevious.setEnabled(currentIndex > 0);
        buttonNext.setEnabled(currentIndex < titles.length - 1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("index", currentIndex);
        super.onSaveInstanceState(outState);
    }
}
