package com.example.pr_idi.mydatabaseexample;

/**
 * BookData
 * Created by pr_idi on 10/11/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ViewDebug;

public class BookData {

    // Database fields
    private SQLiteDatabase database;

    // Helper to manipulate table
    private MySQLiteHelper dbHelper;

    // Here we only select Title and Author, must select the appropriate columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_AUTHOR};

    public BookData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public void updateValoration(Book book) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id = book.getId();
        String valoration = book.getPersonal_evaluation();
        values.put(MySQLiteHelper.COLUMN_PERSONAL_EVALUATION, valoration);
        int i = database.update(MySQLiteHelper.TABLE_BOOKS, values,  MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public String getValoration(Book book) {
        database = dbHelper.getWritableDatabase();
        long id = book.getId();
        String[] columns =  {
            MySQLiteHelper.COLUMN_PERSONAL_EVALUATION
        };
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS, columns, MySQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        String res = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_PERSONAL_EVALUATION));
        cursor.close();
        return res;

    }



    public Book createBook(String title, String author, int year, String publisher, String category, String personal_evaluation) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + title + " " + author);

        // Add data: Note that this method only provides title and author
        // Must modify the method to add the full data

        ///////comentar inici aquí si no funciona
        boolean bo = false;
        for(Book b : getAllBooks()) {
            if (b.getAuthor().equals(author) && b.getTitle().equals(title)) {
                bo = true;
            }
        }
        if (bo) {
            return null;
        }

        else {
            ///////comentar final aquí si no funciona

            values.put(MySQLiteHelper.COLUMN_TITLE, title);
            values.put(MySQLiteHelper.COLUMN_AUTHOR, author);

            // Invented data
            values.put(MySQLiteHelper.COLUMN_PUBLISHER, publisher);
            values.put(MySQLiteHelper.COLUMN_YEAR, year);
            values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
            values.put(MySQLiteHelper.COLUMN_PERSONAL_EVALUATION, personal_evaluation);

            // Actual insertion of the data using the values variable
            long insertId = database.insert(MySQLiteHelper.TABLE_BOOKS, null,
                    values);

            // Main activity calls this procedure to create a new book
            // and uses the result to update the listview.
            // Therefore, we need to get the data from the database
            // (you can use this as a query example)
            // to feed the view.

            Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                    allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            Book newBook = cursorToBook(cursor);

            // Do not forget to close the cursor
            cursor.close();


        // Return the book
        return newBook;
    }}

    public void deleteBook(Book book) {
        long id = book.getId();
        System.out.println("Book deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_BOOKS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            books.add(book);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return books;
    }

    private Book cursorToBook(Cursor cursor) {
        Book book = new Book();
        book.setId(cursor.getLong(0));
        book.setTitle(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
        return book;
    }

    //bookdata funció
    public void deleteAllBooks() { //l'he creat jo (Carlota) només necessaria per a debugar
        Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOKS,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            long id = book.getId();
            database.delete(MySQLiteHelper.TABLE_BOOKS, MySQLiteHelper.COLUMN_ID
                    + " = " + id, null);
            cursor.moveToNext();
        }

    }
}