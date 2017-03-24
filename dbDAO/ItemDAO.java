package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static db.MyDBOpenHelper.TABLE_ITEM;

/**
 * Created by bakhah on 24/03/17.
 */

/*
    Exemple d'utilisation dans un contexte
    final ItemDAOInterface itemDao = new ItemDAO(this)
    itemDAO.open();
    final List<Item> itemList = itemDAO.getAll();
    itemDAO.close();
 */

public class ItemDAO implements ItemDAOInterface {

    private SQLiteDatabase db;
    private MyDBOpenHelper myDBOpenHelper;

    public ItemDAO(Context context)
    {
        myDBOpenHelper = new MyDBOpenHelper(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void open() {
        db = myDBOpenHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public SQLiteDatabase getDB() {
        return db;
    }

    @Override
    public long insertItem(Item item) {
        final ContentValues values = new ContentValues();
        values.put(ItemEnum.COL_NAME.getName(), item.getName());
        return db.insert(TABLE_ITEM, null, values);
    }

    @Override
    public int updateItem(int id, Item item) {
        final ContentValues values = new ContentValues();
        values.put(ItemEnum.COL_NAME.getName(), item.getName());
        return db.update(TABLE_ITEM, values, ItemEnum.COL_ID.getName() + " = " + id, null);
    }

    @Override
    public int removeItem(int id) {
        return db.delete(TABLE_ITEM, ItemEnum.COL_ID.getName() + " = " + id, null);
    }

    @Override
    public List<Item> getAll() {
        final Cursor c = db.rawQuery("Select * from " + TABLE_ITEM, null);

        if (c.getCount() == 0)
            return new ArrayList<>();

        c.moveToFirst();
        final List<Item> items = cursorToListItem(c);
        c.close();
        return items;
    }

    @Override
    public Item getItemByName(String name) {
        return null;
    }

    @Override
    public Item getItemById(int id) {
        final Cursor c = db.query(TABLE_ITEM, new String[]{ItemEnum.COL_ID.getName(), ItemEnum.COL_NAME.getName()},
                ItemEnum.COL_ID.getName() + " = " + id, null, null, null, null);

        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        final Item item = cursorToItem(c);
        c.close();
        return item;
    }

    private Item cursorToItem(final Cursor c)
    {
        final Item item = new Item();

        item.setId(c.getInt(ItemEnum.COL_ID.getColumnNumber()));
        item.setName(c.getString(ItemEnum.COL_NAME.getColumnNumber()));

        return item;
    }

    private List<Item> cursorToListItem(final Cursor c)
    {
        final List<Item> itemList = new ArrayList<>();
        while (!c.isAfterLast()) {
            final Item item = cursorToItem(c);
            itemList.add(item);
            c.moveToNext();
        }
        return itemList;
    }
}
