package edu.zsk.warthunderbootlegwiki.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tank {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String information;
    public String hullArmor;
    public String turretArmor;
    public String crew;
    public String forwardSpeed;
    public String backwardSpeed;
    public String enginePower;
    public String weight;
    public String powerToWeightRatio;
    public String mainArmament;
    public String ammunition;
    public String verticalTurretRotation;
    public String horizontalTurretRotation;
    public String reloadSpeed;
    public String verticalGuidance;
}
