import java.awt.*;
import java.util.ArrayList;

public class LevelMap {
// I want to use this to set up boundaries for Mochi, what he can stand on and what he will run into, etc,
// in order to do this I need to first define the correct variables and then create the constructors for it.
// I'll use floor and wall x's and y's to do this.

	int x, y, width, height;
// I'm trying to create an object for the platform and then create a list to contain it;	
	public LevelMap (int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
	}
	
	public LevelMap (ArrayList array) {
		for (Rectangle next: platlist) {
			Rectangle p1 = next.getBounds();
		}
	}
	public LevelMap () {
		
	}

	static Rectangle plat1 = new Rectangle (500, 0, 1000, 500);
	static Rectangle plat2 = new Rectangle (500-35, 250, 100, 10);
	static Rectangle plat3 = new Rectangle (500-70, 120, 100, 10);
	
	static ArrayList<Rectangle> platlist = new ArrayList<>();
// I previously had this set to static before the {, however removing that also allows me to keep this code.
// I put it back, the new items added need to be static
	static {
	platlist.add(new Rectangle (plat1));
	platlist.add(new Rectangle (plat2));
	platlist.add(new Rectangle (plat3));
	platlist.add(new Rectangle (500-35*3, 250, 100, 10));
	
	}
// Maybe I should move the below to the animate class. Have one class to bring graphical reality to things?
	
	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		for (Rectangle next: platlist) {
			g2.draw(next);
			
			}
	}
}
		
		//okay so you have to use the Graphics 2D to draw this properly. would love to know how to do this correctly
		// doing something like I tried below:	
		//g.drawRect(platlist.get(x), platlist.get(y), platlist.get(width), platlist.get(height));
		// I think I used a getter instead of a setter below: *****

	