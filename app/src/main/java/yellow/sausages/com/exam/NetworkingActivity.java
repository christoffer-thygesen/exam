package yellow.sausages.com.exam;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class NetworkingActivity extends AppCompatActivity {

    private TextView JSONArrayText;
    private TextView httpResponseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        JSONArrayText = findViewById(R.id.JSONArrayText);
        httpResponseText = findViewById(R.id.httpResponseText);

        //HTTPREQUEST
        new getJSONresponse().execute();

        //JSON STUFF
        InputStream is = getResources().openRawResource(R.raw.kittens);
        String json;
        try {
            json = readFromStream(is);
            JSONObject root = new JSONObject(json);
            JSONArray kittens = root.getJSONArray("kittens");
            String firstCatName = kittens.getJSONObject(0).getString("name");
            int firstCatAge = kittens.getJSONObject(0).getInt("age");
            String secondCatName = kittens.getJSONObject(1).getString("name");
            int secondCatAge = kittens.getJSONObject(1).getInt("age");

            JSONArrayText.setText(firstCatAge + " " + firstCatName + " - " + secondCatAge + " " + secondCatName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();

            }
            reader.close();
        }
        return output.toString();
    }


    private class getJSONresponse extends AsyncTask<Void, Void, String> {

        String name = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=3";
        String jsonResponse;
        URL the_url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        @Override
        protected String doInBackground(Void... urls) {
            try {
                the_url = new URL(name);
                urlConnection = (HttpURLConnection) the_url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();

                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();

                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                Log.d("DEBUG_FIRST", "I worked: " + root.toString());
                int st = root.getInt("totalItems");
                Log.d("DEBUG_SE", st + "");
                //String  = root.getInt("totalItems");
                //JSONArray kittens = root.getJSONObject();
                httpResponseText.setText(st + "");

            } catch (JSONException e) {
                Log.d("DEBUG_JSON", "fail 1");
                e.printStackTrace();
            }
        }
    }
}
