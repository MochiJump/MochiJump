package com.MochiJump;

public class StandardJump implements JumpInterface{

	
	public void jump(Mochi Implementer){
		
		if (Implementer.jumpChu == true && Implementer.jTime > 0) {
			Implementer.jTime++;
			if (Implementer.jTime <= 18) {
				Implementer.y -= 6;
				Implementer.uJump = true;
				if (Implementer.mRunR == true || Implementer.mRestR == true) {
					Implementer.setActionToFalse();
					Implementer.mJumpR = true;	
				}
				if (Implementer.mRunL == true || Implementer.mRestL == true) {
					Implementer.setActionToFalse();
					Implementer.mJumpL = true;
				}
			}
			if (Implementer.jTime>18 && Implementer.jTime<= 20) {
				Implementer.y -= 6;
				Implementer.uJump = false;
				Implementer.midJump = true;
				if (Implementer.mRunR == true || Implementer.mRestR == true) {
					Implementer.setActionToFalse();
					Implementer.mJumpR = true;	
				}
				if (Implementer.mRunL == true || Implementer.mRestL == true) {
					Implementer.setActionToFalse();
					Implementer.mJumpL = true;
				}
			}
			if (Implementer.jTime < 25) {
				Implementer.midJump = true;
			}
			if (Implementer.jTime > 25) {	
				Implementer.jTime =0;
				Implementer.midJump = false;
			}
		}
	}
	

	

}
