package org.nando.qscraping;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.nando.qscraping.task.ScrapeTask;

import java.util.Date;

public class MainActivity extends Activity {

    private TextView resultsText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsText = (TextView) findViewById(R.id.resultsText);
        webView = (WebView) findViewById(R.id.webView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void fetchData(View view) {
        Date date = new Date();
        String url = "http://fuelprices.racq.com.au/fuelSearch/performSearch.aspx?_=1&Location=4152&FuelType=Unleaded&SearchType=Advanced&MapName=";
        ScrapeTask task = new ScrapeTask(this);
        resultsText.setText("");
        task.execute(url);
    }

    public void placeResults(String results) {
        webView.loadData(results,"text/html",null);
    }
    
}
