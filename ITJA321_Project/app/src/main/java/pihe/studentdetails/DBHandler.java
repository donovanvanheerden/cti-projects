package pihe.studentdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * 
 * @author Donovan van Heerden | EL2014-0043
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PIHE.db";

    /**
     * Constructor for DbHandler
     * 
     * @param context ApplicationContext for Database retrieval
     */
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate implementation for DbHandler, creates the Students table with
     * specified columns
     * 
     * @param db SQLiteDatabase used to execute the create table on
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * onUpgrade implementation for DbHandler, upgrades the database if there is a
     * new version released.
     * 
     * @param db         SQLiteDatabase to run the upgrade on
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Only upgrade if the version is newer
        if (newVersion > oldVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }

    /**
     * onDowngrade implementation for DbHandler, downgrades the database if a
     * regression occurs.
     * 
     * @param db         SQLiteDatabase to run the downgrade script on
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Static string used to build the create table script.
     */
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + StudentEntry.TABLE_NAME + " ("
            + StudentEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," + StudentEntry.COLUMN_NAME_FIRSTNAME + " TEXT,"
            + StudentEntry.COLUMN_NAME_LASTNAME + " TEXT," + StudentEntry.COLUMN_NAME_CONTACTNUMBER + " TEXT,"
            + StudentEntry.COLUMN_NAME_ADDRESS + " Text)";

    /**
     * Static string used to build the drop table script.
     */
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;

    /**
     * Static class implementation for the database table.
     */
    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Students";
        public static final String COLUMN_NAME_ID = "Id";
        public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
        public static final String COLUMN_NAME_LASTNAME = "LastName";
        public static final String COLUMN_NAME_CONTACTNUMBER = "ContactNumber";
        public static final String COLUMN_NAME_ADDRESS = "Address";
    }

    /**
     * addStudent, inserts a student into the database.
     * 
     * @param student Student to insert.
     */
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(StudentEntry.COLUMN_NAME_ID, student.getId());
        values.put(StudentEntry.COLUMN_NAME_FIRSTNAME, student.getFirstName());
        values.put(StudentEntry.COLUMN_NAME_LASTNAME, student.getLastName());
        values.put(StudentEntry.COLUMN_NAME_CONTACTNUMBER, student.getContactNumber());
        values.put(StudentEntry.COLUMN_NAME_ADDRESS, student.getAddress());

        db.insert(StudentEntry.TABLE_NAME, null, values);
    }

    /**
     * Search, retrieve a student from the database that matches the id passed.
     * 
     * @param id integer value used to find the student
     * @return Student instance
     */
    public Student search(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String firstName = "";
        String lastName = "";
        String contactNumber = "";
        String address = "";

        try {
            String query = "SELECT * FROM " + StudentEntry.TABLE_NAME + " WHERE " + StudentEntry.COLUMN_NAME_ID + " = "
                    + id + ";";

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                firstName = cursor.getString(cursor.getColumnIndexOrThrow(StudentEntry.COLUMN_NAME_FIRSTNAME));
                lastName = cursor.getString(cursor.getColumnIndexOrThrow(StudentEntry.COLUMN_NAME_LASTNAME));
                contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(StudentEntry.COLUMN_NAME_CONTACTNUMBER));
                address = cursor.getString(cursor.getColumnIndexOrThrow(StudentEntry.COLUMN_NAME_ADDRESS));
            }

        } catch (Exception ex) {
            // silently catch exceptions
        }

        return new Student(id, firstName, lastName, contactNumber, address);
    }
}