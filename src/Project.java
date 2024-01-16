import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GameFrame extends JFrame{
	
	private int imgWidth,imgHeight;
	private Container contentPane;
	private int row=-1;//가로선
	private int col=-1;//세로선
	private ImageIcon imgIcon;
	public GameFrame()
	{
		String imgPath="a.png";
		imgIcon=new ImageIcon(imgPath);
		try {
			imgWidth=getImageWidth(imgPath);
			imgHeight=getImageHeight(imgPath);
		} catch (IOException e) {
			System.out.println("이미지가 없습니다.");
		}
		setSize(imgWidth,imgHeight);
		setTitle("그림 맞추기 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(imgWidth+","+imgHeight);
		JLabel imgLabel=new JLabel(imgIcon);
		contentPane=getContentPane();
		contentPane.add(imgLabel);
		createMenu();
		setVisible(true);
	}
	
	private void createMenu()
	{
		JMenuBar mb=new JMenuBar();
		JMenu menu=new JMenu("쪼개기");
		JMenuItem []menuItem=new JMenuItem[3];
		
		//메뉴 아이템 설정
		menuItem[0]=new JMenuItem("3X3");
		menuItem[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				col=3;
				row=3;
				System.out.println("col : "+col+" row : "+row);
				splitImage();//사진저장
				SplitPanel sp=new SplitPanel(GameFrame.this);//패널바꾸기
				GameFrame.this.setContentPane(sp);
				GameFrame.this.revalidate();
				GameFrame.this.repaint();
			}
		});
		
		menuItem[1]=new JMenuItem("4X4");
		menuItem[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				row=4;
				col=4;
				System.out.println("col : "+col+" row : "+row);
				splitImage();//사진저장
				SplitPanel sp=new SplitPanel(GameFrame.this);//패널바꾸기
				GameFrame.this.setContentPane(sp);
				GameFrame.this.revalidate();
				GameFrame.this.repaint();
				
			}
		});
		menuItem[2]=new JMenuItem("직접 설정");
		menuItem[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InputDialog inputDialog=new InputDialog(GameFrame.this, "직접 설정");
				
			}
		});
		
		menu.add(menuItem[0]);
		menu.add(menuItem[1]);
		menu.add(menuItem[2]);
		mb.add(menu);
		
		GameFrame.this.setJMenuBar(mb);
	}
	class InputDialog extends JDialog
	{
		
		public InputDialog(JFrame frame, String title)
		{
			super(frame,title);
			setSize(300,300);
			setLayout(new GridLayout(3,2));
			
			
			JTextField colAnswer=new JTextField(10);
			JTextField rowAnswer=new JTextField(10);
			JButton setButton=new JButton("설정");
			setButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String colString=colAnswer.getText();
					String rowString=rowAnswer.getText();
					
					if(colString.equals("")||rowString.equals(""))
					{
						JOptionPane.showMessageDialog(null, "빈칸을 채워주세요","빈칸 에러",JOptionPane.ERROR_MESSAGE);
						return;
					}
					col=Integer.parseInt(colString);
					row=Integer.parseInt(rowString);
					System.out.println("col : "+col+" row : "+row);
					setVisible(false);
					
					splitImage();//사진저장
					SplitPanel sp=new SplitPanel(GameFrame.this);//패널바꾸기
					GameFrame.this.setContentPane(sp);
					GameFrame.this.revalidate();
					GameFrame.this.repaint();
				}
			});
			
			add(new JLabel("세로 줄 : "));
			add(colAnswer);
			add(new JLabel("가로 줄 : "));
			add(rowAnswer);
			add(new JLabel(""));
			add(setButton);
			
			
			setVisible(true);
			
			
		}
	}
	
	private  int getImageWidth(String path) throws IOException
	{
		BufferedImage image = ImageIO.read(new File(path));
		return image.getWidth();
	}
	
	private static int getImageHeight(String path) throws IOException
	{
		BufferedImage image = ImageIO.read(new File(path));
		return image.getHeight();
	}
	
	//이미지를 row,col에 맞게 쪼개기
	private void splitImage()
	{
		// icon -> buffer로 바꾼다.
		Image img=imgIcon.getImage();
		BufferedImage originalImage=new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	
		// 이미지를 복사한다.
		Graphics g = originalImage.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		
		//getSubimage이미지를 통해 나눈다.
		if(row<=0||col<=0)
		{
			return;
		}
		int number=0;
		int width=imgWidth/col;
		int height=imgHeight/row;
		for(int i=0; i<row; i++ )
		{
			for(int j=0; j<col; j++)
			{
				BufferedImage subImg=originalImage.getSubimage(j*width, i*height, width, height);

			   
			    
				//buffer-> icon으로 바꿔서 저장한다.
				try {
					ImageIO.write(subImg, "png", new File("C:/Users/ghddm/Desktop/코딩캠프/Images/part"+Integer.toString(number)+".png"));
					System.out.println("C:/Users/ghddm/Desktop/코딩캠프/Images/part"+Integer.toString(number)+".png"+"저장완료");
				} catch (IOException e) {
					return;
				}
				number++;
			}
		}
		//number--;
		System.out.println("총 : "+number+"개의 사진으로 쪼갰습니다.");
	}
	
	
	
	
	public int getCol()
	{
		return this.col;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	
	
}

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();

	}

}
