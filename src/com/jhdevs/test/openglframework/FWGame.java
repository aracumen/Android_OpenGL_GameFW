package com.jhdevs.test.openglframework;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class FWGame extends Activity {
	final FWEngine gameEngine = new FWEngine();
	private FWGameView gameView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FWGameView(this);
        setContentView(gameView);
    }
    @Override
    protected void onResume() {
       super.onResume();
       gameView.onResume();
    }

    @Override
    protected void onPause() {
       super.onPause();
       gameView.onPause();
    }

   	@Override
   	public boolean onTouchEvent(MotionEvent event) {
   		//
   		float x = event.getX();
        float y = event.getY();
        int height = FWEngine.display.getHeight() / 4;
        int playableArea = FWEngine.display.getHeight() - height;
        
        //Touch

		return false;
    }
	
}
