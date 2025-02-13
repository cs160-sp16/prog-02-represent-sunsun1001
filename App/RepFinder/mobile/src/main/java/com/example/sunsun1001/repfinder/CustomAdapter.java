package com.example.sunsun1001.repfinder;

/**
 * Created by sunsun1001 on 3/1/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;

import com.squareup.picasso.Picasso;

public class CustomAdapter extends BaseAdapter {

    String[] senatorNames;
    String[] siteUrl;
    String[] emailAdd;
    String[] tweet;
    String[] bioID;
    String[] party;
    String[] termDate;


    Context context;
    public Intent newIntent;
    private static LayoutInflater inflater = null;

    public CustomAdapter(listReps mainActivity, String[] senatorNames,
                         String[] siteUrl, String[] emailAdd, String[] tweet, String[] bioID,
                         String[] party, String[] termDate) {
        // TODO Auto-generated constructor stub

        this.senatorNames = senatorNames;
        this.siteUrl = siteUrl;
        this.emailAdd = emailAdd;
        this.tweet = tweet;
        this.bioID = bioID;
        this.party = party;
        this.termDate = termDate;


        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return senatorNames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView textSenatorNames;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View myRowView = inflater.inflate(R.layout.row, null);

        // Formatting the Rows

        // Text view formatting
        holder.textSenatorNames = (TextView) myRowView.findViewById(R.id.name);
        String output = "\n\n\t\t\t" + senatorNames[position] +
                "\n\n\n\t\t\t" +
                siteUrl[position] + "\n\n\t\t\t" +
                emailAdd[position] + "\n\n\t\t\t\t" + tweet[position] +
                "\n";
        SpannableString text = new SpannableString(output);

        if (this.party[position].equalsIgnoreCase("D")) {
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#2F80ED")), 0,
                    5 + senatorNames[position].length(), 0);
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#2F80ED")),
                    output.length() - tweet[position].length() - 5, output.length(), 0);
        } else if (this.party[position].equalsIgnoreCase("I")) {
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#219653")), 0,
                    5 + senatorNames[position].length(), 0);
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#219653")),
                    output.length() - tweet[position].length() - 5, output.length(), 0);
        } else {
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#EB5757")), 0,
                    5 + senatorNames[position].length(), 0);
            text.setSpan(new ForegroundColorSpan(Color.parseColor("#EB5757")),
                    output.length() - tweet[position].length() - 5, output.length(), 0);
        }
        holder.textSenatorNames.setText(text, TextView.BufferType.SPANNABLE);

        // Image

        holder.img = (ImageView) myRowView.findViewById(R.id.imageView1);
        String url = "https://theunitedstates.io/images/congress/original/" + bioID[position]
                + ".jpg";
        Picasso.with(context)
                .load(url)
                .into(holder.img);

        holder.img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                newIntent.putExtra("name", senatorNames[position]);
                newIntent.putExtra("bioID", bioID[position]);
                newIntent.putExtra("term", termDate[position]);
                newIntent.putExtra("party", party[position]);
                context.startActivity(newIntent);
            }
        });

        holder.textSenatorNames.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                newIntent.putExtra("name", senatorNames[position]);
                newIntent.putExtra("bioID", bioID[position]);
                newIntent.putExtra("term", termDate[position]);
                newIntent.putExtra("party", party[position]);
                context.startActivity(newIntent);
            }
        });


        return myRowView;
    }

}