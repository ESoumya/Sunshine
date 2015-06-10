package com.example.fresh.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

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
}
