package com.jhdevs.test.openglframework;

import com.jhdevs.test.openglframework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class FWMainMenu extends Activity {
    /** Called when the activity is first created. */
	final FWEngine engine = new FWEngine();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FWEngine.context = getApplicationContext();
       
        
        /** Fire up background music */
       FWEngine.musicThread = new Thread(){
        	public void run(){
        		Intent bgmusic = new Intent(getApplicationContext(), FWMusic.class);
        		startService(bgmusic);
  
        	}
        };
        FWEngine.musicThread.start();
        

        
        /** Set menu button options */
        ImageButton start = (ImageButton)findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
        
        start.getBackground().setAlpha(FWEngine.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(FWEngine.HAPTIC_BUTTON_FEEDBACK);
       
        exit.getBackground().setAlpha(FWEngine.MENU_BUTTON_ALPHA); 
        exit.setHapticFeedbackEnabled(FWEngine.HAPTIC_BUTTON_FEEDBACK);
        
        start.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				/** Start Game!!!! */
				Intent game = new Intent(getApplicationContext(),FWGame.class);
				FWMainMenu.this.startActivity(game);

			}
        	
        });
        
        exit.setOnClickListener(new OnClickListener(){ 
			public void onClick(View v) {
				boolean clean = false;
				clean = engine.onExit(v);	
				if (clean)
				{
					int pid= android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				}
			}
        	});
    }

}
