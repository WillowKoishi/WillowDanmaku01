package willow.danmaku01;
import android.support.v7.app.*;
import android.os.*;
import willow.danmaku01.view.*;
import android.view.View.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity
{
MainMenu mm;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mm=(MainMenu)this.findViewById(R.id.mm);
		h.postDelayed(r,1);
//		mm.setOnTouchListener(new OnTouchListener(){
//
//				@Override
//				public boolean onTouch(View p1, MotionEvent p2)
//				{
//					if(p2.getY()>=mm.getHeight()/7f*3-30&&
//					   p2.getY()<=mm.getHeight()/7f*3+30){

//					}
//					return false;
//				}
//			});
	}

	@Override
	protected void onDestroy()
	{System.exit(0);
		// TODO: Implement this method
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO: Implement this method
		if(keyCode==KeyEvent.KEYCODE_BACK&&!mm.loading){
			if(mm.mainMenuType==mm.lastMenuType){
			if(mm.mainMenuType==mm.START_CHOOSE_RANK){
				mm.mainMenuType=mm.MAINMENU;
			}
		}
		}
		return true;
	}
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}

	};

	Runnable r=new Runnable(){

		@Override
		public void run(){
			if(mm.chooseRank){
		startActivity(new Intent(MainActivity.this,Start.class));
		mm.chooseRank=false;
		}
			h.postDelayed(r,1000);
		}
	};
	@Override
	protected void onStop()
	{mm.loading=false;
		mm.loadAlpha=1;		
		// TODO: Implement this method
		super.onStop();
	}
	
}
