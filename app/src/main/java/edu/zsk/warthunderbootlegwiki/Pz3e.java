package edu.zsk.warthunderbootlegwiki;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.zsk.warthunderbootlegwiki.db.AppDB;
import edu.zsk.warthunderbootlegwiki.db.entity.Tank;

public class Pz3e extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pz3e);

        TextView textView = findViewById(R.id.textView3);
        loadTank();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void loadTank() {
        new Thread(() -> {
            String targetName = "Pz.III E";

            Tank tank = AppDB.getInstance(this).tankDao().getTankByName(targetName);

            if (tank != null) {
                String display = "Name: " + tank.name + "\n\n"
                        +"\n"
                        +"Tank Statistics:\n\n"
                        + "Hull Armor: " + tank.hullArmor + "\n"
                        + "Turret Armor: "+ tank.turretArmor + "\n\n"
                        + "Crew: "+ tank.crew + "\n\n"
                        + "Forward Speed: "+ tank.forwardSpeed + "\n"
                        + "Backwards Speed: "+ tank.backwardSpeed + "\n\n"
                        + "Engine Power: "+ tank.enginePower + "\n"
                        + "Weight: "+ tank.weight + "\n"
                        + "Power To Weight Ratio: "+ tank.powerToWeightRatio + "\n\n"
                        +"\n"
                        + "Armament Statistics: " + "\n\n"
                        + "Main Armament: "+ tank.mainArmament + "\n"
                        + "Reload Speed : "+ tank.reloadSpeed + "\n"
                        + "Ammunition: "+ tank.ammunition + "\n\n"
                        + "Vertical Turret Rotation: "+ tank.verticalTurretRotation + "\n"
                        + "Horizontal Turret Rotation: "+ tank.horizontalTurretRotation + "\n"
                        + "Vertical Guidence: "+ tank.verticalGuidance + "\n\n"
                        +"\n"
                        + "Information:" + tank.information;

                runOnUiThread(() -> {
                    TextView textView = findViewById(R.id.textView3);
                    textView.setText(display);
                });
            } else {
                runOnUiThread(() -> {
                    TextView textView = findViewById(R.id.textView3);
                    textView.setText("Tank not found");
                });
            }

        }).start();
    }
}