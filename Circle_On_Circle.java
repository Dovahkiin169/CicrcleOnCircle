import java.awt.Color; 
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.*; 

import javax.swing.*; 

public class Circle_On_Circle extends JFrame implements ActionListener
 { 
	private static final long serialVersionUID = 1L;
    protected R r; 

    private JMenu[] menu = { new JMenu("Menu")}; 

	private JMenuItem[] items = { new JMenuItem("Opis"), 
								  new JMenuItem("Autor"), 
								  new JMenuItem("Zakącz"), };
	public Circle_On_Circle()
	 { 
		super("Circle_On_Circle"); 
		setSize(400,400); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		r = new R(); 
		r.addKeyListener(r);
		r.addMouseListener(r);
		setContentPane(r); 
		r.setFocusable(true);
		for (int i = 0; i <items.length; i++) 
	items[i].addActionListener(this); 

	menu[0].add(items[0]); 
	menu[0].add(items[1]); 
	menu[0].add(items[2]); 

	JMenuBar menubar = new JMenuBar(); 
	for (int i = 0; i < menu.length; i++) 
	menubar.add(menu[i]); 
	setJMenuBar(menubar); 
	r.addKeyListener(r);
    r.setFocusable(true);
	setContentPane(r);
	setVisible(true); 
} 

public static void main(String[] args)
 { 
	new Circle_On_Circle(); 
 }

@Override
public void actionPerformed(ActionEvent e) 
 {
	Object zrodlo = e.getSource();
	
	if (zrodlo == this.items[0]) 
	{
		JOptionPane.showMessageDialog(null, "Kółko na kółku");
	}

	if (zrodlo == this.items[1]) 
	 {
		JOptionPane.showMessageDialog(null, "Dmytro Verkhovsky");
	 }
	if (zrodlo == this.items[2]) 
	 {
		System.exit(0);
	 }
	
	
	
	}



} 
class R extends JPanel implements Runnable,KeyListener,MouseListener
 { 
	boolean l= true;
	private static final long serialVersionUID = 1L;
	int x = 0, y= 0; 
	float a = 0, b = 0; 
	int r =100; 
	int K=30;
	boolean started = true; 
	
	public void paintComponent(Graphics g)
	 { 
		super.paintComponent(g); 
		double x =  MouseInfo.getPointerInfo().getLocation().getX();
		double y =  MouseInfo.getPointerInfo().getLocation().getY();//0.5 * height;
		double r = 100;
		if(l==true)
		 {
			g.setColor(Color.BLUE);
			g.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));

			x += r * Math.cos(a);
			y += r * Math.sin(a);
			r =0.3*r;
			g.setColor(Color.RED);
			g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
			x += 20 * Math.cos(b);
			y += 20 * Math.sin(b);
			r =0.7*r;
			g.setColor(Color.YELLOW);
			g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		 }
		if(started)
		 { 
			Thread an = new Thread(this); 
			an.start(); 
			started = false;
		 } 
}
@Override 
public void run() 
 { 
	while(true)
	 { 
		a+=0.05;
		b-=0.05;
		repaint(); 
		try 
		 { 
			Thread.sleep(K); 
		 }
		catch (InterruptedException e) 
		 { 
			e.printStackTrace(); 
		 } 
	 } 
 }

@Override
public void keyTyped(KeyEvent e) 
 {
	switch (e.getKeyChar()) 
   	 {
	case 'r':
		 K+=10;
		
		break;
	case 'k':
		K=K-10;
		if(K<=0)
		{
			K=5;
		}
		
		break;
	}
}

@Override
public void keyPressed(KeyEvent e) 
{

	switch(e.getKeyCode())
	{
	 case KeyEvent.VK_LEFT:
	   K=K+11;
	
		break;
	 case KeyEvent.VK_RIGHT:
	   K=K-11;
	   if(K<=0)
		{
			K=1;
		}
	
		break;
	}
}

@Override
public void keyReleased(KeyEvent e) {} 
@Override
public void mouseClicked(MouseEvent e) {}

@Override
public void mousePressed(MouseEvent e) {}

@Override
public void mouseReleased(MouseEvent e) {}

@Override
public void mouseEntered(MouseEvent e) 
 {
	l=true;	
	repaint();
 }

@Override
public void mouseExited(MouseEvent e) 
 {
	l=false;	
	repaint();
 }
}
