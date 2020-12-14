package ru.geakbrains.level2.lesson4;

public class MFU {

//    private static final Object mon = new Object();
    private static String action = "all";
    private static final Object print = new Object();
    private static final Object scan = new Object();
    private static final Object net = new Object();

    public static void main (String[] args){
        MFU mfu = new MFU();
        Thread print = new Thread(()->{mfu.print();});
        Thread scan = new Thread(()->{mfu.scan();});
        Thread fax = new Thread(()->{mfu.fax();});
        Thread copy = new Thread(()->{mfu.copy();});
        Thread copy2 = new Thread(()->{mfu.copy();});

        copy.start();
        print.start();
        scan.start();
        fax.start();
        copy2.start();
    }


    public void print(){
        synchronized (print) {
            try {
                while (action.equals("print")|| action.equals("copy")) {
                    print.wait();
                }
                action = "print";
                System.out.println("I'm printing...");
                Thread.sleep(5000);
                action = "all";
                System.out.println("Printing finish");
                print.notifyAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void scan(){
        synchronized (scan) {
            synchronized (net) {
                try {
                    while (action.equals("scan") || action.equals("fax")) {
                        net.wait();
                        scan.wait();
                    }
                    action = "scan";
                    System.out.println("I'm scanning...");
                    Thread.sleep(3000);
                    action = "all";
                    System.out.println("Scanning finish");
                    net.notifyAll();
                    scan.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fax(){
        synchronized (net) {
            try {
                while ( action.equals("fax")) {
                    net.wait();
                }
                action = "fax";
                System.out.println("I'm faxing...");
                Thread.sleep(1000);
                action = "all";
                System.out.println("Faxing finish");
                net.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy(){
        synchronized (print) {
            synchronized (scan) {
                try {
                    while (action.equals("print")|| action.equals("copy")|| action.equals("scan")) {
                        print.wait();
                        scan.wait();
                    }
                    action = "copy";
                    System.out.println("I'm copying...");
                    Thread.sleep(10000);
                    action = "all";
                    System.out.println("Copying finish");
                    print.notifyAll();
                    scan.notifyAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
