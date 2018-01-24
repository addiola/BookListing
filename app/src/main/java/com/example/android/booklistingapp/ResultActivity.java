package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Addi_ola on 07/01/2018.
 */

public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookItem>> {
        private static final String LOG_TAG = ResultActivity.class.getName();

        /** URL for data data from the USGS dataset */
        private static final String REQUEST_URL =
               "https://www.googleapis.com/books/v1/volumes";

        /**
         * Constant value for the loader ID. We can choose any integer.
         * This really only comes into play if you're using multiple loaders.
         */
        private static final int BOOKITEM_LOADER_ID = 1;

        /** Adapter for the list*/
        private BookItemAdapter mAdapter;

        /** TextView that is displayed when the list is empty */
        private TextView mEmptyStateTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.search_result);



            // Find a reference to the {@link ListView} in the layout
            ListView bookItemListView = (ListView) findViewById(R.id.list);

            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            bookItemListView.setEmptyView(mEmptyStateTextView);

            // Create a new adapter that takes an empty list of bookitems as input
            mAdapter = new BookItemAdapter(this, new ArrayList<BookItem>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            bookItemListView.setAdapter(mAdapter);


            // Get a reference to the ConnectivityManager to check state of network connectivity
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(BOOKITEM_LOADER_ID, null, this);
            } else {
                // Otherwise, display error
                // First, hide loading indicator so error message will be visible
                View loadingIndicator = findViewById(R.id.loading_spinner);
                loadingIndicator.setVisibility(View.GONE);

                // Update empty state with no connection error message
                mEmptyStateTextView.setText(R.string.no_internet);
            }
        }

        @Override
        public Loader<List<BookItem>> onCreateLoader(int i, Bundle bundle) {

            Bundle extras = getIntent().getExtras();
            String queryString = extras.getString("message");

            Uri baseUri = Uri.parse(REQUEST_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("q", queryString);
            uriBuilder.appendQueryParameter("maxResult", "10");

//
//            return new BookItemLoader(this , uriBuilder.toString());
            return new BookItemLoader(this , uriBuilder.toString());

        }

        @Override
        public void onLoadFinished(Loader<List<BookItem>> loader, List<BookItem> bookItems) {
            // Hide loading indicator because the data has been loaded
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            // Set empty state text to display string when no data is found
            mEmptyStateTextView.setText(R.string.empty_view);

            // Clear the adapter of previous  data
            mAdapter.clear();

            // If there is a valid list , then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (bookItems != null && !bookItems.isEmpty()) {
                mAdapter.addAll(bookItems);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<BookItem>> loader) {
            // Loader reset, so we can clear out our existing data.
            mAdapter.clear();
        }


}



