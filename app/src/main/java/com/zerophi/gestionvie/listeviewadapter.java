package com.zerophi.gestionvie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class listeviewadapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mInflater;
    List<model> mList;
    ArrayList<model> mArrayList;

    public listeviewadapter(Context context, List<model> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
        this.mArrayList= new ArrayList<model>();
        this.mArrayList.addAll(mList);
    }
public class ViewHolder {
        TextView mTitle;
        TextView mDesc;
        ImageView mIcon;

}
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView  == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.rows,null);
         //   holder.mTitle =  convertView.findViewById(R.id.MainTitle);
         //   holder.mDesc = convertView.findViewById(R.id.Maindescription);
            holder.mIcon = convertView.findViewById(R.id.MainIcon);

            convertView.setTag(holder);

        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mDesc.setText(mList.get(position).getDesc());
        holder.mIcon.setImageResource(mList.get(position).getIcon());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    //filtrer
    public void filtrer (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        mList.clear();
        if(charText.length()==0){
            mList.addAll(mArrayList);

        }else
        {
            for(model mmodel : mArrayList){
                if (mmodel.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    mList.add(mmodel);
                }


            }
        }
notifyDataSetChanged();
    }


}
