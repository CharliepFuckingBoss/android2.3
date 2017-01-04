//main
package com.example.pr_idi.mydatabaseexample;


import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.R.attr.fragment;


public class MainActivity extends Activity {

    private String[] items = {
            "Main",
            "Search",
            "akslfdj"
    };

    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private int selectedPosition;
    private BookData bookdata;


    private void mostramiss() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Error");
        //.setMessage("What opinion do you have about this book?")
        builder.setMessage("This book already exists.");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bookdata = new BookData(this);
        bookdata.open();
        bookdata.deleteAllBooks();  //descomentar-ho per a eliminar tots els llibres de la BD
        /*String[] newBook = new String[] { "Miguel Strogoff", "Jules Verne", "Ulysses", "James Joyce", "Don Quijote", "Miguel de Cervantes", "Metamorphosis", "Kafka" };
        int nextInt = new Random().nextInt(4);
        // save the new book to the database
        bookdata.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);*/



        @SuppressWarnings("unchecked")
        Book book = new Book();
        book = bookdata.createBook("Harry Potter", "JK Rowling", 1997, "Bloomsbury", "Fantasia", "Very Great");
        if (book == null) mostramiss();
        book = bookdata.createBook("Paterson", "William Carlos Williams", 1946, "Christopher Beach", "Poesy", "Very Great");
        if (book == null) mostramiss();
        book = bookdata.createBook("Perico Palotes", "Jaimito", 1000, "Mari Carmen", "Infantil", "Very Bad");
        if (book == null) mostramiss();
        book = bookdata.createBook("El Medico", "Noah Gordon", 1986, "Readers", "Classic", "Regular");
        if (book == null) mostramiss();



        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new Fragment_1());
        tx.commit();


/* Getting reference to the DrawerLayout */
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerList = (ListView) findViewById(R.id.drawer_list);

    /* Creating an ArrayAdapter to add items to mDrawerList */
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.drawer_list_item, items);

/* Setting the adapter to myDrawerList */
        myDrawerList.setAdapter(adapter);

        // Setting item click listener to mDrawerList
        myDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                //Replace fragment content
                updateFragment();
                myDrawerLayout.closeDrawer(myDrawerList);
            }
        });
    }

    public void updateFragment() {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (selectedPosition) {
            case 0:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_1 fragment1 = new Fragment_1();
                fragmentTransaction.replace(R.id.content_frame, fragment1);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_2 fragment2 = new Fragment_2();
                fragmentTransaction.replace(R.id.content_frame, fragment2);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_3 fragment3 = new Fragment_3();
                fragmentTransaction.replace(R.id.content_frame, fragment3);
                fragmentTransaction.commit();
                break;
        }



    }

    // Life cycle methods. Check whether it is necessary to reimplement them

    @Override
    protected void onResume() {
        bookdata.open();
        super.onResume();
    }

    // Life cycle methods. Check whether it is necessary to reimplement them

    @Override
    protected void onPause() {
        bookdata.close();
        super.onPause();
    }

}