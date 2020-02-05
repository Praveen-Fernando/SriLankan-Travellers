package com.example.srilankantravellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    int IMAGES[]= {R.drawable.sigiriya, R.drawable.galle, R.drawable.ganga, R.drawable.inde , R.drawable.nine};

    String items[] = new String[] {"Sigiriya","Galle Fort","Gangaramaya Temple","Independent Square","Nine Arch Bridge"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        ListView listView =(ListView)findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        //listView.setAdapter(adapter);

        customAdapter customAdapter = new customAdapter();
        listView.setAdapter(customAdapter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,items[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this,Activity2.class);
                intent.putExtra("laka",items[position]);
                startActivity(intent);
            }
        });

    }
    class customAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView__name = (TextView)view.findViewById(R.id.textView_name);

            imageView.setImageResource(IMAGES[i]);
            textView__name.setText(items[i]);

            ListView listView = (ListView) findViewById(R.id.listView);



            return null;
        }
    }
}
