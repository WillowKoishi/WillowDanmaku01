package willow.danmaku01;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.view.View.*;

public class danmaku extends View
{private Paint paint;
public float selfX,selfY;
	public int game=0;
	public float touchX,touchY;
public void init(){
	paint=new Paint();
	paint.setAntiAlias(true);
}
	@Override
	protected void onDraw(Canvas canvas)
	{startGame(canvas);
		canvas.drawText(selfX+"\n"+selfY,100,100,paint);
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
	 canvas.drawCircle(selfX,selfY,getHeight()/140f,paint);
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
 public class Enermy{
	 int life=1;
	 //int image=;
 }
}
