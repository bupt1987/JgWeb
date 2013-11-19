package client;

import java.util.ArrayList;
import java.util.List;

import com.zhaidaosi.game.jgframework.common.queue.BaseQueue;
import com.zhaidaosi.game.jgframework.common.queue.BaseQueueElement;

public class TestQueue {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		BaseQueue<Integer> queue = new BaseQueue<Integer>();
		List<BaseQueueElement<Integer>> list = new ArrayList<BaseQueueElement<Integer>>();
		for (int i = 0; i < 10000000; i++) {
			list.add(queue.put(i));
		}
//		int size = list.size() - 1;
//		for (int i = size; i >= 0; i--) {
//			queue.remove(list.get(i));
//		}
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			queue.remove(list.get(i));
		}
		
//		for (int i = 0; i < 10000000; i++) {
//			queue.take();
//		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(queue.size());
	}

}
