//fragment2
package com.example.pr_idi.mydatabaseexample;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by carlota on 27/12/16.
 */

public class Fragment_1 extends Fragment {
    static final String MENU_ITEM = "Main";
    private List<Book> llistallibres;
    private ListView list;
    private SimpleAdapter adaptador;
    private String newopinion;
    private Book llibreModificar;
    private String opinion;
    private BookData bd;

    private void updateAttrib(String s) {
        newopinion = s;
    }
    private void updateOp() {
        llibreModificar.setPersonal_evaluation(newopinion);
        BookData bd = new BookData(getActivity());
        bd.updateValoration(llibreModificar);
        //System.out.println(llibreModificar.getId());
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_1, container, false);

        //Llistar tots els llibres inicialment
        Context ctx = getActivity();
        BookData bookData = new BookData(ctx);
        bookData.open();
        llistallibres = bookData.getAllBooks();

        Collections.sort(llistallibres, new Comparator<Book>() {

            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        //fem aparèixer llistallibres en list, pantalla

        list = (ListView) view.findViewById(R.id.filmList);

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Book book : llistallibres) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", book.getTitle());
            datum.put("author", book.getAuthor());
            data.add(datum);
        }


        adaptador = new SimpleAdapter(getActivity(), data,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "author"},
                new int[]{android.R.id.text1,
                        android.R.id.text2});
        list.setAdapter(adaptador);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {


                Map<String, String> fila = new HashMap<String, String>(2);
                fila = (Map<String, String>) parent.getItemAtPosition(position);
                String title = fila.get("title");
                String author = fila.get("author");
                //opinion = null;
                for (Book b : llistallibres) {
                    if (b.getAuthor().equals(author) && b.getTitle().equals(title)) {
                        //opinion = b.getPersonal_evaluation();
                        llibreModificar = b;
                    }
                }
                //he de esborrar la pròxima línia
                //opinion = "Very bad";
                bd = new BookData(getActivity());
                opinion = bd.getValoration(llibreModificar);
                int index = -1;
                if (opinion == null) index = -1;
                else {
                    if (opinion.equals("Very Bad")) index = 0;
                    else if (opinion.equals("Bad")) index = 1;
                    else if (opinion.equals("Regular")) index = 2;
                    else if (opinion.equals("Great")) index = 3;
                    else if (opinion.equals("Very Great")) index = 4;
                }


                final CharSequence[] items = {
                        "Very Bad",
                        "Bad",
                        "Regular",
                        "Great",
                        "Very Great"
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Set the dialog title
                builder.setTitle("Personal Opinion");
                //.setMessage("What opinion do you have about this book?")

                builder.setSingleChoiceItems(items, index, new DialogInterface.OnClickListener()  {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedOpinion = (String) items[which];
                        updateAttrib(selectedOpinion);
                    }});

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateAttrib(opinion);
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateOp();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });


        return view;
    }

}
