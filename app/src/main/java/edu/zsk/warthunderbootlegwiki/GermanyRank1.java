package edu.zsk.warthunderbootlegwiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GermanyRank1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_germany_rank1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void gotoPz35t(View view) {
        startActivity(new Intent(GermanyRank1.this, Pz35t.class));
    }

    public void gotoPz3E(View view) {
        startActivity(new Intent(GermanyRank1.this, Pz3e.class));
    }

    public void gotoPz3B(View view) {
        startActivity(new Intent(GermanyRank1.this, Pz3B.class));
    }

    public void gotoPz38tA(View view) {
        startActivity(new Intent(GermanyRank1.this, Pz38tA.class));
    }

    public void gotoPz3F(View view) {
        startActivity(new Intent(GermanyRank1.this, Pz3f.class));
    }
}