package com.handira.librarify.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// Membuat entitas member
@Entity
public class Member {
    // atribut dari member
    @PrimaryKey
    public int id;
    public String name;
    public String dob;
    public String address;
}
