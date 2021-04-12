

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MyFrameTank extends JFrame {
	
	
	//˫���弼��������Ƶ��  
    private Image offScreenImage = null;      //����һ���µ�Image���󣬼��ڶ�����
    private Graphics gOffScreen = null;
    private int n=7;
    private ArrayList<SpiritTank> enemyTank=new ArrayList<SpiritTank>();
    private PlayerTank myTank;
    
	public MyFrameTank(String string) {
		// TODO Auto-generated constructor stub
		super("λ�ƶ���");
		//setBackground(Color.pink);
		setSize(ConVal.WEDTH,ConVal.HEIGHT);//���ô���Ŀ�͸�
		
		myTank = new PlayerTank(50,50,1,2); //0��̹�˿���ͨ����������������
		//myTank.setStyle(1);     //frome 0
		//myTank.setDir(2);		//0 for shang,1 for you,2 forxia,3 forzuo
		
		//Iterator<Tank> it=enemyTank.iterator();
		for(int i=0;i<n;i++) {
			enemyTank.add(new SpiritTank(100+i*50,100,2,2));
			System.out.print("chuangzhao");
		}
		
//		enemyTank[0] = new Tank(100,100);//1��̹��ÿ��0.5�����ת�䷽�򣬲�һֱ�ƶ�
//		enemyTank[0].setStyle(0);     
//		enemyTank[0].setDir(2);	
//		
//		enemyTank[1] = new Tank(150,150);//1��̹��ÿ��0.5�����ת�䷽�򣬲�һֱ�ƶ�
//		enemyTank[1].setStyle(2);     
//		enemyTank[1].setDir(2);	
		
		this.addKeyListener(myTank);
		
		//���ô���ر���Ϊ�����û��������Ĺر�ͼ��ʱ����������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setVisible(true);//���ô���ɼ�
		
		Thread multiThreadEnemy=new Thread(new EnemyRunnable(enemyTank));
		multiThreadEnemy.start();
		
		for(int i=0;;i++) {			
			myTank.move();  //tank 0 moves
			
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
        super.paint(gOffScreen); 
        //�����Ļ        
        //Color c = gOffScreen.getColor();  
        gOffScreen.setColor(Color.pink);  
        gOffScreen.fillRect(0, 0, ConVal.WEDTH,ConVal.HEIGHT);  
        //gOffScreen.setColor(c);
        // ���ø�����ػ淽������ֹ�ٴ���ײ����ػ�
        
        //����������Ϸ����	      
        	myTank.paint(gOffScreen);  	
        	//Iterator<Tank> it=enemyTank.iterator();
         	for(int j=0;j<enemyTank.size();j++) {
        		enemyTank.get(j).paint(gOffScreen);
        	}
        //���ڶ�����е�����һ����ȫ�����Ƶ���Ļ
        g.drawImage(offScreenImage, 0, 0, null);

		
	}


}
