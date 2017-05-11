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
		if(keyCode==KeyEvent.KEYCODE_BACK&&!mm.loading&&event.getAction()==event.ACTION_DOWN){
			if(mm.mainMenuType==mm.lastMenuType){
			if(mm.mainMenuType==mm.START_CHOOSE_RANK){
				mm.mainMenuType=mm.MAINMENU;
			}
			if(mm.mainMenuType==mm.MAINMENU&&mm.lastMenuType==mm.MAINMENU){
				mm.antiReShow();
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
			if(mm.chooseRank&&mm.loading){
		Intent intent= new Intent(MainActivity.this,Start.class);
		intent.putExtra("rank",mm.rank);
		startActivity(intent);
		mm.chooseRank=false;
		}
		if(mm.antiReShow&&mm.reShowMove<-mm.getHeight()){
			finish();
		}
			h.postDelayed(r,10);
		}
	};
	@Override
	protected void onStop()
	{mm.loading=false;
		mm.loadAlpha=1;		
		// TODO: Implement this method
		super.onStop();
	}

	@Override
	protected void onResume()
	{mm.reShow();
		// TODO: Implement this method
		super.onResume();
	}

	@Override
	protected void onStart()
	{mm.reShow();
		// TODO: Implement this method
		super.onStart();
	}
	
}
