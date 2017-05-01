package willow.danmaku01;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.view.View.*;

public class danmaku extends View
{private Paint paint,pauseText;
public float selfX,selfY;
	public int game=0;
	public float touchX,touchY;
	public static final int isSTART=0,isPAUSE=1,isDEAD=2;
	public int GAME_SITU;
public void init(){
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
	 
	 canvas.drawCircle(selfX,selfY,getHeight()/240f,paint);
	//canvas.drawText("FPS:"+(1/(long)(System.currentTimeMillis()-fps)),0,getHeight()-20f,paint);
 }
	private void pauseGame(Canvas canvas)
	{
		canvas.drawCircle(selfX,selfY,getHeight()/240f,paint);
		canvas.drawText("游戏暂停",getWidth()/4f,getHeight()/2f,pauseText);
	} 
//	@Override
//	public boolean onTouchEvent(MotionEvent event)
//	{touchX=event.getX();
//	 touchY=event.getY();
//		// TODO: Implement this method
//		return super.onTouchEvent(event);
//	}
//	public float getTouchX(){
//		return touchX;
//	}
//	 public float getTouchY(){
//		 return touchY;
//	 }
 public class Sprite{
	 int life=1;
	 int image;
	 float postX;
	 float postY;
	 float vx,vy,ax,ay,power=0.00f;
 }
 public class Self extends Sprite{
	 int bomb=2;
 }
 public class Enermy extends Sprite{
	 float bonus=0.1f;
 }
 public class Boss extends Sprite{
	 int spellcard=2;
 }
}
