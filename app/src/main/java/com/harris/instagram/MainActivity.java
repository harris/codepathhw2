package com.harris.instagram;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
  private PictureAdapter<Picture> pictureAdapter;
  private List<Picture> pictures;
  private ListView pictureList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pictures = new ArrayList<Picture>();
    pictureAdapter = new PictureAdapter<>(this, android.R.layout.simple_list_item_1, pictures);
    pictureList = (ListView) findViewById(R.id.picture_list);
    pictureList.setAdapter(pictureAdapter);
    loadPictures();
  }

  private void loadPictures() {
    String url =
        "https://api.instagram.com/v1/media/popular?client_id=e1f898ceaeff41b088947d0cc3df8f7b";
    AsyncHttpClient client = new AsyncHttpClient();
    client.get(url, new JsonHttpResponseHandler(){

      @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
          JSONArray picturesData = response.getJSONArray("data");
          for (int i = 0; i < picturesData.length(); i++) {
            JSONObject jsonObject = picturesData.getJSONObject(i);
            pictures.add(Picture.parseJSON(jsonObject));
          }
          pictureAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
