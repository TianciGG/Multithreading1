package chauncy.thread;

class DemoThread extends Thread {
	
	/**
	 * 在run方法中不能抛出异常，只能trycatch
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			//sleep 传的毫秒数
			try {
				//sleep作用是让当前线程从运行状态变成休眠状态，如果时间到期会到运行状态。
				//sleep不能释放锁，多线程之间实现同步 wait可以释放锁
				Thread.sleep(1000);
				//获取到线程的ID，这个ID是多线程随机分配不重复的主键
				//不用关心Id是从哪里来的，是JVM底层实现，只需要了解getId是作为多线程区分用
				//getId使用场景：使用多线程并且需要打日志一定要把getId打印出来进行区分不同的多线程
			} catch (InterruptedException e) {
			}
			//getId和getName方法是从Thread类里来的，只有继承Thread类才能使用该方法。
			System.out.println("id():"+getId()+"----name:"+getName()+"----i:" + i);
		}
	}
}

/**
 * @classDesc: 功能描述()
 * @author: ChauncyWang
 * @createTime: 2019年3月6日 上午11:24:03
 * @version: 1.0
 */
public class ThreadDemo05 {
	public static void main(String[] args) {
		DemoThread demoThread=new DemoThread();
		//自定义线程名称
		demoThread.setName("线程①");
		demoThread.start();
		DemoThread demoThread2=new DemoThread();
		demoThread2.setName("线程二");
		demoThread2.start();
	}
}
