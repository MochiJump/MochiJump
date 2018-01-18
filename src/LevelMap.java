import java.awt.*;
import java.util.ArrayList;

public class LevelMap {
// I want to use this to set up boundaries for Mochi, what he can stand on and what he will run into, etc,
// in order to do this I need to first define the correct variables and then create the constructors for it.
// I'll use floor and wall x's and y's to do this.

	int x, y, width, height;
// I'm trying to create an object for the platform and then create a list to contain it;
// this thing below here is a setter, but I have it setup as a constructor. I don't think this is right.
	public LevelMap (int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
	}
	// hmm is this a good constructor for this class? Will I be able to access the arraylist inside of it to use with it
	// in another class? Might be worth trying to use it in the testclass when I get home and have access to my IDE
	public LevelMap (ArrayList array) {
		for (Rectangle next: platlist) {
			Rectangle p1 = next.getBounds();
		}
	}
	public LevelMap () {
		
	}

//	static Rectangle plat1 = new Rectangle (500, 0, 1000, 500);
//	static Rectangle plat2 = new Rectangle (500-35, 250, 100, 10);
//	static Rectangle plat3 = new Rectangle (500-70, 120, 100, 10);
	
	ArrayList<Rectangle> platlist = new ArrayList<>();
// let's see if I can turn this into something better also let's try to avoid static objects and classes if possible.
	
	private void addPlat (int x, int y, int width, int height){
	platlist.add (new Rectangle (x,y,width,height);	
	}
	// does this need to be inside a method like private void PlatSetup (){...}	      
	addPlat (500, 0, 1000, 500);
	addPlat (500 - 35, 250, 100, 10);
	addPlat (500 - 70, 120, 100, 10);
	addPlat (500-35*3, 250, 100, 10);
	
	// this getter needs to be tested, I think there something that is trying to call on this in mochi class but it now
	// has a different name.
	private void getPlat(){
		return this.platlist;
	}

		      
//	static {
//	platlist.add(new Rectangle (plat1));
//	platlist.add(new Rectangle (plat2));
//	platlist.add(new Rectangle (plat3));
//	platlist.add(new Rectangle (500-35*3, 250, 100, 10));
//	}

// Maybe I should move the below to the animate class. Have one class to bring graphical reality to things?
// at the very least this method needs to be used.	
	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		for (Rectangle next: platlist) {
			// added fillRect
			g2.fillRect(next);
			g2.draw(next);
			
			}
	}
}
		
		//okay so you have to use the Graphics 2D to draw this properly. would love to know how to do this correctly
		// doing something like I tried below:	
		//g.drawRect(platlist.get(x), platlist.get(y), platlist.get(width), platlist.get(height));	
