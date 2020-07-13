package com.example.practice.ui.health;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.ui.contacts.CustomAdapter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class HealthExerciseAdapter extends RecyclerView.Adapter<HealthExerciseAdapter.HealthExerciseViewHolder> {
    private Context mContext;
    private long[] mStartTimeMilliArray;
    private String[] mFriendArray;

    public HealthExerciseAdapter(Context pContext, long[] pStartTimeMilliArray, String[] pFriendArray) {
        mContext = pContext;
        mStartTimeMilliArray = pStartTimeMilliArray;
        mFriendArray = pFriendArray;
    }

    @NonNull
    @Override
    public HealthExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment3_exercise_item, parent, false);
        return new HealthExerciseViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HealthExerciseViewHolder holder, int position) {
        long startTimeMilli = mStartTimeMilliArray[position];
        String friend = mFriendArray[position];

//        startTimeMilli = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")).getTimeInMillis();
//        friend = "Ryan, Pikachu";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTimeMilli);
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String stringHour = hour < 10 ? "0" + hour : hour + "";
        String stringMinute = minute < 10 ? "0" + minute : minute + "";

        holder.date.setText(month + "/" + day);
        holder.time.setText(stringHour + ":" + stringMinute);
        holder.friend.setText(friend);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HealthExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView date, time, friend;

        public HealthExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            friend = itemView.findViewById(R.id.friend);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private long getStartTimeSeoul() {
        return LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.of("+09:00")) * 1000;
    }
}