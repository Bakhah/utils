package db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by bakhah on 24/03/17.
 */

public interface ItemDAOInterface {
    int DB_VERSION = 1;
    String DB_NAME = "item.db";

    void open();
    void close();
    SQLiteDatabase getDB();
    long insertItem(Item item);
    int updateItem(int id, Item item);
    int removeItem(int id);
    List<Item> getAll();
    Item getItemByName(String name);
    Item getItemById(int id);
}
