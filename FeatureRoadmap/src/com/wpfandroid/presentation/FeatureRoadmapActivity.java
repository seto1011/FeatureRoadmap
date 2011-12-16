package com.wpfandroid.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpfandroid.dbaccess.DataHelper;
import com.wpfandroid.pojo.Roadmap;

public class FeatureRoadmapActivity extends ListActivity {
	
	public static String CURRENTLY_SELECTED = null;
	public static ArrayList<String> ROADMAPNAMES = new ArrayList<String>();

	private DataHelper dh;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.dh = new DataHelper(this);
        
        // Begin - sample data to fill database
        // When not necessary comment
		this.dh.deleteAllMilestones();
		this.dh.deleteAllRoadmaps();

		this.dh.createRoadmap("roadmapName1", "2011/11/01", "2011/12/29", 1);
		this.dh.createRoadmap("roadmapName2", "2011/11/02", "2011/12/30", 1);
		this.dh.createRoadmap("roadmapName3", "2011/11/03", "2011/12/31", 1);

		this.dh.createMilestone("milestoneName1a", "description1a", "2011/12/01",
				1);
		this.dh.createMilestone("milestoneName1b", "description1b", "2011/12/11",
				1);
		this.dh.createMilestone("milestoneName1c", "description1c", "2011/12/21",
				1);
		
		this.dh.createMilestone("milestoneName2a", "description2", "2011/12/02",
				2);
		this.dh.createMilestone("milestoneName2b", "description2", "2011/12/12",
				2);
		this.dh.createMilestone("milestoneName2c", "description2", "2011/12/22",
				2);
		
		this.dh.createMilestone("milestoneName3a", "description3", "2011/12/03",
				3);
		this.dh.createMilestone("milestoneName3b", "description3", "2011/12/13",
				3);
		this.dh.createMilestone("milestoneName3c", "description3", "2011/12/23",
				3);
		// End - sample data to fill database
		
		List<Roadmap> roadmaps = this.dh.selectAllRoadmaps();
		
		for (Roadmap roadmap : roadmaps) {
			Log.d("EXPECTED", "Begin loop - Filling ArrayList of ROADMAPNAMES");
			ROADMAPNAMES.add(roadmap.getName());
			
			Log.d("EXPECTED", "End loop - Filling ArrayList of ROADMAPNAMES");
		}
		
		/// ####################
        setListAdapter(new ArrayAdapter<String>(this, com.wpfandroid.presentation.R.layout.simpl_list_item, ROADMAPNAMES));

        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setClickable(true);
        
        final String currentlySelected = "";
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
              //currentlySelected = (String) (lv.getItemAtPosition(myItemInt));
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
	            		startActivity(new Intent(FeatureRoadmapActivity.this, DragNDropActivity.class));
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