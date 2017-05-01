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
		float onTouchX,onTouchY;
				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{if(p2.getAction()==p2.ACTION_DOWN){
				if(i==0){
					i=1;
					onTouchX=p2.getX();
					onTouchY=p2.getY();
				}		
				}
				if(p2.getAction()==p2.ACTION_UP){
					i=0;
				}
				if(p2.getAction()==p2.ACTION_MOVE&&i!=0){
					danmaku.selfX=danmaku.selfX+(p2.getX()-onTouchX)/10;
					danmaku.selfY=danmaku.selfY+(p2.getY()-onTouchY)/10;
				}
					// TODO: Implement this method
					return true;
				}
			});
    }
}
