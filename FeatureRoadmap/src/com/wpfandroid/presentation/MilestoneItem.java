package com.wpfandroid.presentation;

import com.wpfandroid.pojo.Milestone;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MilestoneItem extends Milestone
{
	private View itemView;
	private OwnHorizontalScrollView scrollView;
	private int displayWidth;
	private TextView beschriftungView;
	
	public MilestoneItem(View itemView, String milestoneName, OwnHorizontalScrollView scrollView, int posX, int displayWidth) 
	{
		this.itemView = itemView;
		this.beschriftungView = (TextView) ((ViewGroup) itemView).getChildAt(((ViewGroup) itemView).getChildCount() - 1);
		this.displayWidth = displayWidth;
		this.scrollView = scrollView; 
		
		beschriftungView.setText(milestoneName);
		
		// position the new item at the clicked position
		FrameLayout.LayoutParams par = (LayoutParams) itemView.getLayoutParams();

		par.leftMargin = scrollView.getScrollX() + posX - (106 / 2);
		par.topMargin = 0;
		itemView.setLayoutParams(par);
		
		itemView.setOnTouchListener(dragItem);
		
	}

	public String getName()
	{
		return beschriftungView.getText().toString();
	}
	
	public int getPos()
	{
		return ((LayoutParams) itemView.getLayoutParams()).leftMargin;
	}
	
	public OnTouchListener dragItem = new OnTouchListener()
    {
		public boolean onTouch(View v, MotionEvent event) 
		{
			FrameLayout.LayoutParams par = (LayoutParams) v.getLayoutParams();
			
			if(v.getId() != R.id.oben && v.getId() != R.id.unten)
			{	
					switch(event.getAction())
					{
						case MotionEvent.ACTION_MOVE:
						{
		                    par.leftMargin = scrollView.getScrollX() + (int)event.getRawX() - (v.getWidth()/2);		                    

		                    if((int) event.getRawX() > displayWidth * 0.85)
		                    {
		                    	scrollView.smoothScrollBy(20, 0);                   
		                    }
		                    
		                    if((int) event.getRawX() < displayWidth * 0.15)
		                    {
		                    	scrollView.smoothScrollBy(-20, 0);
		                    }
		                    
							v.setLayoutParams(par);		
							
							break;
						}//inner case MOVE
						case MotionEvent.ACTION_UP:
						{
		                    par.leftMargin = scrollView.getScrollX() + (int)event.getRawX() - (v.getWidth()/2);
							v.setLayoutParams(par);
													
							scrollView.setIsScrollable(true);	

							break;
						}//inner case UP
						case MotionEvent.ACTION_CANCEL:
						{
		                    par.leftMargin = (int)event.getRawX() - (v.getWidth()/2);
							v.setLayoutParams(par);
													
							scrollView.setIsScrollable(false);	
							break;
						}//inner case UP
						case MotionEvent.ACTION_DOWN:
						{
						    scrollView.setIsScrollable(false);
		
						    v.setLayoutParams(par);
							
							break;
						}//inner case UP
					}//inner switch
				}//if pawn
			return true;
		}//onTouch
    };//dragItem

}
