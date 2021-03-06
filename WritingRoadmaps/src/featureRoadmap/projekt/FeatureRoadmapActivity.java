package featureRoadmap.projekt;

//import android.R;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FeatureRoadmapActivity extends ListActivity {
	  static ArrayList<String> COUNTRIES = new ArrayList<String>();

	public static String currentlySelected = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	COUNTRIES.add("Afghanistan");
    	COUNTRIES.add("Albania");
    	COUNTRIES.add("Algeria");
    	COUNTRIES.add("American Samoa");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, COUNTRIES));

        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setClickable(true);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
              currentlySelected = (String) (lv.getItemAtPosition(myItemInt));
              //Toast.makeText(getApplicationContext(), currentlySelected,
      		  //		Toast.LENGTH_SHORT).show();
            }});
   
        
        final Button loadButton = (Button) findViewById(R.id.loadRoadmap);
        if(loadButton != null)
        {
        	loadButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	if(currentlySelected != null)
	            	{
	            		Toast.makeText(getApplicationContext(), currentlySelected,
	            				Toast.LENGTH_SHORT).show();
	            		startActivity(new Intent(FeatureRoadmapActivity.this, RoadmapActivity.class));
	            	}
	            }
	        });
        }
        
        final Button newButton = (Button) findViewById(R.id.newRoadmap);
        if(newButton != null)
        {
        	newButton.setOnClickListener(new View.OnClickListener() {
	            @SuppressWarnings("unchecked")
				public void onClick(View view) {
	            	CharSequence newName = ((TextView) findViewById(R.id.newRoadmapName)).getText();
	            	if(newName != null && newName.toString().isEmpty() == false)
	            	{
	            		((ArrayAdapter<String>) lv.getAdapter()).add(newName.toString());
	            		
	            		((TextView) findViewById(R.id.newRoadmapName)).setText("");
	            		((TextView) findViewById(R.id.newRoadmapName)).clearFocus();
	            		((TextView) findViewById(R.id.newRoadmapName)).setVisibility(((TextView) findViewById(R.id.newRoadmapName)).INVISIBLE);
	            		
	            		newButton.setText("New Roadmap");
	            	}
	            	else
	            	{
	            		((TextView) findViewById(R.id.newRoadmapName)).setText("Insert Name here!");
	            		((TextView) findViewById(R.id.newRoadmapName)).setVisibility(((TextView) findViewById(R.id.newRoadmapName)).VISIBLE);
	            		
	            		newButton.setText("Ok");
	            	}
	            		
	            }
	        });
        }

    }
}

