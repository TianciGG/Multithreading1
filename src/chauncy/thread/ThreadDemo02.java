package chauncy.thread;

class CreateThread extends Thread{
	/**
	 * run方法执行 需要线程执行的任务、代码。
	 */
	public void run(){
		for (int i = 0; i < 20; i++) {
			System.out.println("run() i:"+i);
		}
	}
}

/**
 * @classDesc: 功能描述:(创建多线程 方式1：继承Thread类，重写run方法)
 * @author: ChauncyWang
 * @createTime: 2019年3月5日 下午11:48:52
 * @verssion: v1.0
 */
public class ThreadDemo02 {
	public static void main(String[] args) {
		System.out.println("创建线程开始 main");
		//1.定义一个类继承自Thread类，重写run方法
		//2.启动线程
		CreateThread createThread=new CreateThread();
		//启动一个线程是调用start方法 不是run方法，调用run方法相当于主线程执行。
		//注意：使用多线程情况下，代码不会从上往下进行执行，会同时并行执行，要分析主线程子线程顺序。
		createThread.start();
		System.out.println("线程已经开始启动 main");
		for (int i = 0; i < 20; i++) {
			System.out.println("main() i:"+i);
		}
	}
}
