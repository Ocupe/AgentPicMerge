package agentpicmerge;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
// to find the right pixel pixelarrayNr = x+(y*width);
import toxi.geom.Vec2D;
import punktiert.math.*;
import punktiert.physics.VBoid;
import punktiert.physics.VParticle;
import punktiert.physics.VParticleGroup;
import punktiert.physics.VPhysics;


public class AgentPicMerge extends PApplet {
	
	PImage canvas, pic1, pic2;
	
	VPhysics physics;
	VParticleGroup groupe01, gourpe02;
	
	public void setup() 
	{
		size(500,500);

		canvas = createImage(500, 500, RGB);
		pic1 = loadImage("pic01.jpg");
		pic2 = loadImage("pic02.jpg");
		pic1.resize(width, height);
		pic2.resize(width, height);
		pic1.loadPixels();
		pic2.loadPixels();
		
		//punktiert code
		physics = new VPhysics(width, height);
		
		int amount = 10;

		for (int i = 0; i < amount; i++) 
		{
			Vec pos = new Vec(random(10, width), random(10, height));
			float rad = random(3, 6);
			VBoid p = new VBoid(pos);
			p.swarm.setSeperationScale((float) (rad*.7));
			p.setRadius(rad);
			physics.addParticle(p);
//			if(i < amount/2){
//				groupe01.addParticle(p);
//			}else{
//				gourpe02.addParticle(p);
//			}
//			
//			physics.addGroup(groupe01);
//			physics.addGroup(gourpe02);
		}
		  
		  
	}

	public void draw() 
	{
		background(0);

		physics.update();
		

		canvas.loadPixels();
		
		if(canvas.loaded)
		{

			for (VParticle p : physics.particles) 
			{
				int pixLoc1 = getPixLoc(round(p.x), round(p.y));
				canvas.pixels[pixLoc1] = color(pic1.pixels[pixLoc1]);
			}

			//Update canvas pixel
			canvas.updatePixels();

			//display canvas
			image(canvas, 0, 0);

			for (VParticle p : physics.particles) 
			{
				ellipse(p.x, p.y, p.getRadius()*2, p.getRadius()*2);
			}
		}else{
			println("not yet loaded");
		}

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
		
		
		
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] {  agentpicmerge.AgentPicMerge.class.getName() });
	}
}
