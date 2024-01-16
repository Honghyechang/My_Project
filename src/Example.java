import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


class MyFrame extends JFrame
{
	public MyFrame()
	{
		setSize(500,500);
		setLayout(new GridLayout(3,3));
		
		getContentPane().addMouseListener(new MyMouse());
		for(int i=0; i<9; i++)
		{
			JLabel label=new JLabel(Integer.toString(i));
			//label.addMouseListener(new MyMouse());
			add(label);
		}
		
		setVisible(true);
	}
}


class MyMouse extends MouseAdapter
{
	
	Point before=null;
	Point after=null;
	JLabel beforeLabel=null;
	JLabel afterLabel=null;
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.after=e.getPoint();
		System.out.println("rel -> "+after.x+","+after.y);
		Container c=(Container)e.getSource();
		afterLabel=(JLabel)c.getComponentAt(after);
		System.out.println(afterLabel.getText());
		
		c.add(afterLabel,Integer.parseInt(beforeLabel.getText()));
		c.add(beforeLabel,Integer.parseInt(afterLabel.getText()));
		
		 c.revalidate();
         c.repaint();
		//바꾸기
		
//		con.add(afterLabel,Integer.parseInt(beforeLabel.getText()));
//		con.add(beforeLabel,Integer.parseInt(afterLabel.getText()));
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.before=e.getPoint();
		System.out.println("press -> "+before.x+","+before.y);
		Container c=(Container)e.getSource();
		beforeLabel=(JLabel)c.getComponentAt(before);
		System.out.println(beforeLabel.getText());
		
}
}
	
	
public class Example {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}
}

