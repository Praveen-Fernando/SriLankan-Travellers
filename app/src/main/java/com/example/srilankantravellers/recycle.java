package com.example.srilankantravellers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import java.util.ArrayList;

public class recycle extends RecyclerView.Adapter<recycle.ViewHolder> implements Filterable {
    Context context;
    List<Review> MainImageUploadInfoList;
    List<Review> filteredList;
    DatabaseReference dbRef;

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Review> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll( filteredList );
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Review review : filteredList) {
                    if (review.getName().toLowerCase().contains( filterPattern )) {
                        filteredList.add( review );
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            MainImageUploadInfoList.clear();
            MainImageUploadInfoList.addAll( (List) results.values );
            notifyDataSetChanged();
        }
    };

    public recycle(Context context, List<Review> TempList) {

        this.MainImageUploadInfoList = TempList;
        filteredList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recyclerview_review, parent, false );

        ViewHolder viewHolder = new ViewHolder( view );

        return viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(filteredList.get( position ).getName().equals( ShareUname.uname )) {
            holder.r2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent( context, Review_update.class );
                    intent.putExtra( "key", filteredList.get( position ).getKey() );
                    System.out.println( "ttttttttttttttttttttttttt999999" + filteredList.get( position ).getKey() );
                    intent.putExtra( "laka", filteredList.get( position ).getName() );
                    intent.putExtra( "e1_update", filteredList.get( position ).getReview() );
                    context.startActivity( intent );
                }
            } );
        }

        final Review review = MainImageUploadInfoList.get( position );

        holder.nameView.setText( review.getName() );
        holder.reView.setText( review.getReview() );

        if (ShareUname.uname.equals( filteredList.get( position ).getName() )) {
            holder.mDeleteImage.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbRef = FirebaseDatabase.getInstance().getReference().child( "Review" ).child( review.getKey() );

                    dbRef.addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dbRef.child( review.getName() ).removeValue();
                            MainImageUploadInfoList.remove( position );
                            notifyDataSetChanged();



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            System.out.println( "The read failed: " + databaseError.getCode() );
                        }
                    } );


                }

            } );
        }else {
            holder.mDeleteImage.setVisibility( View.INVISIBLE );
        }
    }
    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView reView;


        public ImageButton mDeleteImage;
        public View r2;

        public ViewHolder(View itemView) {

            super( itemView );

            nameView = (TextView) itemView.findViewById( R.id.ShowNameTextView);
            reView = (TextView) itemView.findViewById( R.id.ShowReviewtextView);



            mDeleteImage = itemView.findViewById( R.id.image_delete );
            r2=itemView.findViewById(R.id.r2);

        }
    }
}
