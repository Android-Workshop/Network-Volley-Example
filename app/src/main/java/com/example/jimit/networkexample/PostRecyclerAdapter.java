package com.example.jimit.networkexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jimit on 24-12-2015.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Post> objects;

    public PostRecyclerAdapter(Context context, @NonNull ArrayList<Post> objects) {
        this.context = context;
        this.objects = objects;
    }

    public void removeAll() {
        objects.clear();
        notifyDataSetChanged();
    }

    public void addAll(@NonNull ArrayList<Post> objects) {
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public void add(Post data) {
        objects.add(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderPost(LayoutInflater.from(context).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderPost) {
            ViewHolderPost viewHolder = (ViewHolderPost) holder;
            viewHolder.lblId.setText("id : " + objects.get(position).getId());
            viewHolder.lblUser.setText("User : " + objects.get(position).getUserId());
            viewHolder.lblTitle.setText("Title : " + objects.get(position).getTitle());
            viewHolder.lblBody.setText("Body : " + objects.get(position).getBody());
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolderPost extends RecyclerView.ViewHolder {

        protected TextView lblId, lblUser, lblTitle, lblBody;

        public ViewHolderPost(View itemView) {
            super(itemView);
            lblId = (TextView) itemView.findViewById(R.id.lbl_id);
            lblUser = (TextView) itemView.findViewById(R.id.lbl_user);
            lblTitle = (TextView) itemView.findViewById(R.id.lbl_title);
            lblBody = (TextView) itemView.findViewById(R.id.lbl_body);
        }
    }
}
