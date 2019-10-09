package xyz.lennon.concurrent;

import java.util.concurrent.*;

public class ThreadBase {
    public static void main(String[] args) {
        //1.继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread");
                super.run();
            }
        };
        thread.start();
        //2.实现runable接口
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        Thread thread2 = new Thread(()-> System.out.println("lambada"));
        thread1.start();
        thread2.start();
        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
//        Future future = service.submit((Callable<String>) () -> "通过实现Callable接口");
        try {
            String result = future.get().toString();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
