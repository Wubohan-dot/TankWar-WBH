import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tank {
	private int x,y,style=0;
	private int imgX;
	private int imgY;
	//private Image img;
	//public File f;
	private int dir=0;
	private int frameCount=0;
	
	
	public Tank(int _x,int _y) {
		x=_x;
		y=_y;
		Calculate();
	}
	public Tank(int _x,int _y,int _style,int _dir) {
		this(_x,_y);
		style=_style;
		dir=_dir;
		Calculate();
	}
	public void setStyle(int t) {
		style=t;
		Calculate();
	}
	private void Calculate() {
		imgX=style*68*4+dir*68;
		imgY=style/4*34;
		if(imgY==34) {
			imgX-=16*68;
		}
				
	}
	public void setDir(int t) {dir=t;Calculate();}
	public int getDir() {return dir;}
	
	public void setX(int _x) {x=_x;}
	public int getX() {return x;}
	
	public void setY(int _y) {y=_y;}
	public int getY() {return y;}
	
	//�ƶ���ÿ��һ��
	public void move() 
	{ 
		switch(dir) 
		{
			case 0: y-=1; break;
			case 1: x+=1; break;
			case 2: y+=1; break;
			case 3: x-=1; break;
		}
	}
	

	
	//���ÿ�ͼ����g������
	public void paint(Graphics g) {
		
		frameCount++;
		/*
		if(frameCount%2==0) {
			g.drawImage(ConVal.IMG, x, y,x+34, y+34, 34*(2*dir), style*34, 34*(2*dir+1), (style+1)*34,null);//the last is null?
		}
		else {
			g.drawImage(ConVal.IMG, x, y,x+34, y+34, 34*(2*dir+1), style*34, 34*(2*dir+2), (style+1)*34,null);
		}
		*/
		if(frameCount%2==0) {
			g.drawImage(ConVal.IMG, x, y,x+34, y+34, imgX, imgY, imgX+34, imgY+34,null);//the last is null?
		}
		else {
			g.drawImage(ConVal.IMG, x, y,x+34, y+34, imgX+34, imgY, imgX+68, imgY+34,null);
		}

	}
	
	
	
	
}
