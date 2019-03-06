package chauncy.thread;

class CreateRunnable implements Runnable{

	/**
	 * run方法执行线程需要执行的任务、代码
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("run() i:" + i);
		}
	}
	
}

/**   
* @classDesc: 功能描述(创建多线程 方式2：实现Runnable接口，重写run方法)  
* @author: ChauncyWang
* @createTime: 2019年3月6日 上午11:01:01   
* @version: 1.0  
*/  
public class ThreadDemo03 {
	public static void main(String[] args) {
		// 定义一个类，实现Runnable接口，重写run方法
		System.out.println("创建线程开始！main");
		CreateRunnable createRunnable=new CreateRunnable();
		Thread thread=new Thread(createRunnable);
		thread.start();
		System.out.println("线程已经启动！main");
		for (int i = 0; i < 100; i++) {
			System.out.println("main() i:" + i);
		}
	}
}
