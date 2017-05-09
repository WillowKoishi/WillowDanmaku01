package willow.danmaku01;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.view.View.*;
import android.graphics.drawable.*;
import java.util.*;
import android.media.*;
import android.text.method.*;
import java.io.*;
import android.os.*;
import willow.danmaku01.danmaku.*;
import android.graphics.drawable.shapes.*;

public class danmaku extends View
{private Paint paint,pauseText,pauseText2,pauseText3,textPaint,sOBPaint,bombText,pauseButtonText;
	public float selfX,selfY,unit,power=0;
	public long game=0,frame2,no_enemy_time,frame3,score,bombTimeFrame;
	public int game_life,defaultLife=3,sound,point,allPoint,stage=1,bombLimit,defaultbomb=2;
	public Path earth_sky;
	boolean b1=true,b2=true,b3=true,b4=true;
	String[] stageName={"","簡星表面","卡門線邊縁","",""};
	public int DANMAKU_TYPE_CIRCLE=0,DANMAKU_TYPE_BITMAP=1,DANMAKU_TYPE_RAY=2;
	public List<shotDanmaku> shotDAnmaku=new ArrayList<shotDanmaku>();
	public List<Enemy> enemy=new ArrayList<Enemy>();
	public List<Bonus> bonus_list=new ArrayList<Bonus>();
	public List<Text> text_list=new ArrayList<Text>();
	public List<BombDanmaku> bomb_list=new ArrayList<BombDanmaku>();
	public List<ShotOrBoom> sOB=new ArrayList<ShotOrBoom>();
	public float touchX,touchY,move,pectime,secs,fps;
	public Bitmap mmp,shot,bg_grass,sky,enemy1,enemy2,lmp,jmp,dian,pOwer,bomb,shot1,emp,finalB,bombshot,fire;
	public float frame=1,bg_garss_m;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2,isTALK=3,DRAWABLE_CANVAS=0,DRAWABLE_BITMAP=1;
	public int GAME_SITU;
	public boolean liftoff=false,no_enemy=false,canShot=true,bombTime=false,unBombTime=true,useAntiAlias=true,isChooseBack=false,ChoosedBack=false,isChooseRetry=false;
	public Context mContext;
	public Matrix m;
	public Bitmap[] exploPng;
	int i=0;
	float onTouchX,onTouchY,x,y;
	//private MediaPlayer sound_explo;
	private SoundPool soundPool;
	public void init(Context context)//初始化游戏资源
	{m = new Matrix();
		Bitmap nmp=((BitmapDrawable)getResources().getDrawable(R.drawable.loser)).getBitmap();
		mmp = Bitmap.createScaledBitmap(nmp, nmp.getWidth() / 6, nmp.getHeight() / 6, true); 
		lmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.power0)).getBitmap();
		Bitmap kmp=((BitmapDrawable)getResources().getDrawable(R.drawable.smearthgrass)).getBitmap();
		bg_grass = Bitmap.createScaledBitmap(kmp, kmp.getWidth() / 2, kmp.getHeight() / 2, true);
		jmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.earth_sky_background)).getBitmap();
		Bitmap imp=((BitmapDrawable)getResources().getDrawable(R.drawable.enemy1)).getBitmap();
		enemy1 = Bitmap.createScaledBitmap(imp, imp.getWidth() / 5, imp.getHeight() / 5, true);
		Bitmap hmp=((BitmapDrawable)getResources().getDrawable(R.drawable.i)).getBitmap();
		dian = Bitmap.createScaledBitmap(hmp, hmp.getWidth() / 2, hmp.getHeight() / 2, true);
		Bitmap gmp=((BitmapDrawable)getResources().getDrawable(R.drawable.p)).getBitmap();
		pOwer = Bitmap.createScaledBitmap(gmp, gmp.getWidth() / 2, gmp.getHeight() / 2, true);
		Bitmap fmp=((BitmapDrawable)getResources().getDrawable(R.drawable.b)).getBitmap();
		bomb = Bitmap.createScaledBitmap(fmp, fmp.getWidth() / 2, fmp.getHeight() / 2, true);
		emp = ((BitmapDrawable)getResources().getDrawable(R.drawable.power1)).getBitmap();
		Bitmap dmp=((BitmapDrawable)getResources().getDrawable(R.drawable.f)).getBitmap();
		finalB = Bitmap.createScaledBitmap(dmp, dmp.getWidth() / 2, dmp.getHeight() / 2, true);
		Bitmap cmp=((BitmapDrawable)getResources().getDrawable(R.drawable.bomb)).getBitmap();
		bombshot = Bitmap.createScaledBitmap(cmp, cmp.getWidth() / 4, cmp.getHeight() / 4, true);
		Bitmap bmp=((BitmapDrawable)getResources().getDrawable(R.drawable.fire)).getBitmap();
		fire = Bitmap.createScaledBitmap(bmp, bmp.getWidth() / 5, bmp.getHeight() / 5, true);
		Bitmap zmp=((BitmapDrawable)getResources().getDrawable(R.drawable.enemy2)).getBitmap();
		enemy2 = Bitmap.createScaledBitmap(zmp, zmp.getWidth() / 7, zmp.getHeight() / 7, true);
		//加载游戏音效
		exploPng = new Bitmap[10];
		exploPng[0] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo0)).getBitmap();
		exploPng[1] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo1)).getBitmap();
		exploPng[2] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo2)).getBitmap();
		exploPng[3] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo3)).getBitmap();
		exploPng[4] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo4)).getBitmap();
		exploPng[5] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo5)).getBitmap();
		exploPng[6] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo6)).getBitmap();
		exploPng[7] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo7)).getBitmap();
		exploPng[8] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo8)).getBitmap();
		exploPng[9] = ((BitmapDrawable)getResources().getDrawable(R.drawable.explo9)).getBitmap();
		soundPool = new SoundPool(200, AudioManager.STREAM_MUSIC, 5);
		soundPool.load(context, R.raw.explo, 3);
		soundPool.load(context, R.raw.pdel, 2);
		soundPool.load(context, R.raw.pshot, 1);
		soundPool.load(context, R.raw.item, 4);
		soundPool.load(context, R.raw.tip, 7);
		soundPool.load(context, R.raw.bom, 6);
		soundPool.load(context, R.raw.change, 2);
		pauseText = new Paint();
		pauseText.setAntiAlias(useAntiAlias);
		pauseText.setColor(Color.argb(255, 255, 255, 255));
		pauseText.setTextSize(60);
		pauseText.setTextAlign(Paint.Align.CENTER);
		pauseText.setShadowLayer(7,0,0,0xff000000);
		pauseText.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/ttc.ttc"));
		pauseText2 = new Paint();
		pauseText2.setAntiAlias(useAntiAlias);
		pauseText2.setColor(Color.WHITE);
		pauseText2.setTextSize(25);
		pauseText2.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/exoi.ttf"));
		pauseText2.setShadowLayer(3,0,0,0xff000000);
		pauseText3=new Paint();
		pauseText3.setAntiAlias(useAntiAlias);
		pauseText3.setTextSize(40);
		pauseText3.setTextAlign(Paint.Align.CENTER);
		pauseText3.setColor(Color.WHITE);
		pauseText3.setShadowLayer(5,0,0,0xff000000);
		pauseText3.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/ttc.ttc"));
		textPaint = new Paint();
		textPaint.setAntiAlias(useAntiAlias);
		textPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/ttc.ttc"));
		textPaint.setTextSize(60);
		textPaint.setTextAlign(Paint.Align.CENTER);
		GAME_SITU = isSTART;
		paint = new Paint();
		paint.setAntiAlias(useAntiAlias);
		paint.setColor(Color.argb(255, 255, 88, 88));
		sOBPaint = new Paint();
		sOBPaint.setAntiAlias(useAntiAlias);
		sOBPaint.setColor(0xffffaa33);
		bombText=new Paint();
		bombText.setAntiAlias(useAntiAlias);
		bombText.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/exoi.ttf"));
		bombText.setTextAlign(Paint.Align.RIGHT);
		bombText.setTextSize(40);
		bombText.setShadowLayer(5,0,0,0xff000000);
		bombLimit=defaultbomb;
		game_life=defaultLife;
		pauseButtonText=new Paint();
		pauseButtonText.setTypeface(Typeface.createFromAsset(context.getAssets(),"font/exoi.ttf"));
		pauseButtonText.setTextSize(40);
		pauseButtonText.setColor(Color.WHITE);
		pauseButtonText.setShadowLayer(5,0,0,0xff000000);
		pauseButtonText.setAntiAlias(useAntiAlias);
		pauseButtonText.setTextAlign(Paint.Align.RIGHT);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{ frame2 = frame2 + 1;
//判断游戏状态
		//if(GAME_SITU!=isDEAD){

		//}

		if (GAME_SITU == isSTART)
		{
			startGame(canvas);}
		if (GAME_SITU == isPAUSE || GAME_SITU == isDEAD)
		{
		pauseGame(canvas);}
		drawText(canvas);
		drawDialog(canvas);
		invalidate();
		super.onDraw(canvas);

	}
	/*----------------------------------------*/
	public danmaku(Context context)
	{

		super(context);
		init(context);
		mContext = context;
	}
	public danmaku(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
		init(context);
		mContext = context;
	}
	public danmaku(Context context, AttributeSet attributeSet, int defStyle)
	{
		super(context, attributeSet, defStyle);
		init(context);
		mContext = context;
	}
	/*--------------------游戏运行中--------------------*/
	public void startGame(Canvas canvas)
	{isChooseRetry=false;
	isChooseBack=false;
		if (game == 0)
		{unit = getHeight() / 100f;
			selfX = getWidth() / 2f;
			selfY = getHeight() / 3f * 2f;
			showText("〜Stage " + stage + "〜", 1);
			game = +1;
			sky = Bitmap.createScaledBitmap(jmp, getWidth(), getHeight(), true);
			bg_garss_m = selfY + mmp.getHeight() / 2f;
			canShot = false;
		}
		if (!no_enemy)
		{
			no_enemy_time = 0;
		}
		if (frame3 == 120)
		{
			showText(stageName[stage], 2);
			canShot = true;
		}
		if (no_enemy == true)
		{
			paint.setColor(Color.BLUE);
			no_enemy_time = no_enemy_time + 1;
		}
		if (no_enemy_time > 90)
		{
			paint.setColor(Color.argb(255, 255, 88, 88));
			no_enemy = false;
			no_enemy_time = 0;
		}
		frame3 = frame3 + 1;
		frame = frame + 1;
		drawSky(canvas);
		canvas.drawBitmap(fire, selfX - mmp.getWidth() / 2f + 2, selfY + mmp.getHeight() / 2f, paint);
		if (!liftoff)
		{
			canvas.drawBitmap(bg_grass, getWidth() / 2 - bg_grass.getWidth() / 2, bg_garss_m + move, paint);
			move = move + 0.02f * frame3;
			if (move >= bg_grass.getHeight())
			{
				liftoff = true;
				//bg_grass = null;
				game = game + 1;
			}
			if (selfY + mmp.getHeight() / 2f >= bg_garss_m + move)
			{
				selfY = bg_garss_m + move - mmp.getHeight() / 2;
			}
		}
		for (int i=0;i < shotDAnmaku.size();i++)
		{	shotDanmaku sd=shotDAnmaku.get(i);
			sd.thereTime++;
			sd.y = sd.y + sd.vy + sd.thereTime * sd.ay * unit;
			sd.x = sd.x + sd.vx * unit + sd.thereTime * sd.ax * unit;
			canvas.drawBitmap(sd.tietu, sd.x - sd.tietu.getWidth() / 2f, sd.y - sd.tietu.getHeight() / 2, paint);
			if (sd.y < 0 - shot.getHeight())//- getHeight() * 2)
			{
				shotDAnmaku.remove(i);
			}
		}
		if (point >= 100)
		{
			game_life++;
			soundPool.play(6, 1, 1, 0, 0, 1);
			showText("Life Extra!!!", 2);
			point = 0;
		}
		if (bombTime)
		{
			if (unBombTime)
			{
				addBomb();
				bombTimeFrame = 0;
				soundPool.play(7, 1, 1, 0, 0, 1);
				unBombTime = false;
			}
			bombTimeFrame++;
			if (bombTimeFrame % 30 == 0)
			{
				addBomb();
			}
			setTop((int)(20 * Math.random()) - 10);
			setLeft((int)(20 * Math.random()) - 10);
			if (bombTimeFrame > 150)
			{
				bombTime = false;
				unBombTime = !bombTime;
				setTop(0);
				setLeft(0);
			}
		}
		if (frame == 10)
		{
			frame = 1;
		}
		if (frame == 1 && canShot)
		{
			shotDanmaku st=new shotDanmaku();
			if (power < 2)
			{
				st.x = selfX;
			}
			st.y = selfY - mmp.getHeight() / 2;
			shot = Bitmap.createScaledBitmap(lmp, lmp.getWidth() / 14, lmp.getHeight() / 14, true);
			shot1 = Bitmap.createScaledBitmap(emp, emp.getWidth() / 14, emp.getHeight() / 14, true);
			st.tietu = shot;
			soundPool.play(3, 1, 1, 0, 0, 1);
			if (power >= 1)
			{
				if (b1)
				{
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Power UP!!", 2);
					b1 = false;
				}
				shotDanmaku su=new shotDanmaku();
				su.tietu = shot1;
				su.vx = 0.3f;
				su.ay = -0.2f;
				su.vy = 12f;
				su.x = selfX + mmp.getWidth() / 2f;
				su.y = selfY + mmp.getHeight() / 2f;
				shotDAnmaku.add(su);
				shotDanmaku sv=new shotDanmaku();
				sv.tietu = shot1;
				sv.vx = -0.3f;
				sv.ay = -0.2f;
				sv.vy = 12f;
				sv.x = selfX - mmp.getWidth() / 2f;
				sv.y = selfY + mmp.getHeight() / 2;
				shotDAnmaku.add(sv);
			}
			if (power >= 4)
			{
				if (b4)
				{
					b4 = false;
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Full Power Mode!!", 3);
					power = 4.0f;
				}
			}
			if (power >= 3)
			{
				if (b3)
				{
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Power UP!!", 2);
					b3 = false;
				}
			}
			if (power >= 2)
			{st.x = selfX - st.tietu.getWidth();
				shotDanmaku sr=new shotDanmaku();
				sr.tietu = shot;
				sr.x = selfX + sr.tietu.getWidth();
				sr.y = selfY - mmp.getHeight() / 2;
				shotDAnmaku.add(sr);
				if (b2)
				{
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Power UP!!", 2);
					b2 = false;
				}
			}
			shotDAnmaku.add(st);
		}
		if (selfX < 0)
		{
			selfX = 1;
		}
		else if (selfX > getWidth())
		{
			selfX = getWidth() - 1;
		}
		if (selfY < 0)
		{
			selfY = 1;
		}
		else if (selfY > getHeight())
		{
			selfY = getHeight() - 1;
		}	
		drawBomb(canvas);
		drawSelf(canvas);
		for (int l=0;l < 2;l++)
		{
			drawBonus(canvas);}
		for (int l=0;l < 2;l++)
		{
			drawEnemy(canvas); 
		}
		drawShotOrBoom(canvas);
	}
	/*------------------游戏暂停或死亡----------------------*/
	private void pauseGame(Canvas canvas)
	{ drawSky(canvas);
		canvas.drawBitmap(fire, selfX - mmp.getWidth() / 2f + 2, selfY + mmp.getHeight() / 2f, paint);
		if (!liftoff)
		{
			canvas.drawBitmap(bg_grass, getWidth() / 2 - bg_grass.getWidth() / 2, bg_garss_m + move, paint);
		}
		for (int i=0;i < shotDAnmaku.size();i++)
		{
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(sd.tietu, sd.x - sd.tietu.getWidth() / 2f, sd.y - sd.tietu.getHeight() / 2f, paint);
		}
		drawBomb(canvas);
		drawSelf(canvas);
		drawBonus(canvas);
		drawEnemy(canvas);
		drawShotOrBoom(canvas);
	} 
	/*------------------游戏对象----------------------*/
	public static class Sprite
	{
		public float life=1;
		public float postX=0;
		public float thereTime=0;
		public float postY=0;
		public Bitmap tietu=null;
		public float vx,vy,ax=0,ay=0,power=0.00f;
		public boolean isDEAD=false;
		public float getPostY()
		{
			return postY;
		}
	}
	public class Enemy extends Sprite
	{
		float bonus=0.1f;	
		boolean isBoss=false;
	}
	public class Bonus extends Sprite
	{
		public static final int D=0,P=1,B=2,F=3,UP=4;
		public int BONUS_TYPE=0;
	}
	public static class shotDanmaku
	{
		float radio=0.5f,x,y,vx,vy=0,ax=0,ay=-0.1f,thereTime=0;
		Bitmap tietu=null;
		boolean haveShot=false;
	}
	public static class Text//游戏提示文本
	{
		int alpha=255;
		long saveFrame=60;
		long changeFrame=60;
		long staticChange=60;
		String text="";
	}
	public static class BombDanmaku extends Sprite
	{
		float rotat=0;
	}
	public class EnemyDanmaku extends Sprite
	{
		int Shape=DANMAKU_TYPE_CIRCLE;	
	}
	public class ShotOrBoom extends Sprite
	{
		int Shape=DANMAKU_TYPE_CIRCLE;
		boolean isForSelf=false;
		float scale=2;
	}
	/*-------------------对象绘制方法---------------------*/
	private void drawSelf(Canvas canvas)
	{
		canvas.drawBitmap(mmp, selfX - mmp.getWidth() / 2f, selfY - mmp.getHeight() / 2f, paint);
		canvas.drawCircle(selfX, selfY, getHeight() / 260f, paint);
	}
	private void drawSky(Canvas canvas)
	{
		canvas.drawBitmap(sky, 0, 0, paint);
	}
	private void part1()
	{

	}
	public float getFrame()
	{
		return frame2;
	}
	private void drawEnemy(Canvas canvas)
	{
		for (int i=0;i < enemy.size();i++)
		{

			Enemy me=enemy.get(i);
			if (GAME_SITU == isSTART)
			{
				me.postY = me.postY + me.vy + me.ay * me.thereTime;	
				me.postX = me.postX + me.ax * me.thereTime;
			}
			m.reset();
			m.postTranslate(me.postX - me.tietu.getWidth() / 2f, me.postY - me.tietu.getHeight() / 2f);
			canvas.drawBitmap(me.tietu, m, paint);

			if (me.isDEAD)
			{
				enemy.remove(i);			
			}
			if (me.life <= 0)
			{
				addBonus(0, me.bonus, me.postX, me.postY);
				score = score + 100;
				soundExplo();
				me.isDEAD = true;
				addSOB(me.postX, me.postY, me.tietu.getWidth() / 64f, DANMAKU_TYPE_BITMAP, false);
			}
			if (me.postY > getHeight() + me.tietu.getHeight() / 2f
				|| me.postX < -me.tietu.getWidth()
				|| me.postX > getWidth() + me.tietu.getWidth())
			{
				me.isDEAD = true;
			}
			for (int j=0;j < shotDAnmaku.size();j = j + 1)
			{
				shotDanmaku sd=shotDAnmaku.get(j);
				if (sd.x + sd.tietu.getWidth() / 2f 
					> me.postX - (me.tietu.getWidth() / 2f)
					&& sd.x - sd.tietu.getWidth() / 2f
					< me.postX + (me.tietu.getWidth() / 2f)			
					&& sd.y - sd.tietu.getHeight() / 2 
					< me.postY + (me.tietu.getHeight() / 2f)
					&& sd.y + sd.tietu.getHeight() / 2f 
					> me.postY - (me.tietu.getHeight() / 2f)
					&& !sd.haveShot)
				{
					me.life = me.life - 1 - power / 2f;
					if (me.life <= 0)
					{
						me.isDEAD = true;

					}
					else
					{
						score = score + 10;
						soundPool.play(5, 0.8f, 0.8f, 0, 0, 1);
						addSOB(sd.x, sd.y, 1, DANMAKU_TYPE_CIRCLE, false);
					}
					//sd.haveShot = true;
					//sd.tietu.setWidth(1);
					//sd.tietu.setHeight(1);
					shotDAnmaku.remove(j);
				}
				if (selfX > me.postX - me.tietu.getWidth() / 2f
					&& selfX  < me.postX + me.tietu.getWidth() / 2f
					&& selfY < me.postY + me.tietu.getHeight() / 2f
					&& selfY  > me.postY - me.tietu.getHeight() / 2f
					&& !me.isBoss && GAME_SITU != isDEAD && unBombTime && !no_enemy)
				{
					isShotByEnemy();
					me.isDEAD = true;
				}
			}
		}
	}
	private void drawBonus(Canvas canvas)
	{
		for (int i=0;i < bonus_list.size();i = i + 1)
		{
			Bonus mBonus=bonus_list.get(i);
			canvas.drawBitmap(mBonus.tietu,//mBonus.postX -bomb.getWidth()/2f,mBonus.postY-bomb.getHeight()/2f,paint);
							  mBonus.postX - mBonus.tietu.getWidth() / 2f,
							  mBonus.postY - mBonus.tietu.getHeight() / 2f, paint);
			if (mBonus.postY > getHeight() + mmp.getHeight() * 3 || mBonus.isDEAD)
			{
				bonus_list.remove(i);
			}
			if (GAME_SITU == isSTART)
			{if (unBombTime && bomb_list.size() <= 0 &&
				 mmp.getHeight() < Math.sqrt(Math.pow(selfY - mBonus.postY, 2) + (Math.pow(selfX - mBonus.postX, 2))))
				{
					mBonus.postY = mBonus.postY + 1;
				}
				else
				{
					mBonus.postX = mBonus.postX + (selfX - mBonus.postX) / 10f;
					mBonus.postY = mBonus.postY + (selfY - mBonus.postY) / 10f;
				}
			}
			if (mBonus.postX - mBonus.tietu.getWidth() / 2
				< selfX + mmp.getWidth() / 2f
				&& mBonus.postX + mBonus.tietu.getWidth() / 2
				> selfX - mmp.getWidth() / 2f
				&& mBonus.postY - mBonus.tietu.getHeight() / 2
				< selfY + mmp.getHeight() / 2f
				&& mBonus.postY - mBonus.tietu.getHeight() / 2f
				> selfY - mmp.getHeight() / 2f
				&& !mBonus.isDEAD)
			{
				switch (mBonus.BONUS_TYPE)
				{
					case mBonus.D:
						score = score + 100;
						point++;
						allPoint++;
						soundPool.play(4, 1, 1, 0, 0, 1);
						break;
					case mBonus.P:
						soundPool.play(4, 1, 1, 0, 0, 1);
						if (power < 4)
						{
							power = power + (float)(0.03 + 0.07 * Math.random());}
						score = score + 50;
						break;
					case mBonus.B:
						soundPool.play(4, 1, 1, 0, 0, 1);
						break;
					case mBonus.UP:
						break;
					case mBonus.F:
						b1 = false;
						b2 = false;
						b3 = false;
						power = 4;
						break;
				}
				mBonus.isDEAD = true;

			}

		}
	}
	private void drawText(Canvas canvas)
	{
		for (int i=0;i < text_list.size();i++)
		{
			Text t=text_list.get(i);
			t.saveFrame--;
			textPaint.setARGB(t.alpha, 255, 255, 255);
			if (t.saveFrame < 0)
			{
				t.alpha = (int)(t.alpha - 255 / t.staticChange);
				t.changeFrame--;
				if (t.changeFrame <= 0 || t.alpha < 5)
				{
					text_list.remove(i);
				}
			}
			canvas.drawText(t.text, getWidth() / 2f, getHeight() / 3f, textPaint);
		}
	}
	private void  drawBomb(Canvas canvas)
	{
		for (int i=0;i < bomb_list.size();i++)
		{   BombDanmaku bd=bomb_list.get(i); 
			m.reset();
			m.postRotate((float)(Math.atan2(bd.vx, -bd.vy) * 180f / Math.PI), bd.tietu.getWidth() / 2f, bd.tietu.getHeight() / 2f);
			m.postTranslate(bd.postX - bd.tietu.getWidth() / 2f, bd.postY - bd.tietu.getHeight() / 2f);
			canvas.drawBitmap(bd.tietu, m, paint);	
			if (bd.isDEAD || bd.thereTime >= 300)
			{
				bomb_list.remove(i);
				addSOB(bd.postX, bd.postY, 2, DANMAKU_TYPE_BITMAP, false);
			}
			for (int in=0;in < enemy.size();in++)
			{
				Enemy me=enemy.get(in);
				if (bd.postX > me.postX - (me.tietu.getWidth() / 2f)
					&& bd.postX < me.postX + (me.tietu.getWidth() / 2f)			
					&& bd.postY < me.postY + (me.tietu.getHeight() / 2f)
					&& bd.postY > me.postY - (me.tietu.getHeight() / 2f)
					&& !bd.isDEAD && !me.isDEAD)
				{
					me.life = me.life - 10;
					bd.isDEAD = true;
				}
			}
			if (enemy.size() > 0)
			{		float[] d=new float[enemy.size()];
				for (int j=0;j < enemy.size();j++)
				{
					d[j] = (float)(Math.sqrt(Math.pow(bd.postY - enemy.get(j).postY, 2) + Math.pow(bd.postX - enemy.get(j).postX, 2)));
				}
				float min=d[0];
				int index=0;
				for (int k=0;k < enemy.size();k++)
				{
					if (d[k] < min)
					{
						min = d[k];				
					    index = k;
					}	
				}
				bd.vx = (enemy.get(index) .postX - bd.postX) / d[index] * 17;
				bd.vy = (enemy.get(index) .postY  - bd.postY) / d[index] * 17;
				//canvas.drawText("" + index, 100, 150, paint);	
			}
			if (GAME_SITU == isSTART)
			{ bd.thereTime++;
				//canvas.drawText(index+"/"+b,100,100,paint); 
				bd.postX = bd.postX + bd.vx;
				bd.postY = bd.postY + bd.vy;}

		}
	}
	public void drawShotOrBoom(Canvas canvas)
	{
		for (int k=0;k < 1;k++)
		{

			for (int i=0;i < sOB.size();i++)
			{
				ShotOrBoom sOb=sOB.get(i);
				if (GAME_SITU == isSTART)
				{
					sOb.thereTime++;
				}
				if (sOb.Shape == DANMAKU_TYPE_CIRCLE && !sOb.isForSelf)
				{
					sOBPaint.setAlpha(255 - (int)sOb.thereTime * 25);
					canvas.drawCircle(sOb.postX, sOb.postY, getWidth() / 45f, sOBPaint);
					if (sOb.thereTime > 10)
					{
						sOB.remove(i);
					}
				}
				if (sOb.Shape == DANMAKU_TYPE_BITMAP && !sOb.isForSelf)
				{
					int f=(int)sOb.thereTime / 2;
					Bitmap e=Bitmap.createScaledBitmap(exploPng[f], (int)(exploPng[f].getWidth() * sOb.scale), (int)(exploPng[f].getHeight() * sOb.scale), true);
					canvas.drawBitmap(e, sOb.postX - e.getWidth() / 2f, sOb.postY - e.getHeight() / 2f, paint);
					if (sOb.thereTime > 18)
					{
						sOB.remove(i);
					}

				}
			}
		}
	}
	/*---------------增加对象方法----------------*/
	public void addEnemy()
	{
		Enemy mEnemy= new Enemy();
		mEnemy.postX = getWidth() * (float)(Math.random());
		mEnemy.isDEAD = false;
		mEnemy.vy = 2;
		mEnemy.life = 5;
		mEnemy.bonus = 0.3f;
		mEnemy.tietu = enemy2;
		mEnemy.postY = -mEnemy.tietu.getHeight();
		enemy.add(mEnemy);
	}
	public void addSOB(float postX, float postY, float scale, int Shape, boolean isForSelf)
	{
		ShotOrBoom sob=new ShotOrBoom();
		sob.postX = postX;
		sob.postY = postY;
		sob.Shape = Shape;
		sob.scale = scale;
		sob.isForSelf = isForSelf;
		sOB.add(sob);
	}
	public void reSet()
	{selfX = getWidth() / 2f;
		selfY = getHeight() / 3f * 2f;
		game = 0;
		frame2 = 0;
		frame3 = 0;
		move = 0;
		liftoff = false;
		enemy.clear();
		no_enemy = false;
		no_enemy_time = 0l;
		bonus_list.clear();
		shotDAnmaku.clear();
		bomb_list.clear();
		sOB.clear();
		text_list.clear();
		bombTime = false;
		unBombTime = !bombTime;
		bombTimeFrame = 0;
		setTop(0);
		setLeft(0);
		GAME_SITU = isSTART;
		game_life = 3;
		power = 0;
		b1 = true;
		b2 = true;
		b3 = true;
		b4 = true;
		sound = 0;
		point = 0;
		allPoint = 0;
		bombLimit=defaultbomb;
		game_life=defaultLife;
		paint.setColor(Color.argb(255, 255, 88, 88));
	}
	public void isShotByEnemy()
	{if (unBombTime)
		{
			addSOB(selfX, selfY, 1, DANMAKU_TYPE_BITMAP, false);
			game_life = game_life - 1;
			if (game_life <= 0)
			{
				GAME_SITU = isDEAD;
				paint.setColor(Color.argb(255, 0, 0, 0));
				soundPool.play(2, 1, 1, 0, 0, 1);
			}
			else
			{bombLimit=defaultbomb;
				soundPool.play(2, 1, 1, 0, 0, 1);
				if (power >= 1)
				{
					soundPool.play(2, 1, 1, 0, 0, 1);
					if (power > 1)
					{
						power = power - 1.1f;
					}
					else
					{
						power = power - 1;
					}
					if (game_life > 1)
					{
						for (int i=0;i < 6;i++)
						{
							addBonus(0.2f, 0.1f, getWidth() / 6f + (getWidth() / 4f * 3) * (float)Math.random(), getHeight() / 3);
						}}
					if (power <= 4)
					{
						b4 = true;
					}
					if (power <= 3)
					{
						b3 = true;
					}
					if (power <= 2)
					{
						b2 = true;
					}
					if (power <= 1)
					{
						b1 = true;
					}}
				if (game_life == 1)
				{
					addBonus(0.41f, 0.05f, getWidth() / 2f, getHeight() / 2f);

				}
				no_enemy = true;
				//no_enemy_time=0;
				selfX = getWidth() / 2f;
				selfY = getHeight() / 4f * 3f;
			}
		}
	}
	public void soundExplo()
	{
		soundPool.play(1, 1, 1, 0, 0, 1);
	}
	public void addBonus(float min, float bonus , float postX, float postY)
	{
		Bonus mBonus=new Bonus();
		float asBonus=min + (float)(bonus * Math.random());
		mBonus.postX = postX;
		mBonus.postY = postY;
		if (0.1f < asBonus && asBonus < 0.2f)
		{//点
			mBonus.tietu = dian;
			mBonus.BONUS_TYPE = mBonus.D;
			bonus_list.add(mBonus);
		}
		if (asBonus > 0.2f && asBonus < 0.3)
		{//攻击力
			mBonus.tietu = pOwer;
			mBonus.BONUS_TYPE = mBonus.P;
			bonus_list.add(mBonus);
		}
		if (asBonus > 0.3f && asBonus < 0.4f)
		{//炸弹
			mBonus.tietu = bomb;
			mBonus.BONUS_TYPE = mBonus.B;
			bonus_list.add(mBonus);
		}
		if (asBonus > 0.4f && asBonus < 0.5f)
		{//最后一条命
			mBonus.tietu = finalB;
			mBonus.BONUS_TYPE = mBonus.F;
			bonus_list.add(mBonus);
		}
		else
		{
			//mBonus.tietu=dian;
			//mBonus.BONUS_TYPE=mBonus.D;
			//bonus_list.add(mBonus);
		}
	}
	public void showText(String text, long showTime, long saveTime)
	{
		text_list.clear();
		Text t=new Text();
		t.text = text;
		t.changeFrame = saveTime * 60;
		t.saveFrame = showTime * 60;
		t.staticChange = saveTime * 60;
		text_list.add(t);
	}
	public void showText(String text, long showTime)
	{
		Text t=new Text();
		t.text = text;
		t.saveFrame = showTime * 60;
		text_list.add(t);
	}
	public void addBomb()
	{
		BombDanmaku bd=new BombDanmaku();
		bd.tietu = bombshot;
		bd.postY = selfY;
		bd.vx = 0;
		bd.vy = 0;
		int i=(int)(0.5 + Math.random());
		bd.postX = selfX - mmp.getWidth()  + 2 * i * mmp.getWidth();
		bomb_list.add(bd);
	}
	public boolean panduan(float thylta, float px, float py, float xm, float ym, Bitmap bitmap)
	{//判断旋转后的敌机弹幕与自机的碰撞
		float A=-(float)Math.tan((thylta + 90) / 180 * Math.PI);
		float C=-py - A * px;
		float re1=(float)(Math.abs(A * xm + ym + C)) / ((float)Math.sqrt(A * A + 1));//两点距离公式
		float A2=-(float)Math.tan((thylta) / 180 * Math.PI);
		float C2=-py - A2 * px;
		float re2=(float)(Math.abs(A2 * xm + ym + C2)) / ((float)Math.sqrt(A2 * A2 + 1));
		return (re1 < bitmap.getWidth() / 2f && re2 < bitmap.getHeight() / 2f);
		}
	public void onDea()//释放资源
	{
		soundPool.release();
		mmp.recycle();
		shot.recycle();
		enemy.clear();
		enemy1.recycle();
		bonus_list.clear();
		bomb.recycle();
		dian.recycle();
		pOwer.recycle();
		jmp.recycle();
		for(int ii=0;ii<exploPng.length;ii++){
			exploPng[ii].recycle();
		}
		lmp.recycle();
		bg_grass.recycle();
	}
	@Override
	public boolean onTouchEvent(MotionEvent p2)
	{onDialog(p2);
		if (p2.getAction() == p2.ACTION_DOWN && GAME_SITU == isSTART
		 && !no_enemy)
		{
			if (i == 0)
			{
				i = 1;
				onTouchX = p2.getX();
				onTouchY = p2.getY();
				x = selfX;
				y = selfY;
			}		
		}
		if (p2.getAction() == p2.ACTION_UP)
		{
			i = 0;
		}
		if (p2.getAction() == p2.ACTION_MOVE && i != 0
			&& GAME_SITU == isSTART
			&& !no_enemy)
		{
			selfX = x + (p2.getX() - onTouchX) * 1.1f;
			selfY = y + (p2.getY() - onTouchY) * 1.1f;
		}
		return !no_enemy;
	}
	private void drawDialog(Canvas canvas)
	{
		canvas.drawText("FPS:" + secs, 0, getHeight() - 20f, pauseText2);
		canvas.drawText("Player:" + game_life, 0, getHeight() - 140f, pauseText2);
		canvas.drawText("Power:" + ((int)(power * 100)) / 100f, 0, getHeight() - 80f, pauseText2);
		canvas.drawText("Score:" + score, 0, getHeight() - 170f, pauseText2);
		canvas.drawText("Point:" + allPoint + "/100", 0, getHeight() - 50f, pauseText2);
		canvas.drawText("Spell:" + bombLimit , 0, getHeight() - 110, pauseText2);
		canvas.drawText("「Bomb」",getWidth(),getHeight()-20,bombText);
		canvas.drawText("Pause ",getWidth(),45,pauseButtonText);
		if(bombLimit<=0){
			bombText.setColor(0xffaaaaaa);
		}
		else{
			bombText.setColor(0xff00ff33);
		}
		if (GAME_SITU == isPAUSE)
		{
			canvas.drawText("遊戲暫停", getWidth() / 2f, getHeight() / 3f, pauseText);
			if(!isChooseBack&&!isChooseRetry){
				canvas.drawText("繼續遊戲", getWidth() / 2f, getHeight() / 2f, pauseText3);
				canvas.drawText("從頭開始遊戲", getWidth() / 2f, getHeight() / 2f+75, pauseText3);
				canvas.drawText("返回至標題界面", getWidth() / 2f, getHeight() / 2f+150, pauseText3);
			}
			if(isChooseBack||isChooseRetry){
				canvas.drawText("真的要這樣做麼?", getWidth() / 2f, getHeight() / 2f, pauseText3);
				canvas.drawText("確定", getWidth() / 2f, getHeight() / 2f+75, pauseText3);
				canvas.drawText("取消", getWidth() / 2f, getHeight() / 2f+150, pauseText3);
			}
		}
		else if (GAME_SITU == isDEAD)
		{
			canvas.drawText("滿目瘡痍", getWidth() / 2f, getHeight() / 3f, pauseText);
			canvas.drawText("從頭開始遊戲", getWidth() / 2f, getHeight() / 2f, pauseText3);
			canvas.drawText("返回至標題界面", getWidth() / 2f, getHeight() / 2f+75, pauseText3);
		}
	}
	private void onDialog(MotionEvent p2)
	{
		if(p2.getAction()==p2.ACTION_DOWN){
			if(p2.getX()<=getWidth()&&
			p2.getX()>=(getWidth()/5f*4f)&&
			p2.getY()>=getHeight()-80&&
			bombLimit>0&&unBombTime&&GAME_SITU==isSTART){
				bombTime=true;
				bombLimit--;
			}
			if(p2.getX()>=getWidth()/6f*5f&&
			p2.getY()<=80){
				GAME_SITU=isPAUSE;
			}
			if(p2.getY()>=getHeight()/2f-25&&
			p2.getY()<=getHeight()/2f+50){
				if(GAME_SITU==isPAUSE&&!isChooseBack){
					GAME_SITU=isSTART;		
				}
				else if(GAME_SITU==isDEAD){
					reSet();
				}
			}
			if(p2.getY()>=getHeight()/2f+50&&
			p2.getY()<=getHeight()/2f+125){
				if(GAME_SITU==isPAUSE){
					if(!isChooseBack){
					if(!isChooseRetry){
				isChooseRetry=true;
				}
				else if(isChooseRetry){
					reSet();
					isChooseRetry=false;
				}
				}
				if(isChooseBack&&!isChooseRetry){
					ChoosedBack=true;
				}
				}
				else if(GAME_SITU==isDEAD){
					ChoosedBack=true;
				}
			}
			if(p2.getY()>=getHeight()/2f+125&&
			p2.getY()<=getHeight()/2f+200){
				if(GAME_SITU==isPAUSE){
				if(isChooseRetry){
					isChooseRetry=false;
					isChooseBack=false;
				}else
				if(isChooseBack){
					isChooseBack=false;
					isChooseRetry=false;
				}else
				 if(!isChooseBack&&!isChooseRetry){
					 isChooseBack=true;
					 isChooseRetry=false;
				 }
				}
			}
		}
	}
}
