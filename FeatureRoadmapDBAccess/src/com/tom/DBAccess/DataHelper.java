package com.tom.DBAccess;

import java.util.ArrayList;
import java.util.List;

import com.tom.pojo.Milestone;
import com.tom.pojo.Roadmap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataHelper {

	private static final String DATABASE_NAME = "FeatureRoadmap.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME_ROADMAP = "roadmap";
	private static final String TABLE_NAME_MILESTONE = "milestone";

	private Context context;
	private SQLiteDatabase db;

	private SQLiteStatement insertStmtRoadmap;
	private SQLiteStatement insertStmtMilestone;

	private SQLiteStatement selectStmtRoadmap;

	private static final String INSERT_ROADMAP = "insert into "
			+ TABLE_NAME_ROADMAP
			+ "(name, start_date, end_date, project_id) values (?, ?, ?, ?)";
	private static final String INSERT_MILESTONE = "insert into "
			+ TABLE_NAME_MILESTONE
			+ "(name, description, date, roadmap_id) values (?, ?, ?, ?)";

	private static final String SELECT_ROADMAP = "SELECT * FROM "
			+ TABLE_NAME_ROADMAP + " WHERE (id) = (?)";

	public DataHelper(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
//		this.context.deleteDatabase(DATABASE_NAME);
		this.insertStmtRoadmap = this.db.compileStatement(INSERT_ROADMAP);
		this.insertStmtMilestone = this.db.compileStatement(INSERT_MILESTONE);
		this.selectStmtRoadmap = this.db.compileStatement(SELECT_ROADMAP);
	}

	public void selectRoadmap(int id) {
		this.selectStmtRoadmap.bindLong(1, id);
	}

	public long createRoadmap(String name, String start_date, String end_date,
			int project_id) {
		this.insertStmtRoadmap.bindString(1, name);
		this.insertStmtRoadmap.bindString(2, start_date);
		this.insertStmtRoadmap.bindString(3, start_date);
		this.insertStmtRoadmap.bindLong(4, project_id);
		return this.insertStmtRoadmap.executeInsert();
	}

	public long createMilestone(String name, String description, String date,
			int roadmap_id) {
		this.insertStmtMilestone.bindString(1, name);
		this.insertStmtMilestone.bindString(2, description);
		this.insertStmtMilestone.bindString(3, date);
		this.insertStmtMilestone.bindLong(4, roadmap_id);
		return this.insertStmtMilestone.executeInsert();
	}

	public void deleteAllRoadmaps() {
		this.db.delete(TABLE_NAME_ROADMAP, null, null);
	}

	public void deleteAllMilestones() {
		this.db.delete(TABLE_NAME_MILESTONE, null, null);
	}

	public List<String> selectAllRoadmapNames() {
		List<String> list = new ArrayList<String>();
		Cursor cursor = this.db.query(TABLE_NAME_ROADMAP,
				new String[] { "name" }, null, null, null, null, "name desc");
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public List<String> selectAllMilestoneNames() {
		List<String> list = new ArrayList<String>();
		Cursor cursor = this.db.query(TABLE_NAME_MILESTONE,
				new String[] { "name" }, null, null, null, null, "name desc");
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public List<Milestone> selectAllMilestones() {
		Log.d("EXAMPLE", "selectAllMilestones - Begin");
		List<Milestone> milestones = new ArrayList<Milestone>();
		Cursor cursor = this.db.query(TABLE_NAME_MILESTONE, new String[] {
				"id", "name", "description", "date", "roadmap_id" }, null,
				null, null, null, "id desc");
		
		if (!cursor.moveToFirst()) Log.d("EXAMPLE", "cursor is empty");
	  
		if (cursor.moveToFirst()) {
			do {
				Log.d("EXAMPLE", "cursordurchlauf - begin");
				Milestone milestone = new Milestone(cursor.getInt(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), null);
				milestones.add(milestone);
				Log.d("EXAMPLE", "cursordurchlaufende");
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
			Log.d("EXAMPLE", "cursor is closed");
		}
		
		Log.d("EXAMPLE", "selectAllMilestones - End");
		return milestones;
	}
	
	public List<Roadmap> selectAllRoadmaps() {
		Log.d("EXAMPLE", "selectAllRoadmaps - Begin");
		List<Roadmap> roadmaps = new ArrayList<Roadmap>();
		Cursor cursor = this.db.query(TABLE_NAME_ROADMAP, new String[] {
				"id", "name", "start_date", "end_date", "project_id" }, null,
				null, null, null, "id desc");
		
		if (!cursor.moveToFirst()) Log.d("EXAMPLE", "cursor is empty");
	  
		if (cursor.moveToFirst()) {
			do {
				Log.d("EXAMPLE", "cursordurchlauf - begin");
				Roadmap roadmap = new Roadmap (cursor.getInt(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getInt(4));
				roadmaps.add(roadmap);
				Log.d("EXAMPLE", "cursordurchlaufende");
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
			Log.d("EXAMPLE", "cursor is closed");
		}
		
		Log.d("EXAMPLE", "selectAllRoadmaps - End");
		return roadmaps;
	}
	
	public List<Milestone> getAllMilestonesByRoadmapId(int roadmapId) {
		Log.d("EXAMPLE", "getAllMilestonesByRoadmapId - Begin");
		List<Milestone> milestones = new ArrayList<Milestone>();
		Cursor cursor = this.db.query(TABLE_NAME_MILESTONE, new String[] {
				"id", "name", "description", "date", "roadmap_id" }, "roadmap_id = " + String.valueOf(roadmapId),
				null, null, null, "id desc");
		
		if (!cursor.moveToFirst()) Log.d("EXAMPLE", "cursor is empty");
	  
		if (cursor.moveToFirst()) {
			do {
				Log.d("EXAMPLE", "cursordurchlauf - begin");
				Milestone milestone = new Milestone(cursor.getInt(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), null);
				milestones.add(milestone);
				Log.d("EXAMPLE", "cursordurchlaufende");
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
			Log.d("EXAMPLE", "cursor is closed");
		}
		
		Log.d("EXAMPLE", "getAllMilestonesByRoadmapId - End");
		return milestones;		
	}

	public void closeDB() {
		this.db.close();
	}

	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME_ROADMAP
					+ "(id INTEGER PRIMARY KEY, name TEXT, start_date TEXT, end_date TEXT, project_id INTEGER)");
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME_MILESTONE
					+ "(id INTEGER PRIMARY KEY, name TEXT, description TEXT, date TEXT, roadmap_id INTEGER)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("EXAMPLE",
					"Upgrading database 'FeatureRoadmap.db', this will drop tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ROADMAP);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MILESTONE);
			onCreate(db);
		}
	}
}
