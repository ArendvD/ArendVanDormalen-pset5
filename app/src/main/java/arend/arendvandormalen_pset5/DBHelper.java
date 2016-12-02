package arend.arendvandormalen_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Arend on 2016-11-22.
 * Class that helps with database actions.
 *
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "toDoList.db";
    private static final int DATABASE_VERSION = 1;
    public String TABLE;

    private String task_id = "task";
    private String checked_id = "checked";

    public DBHelper(Context context, String TABLE){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE = TABLE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + task_id + " TEXT, " + checked_id + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);

    }

    public void create(Task task){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(task_id, task.task);
        values.put(checked_id, task.checked);
        db.insert(TABLE, null, values);
        db.close();

    }

    public ArrayList<Task> read(){

        String query = "SELECT _id, " + task_id + " , " + checked_id + " FROM " + TABLE;
        ArrayList<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Task taskData = new Task();
                taskData.setId(cursor.getString(cursor.getColumnIndex("_id")));
                taskData.setTask(cursor.getString(cursor.getColumnIndex(task_id)));
                taskData.setChecked( cursor.getString(cursor.getColumnIndex(checked_id)));
                taskList.add(taskData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public void update(Task task){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(task_id, task.task);
        values.put(checked_id, task.checked);
        db.update(TABLE, values, "_id = ? ", new String[]{String.valueOf(task.id)});
        db.close();
    }

    public void delete(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "_id = ? ", new String[]{id});
        db.close();
    }

}
