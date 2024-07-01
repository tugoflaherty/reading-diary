package com.tugoflaherty.readingdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {

    myDbHelper myHelper;

    public myDbAdapter(Context context) {
        myHelper = new myDbHelper(context);
    }

    public long insertUserData(String pupilName, String parentName, String teacherName) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.PUPIL_NAME, pupilName);
        contentValues.put(myDbHelper.PARENT_NAME, parentName);
        contentValues.put(myDbHelper.TEACHER_NAME, teacherName);
        long id = db.insert(myDbHelper.USERS_TABLE_NAME, null, contentValues);
        return id;
    }

    public String getUserData() {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.PUPIL_NAME, myDbHelper.PARENT_NAME, myDbHelper.TEACHER_NAME};
        Cursor cursor = db.query(myDbHelper.USERS_TABLE_NAME, columns, null, null, null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String pupilName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_NAME));
            String parentName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_NAME));
            String teacherName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_NAME));
            buffer.append(uid+"¬"+pupilName+"¬"+parentName+"¬"+teacherName);
        }
        return buffer.toString();
    }

    public int deleteUser(String uid) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] whereArgs = {uid};
        int count = db.delete(myDbHelper.USERS_TABLE_NAME,myDbHelper.UID+" = ?", whereArgs);
        return count;
    }

    public int updateUser(String uid, String pupilName, String parentName, String teacherName) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.PUPIL_NAME, pupilName);
        contentValues.put(myDbHelper.PARENT_NAME, parentName);
        contentValues.put(myDbHelper.TEACHER_NAME, teacherName);
        String[] whereArgs = {uid};
        int count = db.update(myDbHelper.USERS_TABLE_NAME, contentValues, myDbHelper.UID+" = ?", whereArgs);
        return count;
    }

    public long insertDiaryEntryData(String readingStartDateTime, String readingEndDateTime, String bookTitle, String bookAuthor,
                                     String pageCount, String startPage, String endPage, String pupilEnjoymentRating, String pupilComments, String parentReadingAbilityRating,
                                     String parentComments, String teacherReadingProgressRating, String teacherComments, String userId) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.READING_START_DATE_TIME, readingStartDateTime);
        contentValues.put(myDbHelper.READING_END_DATE_TIME, readingEndDateTime);
        contentValues.put(myDbHelper.BOOK_TITLE, bookTitle);
        contentValues.put(myDbHelper.BOOK_AUTHOR, bookAuthor);
        contentValues.put(myDbHelper.PAGE_COUNT, pageCount);
        contentValues.put(myDbHelper.START_PAGE, startPage);
        contentValues.put(myDbHelper.END_PAGE, endPage);
        contentValues.put(myDbHelper.PUPIL_ENJOYMENT_RATING, pupilEnjoymentRating);
        contentValues.put(myDbHelper.PUPIL_COMMENTS, pupilComments);
        contentValues.put(myDbHelper.PARENT_READING_ABILITY_RATING, parentReadingAbilityRating);
        contentValues.put(myDbHelper.PARENT_COMMENTS, parentComments);
        contentValues.put(myDbHelper.TEACHER_READING_PROGRESS_RATING, teacherReadingProgressRating);
        contentValues.put(myDbHelper.TEACHER_COMMENTS, teacherComments);
        contentValues.put(myDbHelper.USER_ID, userId);
        long id = db.insert(myDbHelper.DIARY_ENTRIES_TABLE_NAME, null, contentValues);
        return id;
    }

    public String getDiaryEntryDataById(String givenUid) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] whereArgs = {givenUid};
        String[] columns = {myDbHelper.DIARY_ENTRIES_TABLE_NAME+"."+myDbHelper.UID, myDbHelper.READING_START_DATE_TIME, myDbHelper.READING_END_DATE_TIME, myDbHelper.BOOK_TITLE, myDbHelper.BOOK_AUTHOR,
                myDbHelper.PAGE_COUNT, myDbHelper.START_PAGE, myDbHelper.END_PAGE, myDbHelper.PUPIL_ENJOYMENT_RATING, myDbHelper.PUPIL_COMMENTS,
                myDbHelper.PARENT_READING_ABILITY_RATING, myDbHelper.PARENT_COMMENTS, myDbHelper.TEACHER_READING_PROGRESS_RATING, myDbHelper.TEACHER_COMMENTS,
                myDbHelper.PUPIL_NAME, myDbHelper.PARENT_NAME, myDbHelper.TEACHER_NAME};
        Cursor cursor = db.query(myDbHelper.DIARY_ENTRIES_TABLE_NAME+" LEFT JOIN "+myDbHelper.USERS_TABLE_NAME+" ON "+myDbHelper.DIARY_ENTRIES_TABLE_NAME+
                "."+myDbHelper.USER_ID+" = "+myDbHelper.USERS_TABLE_NAME+"."+myDbHelper.UID, columns, myDbHelper.DIARY_ENTRIES_TABLE_NAME+"."+myDbHelper.UID+" = ?", whereArgs, null,null,
                myDbHelper.READING_START_DATE_TIME,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String readingStartDateTime = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.READING_START_DATE_TIME));
            String readingEndDateTime = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.READING_END_DATE_TIME));
            String bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOK_TITLE));
            String bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOK_AUTHOR));
            String pageCount = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGE_COUNT));
            String startPage = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.START_PAGE));
            String endPage = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.END_PAGE));
            String pupilEnjoymentRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_ENJOYMENT_RATING));
            String pupilComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_COMMENTS));
            String parentReadingAbilityRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_READING_ABILITY_RATING));
            String parentComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_COMMENTS));
            String teacherReadingProgressRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_READING_PROGRESS_RATING));
            String teacherComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_COMMENTS));
            String pupilName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_NAME));
            String parentName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_NAME));
            String teacherName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_NAME));

            buffer.append(uid+"¬"+readingStartDateTime+"¬"+readingEndDateTime+"¬"+bookTitle+"¬"+bookAuthor+"¬"+pageCount+"¬"+startPage+"¬"+endPage+"¬"+pupilEnjoymentRating+"¬"+pupilComments+"¬"+parentReadingAbilityRating+"¬"+parentComments+"¬"+teacherReadingProgressRating+"¬"+teacherComments+"¬"+pupilName+"¬"+parentName+"¬"+teacherName);
        }
        return buffer.toString();
    }

    public String getDiaryEntryData() {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] columns = {myDbHelper.DIARY_ENTRIES_TABLE_NAME+"."+myDbHelper.UID, myDbHelper.READING_START_DATE_TIME, myDbHelper.READING_END_DATE_TIME, myDbHelper.BOOK_TITLE, myDbHelper.BOOK_AUTHOR,
                myDbHelper.PAGE_COUNT, myDbHelper.START_PAGE, myDbHelper.END_PAGE, myDbHelper.PUPIL_ENJOYMENT_RATING, myDbHelper.PUPIL_COMMENTS,
                myDbHelper.PARENT_READING_ABILITY_RATING, myDbHelper.PARENT_COMMENTS, myDbHelper.TEACHER_READING_PROGRESS_RATING, myDbHelper.TEACHER_COMMENTS,
                myDbHelper.PUPIL_NAME, myDbHelper.PARENT_NAME, myDbHelper.TEACHER_NAME};
        Cursor cursor = db.query(myDbHelper.DIARY_ENTRIES_TABLE_NAME+" LEFT JOIN "+myDbHelper.USERS_TABLE_NAME+" ON "+myDbHelper.DIARY_ENTRIES_TABLE_NAME+
                        "."+myDbHelper.USER_ID+" = "+myDbHelper.USERS_TABLE_NAME+"."+myDbHelper.UID, columns, null, null, null,null,
                myDbHelper.READING_START_DATE_TIME,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String readingStartDateTime = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.READING_START_DATE_TIME));
            String readingEndDateTime = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.READING_END_DATE_TIME));
            String bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOK_TITLE));
            String bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.BOOK_AUTHOR));
            String pageCount = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PAGE_COUNT));
            String startPage = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.START_PAGE));
            String endPage = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.END_PAGE));
            String pupilEnjoymentRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_ENJOYMENT_RATING));
            String pupilComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_COMMENTS));
            String parentReadingAbilityRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_READING_ABILITY_RATING));
            String parentComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_COMMENTS));
            String teacherReadingProgressRating = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_READING_PROGRESS_RATING));
            String teacherComments = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_COMMENTS));
            String pupilName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PUPIL_NAME));
            String parentName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.PARENT_NAME));
            String teacherName = cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.TEACHER_NAME));

            buffer.append(uid+"¬"+readingStartDateTime+"¬"+readingEndDateTime+"¬"+bookTitle+"¬"+bookAuthor+"¬"+pageCount+"¬"+startPage+"¬"+endPage+"¬"+pupilEnjoymentRating+"¬"+pupilComments+"¬"+parentReadingAbilityRating+"¬"+parentComments+"¬"+teacherReadingProgressRating+"¬"+teacherComments+"¬"+pupilName+"¬"+parentName+"¬"+teacherName+"`");
        }
        return buffer.toString();
    }

    public int deleteDiaryEntry(String uid) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] whereArgs = {uid};
        int count = db.delete(myDbHelper.DIARY_ENTRIES_TABLE_NAME,myDbHelper.UID+" = ?", whereArgs);
        return count;
    }

    public int updateDiaryEntry(String uid, String readingStartDateTime, String readingEndDateTime, String bookTitle, String bookAuthor,
                                String pageCount, String startPage, String endPage, String pupilEnjoymentRating, String pupilComments, String parentReadingAbilityRating,
                                String parentComments, String teacherReadingProgressRating, String teacherComments, String userId) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.READING_START_DATE_TIME, readingStartDateTime);
        contentValues.put(myDbHelper.READING_END_DATE_TIME, readingEndDateTime);
        contentValues.put(myDbHelper.BOOK_TITLE, bookTitle);
        contentValues.put(myDbHelper.BOOK_AUTHOR, bookAuthor);
        contentValues.put(myDbHelper.PAGE_COUNT, pageCount);
        contentValues.put(myDbHelper.START_PAGE, startPage);
        contentValues.put(myDbHelper.END_PAGE, endPage);
        contentValues.put(myDbHelper.PUPIL_ENJOYMENT_RATING, pupilEnjoymentRating);
        contentValues.put(myDbHelper.PUPIL_COMMENTS, pupilComments);
        contentValues.put(myDbHelper.PARENT_READING_ABILITY_RATING, parentReadingAbilityRating);
        contentValues.put(myDbHelper.PARENT_COMMENTS, parentComments);
        contentValues.put(myDbHelper.TEACHER_READING_PROGRESS_RATING, teacherReadingProgressRating);
        contentValues.put(myDbHelper.TEACHER_COMMENTS, teacherComments);
        contentValues.put(myDbHelper.USER_ID, userId);
        String[] whereArgs = {uid};
        int count = db.update(myDbHelper.DIARY_ENTRIES_TABLE_NAME, contentValues, myDbHelper.DIARY_ENTRIES_TABLE_NAME+"."+myDbHelper.UID+" = ?", whereArgs);
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "readingDiaryDatabase";
        private static final String DIARY_ENTRIES_TABLE_NAME = "diaryEntriesTable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String USERS_TABLE_NAME = "usersTable";
        private static final String PUPIL_NAME = "PupilName";
        private static final String PARENT_NAME = "ParentName";
        private static final String TEACHER_NAME = "TeacherName";
        private static final String CREATE_USERS_TABLE =
                "CREATE TABLE "+USERS_TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PUPIL_NAME+" VARCHAR(255), "+PARENT_NAME+" VARCHAR(255), "+TEACHER_NAME+" VARCHAR(255));";
        private static final String READING_START_DATE_TIME = "ReadingStartDateTime";
        private static final String READING_END_DATE_TIME = "ReadingEndDateTime";
        private static final String BOOK_TITLE = "BookTitle";
        private static final String BOOK_AUTHOR = "BookAuthor";
        private static final String PAGE_COUNT = "PageCount";
        private static final String START_PAGE = "StartPage";
        private static final String END_PAGE = "EndPage";
        private static final String PUPIL_ENJOYMENT_RATING = "PupilEnjoymentRating";
        private static final String PUPIL_COMMENTS = "PupilComments";
        private static final String PARENT_READING_ABILITY_RATING = "ParentReadingAbilityRating";
        private static final String PARENT_COMMENTS = "ParentComments";
        private static final String TEACHER_READING_PROGRESS_RATING = "TeacherReadingProgressRating";
        private static final String TEACHER_COMMENTS = "TeacherComments";
        private static final String USER_ID = "UserID";
        private static final String CREATE_DIARY_ENTRIES_TABLE =
                "CREATE TABLE "+DIARY_ENTRIES_TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        READING_START_DATE_TIME+" VARCHAR(255), "+READING_END_DATE_TIME+ " VARCHAR(255), "+
                        BOOK_TITLE+" VARCHAR(255), "+BOOK_AUTHOR+" VARCHAR(255), "+PAGE_COUNT+" INTEGER, "+
                        START_PAGE+" VARCHAR(255), "+END_PAGE+" VARCHAR(255), "+PUPIL_ENJOYMENT_RATING+" VARCHAR(255), "+
                        PUPIL_COMMENTS+" VARCHAR(255), "+PARENT_READING_ABILITY_RATING+" VARCHAR(255), "+
                        PARENT_COMMENTS+" VARCHAR(255), "+TEACHER_READING_PROGRESS_RATING+" VARCHAR(255), "+
                        TEACHER_COMMENTS+" VARCHAR(255), "+USER_ID+" INTEGER, FOREIGN KEY ("+USER_ID+") REFERENCES "+USERS_TABLE_NAME+" ("+UID+"));";
        private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS "+USERS_TABLE_NAME;
        private static final String DROP_DIARY_ENTRIES_TABLE = "DROP TABLE IF EXISTS "+DIARY_ENTRIES_TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_USERS_TABLE);
                db.execSQL(CREATE_DIARY_ENTRIES_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_DIARY_ENTRIES_TABLE);
                db.execSQL(DROP_USERS_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}
