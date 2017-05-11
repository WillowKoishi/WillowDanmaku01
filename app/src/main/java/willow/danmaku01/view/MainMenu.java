package willow.danmaku01.view;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.os.*;
import android.graphics.drawable.*;
import willow.danmaku01.*;

public class MainMenu extends View
{public Typeface tf,tf2,tf3,tf4;
	public Paint paint1,paint2,paint3,paint3ex,paint4,paint5,paint6,paint7,paint8,paint9,loadPaintCN,loadPaint;
	public boolean useAntiAlias=true,chooseRank=false,loading=false,reShow=false,antiReShow=false,canExtra=false;
	public float mainMenuMove=0,a=0,rankMove=0;
	public float reShowMove;
	public int mainMenuType=0,lastMenuType=0,rank,loadAlpha=1;
	public Bitmap back,back2,star,n1;
	public static final int MAINMENU=0,START_CHOOSE_RANK=1,EXTRA_START=2,PRA_START=3,SCORE=4,SETTING=5;
	public MainMenu(Context context)
	{
		super(context);
		init(context);
	}
	public MainMenu(Context context, AttributeSet as)
	{
		super(context, as);
		init(context);
	}
	public MainMenu(Context context, AttributeSet attributeSet, int defStyle)
	{
		super(context, attributeSet, defStyle);
		init(context);}
	private void init(Context context)
	{mainMenuType = MAINMENU;
		lastMenuType = MAINMENU;
		back = ((BitmapDrawable)getResources().getDrawable(R.drawable.start_back)).getBitmap();
		back2 = ((BitmapDrawable)getResources().getDrawable(R.drawable.n1)).getBitmap();
		tf = Typeface.createFromAsset(context.getAssets(), "font/bur.ttf");
		tf2 = Typeface.createFromAsset(context.getAssets(), "font/ttc.ttc"); 
		tf3 = Typeface.createFromAsset(context.getAssets(), "font/exoi.ttf");
		tf4 = Typeface.createFromAsset(context.getAssets(), "font/633.ttf"); 
		paint1 = new Paint();
		paint1.setAntiAlias(useAntiAlias);
		paint1.setTypeface(tf2);
		paint1.setTextSize(120);
		paint1.setTextAlign(Paint.Align.CENTER);
		paint1.setColor(0xff00b4a7);
		paint1.setShadowLayer(13, 0, 0, 0xff000000);
		paint2 = new Paint();
		paint2.setAntiAlias(useAntiAlias);
		paint2.setTypeface(tf);
		paint2.setTextSize(27);
		paint2.setTextAlign(Paint.Align.LEFT);
		paint2.setColor(0xffffffff);
		paint2.setShadowLayer(7, 0, 0, 0xaa000000);
		paint3 = new Paint();
		paint3.setAntiAlias(useAntiAlias);
		paint3.setTypeface(tf3);
		paint3.setTextSize(60);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setColor(0xffffffff);
		paint3.setShadowLayer(10, 0, 0, 0xffffffff);
		paint3ex = new Paint();
		paint3ex.setAntiAlias(useAntiAlias);
		paint3ex.setTypeface(tf3);
		paint3ex.setTextSize(60);
		paint3ex.setTextAlign(Paint.Align.CENTER);
		paint3ex.setColor(0xffffffff);
		paint3ex.setShadowLayer(10, 0, 0, 0xffffffff);
		paint4 = new Paint();
		paint4.setAntiAlias(useAntiAlias);
		paint4.setTypeface(tf2);
		paint4.setTextSize(70);
		paint4.setTextAlign(Paint.Align.CENTER);
		paint4.setColor(0xff4bc540);
		paint4.setShadowLayer(3, 0, 0, 0xff000000);
		paint5 = new Paint();
		paint5.setAntiAlias(useAntiAlias);
		paint5.setTypeface(tf2);
		paint5.setTextSize(85);
		paint5.setTextAlign(Paint.Align.CENTER);
		paint5.setColor(0xffd1fbfb);
		paint5.setShadowLayer(5, 0, 0, 0xff00ffff9);
		paint6 = new Paint();
		paint6.setAntiAlias(useAntiAlias);
		paint6.setTypeface(tf2);
		paint6.setTextSize(85);
		paint6.setTextAlign(Paint.Align.CENTER);
		paint6.setColor(0xffddfae3);
		paint6.setShadowLayer(5, 0, 0, 0xff00dd2c);
		paint7 = new Paint();
		paint7.setAntiAlias(useAntiAlias);
		paint7.setTypeface(tf2);
		paint7.setTextSize(85);
		paint7.setTextAlign(Paint.Align.CENTER);
		paint7.setColor(0xfff9e7c5);
		paint7.setShadowLayer(5, 0, 0, 0xffffa500);
		paint8 = new Paint();
		paint8.setAntiAlias(useAntiAlias);
		paint8.setTypeface(tf2);
		paint8.setTextSize(85);
		paint8.setTextAlign(Paint.Align.CENTER);
		paint8.setColor(0xffffcccc);
		paint8.setShadowLayer(5, 0, 0, 0xffff0f00);
		paint9 = new Paint();
		paint9.setAntiAlias(useAntiAlias);
		paint9.setTypeface(tf4);
		paint9.setTextSize(50);
		paint9.setTextAlign(Paint.Align.LEFT);
		paint9.setColor(0xffffffff);
		paint9.setShadowLayer(7, 0, 0, 0xff000000);
		loadPaintCN = new Paint();
		loadPaintCN.setAntiAlias(useAntiAlias);
		loadPaintCN.setTypeface(tf2);
		loadPaintCN.setTextSize(70);
		loadPaintCN.setTextAlign(Paint.Align.CENTER);
		loadPaintCN.setColor(0xffffffff);
		loadPaintCN.setAlpha(1);
		loadPaintCN.setShadowLayer(7, 0, 0, 0xff000000);
		loadPaint = new Paint();
		loadPaint.setAlpha(1);
		loadPaint.setAntiAlias(useAntiAlias);
		loadPaint.setTypeface(tf4);
		loadPaint.setTextSize(50);
		loadPaint.setTextAlign(Paint.Align.CENTER);
		loadPaint.setColor(0xffa9f8ff);
		loadPaint.setShadowLayer(3, 0, 0, 0xff000000);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		rankMove = getHeight();
		reShowMove = getHeight();
		star = Bitmap.createScaledBitmap(back, getHeight(), getHeight() , true);
		n1 = Bitmap.createScaledBitmap(back2,(int)(getHeight()/497f*385f), getHeight(), true);
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{ 
		drawMainMenu(canvas);
		drawAnime();
		invalidate();
		super.onDraw(canvas);
	}
	private void drawAnime()
	{
		if (mainMenuType == START_CHOOSE_RANK && lastMenuType == MAINMENU)
		{
			mainMenuMove = mainMenuMove - 0.7f * a;
			a++;
			if (rankMove > 5)
			{
				rankMove = rankMove * 0.9f;
			}
			if (mainMenuMove <= -getWidth())
			{
				a = 0;
				lastMenuType = mainMenuType;
			}
		}
		if (mainMenuType == MAINMENU && lastMenuType == START_CHOOSE_RANK)
		{
			mainMenuMove = mainMenuMove + 0.7f * a;
			a++;
			if (rankMove <= getHeight())
			{
				rankMove = rankMove / 0.9f;
			}
			if (mainMenuMove >= 0)
			{
				a = 0;
				lastMenuType = mainMenuType;
			}
		}
		if (loading)
		{
			if (loadAlpha < 250)
			{
				loadAlpha = loadAlpha + 10;
			}
			else if(loadAlpha>=250)
			{
				loadAlpha = 255;
			}
			loadPaintCN.setAlpha(loadAlpha);
			loadPaint.setAlpha(loadAlpha);
		}
		else{
			loadPaintCN.setAlpha(0);
			loadPaint.setAlpha(0);
		}
		if (reShow)
		{
			if (reShowMove > 1)
			{
				reShowMove = reShowMove * 0.9f;
			}
			else
			{
				reShow = false;
			}
		}
		if (antiReShow)
		{
			reShowMove = -Math.abs(reShowMove / 0.8f);
		}
	}

	private void drawMainMenu(Canvas canvas)
	{canvas.drawBitmap(star, 0, 0, null);
		canvas.drawText("簡單彈幕", getWidth() / 7f * 3 + reShowMove, getHeight() / 4f - (getHeight() - rankMove) / 3f, paint1);
		canvas.drawText("Start", getWidth() / 4f + mainMenuMove, getHeight() / 7f * 3 - reShowMove, paint3);
		if (canExtra)
		{
			paint3ex.setColor(0xffffffff);
		}
		else
		{
			paint3ex.setColor(0xffaaaaaa);
		}
		canvas.drawText("Extra Start", getWidth() / 4f + 50 + mainMenuMove, getHeight() / 7f * 3 + 60 - reShowMove, paint3ex);
		canvas.drawText("Music Room", getWidth() / 4f + 80 + mainMenuMove, getHeight() / 7f * 3 + 120 - reShowMove, paint3);
		canvas.drawText("Score", getWidth() / 4f + 40 + mainMenuMove, getHeight() / 7f * 3 + 180 - reShowMove, paint3);
		canvas.drawText("Setting", getWidth() / 4f + 60 + mainMenuMove, getHeight() / 7f * 3 + 240 - reShowMove, paint3);
		canvas.drawText("Quit", getWidth() / 4f + 50 + mainMenuMove, getHeight() / 7f * 3 + 300 - reShowMove, paint3);

		canvas.drawText("難易度選擇", getWidth() / 2f + (getWidth() + mainMenuMove), getHeight() / 5f, paint4);
		canvas.drawText("煙   花  級", getWidth() / 2f, getHeight() / 7f * 2 + rankMove, paint5);
		canvas.drawText("探空火箭 級", getWidth() / 2f, getHeight() / 7 * 2 + 150 + rankMove, paint6);
		canvas.drawText("常規火箭 級", getWidth() / 2f, getHeight() / 7 * 2 + 300 + rankMove, paint7);
		canvas.drawText("暴力火箭 級", getWidth() / 2f, getHeight() / 7 * 2 + 450 + rankMove, paint8);
		canvas.drawText("Easy Level", getWidth() / 9f * 5 + (rankMove) / 2f, getHeight() / 7f * 2 + 30, paint9);
		canvas.drawText("Normal Level", getWidth() / 9f * 5 + (rankMove) / 2f, getHeight() / 7f * 2 + 180, paint9);
		canvas.drawText("Hard Level", getWidth() / 9f * 5 + (rankMove) / 2f, getHeight() / 7f * 2 + 330, paint9);
		canvas.drawText("Lunatic Level", getWidth() / 9f * 5 + (rankMove) / 2f, getHeight() / 7f * 2 + 480, paint9);

		if (!loading)
		{
			canvas.rotate(30);
			canvas.drawText("Willow's Simple Danmaku", getWidth() / 3f - (getHeight() - rankMove) / 2f - reShowMove / 2f, getHeight() / 12f, paint2);
			canvas.rotate(0);
		}
		if (loading)
		{
			//canvas.drawColor(Color.argb(loadAlpha, 150, 150, 150));
			canvas.drawBitmap(n1,-30,0,loadPaint);
			canvas.drawText("火箭折壽中", getWidth() / 2f, getHeight() / 5f * 4, loadPaintCN);
			canvas.drawText("Now Loading...", getWidth() / 2f + 60, getHeight() / 5f * 4 + 20, loadPaint);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		if (mainMenuType == lastMenuType && event.getAction() == event.ACTION_UP)
		{
			if (mainMenuType == MAINMENU && !loading && !antiReShow)
			{
				if (event.getY() >= getHeight() / 7f * 3 - 40 &&
					event.getY() <= getHeight() / 7f * 3)
				{
					mainMenuType = START_CHOOSE_RANK;
				}
				if (event.getY() >= getHeight() / 7f * 3 + 20 &&
					event.getY() <= getHeight() / 7f * 3 + 60 && canExtra)
				{
				}
				if (event.getY() >= getHeight() / 7f * 3 + 80 &&
					event.getY() <= getHeight() / 7f * 3 + 120)
				{
				}
				if (event.getY() >= getHeight() / 7f * 3 + 140 &&
					event.getY() <= getHeight() / 7f * 3 + 180)
				{
				}
				if (event.getY() >= getHeight() / 7f * 3 + 200 &&
					event.getY() <= getHeight() / 7f * 3 + 240)
				{
				}
				if (event.getY() >= getHeight() / 7f * 3 + 260 &&
					event.getY() <= getHeight() / 7f * 3 + 300)
				{
					antiReShow();
				}
			}
			if (mainMenuType == START_CHOOSE_RANK && lastMenuType == START_CHOOSE_RANK && !loading)
			{
				if (event.getY() >= getHeight() / 7f * 2 - 70 &&
					event.getY() <= getHeight() / 7f * 2)
				{
					h.postDelayed(r, 3000);
					h.postDelayed(r2, 1000);
					rank = 0;
					loading = true;	
				}
				if (event.getY() >= getHeight() / 7f * 2 + 50 &&
					event.getY() <= getHeight() / 7f * 2 + 150)
				{
					h.postDelayed(r, 3000);
					h.postDelayed(r2, 1000);
					rank = 1;
					loading = true;	
				}
				if (event.getY() >= getHeight() / 7f * 2 + 230 &&
					event.getY() <= getHeight() / 7f * 2 + 300)
				{
					h.postDelayed(r, 3000);
					h.postDelayed(r2, 1000);
					rank = 2;
					loading = true;	
				}
				if (event.getY() >= getHeight() / 7f * 2 + 380 &&
					event.getY() <= getHeight() / 7f * 2 + 450)
				{
					h.postDelayed(r, 3000);
					h.postDelayed(r2, 1000);
					rank = 3;
					loading = true;	
					
				}
			}
		}
		return true;
	}
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}

	};

	Runnable r=new Runnable(){

		@Override
		public void run()
		{
			chooseRank = true;		
		}
	};
	Runnable r2=new Runnable(){

		@Override
		public void run()
		{
			mainMenuType = MAINMENU;
		}
	};
	public void reShow()
	{
		reShow = true;
		reShowMove = getHeight();
	}
	public void antiReShow()
	{
		antiReShow = true;
	}
}
