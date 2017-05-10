package willow.danmaku01;

import android.app.*;
import android.os.*;
import android.view.View.*;
import android.view.*;
import android.support.v7.app.*;
import willow.danmaku01.view.*;
import android.content.*;
public class Start extends AppCompatActivity
{danmaku danmaku;
float p,s,e;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
		Intent intent=this.getIntent();
        setContentView(R.layout.start);
		h.postDelayed(r,1);
		danmaku=(danmaku)this.findViewById(R.id.danmaku);
		danmaku.rank=intent.getIntExtra("rank",0);
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
	public void bomb(View v){
		if(danmaku.unBombTime){
		danmaku.bombTime=true;}
	}
	Runnable r=new Runnable(){

		@Override
		public void run()
		{//if(danmaku.game_life<=0){
			//danmaku.GAME_SITU=danmaku.isDEAD;
		//}
		if(!danmaku.ChoosedBack){
		danmaku.secs=danmaku.frame2-p;
			p=danmaku.frame2;
			if(danmaku.GAME_SITU==danmaku.isSTART){
			danmaku.addEnemy();}
			h.postDelayed(r,1000);
			h.sendEmptyMessage(0);
			}
			if(danmaku.ChoosedBack){
				finish();
			}
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

	@Override
	protected void onDestroy()
	{danmaku.soundPool.release();
		danmaku.onDea();
		//System.gc();
		Runtime.getRuntime().gc();
		//danmaku=null;
	//System.exit(0);
		// TODO: Implement this method
		super.onDestroy();
	}

//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event)
//	{int keyCode=event.getKeyCode();
//		if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
//		danmaku.selfY=danmaku.selfY+danmaku.getHeight()/80f;
//	}
//		if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
//			danmaku.selfX=danmaku.selfX+danmaku.getWidth()/40f;
//		}
//		if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
//			danmaku.selfY=danmaku.selfY-danmaku.getHeight()/80f;
//		}
//		if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
//			danmaku.selfX=danmaku.selfX-danmaku.getWidth()/40f;
//		}
//		return super.dispatchKeyEvent(event);//super.onKeyDown(keyCode, event);
//	}
//
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
	{if(danmaku.GAME_SITU==danmaku.isSTART){
		danmaku.GAME_SITU=danmaku.isPAUSE;}
		else if(danmaku.GAME_SITU!=danmaku.isDEAD){
			danmaku.GAME_SITU=danmaku.isSTART;
		}
		// TODO: Implement this method
	}
	}
		return false;
	}

	@Override
	protected void onStop()
	{danmaku.GAME_SITU=danmaku.isPAUSE;
		// TODO: Implement this method
		super.onStop();
	}
	
	}
