package com.example.he.studenmanagement.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.he.studenmanagement.R;

import java.util.List;

/**
 * Created by wjy on 2018/5/21.
 */

public class BankAdapter extends ArrayAdapter<Bank> {
    private int resourceId;
    public BankAdapter(Context context, int resource, List<Bank> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bank bank = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.bank_id =(TextView) view.findViewById(R.id.item_bank_id);
            viewHolder.bank_type =(TextView) view.findViewById(R.id.item_bank_type);
            viewHolder.bank_difficult= (TextView) view.findViewById(R.id.item_bank_difficult);
            viewHolder.bank_title = (TextView) view.findViewById(R.id.item_bank_title);


            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.bank_id.setText(String.valueOf(bank.getId()));
        viewHolder.bank_type.setText(String.valueOf(bank.getType()));
        viewHolder.bank_difficult.setText(String.valueOf(bank.getDifficult()));
        viewHolder.bank_title.setText(bank.getTitle());


        return view;

    }

    class ViewHolder {
        TextView bank_id;

        TextView bank_title;
        TextView bank_type;
        TextView bank_difficult;

    }

}
