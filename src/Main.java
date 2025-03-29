public class Main {
    public static void main(String[] args) {
        System.out.println("Name of the thread on which main method runs "+Thread.currentThread().getName());

        // Default thread on which main method runs in main thread
        // Every thread belongs to a thread group
        // What is the thread group to which main thread belongs? main thread group
        System.out.println("Name of the thread group to which main thread belongs to "
                +Thread.currentThread().getThreadGroup().getName());

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10;i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "T1");

        t1.start();
        // to which ThreadGroup the t1 belongs to?
        // t1 is created in the main() method therefore main thread created the t1
        // main thread belongs to main thread group
        // therefore t1 belongs to main thread group
        System.out.println("The thread group to which t1 belongs to "+t1.getThreadGroup().getName());

        ThreadGroup group01 = new ThreadGroup("Group 01");
        // who is the parent of ThreadGroup with the name Group 01?
        // The ThreadGroup with the name Group 01 is created by the main thread
        // main Thread belongs to the main ThreadGroup
        // Therefore Group 01 that is created by main Thread also belong to the main ThreadGroup
        System.out.println("Who is the parent of ThreadGroup with name Group 01 "+group01.getParent().getName());

        ThreadGroup group02 = new ThreadGroup(group01, "Group 02");
        // Who is the parent of ThreadGroup with the name Group 02?
        System.out.println("Who is the parent of ThreadGroup with name Group 02 "+group02.getParent().getName());

        Thread t2 = new Thread(group01,() -> {
            for(int i = 0; i < 10;i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Thread t3 = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "T3");

            // which ThreadGroup to which Thread with the name T3 belongs to?
            // T3 is created by T2 and T2 belongs to ThreadGroup - Group 01
            // T3 is also belongs to Group 01

            ThreadGroup group03 = new ThreadGroup("Group 03");
            // who is the parent of ThreadGroup with the name group03?
            // group03 is created by Thread t2 and t2 belongs to Group 01
            // Therefore Group 03 belong to Grpup 01 - parent of Group 03 is group 01
            System.out.println("The parent of Group 03 is "+group03.getParent().getName());


        } , "T2");

        t2.start();
    }
}