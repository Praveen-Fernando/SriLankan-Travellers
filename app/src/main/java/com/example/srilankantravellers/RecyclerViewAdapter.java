package com.example.srilankantravellers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertController;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  implements Filterable{
    Context context;
    List<Tour> MainImageUploadInfoList;
    List<Tour> filteredList;
    DatabaseReference dbRef;
    String key;
    EditText updatetxtName, updatetxtdays, updatetxtBudget;
    TextView updateplaceView,updatedateView;


    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Tour> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll( filteredList );
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Tour tour : filteredList) {
                    if (tour.gettName().toLowerCase().contains( filterPattern )) {
                        filteredList.add( tour );
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

    public RecyclerViewAdapter(Context context, List<Tour> TempList) {

        this.MainImageUploadInfoList = TempList;
        filteredList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recyclerview_tours, parent, false );

        ViewHolder viewHolder = new ViewHolder( view );

        return viewHolder;
    }

    final Tour t=new Tour();


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, TourView.class);
                intent.putExtra("key",filteredList.get( position ).getKey());
                intent.putExtra("txtName",filteredList.get( position ).gettName());
                intent.putExtra("dateView",filteredList.get( position ).getDate());
                intent.putExtra("placeView",filteredList.get( position ).getPlaces());
                intent.putExtra("txtdays",filteredList.get( position ).getDays());
                intent.putExtra("txtBudget",filteredList.get( position ).getBudget());
                context.startActivity(intent);
            }
        });

        final Tour tour = MainImageUploadInfoList.get( position );

        //if(ShareUname.uname.equals(filteredList.get( position ).gettName())) {

        holder.TripTextView.setText( tour.gettName() );
        holder.DateTextView.setText( tour.getDate() );
        holder.PlacesTextView.setText( tour.getPlaces() );
        holder.DaysTextView.setText( tour.getDays() );
        holder.BudgetTextView.setText( tour.getBudget() );

        System.out.println( "klaaaaaaaaaaaaa"+ShareUname.uname +"fffffff"+filteredList.get( position ).gettName());
        System.out.println( ShareUname.uname );

            //holder.mDeleteImage.setVisibility( View.VISIBLE );

        holder.mDeleteImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child( "Tour" );
                //final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child( "Tour" );
                dbRef.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dbRef.child( tour.getKey() ).removeValue();
                        MainImageUploadInfoList.remove( position );
                        notifyDataSetChanged();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                } );


            }

        } );
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

        public TextView TripTextView;
        public TextView DateTextView;
        public TextView PlacesTextView;
        public TextView DaysTextView;
        public TextView BudgetTextView;

        public ImageButton mDeleteImage;
        public View r1;



        public ViewHolder(View itemView) {

            super( itemView );

            TripTextView = itemView.findViewById( R.id.ShowTripTextView );
            DateTextView = itemView.findViewById( R.id.ShowDatetextView );
            PlacesTextView = itemView.findViewById( R.id.ShowPlacetextView );
            DaysTextView =  itemView.findViewById( R.id.ShowDaystextView );
            BudgetTextView =  itemView.findViewById( R.id.ShowBudgettextView );

            mDeleteImage = itemView.findViewById( R.id.image_delete );
             r1 = itemView.findViewById( R.id. r1);
       }

    }

}

