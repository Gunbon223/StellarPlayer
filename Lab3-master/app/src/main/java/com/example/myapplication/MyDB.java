package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {

    public static final String tableName ="ContactTable";
    public static final String Id ="Id";
    public static final String Name ="FullName";
    public static final String PhoneNumber = "PhoneNumber";
    public static final String Image = "Image";

    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao cau sql de tao bang tableContact
        String sqlCreate = "Create table if not exists " + tableName + "("+
                Id +"Integer Primary key, " + Image + " Text, " + Name + "Text, " +PhoneNumber+" Text)";
        db.execSQL(sqlCreate);


    }


    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, contact.getId());
        values.put(Image,contact.getImage());
        values.put(Name,contact.getName());
        values.put(PhoneNumber,contact.getPhoneNumber());
        db.insert(tableName, null,values);
        db.close();
    }

    public void updateContact(int id,Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, contact.getId());
        values.put(Image,contact.getImage());
        values.put(Name,contact.getName());
        values.put(PhoneNumber,contact.getPhoneNumber());
        db.update(tableName, values,Id+"=?",new String[]{(String.valueOf(id))}) ;
        db.close();
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from +" + tableName +"where ID=" +id;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Contact> getAllContact()
    {
        ArrayList<Contact> list = new ArrayList<>();
        String sql = "Select * from " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null) {
            while (cursor.moveToNext())
            {
                Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
                list.add(contact);
            }
        }
        return list;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);
        onCreate(db);

    }



}
