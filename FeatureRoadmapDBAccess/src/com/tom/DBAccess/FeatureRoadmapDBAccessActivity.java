package com.tom.DBAccess;

import java.util.List;

import com.tom.pojo.Milestone;
import com.tom.pojo.Roadmap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FeatureRoadmapDBAccessActivity extends Activity {
	private TextView output;

	private DataHelper dh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.output = (TextView) this.findViewById(R.id.out_text);

		// this.dm = new DataManager(this);

		this.dh = new DataHelper(this);

		// Log.d("EXAMPLE", "Vor dem 1. create");
		// this.dh.createRoadmap("roadmapName1", "2011/12/01", "2011/12/11", 1);
		// Log.d("EXAMPLE", "Nach dem 1. create");
		// this.dh.createRoadmap("roadmapName2y", "2011/12/02", "2011/12/12",
		// 2);
		// this.dh.createRoadmap("roadmapName3", "2011/12/03", "2011/12/13", 3);
		// List<String> names = this.dh.selectAllRoadmapNames();
		// StringBuilder sb = new StringBuilder();
		// sb.append("Names in database:\n");
		// for (String name : names) {
		// sb.append(name + "\n");
		// }
		//
		// this.dh.closeDB();
		//
		// Log.d("EXAMPLE", "names size - " + names.size());
		//
		// this.output.setText(sb.toString());

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

		List<Roadmap> roadmaps = this.dh.selectAllRoadmaps();

		StringBuilder sb = new StringBuilder();
		sb.append("Roadmaps in database:\n");
		for (Roadmap roadmap : roadmaps) {
			sb.append("ID: " + roadmap.getId() + " Name: "
					+ roadmap.getName() + " Description: "
					+ roadmap.getStartDate() + " Date: " + roadmap.getEndDate()
					+ " Roadmap ID: " + String.valueOf(roadmap.getProjectId())
					+ "\n");
			
			List<Milestone> milestones = this.dh.getAllMilestonesByRoadmapId(roadmap.getId());
			
			for (Milestone milestone : milestones) {
				sb.append("Milestone ID: " + milestone.getId() + " Name: "
					+ milestone.getName() + " Description: "
					+ milestone.getDescription() + " Date: "
					+ milestone.getDate() + "\n \n");
			}
			Log.d("EXAMPLE", "stringbuilder durchlaufende");
		}
		
		sb.append("\n");

//		List<Milestone> milestones = this.dh.selectAllMilestones();
//
//		sb.append("Milestones in database:\n");
//		for (Milestone milestone : milestones) {
//			sb.append("ID: " + milestone.getId() + " Name: "
//					+ milestone.getName() + " Description: "
//					+ milestone.getDescription() + " Date: "
//					+ milestone.getDate() + "\n");
//			Log.d("EXAMPLE", "stringbuilder durchlaufende");
//
//			// + milestone.getRoadmap().getId() +
//		}
//		
//		sb.append("\n");

		this.dh.closeDB();

//		Log.d("EXAMPLE", "milestones size - " + milestones.size());

		this.output.setText(sb.toString());
	}
}