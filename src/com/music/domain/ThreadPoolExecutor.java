package com.music.domain;

/**
 * 사용자가 입력한 숫자의 쓰래드를 생성하여 실행
 * @author azi
 *
 */
public class ThreadPoolExecutor {
	
	private final static ThreadPoolExecutor INSTANCE = new ThreadPoolExecutor(); 

	private ThreadPoolExecutor() {}
	
	public static ThreadPoolExecutor getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 입력된 숫자만큼의 쓰래드를 생성한 뒤 실행
	 * 실행 이후 join 처리하여 쓰래드 실행 순서를 보장
	 * @param task 실행할 쓰래드
	 * @param threadCnt : 실행할 쓰래드 숫자
	 */
	public void executeTasks(Runnable task, int threadCnt) {
		
		Thread[] tasks = new Thread[threadCnt];
		
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new Thread(task);
			tasks[i].start();
		}
		
		for (Thread thread : tasks) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	

}
