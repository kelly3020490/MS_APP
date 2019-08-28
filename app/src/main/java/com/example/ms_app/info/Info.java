package com.example.ms_app.info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ms_app.R;
import com.example.ms_app.main.Util;
import com.example.ms_app.task.CommonTask;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Info extends Fragment {
    private Toolbar toolbar;
    private static final String TAG = "Info";
    private RecyclerView rvInfo;
    private CommonTask getInfoTask;

    @Override
    public void onStart() {
        super.onStart();
        if(Util.networkConnected(getActivity())){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action","getAll");
            String jsonOut = jsonObject.toString();
            updateUI(jsonOut);
        }else {
            Util.showToast(getActivity(),R.string.msg_NoNetwork);
        }
    }

    @Nullable
    @Override
    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_info, container, false);
//       在畫面上顯示自訂的toolbar
        toolbar = view.findViewById(R.id.toolbar);
        rvInfo = view.findViewById(R.id.rvInfo);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("園所資訊");
//        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        return view;
    }


    private void updateUI(String jsonOut){
        getInfoTask = new CommonTask(Util.URL +"AnnoServlet",jsonOut);
        List<InfoVO>annoList = null;
        try{
            String jsonIn = getInfoTask.execute().get();
            Type listType;
            listType = new TypeToken<List<InfoVO>>(){
            }.getType();
            annoList = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(jsonIn,listType);

        }catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (annoList == null ||annoList.isEmpty()) {
            Util.showToast(getActivity(),"找不到園所資訊");
        } else {
            rvInfo.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            rvInfo.setAdapter(new InfoListAdapter(getActivity(), annoList));
        }

    }

    private class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.MyViewHolder>{
        private Context context ;
        private LayoutInflater layoutInflater;
        private List<InfoVO> annoList;

        InfoListAdapter(Context context,List<InfoVO> annoList){
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
            this.annoList = annoList;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvCLa,tvTitle,tvDate;

            public MyViewHolder(View itemView){
                super(itemView);
                tvCLa = itemView.findViewById(R.id.tvCLa);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvDate = itemView.findViewById(R.id.tvDate);

            }
        }

        public int getItemCount(){return annoList.size();}

        public  MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            View itemView = layoutInflater.inflate(R.layout.cardview_info,parent,false);
            return  new MyViewHolder(itemView);
        }

        public void onBindViewHolder(MyViewHolder holder,int position){
            final InfoVO anno = annoList.get(position);
            String url = Util.URL+"AnnoServiet";


            holder.tvCLa.setText(anno.getAnno_cla());
            holder.tvTitle.setText(anno.getAnno_title());
            holder.tvDate.setText(String.valueOf(anno.getAnno_date()));
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), Info_detail.class);
                    intent.putExtra("info",anno);
                    startActivity(intent);
                }
            });
        }
    }
    public  void onStop(){
        super.onStop();
        if(getInfoTask != null){
            getInfoTask.cancel(true);
        }



    }


}
