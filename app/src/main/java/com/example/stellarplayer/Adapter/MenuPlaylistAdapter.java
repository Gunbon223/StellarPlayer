package com.example.stellarplayer.Adapter;

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.R;
import com.example.stellarplayer.Service.DBSql;

import java.util.List;

public class MenuPlaylistAdapter extends RecyclerView.Adapter<MenuPlaylistAdapter.MusicViewHolder> {
    private List<Playlists> playlists;
    DBSql dbSql;
    public void setData(List<Playlists> list){
        this.playlists=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_menu,parent,false);

        return new MusicViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Playlists music1 = playlists.get(position);
        if(music1==null){
            return;
        }
//        holder.imgMusic.setImageResource(music1.getCoverArtPath());
        holder.txtTitle.setText(music1.getName());
    }

    @Override
    public int getItemCount() {
        if(playlists != null){
            return playlists.size();
        }
        return 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgMusic;
        private TextView txtTitle;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
//            imgMusic=itemView.findViewById(R.id.imgMenu);
            txtTitle=itemView.findViewById(R.id.nameMenu);
        }
    }
}
