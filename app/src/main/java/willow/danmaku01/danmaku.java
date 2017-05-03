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

public class danmaku extends View
{private Paint paint,pauseText,pauseText2,textPaint;
	public float selfX,selfY,unit,power=0;
	public long game=0,frame2,no_enemy_time,frame3,score;
	public int game_life=3,sound;
	public Path earth_sky;
	boolean b1=true,b2=true,b3=true,b4=true;
	public List<shotDanmaku> shotDAnmaku=new ArrayList<shotDanmaku>();
	public List<Enemy> enemy=new ArrayList<Enemy>();
	public List<Bonus> bonus_list=new ArrayList<Bonus>();
	public List<Text> text_list=new ArrayList<Text>();
	public float touchX,touchY,move,pectime,secs,fps;
	public Bitmap mmp,shot,bg_grass,sky,enemy1,lmp,jmp,dian,pOwer,bomb,shot1,emp;
	public float frame=1,bg_garss_m;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2,isTALK=3,DRAWABLE_CANVAS=0,DRAWABLE_BITMAP=1;
	public int GAME_SITU;
	public boolean liftoff=false,no_enemy=false;
	public Context mContext;

	//private MediaPlayer sound_explo;
	private SoundPool soundPool;
	public void init(Context context)
	{
		Bitmap nmp=((BitmapDrawable)getResources().getDrawable(R.drawable.loser)).getBitmap();
		mmp = Bitmap.createScaledBitmap(nmp, nmp.getWidth() / 6, nmp.getHeight() / 6, true); 
		lmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.power0)).getBitmap();
		Bitmap kmp=((BitmapDrawable)getResources().getDrawable(R.drawable.smearthgrass)).getBitmap();
		bg_grass = Bitmap.createScaledBitmap(kmp, kmp.getWidth() / 2, kmp.getHeight() / 2, true);
		jmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.earth_sky_background)).getBitmap();
		Bitmap imp=((BitmapDrawable)getResources().getDrawable(R.drawable.enemy1)).getBitmap();
		enemy1 = Bitmap.createScaledBitmap(imp, imp.getWidth() / 5, imp.getHeight() / 5, true);
		Bitmap hmp=((BitmapDrawable)getResources().getDrawable(R.drawable.i)).getBitmap();
		dian=Bitmap.createScaledBitmap(hmp,hmp.getWidth()/2,hmp.getHeight()/2,true);
		Bitmap gmp=((BitmapDrawable)getResources().getDrawable(R.drawable.p)).getBitmap();
		pOwer=Bitmap.createScaledBitmap(gmp,gmp.getWidth()/2,gmp.getHeight()/2,true);
		Bitmap fmp=((BitmapDrawable)getResources().getDrawable(R.drawable.b)).getBitmap();
		bomb=Bitmap.createScaledBitmap(fmp,fmp.getWidth()/2,fmp.getHeight()/2,true);
		 emp=((BitmapDrawable)getResources().getDrawable(R.drawable.power1)).getBitmap();
		//sound_explo=new MediaPlayer().create(context,R.raw.explo);
		soundPool= new SoundPool(100,AudioManager.STREAM_MUSIC,5);
		soundPool.load(context,R.raw.explo,1);
		soundPool.load(context,R.raw.pdel,2);
		soundPool.load(context,R.raw.pshot,3);
		soundPool.load(context,R.raw.item,4);
		soundPool.load(context,R.raw.tip,5);
		soundPool.load(context,R.raw.bom,6);
		pauseText = new Paint();
		pauseText.setAntiAlias(true);
		pauseText.setColor(Color.argb(200, 255, 255, 255));
		pauseText.setTextSize(60);
		pauseText.setTextAlign(Paint.Align.CENTER);
		pauseText.setTypeface(Typeface.createFromAsset(context.getAssets(),"font/ttc.ttc"));
		pauseText2=new Paint();
		pauseText2.setAntiAlias(true);
		pauseText2.setColor(Color.WHITE);
		pauseText2.setTextSize(20);
		pauseText2.setTypeface(Typeface.createFromAsset(context.getAssets(),"font/exoi.ttf"));
		textPaint=new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTypeface(Typeface.createFromAsset(context.getAssets(),"font/exoi.ttf"));
		textPaint.setTextSize(60);
		textPaint.setTextAlign(Paint.Align.CENTER);
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
		if (GAME_SITU == isPAUSE||GAME_SITU==isDEAD)
		{
			pauseGame(canvas);}
		drawText(canvas);
		canvas.drawText("FPS:" +secs, 0, getHeight() - 20f, pauseText2);
		canvas.drawText("Life:"+game_life,0,getHeight()-90f,pauseText2);
		canvas.drawText("Power:"+((int)(power*100))/100f,0,getHeight()-55f,pauseText2);
		canvas.drawText("Score:"+score,0,getHeight()-125f,pauseText2);
	invalidate();
		super.onDraw(canvas);
	}
	/*----------------------------------------*/
	public danmaku(Context context)
	{
		
		super(context);
		init(context);
		mContext=context;
	}
	public danmaku(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
		init(context);
		mContext=context;
	}
	public danmaku(Context context, AttributeSet attributeSet, int defStyle)
	{
		super(context, attributeSet, defStyle);
		init(context);
		mContext=context;
	}
	/*----------------------------------------*/
	public void startGame(Canvas canvas)
	{if (game == 0)
		{unit=getHeight()/100f;
			selfX = getWidth() / 2f;
			selfY = getHeight() / 3f * 2f;
			showText("Game Start!!!",3);
			game = +1;
			sky = Bitmap.createScaledBitmap(jmp, getWidth(), getHeight(), true);
			bg_garss_m = selfY + mmp.getHeight() / 2f;
		}
		if(!no_enemy){
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
		drawSky(canvas);
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
			sd.y = sd.y +sd.vy+ sd.thereTime*sd.ay*unit;
			sd.x=sd.x+sd.vx*unit+sd.thereTime*sd.ax*unit;
			canvas.drawBitmap(sd.tietu, sd.x - sd.tietu.getWidth() / 2f, sd.y - sd.tietu.getHeight() / 2, paint);
			if (sd.y < 0 - shot.getHeight() )//- getHeight() * 2)
			{
				shotDAnmaku.remove(i);
	}
		}
		if (frame == 10)
		{
			frame = 1;
		}
		if (frame == 1)
		{
			shotDanmaku st=new shotDanmaku();
			st.x = selfX;
			st.y = selfY - mmp.getHeight()/2;
			shot = Bitmap.createScaledBitmap(lmp, lmp.getWidth() / 14, lmp.getHeight() / 14, true);
			shot1=Bitmap.createScaledBitmap(emp,emp.getWidth()/14,emp.getHeight()/14,true);
			st.tietu = shot;
			soundPool.play(3,1, 1, 0, 0, 1);
			if(power>=1){
				if (b1)
				{
					soundPool.play(6, 1, 1, 0, 0, 1);
				showText("Power Extra!!!",2);
				b1=false;
			}
			shotDanmaku su=new shotDanmaku();
			su.tietu=shot1;
			su.vx=0.3f;
			su.ay=-0.2f;
			su.vy=12f;
				su.x = selfX+mmp.getWidth()/2f;
				su.y = selfY + mmp.getHeight()/2f;
			shotDAnmaku.add(su);
			shotDanmaku sv=new shotDanmaku();
			sv.tietu=shot1;
			sv.vx=-0.3f;
			sv.ay=-0.2f;
			sv.vy=12f;
				sv.x = selfX-mmp.getWidth()/2f;
				sv.y = selfY + mmp.getHeight()/2;
			shotDAnmaku.add(sv);
			}
			if(power>=4){
				if(b4){
				b4=false;
				soundPool.play(6, 1, 1, 0, 0, 1);
				showText("Full Power!!!",3);
				power=4.0f;
				}
			}
			if(power>=3)
			{
				if(b3){
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Power Extra!!!",2);
					b3=false;
				}
			}
			if(power>=2)
			{
				if(b2){
					soundPool.play(6, 1, 1, 0, 0, 1);
					showText("Power Extra!!!",2);
					b2=false;
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
		part1();
		
		
		for(int l=0;l<2;l++){
			drawBonus(canvas);
		drawEnemy(canvas); 
		}
		drawSelf(canvas);
	}
	/*----------------------------------------*/
	private void pauseGame(Canvas canvas)
	{ drawSky(canvas);
		drawBonus(canvas);
		drawEnemy(canvas);
		if (!liftoff)
		{
			canvas.drawBitmap(bg_grass, getWidth() / 2 - bg_grass.getWidth() / 2, bg_garss_m + move, paint);
		}
		for (int i=0;i < shotDAnmaku.size();i++)
		{
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(sd.tietu, sd.x - sd.tietu.getWidth() / 2f, sd.y - sd.tietu.getHeight() / 2f, paint);
		}
		drawSelf(canvas);
		
		if(GAME_SITU==isPAUSE){
			canvas.drawText("遊戲暫停", getWidth() / 2f,getHeight() / 3f, pauseText);
		}else if(GAME_SITU==isDEAD){
			canvas.drawText("滿目瘡痍", getWidth() / 2f, getHeight() / 3f, pauseText);
		}

	} 
	/*----------------------------------------*/
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
		public int BONUS_TYPE=0;
	}
	public  class Boss extends Sprite
	{
		int spellcard=2;
	}
	public static class shotDanmaku
	{
		float radio=0.5f,x,y,vx,vy=0,ax=0,ay=-0.1f,thereTime=0;
		int drawable_type=0;
		Bitmap tietu=null;
		boolean haveShot=false;
	}
	public static class Text{
		int alpha=255;
		long saveFrame=60;
		long changeFrame=60;
		String text="";
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
			canvas.drawBitmap(me.tietu, me.postX - me.tietu.getWidth() / 2f, me.postY - me.tietu.getHeight() / 2f, paint);

			if (me.isDEAD || me.postY > getHeight() + me.tietu.getHeight() / 2f
				|| me.postX < -me.tietu.getWidth()
				|| me.postX > getWidth() + me.tietu.getWidth())
			{
				enemy.remove(i);
			}
			for (int j=0;j < shotDAnmaku.size();j = j + 1)
			{
				shotDanmaku sd=shotDAnmaku.get(j);
				if (sd.x + sd.tietu.getWidth() / 2f 
				> me.postX - (me.tietu.getWidth() / 2f)
					&& sd.x - sd.tietu.getWidth()/ 2f
					< me.postX + (me.tietu.getWidth() / 2f)			
					&& sd.y -sd.tietu.getHeight() / 2 
					< me.postY + (me.tietu.getHeight() / 2f)
					&& sd.y + sd.tietu.getHeight() / 2f 
					> me.postY -( me.tietu.getHeight() / 2f)
					&& !sd.haveShot)
				{
					me.life = me.life - 1-power/2f;
					if (me.life <= 0)
					{
						me.isDEAD = true;
						addBonus(0,me.bonus,me.postX,me.postY);
						score=score+100;
						soundExplo();}
						else{
						score=score+10;
							soundPool.play(5,1,1,0,0,1);
							
						}
					//sd.haveShot = true;
					//sd.tietu.setWidth(1);
					//sd.tietu.setHeight(1);
					shotDAnmaku.remove(j);
				}
				if(selfX> me.postX - me.tietu.getWidth() / 2f
					&&selfX  < me.postX + me.tietu.getWidth() / 2f
					&&selfY < me.postY + me.tietu.getHeight() / 2f
					&&selfY  > me.postY - me.tietu.getHeight() / 2f
					&&!no_enemy
					&&game_life>0){
					isShotByEnemy();
					enemy.remove(i);
					}
			}
		}
	}
	private void drawBonus(Canvas canvas)
	{
		for(int i=0;i<bonus_list.size();i=i+1){
			Bonus mBonus=bonus_list.get(i);
			canvas.drawBitmap(mBonus.tietu,//mBonus.postX -bomb.getWidth()/2f,mBonus.postY-bomb.getHeight()/2f,paint);
			mBonus.postX-mBonus.tietu.getWidth()/2f,
			mBonus.postY-mBonus.tietu.getHeight()/2f,paint);
			if(mBonus.postY>getHeight()+mBonus.tietu.getHeight()){
				bonus_list.remove(i);
			}
			if(GAME_SITU==isSTART){
				mBonus.postY=mBonus.postY+1;//mBonus.vy;
			}
			if(mBonus.postX-mBonus.tietu.getWidth()/2
			<selfX+mmp.getWidth()/2f
			&&mBonus.postX+mBonus.tietu.getWidth()/2
			>selfX-mmp.getWidth()/2f
			&&mBonus.postY-mBonus.tietu.getHeight()/2
			<selfY+mmp.getHeight()/2f
			&&mBonus.postY-mBonus.tietu.getHeight()/2f
			>selfY-mmp.getHeight()/2f
			){
				switch(mBonus.BONUS_TYPE){
					case mBonus.D:
						score=score+100;
					 soundPool.play(4,1,1,0,0,1);
						break;
					case mBonus.P:
					 soundPool.play(4,1,1,0,0,1);
					 if(power<4){
					 power=power+(float)(0.03+0.1*Math.random());}
					 score=score+50;
						break;
					case mBonus.B:
					 soundPool.play(4,1,1,0,0,1);
						break;
					case mBonus.UP:
						break;
						}
				bonus_list.remove(i);
				
			}
			
		}
	}
	private void drawText(Canvas canvas)
	{
		for(int i=0;i<text_list.size();i++){
			Text t=text_list.get(i);
			t.saveFrame--;
			textPaint.setARGB(t.alpha,255,255,255);
			if(t.saveFrame<0){
				t.alpha=t.alpha-(int)(250/t.changeFrame);
				t.changeFrame--;
			 if(t.changeFrame<=0){
				 text_list.remove(i);
			 }
			}
			canvas.drawText(t.text,getWidth()/2f,getHeight()/3f,textPaint);
		}
	}
	public void addEnemy()
	{
		Enemy mEnemy= new Enemy();
		mEnemy.postX = getWidth() * (float)(Math.random());
		mEnemy.isDEAD = false;
		
		mEnemy.vy = 2;
		mEnemy.life = 4;
		mEnemy.bonus=0.3f;
		mEnemy.tietu = enemy1;
		mEnemy.postY = -mEnemy.tietu.getHeight();
		enemy.add(mEnemy);
	}
	public void reSet(){
		game=0;
		frame2=0;
		frame3=0;
		move=0;
		liftoff=false;
		enemy.clear();
		no_enemy=false;
		no_enemy_time=0l;
		bonus_list.clear();
		shotDAnmaku.clear();
		GAME_SITU=isSTART;
		game_life=3;
		power=0;
		b1=true;
		b2=true;
		b3=true;
		b4=true;
		sound=0;
		paint.setColor(Color.argb(255, 255, 88, 88));
	}
	public void isShotByEnemy(){
		soundPool.play(2,1, 1, 0, 0, 1);
		game_life=game_life-1;
		if(game_life<=0){
			GAME_SITU=isDEAD;
			paint.setColor(Color.argb(255, 0,0, 0));
		}
		else{
			no_enemy=true;
			//no_enemy_time=0;
			selfX = getWidth() / 2f;
			selfY = getHeight() / 4f *3f;
		}
		if(power>=1){
			if(power>1){
			power=power-1.1f;
			}
			else{
			power=power-1;
			}
			for(int i=0;i<10;i++){
			addBonus(0.2f,0.1f,getWidth()/6f+(getWidth()/4f*3)*(float)Math.random(),getHeight()/3);
			}
			if(power<=4){
				b4=true;
			}
			if(power<=3){
				b3=true;
			}
			if(power<=2){
				b2=true;
			}
			if(power<=1){
				b1=true;
			}
		}
	}
	public void soundExplo(){
		soundPool.play(1,1, 1, 0, 0, 1);
	}
	public void addBonus(float min,float bonus ,float postX, float postY){
		Bonus mBonus=new Bonus();
		float asBonus=min+(float)(bonus*Math.random());
		mBonus.postX=postX;
		mBonus.postY=postY;
		if(0.1f<asBonus&&asBonus<0.2f){
			mBonus.tietu=dian;
			mBonus.BONUS_TYPE=mBonus.D;
			bonus_list.add(mBonus);
		}
		if(asBonus>0.2f&&asBonus<0.3){
			mBonus.tietu=pOwer;
			mBonus.BONUS_TYPE=mBonus.P;
			bonus_list.add(mBonus);
		}
		if(asBonus>0.3f&&asBonus<0.4f){
			mBonus.tietu=bomb;
			mBonus.BONUS_TYPE=mBonus.B;
			bonus_list.add(mBonus);
		}else{
			//mBonus.tietu=dian;
			//mBonus.BONUS_TYPE=mBonus.D;
			//bonus_list.add(mBonus);
		}
		
	}
	public void showText(String text,long showTime,long saveTime){
		Text t=new Text();
		t.text=text;
		t.changeFrame=saveTime*60;
		t.saveFrame=showTime*60;
		text_list.add(t);
	}
	public void showText(String text,long showTime){
		Text t=new Text();
		t.text=text;
		t.saveFrame=showTime*60;
		text_list.add(t);
	}
	public boolean panduan(float thylta,float px,float py,float xm,float ym,Bitmap bitmap){
		float A=-(float)Math.tan((thylta+90)/180*Math.PI);
		float C=-py-A*px;
		float re1=(float)(Math.abs(A*xm+ym+C))/((float)Math.sqrt(A*A+1));

		float A2=-(float)Math.tan((thylta)/180*Math.PI);
		float C2=-py-A2*px;
		float re2=(float)(Math.abs(A2*xm+ym+C2))/((float)Math.sqrt(A2*A2+1));
		boolean isShot=re1<bitmap.getWidth()/2f&&re2<bitmap.getHeight()/2f;
		//String s=A+","+C+"/"+re1+","+re2+isShot;
		return isShot;}
	public void onDea(){
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
		lmp.recycle();
		bg_grass.recycle();
	}
}
