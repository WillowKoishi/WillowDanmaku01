package willow.danmaku01;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.view.View.*;
import android.graphics.drawable.*;
import java.util.*;
import android.media.*;

public class danmaku extends View
{private Paint paint,pauseText;
public float selfX,selfY;
	public int game=0;
	public Path earth_sky;
	public List<shotDanmaku> shotDAnmaku=new ArrayList<shotDanmaku>();
	public float touchX,touchY,move,pectime,secs,fps;
	public Bitmap mmp,shot,bg_grass,sky;
	public float frame=1,bg_garss_m;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2,isTALK=3,DRAWABLE_CANVAS=0,DRAWABLE_BITMAP=1;
	public int GAME_SITU;
	private boolean liftoff=false;
	private Bitmap jmp;
public void init(){
	Bitmap nmp=((BitmapDrawable)getResources().getDrawable(R.drawable.loser)).getBitmap();
	mmp=Bitmap.createScaledBitmap(nmp,nmp.getWidth()/5,nmp.getHeight()/5,true); 
	Bitmap lmp=((BitmapDrawable)getResources().getDrawable(R.drawable.power0)).getBitmap();
	shot=Bitmap.createScaledBitmap(lmp,lmp.getWidth()/15,lmp.getHeight()/15,true);
	Bitmap kmp=((BitmapDrawable)getResources().getDrawable(R.drawable.smearthgrass)).getBitmap();
	bg_grass=Bitmap.createScaledBitmap(kmp,kmp.getWidth()/2,kmp.getHeight()/2,true);
	jmp=((BitmapDrawable)getResources().getDrawable(R.drawable.earth_sky_background)).getBitmap();
	pauseText=new Paint();
	pauseText.setAntiAlias(true);
	pauseText.setColor(Color.argb(200,255,255,255));
	pauseText.setTextSize(40);
	GAME_SITU=isSTART;
	paint=new Paint();
	paint.setAntiAlias(true);
	paint.setColor(Color.argb(255,255,88,88));
}
	@Override
	protected void onDraw(Canvas canvas)
	{ fps=(float)System.currentTimeMillis()/100000000000000f;
		secs=(fps-pectime);
		
		if(GAME_SITU==isSTART||GAME_SITU==isTALK){
		startGame(canvas);
		}
	if(GAME_SITU==isPAUSE){
		 pauseGame(canvas);}
		invalidate();
		canvas.drawText("FPS:"+1/secs,0,getHeight()-20f,paint);
		
		super.onDraw(canvas);
	}
	public danmaku(Context context){
		super(context);
		init();
	}
	public danmaku(Context context,AttributeSet attributeSet){
		super(context,attributeSet);
		init();
	}
	public danmaku(Context context,AttributeSet attributeSet,int defStyle){
		super(context,attributeSet,defStyle);
		init();
	}
	/*----------------------------------------*/
 public void startGame(Canvas canvas){
	 frame=frame+1;
	 if(game==0){
		 selfX=getWidth()/2f;
		 selfY=getHeight()/3f*2f;
		 game=game+1;
		 sky=Bitmap.createScaledBitmap(jmp,getWidth(),getHeight(),true);
		 bg_garss_m=selfY+mmp.getHeight()/2f;
	 }
	drawSky(canvas);
	 if(!liftoff){
		 canvas.drawBitmap(bg_grass,getWidth()/2-bg_grass.getWidth()/2,bg_garss_m+move,paint);
		 move=move+2;
		 if(move>=bg_grass.getHeight()){
			 liftoff=true;
			 bg_grass=null;
		 }
		 if(selfY+mmp.getHeight()/2f>=bg_garss_m+move){
			 selfY=bg_garss_m+move-mmp.getHeight()/2;
		 }
	 }
	 for(int i=0;i<shotDAnmaku.size();i++){
		 shotDanmaku sd=shotDAnmaku.get(i);
		 canvas.drawBitmap(shot,sd.x-shot.getWidth()/2f,sd.getY()-shot.getHeight()/3,paint);
		 sd.y=sd.y+getHeight()/100f*sd.ay;
		 if(sd.getY()<0-shot.getHeight()){
			shotDAnmaku.remove(i);
		 }
	 }
	 if(frame>=7){
		 frame=1;
		 shotDanmaku st=new shotDanmaku();
		 st.x=selfX;
		 st.y=selfY-mmp.getHeight();
		 shotDAnmaku.add(st);
	 }
	 if(selfX<0){
		 selfX=1;
	 }
	 else if(selfX>getWidth()){
		 selfX=getWidth()-1;
	 }
	 if(selfY<0){
		 selfY=1;
	 }
	 else if(selfY>getHeight()){
		 selfY=getHeight()-1;
	 }
	 drawSelf(canvas);
	 pectime=fps/100000000000000f;
 }
	/*----------------------------------------*/
	private void pauseGame(Canvas canvas)
	{ drawSky(canvas);
		if(!liftoff){
			canvas.drawBitmap(bg_grass,getWidth()/2-bg_grass.getWidth()/2,bg_garss_m+move,paint);
			}
		for(int i=0;i<shotDAnmaku.size();i++){
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(shot,sd.getX()-shot.getWidth()/2f,sd.getY()-shot.getHeight()/3,paint);
	}
		drawSelf(canvas);
		canvas.drawText("游戏暂停",getWidth()/5f,getHeight()/2f,pauseText);
	} 
	/*----------------------------------------*/
 public static class Sprite{
	public int life=1;
	public int image;
	public float postX;
	public float postY;
	public float vx,vy,ax=0,ay=0,power=0.00f;
	public boolean isDEAD=false;
 }
 public class Self extends Sprite{
	 int ship_type=0;
	 int bomb=2;
 }
 public class Enermy1 extends Sprite{
	 float bonus=0.1f;
	 public float bonus(){
		return bonus; 
	 }
 }
 public class Bonus extends Sprite{
	 public static final int D=0,P=1,B=2,F=3,UP=4;
 }
 public  class Boss extends Sprite{
	 int spellcard=2;
 }
public static class shotDanmaku{
	float radio=0.5f,x,y,vx,vy,ax=-2,ay=-4;
	int drawable_type=0;
	public float getAX(){
		return ax;
	}
	public float getAY(){
		return ay;
	}
		public float getY(){
		return y;
		}
		public float getX(){
		return x;
		}
}
	private void drawSelf(Canvas canvas){
		canvas.drawBitmap(mmp,selfX-mmp.getWidth()/2f,selfY-mmp.getHeight()/2f,paint);
		canvas.drawCircle(selfX,selfY,getHeight()/260f,paint);
	}
	private void drawSky(Canvas canvas){
	canvas.drawBitmap(sky,0,0,paint);
	}
}
