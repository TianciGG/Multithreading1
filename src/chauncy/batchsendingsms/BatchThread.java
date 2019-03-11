package chauncy.batchsendingsms;

import java.util.ArrayList;
import java.util.List;

import chauncy.batchsendingsms.entity.UserEntity;
import chauncy.batchsendingsms.util.ListUtils;

class UserThread extends Thread {
	/**
	 * 每个线程分批多少数据
	 */
	private List<UserEntity> listUser;

	public UserThread(List<UserEntity> listUser) {
		this.listUser = listUser;
	}

	@Override
	public void run() {
		for (UserEntity userEntity : listUser) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("name:" + getName() + userEntity.toString());
			// 拿到数据之后调用第三方短信接口
			// 调用短信接口一般不会接收是否发送成功的状态，因为调用短信接口需要转很多接口，需要消耗很多时间，所以在这部分一般会进行一个异步的处理，不需要返回结果。
			// 但是在发送完成后，过了一些时间会收到哪些成功哪些失败的结果，然后系统可以针对返回结果中失败的采用定时任务跑，进行补发。

		}
	}
}

/**
 * @classDesc: 功能描述(多线程分批处理数据)
 * @author: ChauncyWang
 * @createTime: 2019年3月6日 下午2:55:03
 * @version: 1.0
 */
public class BatchThread {
	public static void main(String[] args) {
		// 1.初始化用户
		List<UserEntity> initUser = initUser();
		// 2.定义每一个线程最多跑多少数据
		int userCount = 2;
		// 3.计算线程数，并且计算每个线程跑哪些数据
		List<List<UserEntity>> splitList = ListUtils.splitList(initUser, userCount);
		/**
		 * 针对于真实的10万用户数据就不能使用List集合了，否则会内存溢出
		 * 对于这种情况，每次往数据库查询100条，即创建一个大集合，集合里有很多小集合，然后分段进行发送
		 */
		for (int i = 1; i <= splitList.size(); i++) {
			// 拿到的是每个线程跑多少数据
			List<UserEntity> list = splitList.get(i - 1);
			// 4.让子线程进行分批异步处理数据
			UserThread userThread = new UserThread(list);
			userThread.start();
			// System.out.println("i:"+i+"----"+list.toString());
		}
	}

	/**
	 * @methodDesc: 功能描述(初始化用戶信息)
	 * @author: ChauncyWang
	 * @param: @return
	 * @createTime: 2019年3月6日 下午3:43:18
	 * @returnType: List<UserEntity>
	 */
	public static List<UserEntity> initUser() {
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		for (int i = 1; i <= 11; i++) {
			listUser.add(new UserEntity("userId:" + i, "userName:" + i));
		}
		return listUser;
	}

	/**
	 * 发送短信不需要知道结果，因为很消耗时间，针对于发送失败的部分，可以采用定时任务进行补发。 一般企业发短信流程为：
	 * 需要发送短信的项目组(例如社交项目组)->消息项目组->第三方网关接口
	 * 消息项目组存放每天短信成功失败的消息日志，消息日志不会存放在数据库中，一般存放在redis或者mongodb,
	 * 消息项目组一般会有定时任务去查缓存里哪些是发送失败的，然后把消息进行重新补发。
	 * 在企业级发送短信，一般都会有消息项目组，一般不会让直接调用第三方接口
	 */
}
