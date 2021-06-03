package com.freelance.anantahairstudio.services.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AllServicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalServiceResponse localServiceResponse);
    @Query("Select * from allServices")
    LiveData<List<LocalServiceResponse>> getAllServicesLiveData();

    @Query("Select * from allServices where category_id =:categoryId")
    LiveData<List<LocalServiceResponse>> filterLiveData(String categoryId);

}
