package com.example.fresh.sunshine;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }

    private final String LOGTAG = "MainActivityFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        String[] forecastArray = {
        "Today - Sunny - 88/76",
        "Tomorrow - Sunny - 70/65",
        "Weds - Cloudy - 85/72",
        "Thurs - Sunny - 88/76",
        "Fri - Foggy - 98/76",
        "Sat - Snowy - 24/-13",
        "Sun - Hot - 101/95"
    };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        ArrayAdapter<String> mForecastAdapter =
                new ArrayAdapter<String>(
                        //The current context
                        getActivity(),
                        //ID of list item layout
                        R.layout.list_item_forecast,
                        //ID of the textview to populate
                        R.id.list_item_forecast_textview,
                        //Forecast data
                        weekForecast);

        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }

    public class FetchWeatherTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void...params){
            HttpURLConnection urlCon = null;
            BufferedReader reader = null;

            String forecastJsonStr =  null;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=50014&cnt=7&mode=json&units=metric");

                urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.connect();

                InputStream inputStream = urlCon.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                forecastJsonStr = buffer.toString();
            }
            catch (IOException e) {
                Log.e(LOGTAG, "Error", e);
                return null;
            } finally {
                if (urlCon != null) {
                    urlCon.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e){
                        Log.e(LOGTAG, "Error closing stream", e);
                    }
                }
            }
            return null;//forecastJsonStr;
        }
    }

}
