package com.yungcs.project.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yungcs on 2016/1/7.
 */
public class ContactsAdapter extends RecyclerViewCursorAdapter<ContactsAdapter.MyViewHolder> {



    private static final String TAG = "ContactsAdapter";
    public ContactsAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }




    // new view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        ClickListener clickListener = new ClickListener();
        myViewHolder.linearLayout.setOnClickListener(clickListener);
        view.setTag(myViewHolder.linearLayout.getId(),clickListener);
        return myViewHolder ;
    }

    // bind view
    @Override
    public void onBindViewHolder(final MyViewHolder holder, Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
      /*  String number =cursor.getString(cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER));*/
     /*   long contactsId = Long.parseLong(cursor.getString(cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID)));*/

        holder.contactName.setText(name);
    /*    ClickListener clickListener = (ClickListener) holder.itemView.getTag(
                holder.linearLayout.getId());
        clickListener.setContactsId(contactsId);*/
    }

    @Override
    protected void onContentChanged() {

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView contactName;
        private LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contactNameText);
            linearLayout  = (LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }


    private class ClickListener implements View.OnClickListener {

        private long contactsId;

        public void setContactsId(long contactsId) {
            this.contactsId = contactsId;
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG,"contactsId:"+contactsId); }
    }
}
