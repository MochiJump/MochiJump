


public class HairClipNPCNoCollide extends NoCollideCharacter {

	public HairClipNPCNoCollide (DogLogic dl) {
		super (dl);
	}
  
  public HairClipNPC (DogLogic dl) {
		super (dl);
	}
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new StandardCollision();
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	

}
