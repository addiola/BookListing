package com.example.android.booklistingapp;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Addi_ola on 07/01/2018.
 */

public class BookItemLoader extends AsyncTaskLoader<List<BookItem>> {




    /** Tag for log messages */
    private static final String LOG_TAG = BookItemLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link BookItemLoader}.
     *
     * @param context of the activity
     */

    public BookItemLoader(Context context, String urls) {
        super(context);
        mUrl = urls;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<BookItem> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<BookItem> bookItems = QueryUtils.fetchBookItemsData(mUrl);
        return bookItems;
    }
}

