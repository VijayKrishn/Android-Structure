package com.wozart.route_3.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by wozart on 28/09/17.
 */

public class DeviceDbOperations {

    public ArrayList<String> GetAllDevices(SQLiteDatabase db) {
        ArrayList<String> devices = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + DeviceContract.DeviceEntry.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            devices.add(cursor.getString(1));
        }
        cursor.close();
        return devices;
    }


    public ArrayList<String> GetAllHome(SQLiteDatabase db) {
        ArrayList<String> home = new ArrayList<>();
        Cursor cursor = db.rawQuery("select " + DeviceContract.DeviceEntry.HOME_NAME + " from " + DeviceContract.DeviceEntry.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            home.add(cursor.getString(0));
        }
        cursor.close();
        return home;
    }

    public void InsertBasicData(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from " + DeviceContract.DeviceEntry.TABLE_NAME, null);
        if (cursor.getCount() == 0) {
            ContentValues value = new ContentValues();
            value.put(DeviceContract.DeviceEntry.DEVICE_NAME, "Aura");
            value.put(DeviceContract.DeviceEntry.HOME_NAME, "Home");
            value.put(DeviceContract.DeviceEntry.LOAD_1, "Load 1");
            value.put(DeviceContract.DeviceEntry.LOAD_2, "Load 2");
            value.put(DeviceContract.DeviceEntry.LOAD_3, "Load 3");
            value.put(DeviceContract.DeviceEntry.LOAD_4, "Load 4");

            try {
                db.beginTransaction();
                db.insert(DeviceContract.DeviceEntry.TABLE_NAME, null, value);

                db.setTransactionSuccessful();
            } catch (SQLException e) {
                //Too bad :(
            } finally {
                db.endTransaction();
                cursor.close();
            }
        }
    }

    public void InsertHome(SQLiteDatabase db, String home) {
        ContentValues value = new ContentValues();
        value.put(DeviceContract.DeviceEntry.HOME_NAME, home);
        try {
            db.beginTransaction();
            db.insert(DeviceContract.DeviceEntry.TABLE_NAME, null, value);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            //Too bad :(
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<String> GetRooms(SQLiteDatabase db, String home) {
        String[] params = new String[]{home};
        Cursor cursor = db.rawQuery("select distinct " + DeviceContract.DeviceEntry.ROOM_NAME + " from " + DeviceContract.DeviceEntry.TABLE_NAME + " where " + DeviceContract.DeviceEntry.HOME_NAME
                + " = ?", params);
        ArrayList<String> room = new ArrayList<>();
        while (cursor.moveToNext()) {
            room.add(cursor.getString(0));
        }
        return room;
    }

    public void InsertRoom(SQLiteDatabase db, String home, String room) {
        String x = "null";
        String[] params = new String[]{x};
        Cursor cursor = db.rawQuery("select " + DeviceContract.DeviceEntry.HOME_NAME + " from " + DeviceContract.DeviceEntry.TABLE_NAME + " where " + DeviceContract.DeviceEntry.ROOM_NAME
                + " = ?", params);
        if (cursor.getCount() == 0) {
            ContentValues value = new ContentValues();
            value.put(DeviceContract.DeviceEntry.HOME_NAME, home);
            value.put(DeviceContract.DeviceEntry.ROOM_NAME, room);

            try {
                db.beginTransaction();
                db.insert(DeviceContract.DeviceEntry.TABLE_NAME, null, value);

                db.setTransactionSuccessful();
            } catch (SQLException e) {
                //Too bad :(
            } finally {
                db.endTransaction();

            }
        } else {
            ContentValues cv = new ContentValues();
            cv.put(DeviceContract.DeviceEntry.ROOM_NAME, room);
            db.update(DeviceContract.DeviceEntry.TABLE_NAME, cv, DeviceContract.DeviceEntry.HOME_NAME + " = " + home, null);
        }
        cursor.close();
    }
}
