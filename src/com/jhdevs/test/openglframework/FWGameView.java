package com.jhdevs.test.openglframework;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class FWGameView extends GLSurfaceView {
	private FWGameRenderer renderer;
	
	public FWGameView(Context context) {
		super(context);
		
		renderer = new FWGameRenderer(this);
		
		this.setRenderer(renderer);
		

	}
	

}
