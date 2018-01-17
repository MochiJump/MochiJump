import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestClass extends JPanel {
	
	
	public TestClass () {
		Mochi mochi = new Mochi();
		JPanel testPain = new JPanel();
		float x = mochi.getX();
		JLabel status = new JLabel(Float.toString(x));
		testPain.add(status, BorderLayout.NORTH);
	}
	
}
