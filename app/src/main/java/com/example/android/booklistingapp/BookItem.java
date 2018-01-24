package com.example.android.booklistingapp;

/**
 * Created by Addi_ola on 07/01/2018.
 */

public class BookItem {
    //member variables
    private String mBookTitle;
    private String mAuthorName;

    public BookItem(String book, String name) {
        mBookTitle = book;
        mAuthorName = name;
    }

    //getter methods
    public String getBookTitle(){return mBookTitle;}

    public String getAuthorName(){return mAuthorName;}


}
