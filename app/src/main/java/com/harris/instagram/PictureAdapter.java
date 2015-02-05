package com.harris.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by harris on 2/4/15.
 */
public class PictureAdapter<T> extends ArrayAdapter<Picture>{

  public PictureAdapter(Context context, int resource, List<Picture> objects) {
    super(context, resource, objects);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    Picture picture = getItem(position);
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.picture_list_cell, parent, false);
    }

    ImageView imageView = (ImageView) convertView.findViewById(R.id.picture_image);
    Picasso.with(getContext()).load(picture.getImageUrl()).into(imageView);

    TextView username = (TextView) convertView.findViewById(R.id.username);
    username.setText(picture.getUsername());

    TextView caption = (TextView) convertView.findViewById(R.id.caption);
    caption.setText(picture.getCaption());

    TextView likesCount = (TextView) convertView.findViewById(R.id.likes_count);
    likesCount.setText("" + picture.getLikesCount() + " likes");
    return convertView;
  }
}
