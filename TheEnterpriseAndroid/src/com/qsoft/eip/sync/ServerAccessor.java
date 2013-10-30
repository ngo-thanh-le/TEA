package com.qsoft.eip.sync;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is intended to encapsulate all the actions against Parse.com.
 * <p/>
 * Created by Udini on 7/6/13.
 */
public class ServerAccessor
{
    private class Websites implements Serializable {
        List<Website> results;
    }

    public List<Website> getWebsite(String auth) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "http://localhost:8080/REST/WebService/GetFeeds";

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("X-Parse-Session-Token", auth);

        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK)
            {
                throw new Exception("Error retrieving: " + responseString);
            }

            Websites shows = new Gson().fromJson(responseString, Websites.class);
            return shows.results;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new ArrayList<Website>();
    }
}
