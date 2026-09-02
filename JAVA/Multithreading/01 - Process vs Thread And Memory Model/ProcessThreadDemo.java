public class ProcessThreadDemo {
    public static void main(String[] args) {
        // 1. Inspecting the main thread
        Thread mainThread = Thread.currentThread();
        System.out.println("Current Thread Name : " + mainThread.getName());
        System.out.println("Thread Is Alive     : " + mainThread.isAlive());
        System.out.println("Thread Priority     : " + mainThread.getPriority());
        System.out.println("Thread State        : " + mainThread.getState());
        System.out.println("Thread Group        : " + mainThread.getThreadGroup().getName());

        System.out.println("\n-------------------------------------------------");
        System.out.println("JVM Process & Memory Diagnostics:");
        System.out.println("-------------------------------------------------");

        // 2. Available CPU cores
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available CPU Cores : " + availableProcessors);

        // 3. JVM Heap Memory boundaries
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);

        System.out.println("Initial Heap Total  : " + totalMemory + " MB");
        System.out.println("Free Heap Memory    : " + freeMemory + " MB");
        System.out.println("Max Configured (-Xmx): " + maxMemory + " MB");

        // 4. Demonstrating background thread count in standard JVM process
        System.out.println("\nActive Threads in JVM Process:");
        Thread.getAllStackTraces().keySet().forEach(t -> {
            System.out.println(" - Thread: [" + t.getName() + "], Daemon: " + t.isDaemon() + ", State: " + t.getState());
        });
    }
}
