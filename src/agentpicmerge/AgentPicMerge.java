package agentpicmerge;

import java.sql.Time;

import com.sun.tools.javac.util.Convert;

import processing.core.PApplet;
import processing.core.PImage;
// to find the right pixel pixelarrayNr = x+(y*width);
import punktiert.math.*;
import punktiert.physics.VBoid;
import punktiert.physics.VParticle;
import punktiert.physics.VParticleGroup;
import punktiert.physics.VPhysics;


@SuppressWarnings("serial")
public class AgentPicMerge extends PApplet {
	
	PImage canvas, pic1, pic2;
	
	VPhysics physics;
	VParticleGroup groupe01, groupe02;
	
	boolean safeFrame = false;
	boolean drawAgents = false;
	
	float saveIntervallTimer;
	float waitToSave;
	float lastTime;
	String uniqueFolderToSaveTo;
	
	public void setup() 
	{
		size(1222,800);
		frameRate(200);
		smooth();

		saveIntervallTimer = millis();
		waitToSave = 4000;
		background(0);
		uniqueFolderToSaveTo =str(day())+"_"+str(month())+"_"+str(year())+"-"+str(hour())+"_"+str(minute())+"_"+str(second());
		println("Folder to save to" + uniqueFolderToSaveTo+ " in "+waitToSave+" mil s intervalls.");

		canvas = createImage(width, height, RGB);
		pic1 = loadImage("pic03.jpg");
		pic2 = loadImage("pic04.jpg");
		pic1.resize(width, height);
		pic2.resize(width, height);
		pic1.loadPixels();
		pic2.loadPixels();
		
		//punktiert code
		physics = new VPhysics(width, height);
		groupe01 = new VParticleGroup();
		groupe02 = new VParticleGroup();
		
		int amount = 100;

		for (int i = 0; i < amount; i++) 
		{
			Vec pos = new Vec(random(10, width), random(10, height));
			float rad = 5;
			VBoid p = new VBoid(pos);
			p.swarm.setSeperationScale((float) (2));
			p.setRadius(rad);
			physics.addParticle(p);
			//add particles to two diffrent groupes
			if(i < amount/2){
				groupe01.addParticle(p);
			}else{
				groupe02.addParticle(p);
			}
			physics.addGroup(groupe01);
			physics.addGroup(groupe02);
		}
	}

	public void draw() 
	{
		saveIntervallTimer += millis() - lastTime;
		
		background(0);
		physics.update();
		
		//canvas img is going to be manipulated here
		canvas.loadPixels();
		
		noStroke();
		for(VParticleGroup g : physics.groups)
		{
			for (VParticle p : g.particles) 
			{
				float r = .5f; //ellipse radius
				int pixLoc = getPixLoc(round(p.x), round(p.y));
				if(g == groupe01){
					canvas.pixels[pixLoc] = color(pic1.pixels[pixLoc]);
//					fill(color(pic1.pixels[pixLoc]));
//					ellipse(p.x, p.y, r, r);
				}else{
					canvas.pixels[pixLoc] = color(pic2.pixels[pixLoc]);
//					fill(color(pic2.pixels[pixLoc]));
//					ellipse(p.x, p.y, r, r);
				}
			}
		}
		
		canvas.updatePixels();
		image(canvas, 0, 0);
		
		//draw agents as ellipses if 'd' was pressed
		if(drawAgents)
		{
			for (VParticle p : physics.particles)
			{
				ellipse(p.x, p.y, p.getRadius()*2, p.getRadius()*2);
			}
		}
		
		//if key 's' is pressed take a screenshot
		if (safeFrame && saveIntervallTimer > waitToSave)
		{
			thread("saveFrameToDisk");
			saveIntervallTimer = 0;
		}
//		println(frameRate);
		lastTime = millis();
	}
	
	public void saveFrameToDisk()
	{
		saveFrame("Render/"+uniqueFolderToSaveTo + "/test_#####.png");
		println("frameSafed");
	}
	
	int getPixLoc( float x_, float y_ ){
		
		int location = (int)(x_ + y_ * width);
		
		if(location < 0 || location > ((width*height)-1))
		{
			location = 0;
		}
		return location;
	}
	
	
	public void keyPressed(){
		if(key == 'r') setup();
		if(key == 'd') drawAgents = !drawAgents;
		if(key == 's') safeFrame = !safeFrame;
	}
	
	public void keyDown(){
		
	}
	
	
	public static void main(String _args[]) {
		PApplet.main(new String[] {  agentpicmerge.AgentPicMerge.class.getName() });
	}
}
