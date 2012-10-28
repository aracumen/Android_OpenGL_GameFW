package com.jhdevs.test.openglframework;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;

public class FWGameRenderer implements Renderer{
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0 ;
	
	private GLSurfaceView glSurfaceView;
	private FloatBuffer vertices;
    private int width;
    private int height;
    
    public FWGameRenderer(GLSurfaceView glSurfaceView) {
    	this.glSurfaceView = glSurfaceView;
    }
	
	public void onDrawFrame(GL10 gl) {
		loopStart = System.currentTimeMillis();
		try {
			if (loopRunTime < FWEngine.GAME_THREAD_FPS_SLEEP){
				Thread.sleep(FWEngine.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glColor4f(0.64313725490196f, 0.77647058823529f, 0.22352941176471f, 1);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertices);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		 
	    loopEnd = System.currentTimeMillis();
	    loopRunTime = ((loopEnd - loopStart));
    
	}
	/*
	private void scrollBackground1(GL10 gl){
		if (bgScroll1 == Float.MAX_VALUE){
			bgScroll1 = 0f;
		}
	
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glPushMatrix();
	    gl.glScalef(1f, 1f, 1f);
	    gl.glTranslatef(0f, 0f, 0f);
    
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
	    gl.glTranslatef(0.0f,bgScroll1, 0.0f); 
	   
	    background.draw(gl);
	    gl.glPopMatrix();
	    bgScroll1 +=  SFEngine.SCROLL_BACKGROUND_1;
	    gl.glLoadIdentity();
	}
	*/

	public void onSurfaceChanged(GL10 gl, int width, int height) {		
		
		
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//init
		width = glSurfaceView.getWidth();
        height = glSurfaceView.getHeight();
         
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, 1, 0, 1, 1, -1);
        gl.glClearColor(1, 1, 1, 1);
         
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2 * 3 * Float.SIZE / 8);
        byteBuffer.order(ByteOrder.nativeOrder());
         
        vertices = byteBuffer.asFloatBuffer();
         
        vertices.put(new float[]
                { 
                    0.5f, 0.5f,
                    0.55f, 0.55f,
                    0.6f, 0.5f
                }
        );
         
        vertices.flip();
         
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
}
