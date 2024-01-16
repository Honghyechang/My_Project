import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SplitPanel extends JPanel {
	
			private int row,col;
			private HashMap<JLabel,Integer> imgHashMap=null;
			
			//새로운 panel을 만들것이다.
			public SplitPanel(GameFrame gameFrame) {
				

				
				
				row=gameFrame.getRow();
				col=gameFrame.getCol();
				setLayout(new GridLayout(row,col,1,1));
				addMouseListener(new ChangeImageMouseListner());
				
			
				int cut=col*row;
			
				//현재 본인의 위치를 기억할 수 있어여 한다.
				//그 위치와 part N 이 모두 같으면 게임을 이긴다.
				imgHashMap=new HashMap<JLabel, Integer>();
				ImageIcon icons[]=new ImageIcon[cut];
				JLabel labels[]=new JLabel[cut];
				Vector<Integer> vector=new Vector<Integer>();
				
				//이미지를 준비한다.
				for(int i=0; i<cut; i++)
				{
					icons[i]=new ImageIcon("C:/Users/ghddm/Desktop/코딩캠프/Images/part"+Integer.toString(i)+".png");
					labels[i]=new JLabel(icons[i]);
					
				}
				
				//랜덤으로 배치를 할 것이다.
				int count=0;
				while(count<cut)
				{
					int n=(int)(Math.random()*cut);
					
					if(vector.contains(n))
					{
						
						continue;
					}
					//System.out.println(count);
					SplitPanel.this.add(labels[n]);
					vector.add(n);
					//여기서 COUNT는 본인의 위치를 의미한다.
					imgHashMap.put(labels[n], count);
					count++;
					System.out.println("n값은:" + n +"count값 : "+count);
					
				}
		
				
				
			}
			
			//1맞는지 체크하는 클래스
			//2바꾸는 것
			class ChangeImageMouseListner extends MouseAdapter
			{
				

				Point before=null;
				Point after=null;
				JLabel beforeLabel=null;
				JLabel afterLabel=null;
				int beforePosition=-1;
				int afterPosition=-1;
				
				@Override
				public void mousePressed(MouseEvent e) {
					this.before=e.getPoint();
					Container c=(Container)e.getSource();
					beforeLabel=(JLabel)c.getComponentAt(before);
					beforePosition=imgHashMap.get(beforeLabel);
				}
				
				
				@Override
				public void mouseReleased(MouseEvent e) {
					this.after=e.getPoint();
					Container c=(Container)e.getSource();
					afterLabel=(JLabel)c.getComponentAt(after);
					if(afterLabel==null)return;
					afterPosition=imgHashMap.get(afterLabel);
					
					c.add(afterLabel,beforePosition);
					c.add(beforeLabel,afterPosition);
					
					imgHashMap.put(afterLabel,beforePosition);
					imgHashMap.put(beforeLabel,afterPosition);
					
					 c.revalidate();
			         c.repaint();
			         
			         if(answerCheck()==true)
			         {
			        	 JOptionPane.showMessageDialog(null, "축하드립니다! 퍼즐을 완성하였습니다.","성공",JOptionPane.OK_OPTION);
							
			         }
				}
				
				
				
			}
			
			
			//정답이 맞는지
			private boolean answerCheck()
			{
				Set<JLabel> keys=imgHashMap.keySet();
				Iterator<JLabel> it=keys.iterator();
				
				while(it.hasNext())
				{
					JLabel key=it.next();
					int value=imgHashMap.get(key);
					//System.out.println("key : "+key+"value : "+value);
					ImageIcon icon=(ImageIcon)key.getIcon();
					
					System.out.println(icon.toString());
					StringTokenizer sk = new StringTokenizer(icon.toString(), "/");
					int strCount=sk.countTokens();
					System.out.println(strCount);
					String compareStr="";
					for(int i=0; i< strCount; i++)
					{
						
						if(i==strCount-1)
						{
							compareStr=sk.nextToken();
							break;
						}
						sk.nextToken();
						
					}
					//part5
					
					System.out.println("compareStr : "+compareStr);
					int number=-1;
					if(col*row<10)
					{
						 number=Character.getNumericValue(compareStr.charAt(4));
					}
					else if(col*row>10&&col*row<100)
					{
						if(compareStr.substring(4,6).contains("."))
						{
							 number=Character.getNumericValue(compareStr.charAt(4));
						}
						else
						{
							number=Integer.parseInt(compareStr.substring(4,6));	
						}
						
					}
					System.out.println("number : "+number+",value : "+value);
					if(number!=value)
					{
						System.out.println("틀림");
						return false;
					}
					
				}
				System.out.println("맞춤");
				return true;
				
				
			}

			
			
			
}

