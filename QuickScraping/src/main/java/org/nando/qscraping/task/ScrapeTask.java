package org.nando.qscraping.task;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nando.qscraping.MainActivity;

/**
 * Created by deleonf on 8/07/13.
 */
public class ScrapeTask extends AsyncTask<String,Void,String> {

    private MainActivity mainActivity;

    public ScrapeTask(MainActivity anActivity) {
        mainActivity = anActivity;
    }

    protected void onPreExecute() {
        System.setProperty("http.proxyHost", "proxy.qtc.com.au");
        System.setProperty("http.proxyPort", "8080");

    }

    protected String doInBackground(String... strings) {
        String results = "N/A";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(strings[0]);
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String html = EntityUtils.toString(entity);
                String start = "<td class=\"brandName\">";
                String end = "<td class=\"collected\">";
                String part = html.substring(html.indexOf(start)+start.length());
                results = part.substring(0,part.indexOf(end));


            }

        } catch(Exception e) {
            results = e.getMessage();
        }
        return results;
    }

    protected void onPostExecute(String result) {
        this.mainActivity.placeResults(result);
    }





}
