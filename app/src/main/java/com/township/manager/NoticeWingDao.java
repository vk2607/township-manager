package com.township.manager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NoticeWingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoticeWing... noticeWings);

    @Delete
    void delete(NoticeWing noticeWing);

    @Query("DELETE FROM NoticeWing")
    void deleteAll();
}
