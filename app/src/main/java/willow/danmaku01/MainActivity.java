package willow.danmaku01;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.support.v7.app.*;

public class MainActivity extends AppCompatActivity
{danmaku danmaku;
float p,s;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		h.postDelayed(r,1);
		danmaku=(danmaku)this.findViewById(R.id.danmaku);
		danmaku.setOnTouchListener(new OnTouchListener(){	
		int i=0;
		float onTouchX,onTouchY,x,y;
				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{if(p2.getAction()==p2.ACTION_DOWN&&danmaku.GAME_SITU==danmaku.isSTART
				&&!danmaku.no_enemy){
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
				if(p2.getAction()==p2.ACTION_MOVE&&i!=0
				&&danmaku.GAME_SITU==danmaku.isSTART
				&&!danmaku.no_enemy){
					danmaku.selfX=x+(p2.getX()-onTouchX)*1.1f;
					danmaku.selfY=y+(p2.getY()-onTouchY)*1.1f;
				}
					// TODO: Implement this method
					
					return !danmaku.no_enemy;
				}
			});
    }
	public void start(View v){
		danmaku.GAME_SITU=danmaku.isSTART;
	}
	public void pause(View v){
		danmaku.GAME_SITU=danmaku.isPAUSE;
	}
	public void addenemy(View v){
		danmaku.reSet();
		p=0;
	}
	Runnable r=new Runnable(){

		@Override
		public void run()
		{//if(danmaku.game_life<=0){
			//danmaku.GAME_SITU=danmaku.isDEAD;
		//}
		danmaku.secs=danmaku.frame2-p;
			p=danmaku.frame2;
			h.postDelayed(r,1000);
			danmaku.addEnemy();
			h.sendEmptyMessage(0);
		}
	};
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}
		
	};
}
