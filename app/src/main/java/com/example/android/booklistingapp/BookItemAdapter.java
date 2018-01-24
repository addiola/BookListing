package com.example.android.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Addi_ola on 07/01/2018.
 */

public class BookItemAdapter extends ArrayAdapter<BookItem> {

    public BookItemAdapter(Context context, List<BookItem> bookItems) {
        super(context,0, bookItems);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (convertView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.result_list_items,parent, false );
        }

        BookItem currentBookItem = getItem(position);

        //TextView
        TextView bookTextView = (TextView) listItemView.findViewById(R.id.book_name);
        //set text
        bookTextView.setText(currentBookItem.getBookTitle());


        //AuthorTextView
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_name);
        //set  text
        authorTextView.setText(currentBookItem.getAuthorName());

        return listItemView;
    }
}


