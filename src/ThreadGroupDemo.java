public class ThreadGroupDemo {
    public static void main(String[] args) {
        Thread[] threads = new Thread[8];
        ThreadGroup[] threadGroups = new ThreadGroup[6];

        threads[0] = Thread.currentThread();
        // to index 0 of threads Array main thread is added

        threadGroups[0] = Thread.currentThread().getThreadGroup();
        // to index 0 of threadGroups Array main thread group is added

        threadGroups[1] = new ThreadGroup("ThreadGroup A");
        threadGroups[2] = new ThreadGroup("ThreadGroup B");
        threadGroups[3] = new ThreadGroup("ThreadGroup C");

        threads[1] = new Thread(new MyRunnable(), "Thread 01");

        threads[2] = new Thread(threadGroups[1], new MyRunnable(), "Thread 02");
        threads[3] = new Thread(threadGroups[1], new MyRunnable(), "Thread 03");

        threads[4] = new Thread(threadGroups[3], new MyRunnable(), "Thread 04");

        threadGroups[4] = new ThreadGroup(threadGroups[2], "ThreadGroup B1");
        threadGroups[5] = new ThreadGroup(threadGroups[2], "ThreadGroup B2");

        threads[5] = new Thread(threadGroups[4], new MyRunnable(), "Thread 05");
        threads[6] = new Thread(threadGroups[4], new MyRunnable(), "Thread 06");

        threads[7] = new Thread(threadGroups[5], new MyRunnable(), "Thread 07");

        for(int i = 1; i < threads.length; i++) {
            threads[i].start();
        }

        // activeCount() method in ThreadGroup

        System.out.println("Active Threads in the main ThreadGroup is "+threadGroups[0].activeCount());

        // activeGroupCount() method in the ThreadGroup

        System.out.println("Active ThreadGroups in the main ThreadGroup is "+threadGroups[0].activeGroupCount());

        Thread[] activeThreads = new Thread[threadGroups[0].activeCount() * 2];

        threadGroups[0].enumerate(activeThreads);

        System.out.println("List of active threads in thread group with name "+threadGroups[0].getName());
        for (Thread t : activeThreads) {
            if (t != null) {
                System.out.println(t.getName());
            }
        }

        ThreadGroup[] activeThreadGroup = new ThreadGroup[threadGroups[0].activeGroupCount() * 2];
        threadGroups[0].enumerate(activeThreadGroup);

        System.out.println("List of active thread group in thread group with name "+threadGroups[0].getName());
        for (ThreadGroup tg : activeThreadGroup) {
            if (tg != null) {
                System.out.println(tg.getName());
            }
        }

        Thread[] activeThreads2 = new Thread[threadGroups[0].activeCount() * 2];
        threadGroups[0].enumerate(activeThreads2, false);

        System.out.println("List of active threads in thread group with name "+threadGroups[0].getName()+" with recursion false");
        for (Thread t : activeThreads2) {
            if (t != null) {
                System.out.println(t.getName());
            }
        }

        ThreadGroup[] activeThreadGroup2 = new ThreadGroup[threadGroups[0].activeGroupCount() * 2];
        threadGroups[0].enumerate(activeThreadGroup2, false);

        System.out.println("List of active thread group in thread group with name "+threadGroups[0].getName()+" with recursion false");
        for (ThreadGroup tg : activeThreadGroup2) {
            if (tg != null) {
                System.out.println(tg.getName());
            }
        }


    }
}
