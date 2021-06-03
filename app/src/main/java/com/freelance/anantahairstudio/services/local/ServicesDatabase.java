package com.freelance.anantahairstudio.services.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LocalServiceResponse.class},version = 1,exportSchema = false)
public abstract class ServicesDatabase extends RoomDatabase {
    private static volatile ServicesDatabase servicesDatabase;

    public static ServicesDatabase getDatabase(final Context context) {
            {
                if (servicesDatabase == null) {
                    servicesDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            ServicesDatabase.class, "all_services")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }

        return servicesDatabase;
    }
    public abstract AllServicesDao getAllServices();

}
