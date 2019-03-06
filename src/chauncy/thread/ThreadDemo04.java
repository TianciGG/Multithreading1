package chauncy.thread;

/**   
* @classDesc: 功能描述(创建多线程 方式3：使用匿名内部类方式创建多线程)  
* @author: ChauncyWang
* @createTime: 2019年3月6日 上午11:15:16   
* @version: 1.0  
*/  
public class ThreadDemo04 {
	public static void main(String[] args) {
		System.out.println("创建线程成功！");
		//使用匿名内部类方式创建线程
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("run() i:" + i);
				}
			}
		}).start();
		System.out.println("创建线程结束！");
		//thread.start();
		for (int i = 0; i < 100; i++) {
			System.out.println("main() i:" + i);
		}
	}
}
