package com.tugoflaherty.readingdiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class myHistoryAdapter extends RecyclerView.Adapter<myHistoryAdapter.ViewHolder> {
    public List<String> historyData;
    public Context context;

    public myHistoryAdapter(List<String> historyData) {
        this.historyData = historyData;
    }

    public void setDataSet(List<String> historyData){
        this.historyData = historyData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView startDate, bookTitle, bookAuthor;

        public ViewHolder(View view) {
            super(view);
            startDate = (TextView) view.findViewById(R.id.list_item_start_date_time);
            bookTitle = (TextView) view.findViewById(R.id.list_item_book_title);
            bookAuthor = (TextView) view.findViewById(R.id.list_item_book_author);
        }
    }

    @Override
    public myHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_history_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myHistoryAdapter.ViewHolder holder, int position) {
        String[] recordData = historyData.get(position).split("¬");
        String uid = recordData[0];
        String startDate = recordData[1];
        String bookTitle = recordData[3];
        String bookAuthor = recordData[4];
        holder.startDate.setText(startDate);
        holder.bookTitle.setText(bookTitle);
        holder.bookAuthor.setText(bookAuthor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                viewDiaryEntry(uid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyData.size();
    }

    private void viewDiaryEntry(String uid) {
        Intent ViewDiaryEntryScreen = new Intent(context,ViewDiaryEntryActivity.class);
        ViewDiaryEntryScreen.putExtra("diaryEntryId",uid);
        context.startActivity(ViewDiaryEntryScreen);
    }
}
