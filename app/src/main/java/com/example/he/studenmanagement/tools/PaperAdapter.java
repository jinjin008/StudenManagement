package com.example.he.studenmanagement.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.he.studenmanagement.R;
import com.example.he.studenmanagement.tools.Paper;

import java.util.List;

/**
 * Created by wjy on 2018/5/31.
 */

public class PaperAdapter extends ArrayAdapter<Paper> {
    private int resourceId;

//    private paperDatabaseHelper dbHelper;

    public PaperAdapter(Context context, int resource, List<Paper> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Paper paper = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.paper_id =(TextView) view.findViewById(R.id.item_paper_id);
            viewHolder.paper_name =(TextView) view.findViewById(R.id.item_paper_name);
            viewHolder.paper_date =(TextView) view.findViewById(R.id.item_paper_date);
//            viewHolder.bankId =(TextView) view.findViewById(R.id.item_paper_bankId);
//            viewHolder.paper_date=(TextView)view.findViewById(R.id.item_paper_date);



            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor=db.rawQuery("select datetime('now','localtime') from paper where id =", new String[]{paper.getId()});
//        String date = cursor.getString(cursor.getColumnIndex("datetime('now','localtime')"));


        viewHolder.paper_date.setText("2018-6-2");
//        viewHolder.bankId.setText(paper.getBankId());
        viewHolder.paper_id.setText(paper.getId());
//        viewHolder.paper_date.setText((CharSequence) paper.getDate());
        viewHolder.paper_name.setText(paper.getName());


        return view;

    }

    class ViewHolder {
        TextView paper_id;
        TextView paper_date;
        //        TextView bankId;
//        TextView paper_date;
        TextView paper_name;


    }
}
