package com.handira.librarify.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Member {
    @PrimaryKey
    public int id;
    public String name;
    public String dob;
    public String address;
}
