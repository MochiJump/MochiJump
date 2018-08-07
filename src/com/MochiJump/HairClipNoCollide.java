


public class HairClipNoCollide extends NoCollideCharacter {

	public HairClipNoCollide (DogLogic dl) {
		super (dl);
	}
  
  public HairClipNoCollide (DogLogic dl) {
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
