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

public class danmaku extends View
{private Paint paint,pauseText;
	public float selfX,selfY;
	public long game=0,frame2,no_enemy_time,frame3;
	public int game_life=3;
	public Path earth_sky;
	public List<shotDanmaku> shotDAnmaku=new ArrayList<shotDanmaku>();
	public List<Enemy> enemy=new ArrayList<Enemy>();
	public float touchX,touchY,move,pectime,secs,fps;
	public Bitmap mmp,shot,bg_grass,sky,enemy1,lmp;
	public float frame=1,bg_garss_m;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2,isTALK=3,DRAWABLE_CANVAS=0,DRAWABLE_BITMAP=1;
	public int GAME_SITU;
	public boolean liftoff=false,no_enemy=false;
	private Bitmap jmp;
	private MediaPlayer sound_explo=new MediaPlayer();
	public void init(Context context)
	{
		Bitmap nmp=((BitmapDrawable)getResources().getDrawable(R.drawable.loser)).getBitmap();
		mmp = Bitmap.createScaledBitmap(nmp, nmp.getWidth() / 5, nmp.getHeight() / 5, true); 
		lmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.power0)).getBitmap();
		Bitmap kmp=((BitmapDrawable)getResources().getDrawable(R.drawable.smearthgrass)).getBitmap();
		bg_grass = Bitmap.createScaledBitmap(kmp, kmp.getWidth() / 2, kmp.getHeight() / 2, true);
		jmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.earth_sky_background)).getBitmap();
		Bitmap imp=((BitmapDrawable)getResources().getDrawable(R.drawable.enemy1)).getBitmap();
		enemy1 = Bitmap.createScaledBitmap(imp, imp.getWidth() / 5, imp.getHeight() / 5, true);
		sound_explo.create(context,R.raw.explo);
		try
		{
			sound_explo.prepare();
		}
		catch (IOException e)
		{}
		catch (IllegalStateException e)
		{}
		pauseText = new Paint();
		pauseText.setAntiAlias(true);
		pauseText.setColor(Color.argb(200, 255, 255, 255));
		pauseText.setTextSize(40);
		GAME_SITU = isSTART;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 88, 88));
	}
	@Override
	protected void onDraw(Canvas canvas)
	{ frame2 = frame2 + 1;
		
		if (GAME_SITU == isSTART)
		{
			startGame(canvas);}
		if (GAME_SITU == isPAUSE)
		{
			pauseGame(canvas);}
		canvas.drawText("FPS:" + secs, 0, getHeight() - 20f, pauseText);
		canvas.drawText("Life:"+game_life,0,getHeight()-55f,pauseText);
	invalidate();
		super.onDraw(canvas);
	}
	/*----------------------------------------*/
	public danmaku(Context context)
	{
		super(context);
		init(context);
	}
	public danmaku(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
		init(context);
	}
	public danmaku(Context context, AttributeSet attributeSet, int defStyle)
	{
		super(context, attributeSet, defStyle);
		init(context);
	}
	/*----------------------------------------*/
	public void startGame(Canvas canvas)
	{if(!no_enemy){
			no_enemy_time=0;
		}
		if(no_enemy==true){
			paint.setColor(Color.BLUE);
			no_enemy_time=no_enemy_time+1;
		}
		if(no_enemy_time>90){
			paint.setColor(Color.argb(255, 255, 88, 88));
			no_enemy=false;
			no_enemy_time=0;
		}
		frame3=frame3+1;
		frame = frame + 1;
		if (game == 0)
		{
			selfX = getWidth() / 2f;
			selfY = getHeight() / 3f * 2f;
			game = +1;
			sky = Bitmap.createScaledBitmap(jmp, getWidth(), getHeight(), true);
			bg_garss_m = selfY + mmp.getHeight() / 2f;
		}
		drawSky(canvas);
		if (!liftoff)
		{
			canvas.drawBitmap(bg_grass, getWidth() / 2 - bg_grass.getWidth() / 2, bg_garss_m + move, paint);
			move = move + 0.02f * frame2;
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
		{ 
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(sd.tietu, sd.x - shot.getWidth() / 2f, sd.getY() - shot.getHeight() / 3, paint);
			sd.y = sd.y + getHeight() / 100f * sd.ay;
			if (sd.getY() < 0 - shot.getHeight() - getHeight() * 2)
			{
				shotDAnmaku.remove(i);
			}
			if (sd.getY() + sd.tietu.getHeight() / 2f < 0)
			{
				sd.haveShot = true;
			}
			drawEnemy(canvas); 
		}
		if (frame == 15)
		{
			frame = 1;
		}
		if (frame == 1)
		{
			shotDanmaku st=new shotDanmaku();
			st.x = selfX;
			st.y = selfY - mmp.getHeight();
			shot = Bitmap.createScaledBitmap(lmp, lmp.getWidth() / 15, lmp.getHeight() / 15, true);
			st.tietu = shot;
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
		part1();
		drawSelf(canvas);
		// pectime=fps/100000000000000f;
	}
	/*----------------------------------------*/
	

		
	
	/*----------------------------------------*/
	private void pauseGame(Canvas canvas)
	{ drawSky(canvas);
		drawEnemy(canvas);
		if (!liftoff)
		{
			canvas.drawBitmap(bg_grass, getWidth() / 2 - bg_grass.getWidth() / 2, bg_garss_m + move, paint);
		}
		for (int i=0;i < shotDAnmaku.size();i++)
		{
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(shot, sd.getX() - shot.getWidth() / 2f, sd.getY() - shot.getHeight() / 3, paint);
		}
		drawSelf(canvas);
		if(GAME_SITU==isPAUSE){
			canvas.drawText("游戏暂停", getWidth() / 5f, getHeight() / 2f, pauseText);
		}else if(GAME_SITU==isDEAD){
			canvas.drawText("满目疮痍", getWidth() / 5f, getHeight() / 2f, pauseText);
		}

	} 
	/*----------------------------------------*/
	public static class Sprite
	{
		public int life=1;
		public int image;
		public float postX=0;
		public float postY=0;
		public Bitmap tietu=null;
		public float vx,vy,ax=0,ay=0,power=0.00f;
		public boolean isDEAD=false;
		public float getPostY()
		{
			return postY;
		}
	}
	public class Self extends Sprite
	{
		int ship_type=0;
		int bomb=2;
	}
	public class Enemy extends Sprite
	{
		float bonus=0.1f;
		public float bonus()
		{
			return bonus; 
		}
	}
	public class Bonus extends Sprite
	{
		public static final int D=0,P=1,B=2,F=3,UP=4;
	}
	public  class Boss extends Sprite
	{
		int spellcard=2;
	}
	public static class shotDanmaku
	{
		float radio=0.5f,x,y,vx,vy,ax=-2,ay=-3;
		int drawable_type=0;
		Bitmap tietu=null;
		boolean haveShot=false;
		public float getAX()
		{
			return ax;
		}
		public float getAY()
		{
			return ay;
		}
		public float getY()
		{
			return y;
		}
		public float getX()
		{
			return x;
		}
	}
	/*----------------------------------------*/
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
		for (int i=0;i < enemy.size();i = i + 1)
		{

			Enemy me=enemy.get(i);
			if (GAME_SITU == isSTART)
			{
				me.postY = me.postY + me.vy;

			}
			canvas.drawBitmap(enemy1, me.postX - me.tietu.getWidth() / 2f, me.postY - me.tietu.getHeight() / 2f, paint);

			if (me.isDEAD || me.postY > getHeight() + me.tietu.getHeight() / 2f
				|| me.postX < -me.tietu.getWidth()
				|| me.postX > getWidth() + me.tietu.getWidth())
			{
				enemy.remove(i);
			}
			for (int j=0;j < shotDAnmaku.size();j = j + 1)
			{
				shotDanmaku sd=shotDAnmaku.get(j);
				if (sd.x - sd.tietu.getWidth() / 2f > me.postX - me.tietu.getWidth() / 2f
					&& sd.x + sd.tietu.getWidth() / 2f < me.postX + me.tietu.getWidth() / 2f
					&& sd.y + sd.tietu.getHeight() / 2 < me.postY + me.tietu.getHeight() / 2f
					&& sd.y - sd.tietu.getHeight() / 2f > me.postY - me.tietu.getHeight() / 2f
					&& !sd.haveShot)
				{
					me.life = me.life - 1;
					if (me.life <= 0)
					{
						me.isDEAD = true;
						soundExplo();}
					sd.haveShot = true;
					sd.tietu.setWidth(1);
					sd.tietu.setHeight(1);
				}
				if(selfX> me.postX - me.tietu.getWidth() / 2f
					&&selfX  < me.postX + me.tietu.getWidth() / 2f
					&&selfY < me.postY + me.tietu.getHeight() / 2f
					&&selfY  > me.postY - me.tietu.getHeight() / 2f
					&&!no_enemy){
					isShotByEnemy();
					enemy.remove(i);
					}
			}
		}
	}
	public void addEnemy()
	{
		Enemy mEnemy= new Enemy();
		mEnemy.postX = getWidth() * (float)(Math.random());
		mEnemy.isDEAD = false;
		mEnemy.postY = -enemy1.getHeight();
		mEnemy.vy = 1;
		mEnemy.life = 1;
		mEnemy.tietu = enemy1;
		enemy.add(mEnemy);
	}
	public void reSet(){
		game=0;
		frame2=0;
		move=0;
		liftoff=false;
		enemy.clear();
		no_enemy=false;
		no_enemy_time=0l;
		shotDAnmaku.clear();
		GAME_SITU=isSTART;
		game_life=3;
		paint.setColor(Color.argb(255, 255, 88, 88));
	}
	public void isShotByEnemy(){
		game_life=game_life-1;
		if(game_life<=0){
			//GAME_SITU=isDEAD;
		}
		else{
			no_enemy=true;
			//no_enemy_time=0;
			selfX = getWidth() / 2f;
			selfY = getHeight() / 3f * 2f;
		}
		soundExplo();
	}
	public void soundExplo(){
		sound_explo.start();
	}
}
