package com.handira.librarify.database.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;
import com.handira.librarify.database.entity.Member;
import java.util.List;

@Dao
public interface MemberDao {
    @Query("SELECT * FROM member")
    List<Member> getAll();

    @Insert
    void insertAll(Member... members);

    @Query("UPDATE member SET name=:name, dob=:dob, address=:address WHERE id=:id")
    void update(int id, String name, String dob, String address);

    @Query("SELECT * FROM member WHERE id=:id")
    Member get(int id);
    
    @Delete
    void delete(Member member);

}
