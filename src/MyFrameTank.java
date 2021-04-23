

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MyFrameTank extends JFrame {
	
	
	//˫���弼��������Ƶ��  
    private Image offScreenImage = null;      //����һ���µ�Image���󣬼��ڶ�����
    private Graphics gOffScreen = null;
    private int n=7;
    private Vector<SpiritTank> enemyTank=new Vector<SpiritTank>();
    private PlayerTank myTank;
    static public int[][] TankPlant= {{100,100},{350,100},{600,100}};
    private Vector<Bullet> bullets=new Vector<Bullet>();
    
    
	public MyFrameTank(String string) {
		// TODO Auto-generated constructor stub
		super("λ�ƶ���");
		//setBackground(Color.pink);
		setSize(ConVal.WEDTH,ConVal.HEIGHT);//���ô���Ŀ�͸�
		
		myTank = new PlayerTank(50,50,2,2,bullets); //0��̹�˿���ͨ����������������
		myTank.setV(2);
	
		
		this.addKeyListener(myTank);
		
		//���ô���ر���Ϊ�����û��������Ĺر�ͼ��ʱ����������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setVisible(true);//���ô���ɼ�

		Random rd=new Random();
		int createTime=0;
		int shotTime=rd.nextInt(15);
		
		for(int i=0;;i++) {			
			myTank.move();  //tank 0 moves
			
			for(SpiritTank tank:enemyTank) {
				tank.randomMove();
			}
			createTime++;
			
			shotTime++;
			if(shotTime>30) {
				for(SpiritTank tank:enemyTank) {
					if(rd.nextInt(3)==1) {
						Bullet bullet=tank.fire();
						bullet.setIfMy(false);
						bullets.add(bullet);
					}
					
						
				}
				shotTime=0;
			}
			
			
			if(createTime>=20) {
				if(rd.nextInt(2)==1) {
					int pt=rd.nextInt(3);
					SpiritTank atank=new SpiritTank(MyFrameTank.TankPlant[pt][0],MyFrameTank.TankPlant[pt][1],4,2,bullets);
					enemyTank.add(atank);
				}
				createTime=0;
			}
			for(int i2=0;i2<bullets.size();i2++) if(bullets.get(i2).checkNo()) {bullets.removeElementAt(i2);}
			for(int i2=0;i2<bullets.size();i2++) {
				
				bullets.get(i2).move();
				for(int i1=0;i1<enemyTank.size();i1++) {
					if(bullets.get(i2).getIfMy()&&bullets.get(i2).getX()<enemyTank.get(i1).getX()+17&&bullets.get(i2).getX()>enemyTank.get(i1).getX()-17&&bullets.get(i2).getY()<enemyTank.get(i1).getY()+17&&bullets.get(i2).getY()>enemyTank.get(i1).getY()-17) {
						bullets.get(i2).explode();
						enemyTank.removeElementAt(i1);
						//bullets.remove(b);
					}
				}
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	//paint���������ػ����Զ�����,�ڴ˺�����ʵ��˫�������  
	public void paint(Graphics g){
		//��һ��ʹ��offScreenImageʱ�����������ڶ����棬�����Ļ�������ȫ�������ڵڶ�������
        if(offScreenImage == null) {  
        	//��ȡ��������λ�õ�ͼƬ
            offScreenImage = this.createImage(ConVal.WEDTH,ConVal.HEIGHT); 
            //��ý�ȡͼƬ�Ļ���
            gOffScreen = offScreenImage.getGraphics();  
        }
        
        
        
        //�����Ļ        
        //Color c = gOffScreen.getColor();  
        //super.paint(gOffScreen); 
        super.paint(gOffScreen); 
        gOffScreen.setColor(Color.pink);  
        gOffScreen.fillRect(0, 0, ConVal.WEDTH,ConVal.HEIGHT);  
        //gOffScreen.setColor(c);
        // ���ø�����ػ淽������ֹ�ٴ���ײ����ػ�
        
        
        gOffScreen.setColor(Color.white);
        
        for(int i=0;i<3;i++) {
        	
        	//gOffScreen.drawRect(TankPlant[i][0], TankPlant[i][1], 34, 34);
        	gOffScreen.drawImage(ConVal.IMG,TankPlant[i][0], TankPlant[i][1],TankPlant[i][0]+34, TankPlant[i][1]+34,34*4,34*7,34*5,34*8,null);
        }
        
        
        //����������Ϸ����	      
        	myTank.paint(gOffScreen);  	
        	//Iterator<Tank> it=enemyTank.iterator();
         	for(int j=0;j<enemyTank.size();j++) {
        		enemyTank.get(j).paint(gOffScreen);
        	}
         	for(Bullet b:bullets) {
				b.paint(gOffScreen);
			}
        //���ڶ�����е�����һ����ȫ�����Ƶ���Ļ
        g.drawImage(offScreenImage, 0, 0, null);

		
	}


}
