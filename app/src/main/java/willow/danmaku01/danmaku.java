package willow.danmaku01;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.view.View.*;
import android.graphics.drawable.*;
import java.util.*;

public class danmaku extends View
{private Paint paint,pauseText;
public float selfX,selfY;
	public int game=0;
	public List<shotDanmaku> shotDAnmaku=new ArrayList<shotDanmaku>();
	public float touchX,touchY;
	public Bitmap mmp,shot;
	public float frame=1;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2,DRAWABLE_CANVAS=0,DRAWABLE_BITMAP=1;
	public int GAME_SITU;
public void init(){
	Bitmap nmp=((BitmapDrawable)getResources().getDrawable(R.drawable.loser)).getBitmap();
	mmp=Bitmap.createScaledBitmap(nmp,nmp.getWidth()/5,nmp.getHeight()/5,true); 
	Bitmap lmp=((BitmapDrawable)getResources().getDrawable(R.drawable.power0)).getBitmap();
	shot=Bitmap.createScaledBitmap(lmp,lmp.getWidth()/15,lmp.getHeight()/15,true);
	pauseText=new Paint();
	pauseText.setAntiAlias(true);
	pauseText.setColor(Color.argb(200,20,150,255));
	pauseText.setTextSize(30);
	GAME_SITU=isSTART;
	paint=new Paint();
	paint.setAntiAlias(true);
	paint.setColor(Color.argb(255,255,88,88));
}
	@Override
	protected void onDraw(Canvas canvas)
	{
		if(GAME_SITU==isSTART){
		startGame(canvas);
		}
	if(GAME_SITU==isPAUSE){
		 pauseGame(canvas);}
		 
		invalidate();
		// TODO: Implement this method
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
 public void startGame(Canvas canvas){
	 //long fps=System.currentTimeMillis();
	 for(int i=0;i<shotDAnmaku.size();i++){
		 shotDanmaku sd=shotDAnmaku.get(i);
		 canvas.drawBitmap(shot,sd.getX()-shot.getWidth()/2f,sd.getY()-shot.getHeight()/3,paint);
		 sd.y=sd.y+getHeight()/100f*sd.ay;
		 if(sd.getY()<0-shot.getHeight()){
			shotDAnmaku.remove(i);
		 }
	 }
	 if(frame>=10){
		 frame=1;
		 shotDanmaku st=new shotDanmaku();
		 st.x=selfX;
		 st.y=selfY-mmp.getHeight();
		 shotDAnmaku.add(st);
	 }
	 frame=frame+1;
	 if(game==0){
		 selfX=getWidth()/2f;
		 selfY=getHeight()/3f*2f;
		 game=game+1;
	 }
	 if(selfX<0){
		 selfX=0;
	 }
	 else if(selfX>getWidth()){
		 selfX=getWidth();
	 }
	 if(selfY<0){
		 selfY=0;
	 }
	 else if(selfY>getHeight()){
		 selfY=getHeight();
	 }
	 
	 drawSelf(canvas);
	//canvas.drawText("FPS:"+((long)(System.currentTimeMillis()-fps)),0,getHeight()-20f,paint);
 }
	private void pauseGame(Canvas canvas)
	{   drawSelf(canvas);
		for(int i=0;i<shotDAnmaku.size();i++){
			shotDanmaku sd=shotDAnmaku.get(i);
			canvas.drawBitmap(shot,sd.getX()-shot.getWidth()/2f,sd.getY()-shot.getHeight()/3,paint);
	}
		canvas.drawText("游戏暂停",getWidth()/5f,getHeight()/2f,pauseText);
	} 
	private void drawSelf(Canvas canvas){
		canvas.drawBitmap(mmp,selfX-mmp.getWidth()/2f,selfY-mmp.getHeight()/2f,paint);
		canvas.drawCircle(selfX,selfY,getHeight()/260f,paint);
	}
 public class Sprite{
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
 public class Enermy extends Sprite{
	 float bonus=0.1f;
 }
 public class Boss extends Sprite{
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
}
