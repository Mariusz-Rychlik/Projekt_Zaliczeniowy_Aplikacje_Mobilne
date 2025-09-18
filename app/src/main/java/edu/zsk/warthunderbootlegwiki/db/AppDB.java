package edu.zsk.warthunderbootlegwiki.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.zsk.warthunderbootlegwiki.db.entity.Tank;
import edu.zsk.warthunderbootlegwiki.db.tankDao;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Tank.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;
    private static Context appContext;

    public abstract tankDao tankDao();

    public static synchronized AppDB getInstance(Context context) {
        appContext = context.getApplicationContext();
        if (instance == null) {
            instance = Room.databaseBuilder(appContext,
                            AppDB.class, "tank_database")
                    .addCallback(prepopulateCallback)
                    .build();
        }
        return instance;
    }

    private static final Callback prepopulateCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    AssetManager assetManager = appContext.getAssets();
                    InputStream inputStream = assetManager.open("tanks.json");
                    InputStreamReader reader = new InputStreamReader(inputStream);

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Tank>>() {}.getType();
                    List<Tank> tankList = gson.fromJson(reader, listType);

                    getInstance(appContext).tankDao().insertAll(tankList);
                    Log.d("RoomInit", "Prepopulated " + tankList.size() + " tanks");
                } catch (Exception e) {
                    Log.e("RoomInit", "Failed to preload tanks", e);
                }
            });
        }
    };
    public void reloadFromJson() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                tankDao dao = getInstance(appContext).tankDao();

                // Clear current tanks
                dao.deleteAll();

                // Read JSON again
                AssetManager assetManager = appContext.getAssets();
                InputStream inputStream = assetManager.open("tanks.json");
                InputStreamReader reader = new InputStreamReader(inputStream);

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Tank>>() {}.getType();
                List<Tank> tankList = gson.fromJson(reader, listType);

                dao.insertAll(tankList);

                Log.d("RoomReload", "Reloaded " + tankList.size() + " tanks from JSON");
            } catch (Exception e) {
                Log.e("RoomReload", "Failed to reload tanks", e);
            }
        });
    }
}
