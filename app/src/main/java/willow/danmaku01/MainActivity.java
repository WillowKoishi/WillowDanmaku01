package willow.danmaku01;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;

public class MainActivity extends Activity 
{danmaku danmaku;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		danmaku=(danmaku)this.findViewById(R.id.danmaku);
		danmaku.setOnTouchListener(new OnTouchListener(){	
		int i=0;
		float onTouchX,onTouchY,x,y;
				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{if(p2.getAction()==p2.ACTION_DOWN&&danmaku.GAME_SITU==danmaku.isSTART){
				if(i==0){
					i=1;
					onTouchX=p2.getX();
					onTouchY=p2.getY();
					x=danmaku.selfX;
					y=danmaku.selfY;
				}		
				}
				if(p2.getAction()==p2.ACTION_UP){
					i=0;
				}
				if(p2.getAction()==p2.ACTION_MOVE&&i!=0&&danmaku.GAME_SITU==danmaku.isSTART){
					danmaku.selfX=x+(p2.getX()-onTouchX)*1.1f;
					danmaku.selfY=y+(p2.getY()-onTouchY)*1.1f;
				}
					// TODO: Implement this method
					return true;
				}
			});
    }
	public void start(View v){
		danmaku.GAME_SITU=danmaku.isSTART;
	}
	public void pause(View v){
		danmaku.GAME_SITU=danmaku.isPAUSE;
	}
	
	
}
