import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

//����ʼ����һϵ�е���̹�˴��룬ʹ֮�ı䷽���ƶ�

public class EnemyRunnable implements Runnable{

	private ArrayList<SpiritTank> t;
	//private int num;
	public EnemyRunnable(ArrayList<SpiritTank> _t){
		t=_t; 
	}
		
		@Override
		public void run() {
			int createTime=0;
			// TODO Auto-generated method stub
//			//Iterator<Tank> its=t.iterator();
//			for(int i=0;;i++) {			
//				for(int j=0;j<t.size();j++) {
//					
//					
//					//if(i%5==0) t.get(j).randomTurn();  //each 0.5 second, tank 1 change its direction randomly
//				t.get(j).randomMove();   //tank 1 moves
//				}
//				
//				//repaint();
//				
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			Random rd=new Random();
			SpiritTank atank;
			while(true) {
				try {
					Thread.sleep(100);
					for(SpiritTank tank:t) {
						tank.randomMove();
					}
					createTime++;
					if(createTime>=10) {
						if(rd.nextInt(2)==1) {
							int pt=rd.nextInt(3);
							atank=new SpiritTank(MyFrameTank.TankPlant[pt][0],MyFrameTank.TankPlant[pt][1],4,2);
							t.add(atank);
						}
						createTime=0;
					}
				}catch(InterruptedException e) {
					System.out.println(e.toString());
				}
			}
			
		}
}