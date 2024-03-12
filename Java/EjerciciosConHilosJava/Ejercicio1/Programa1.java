public class Programa1{
    public static void main(String[] args) throws InterruptedException{
        Hilo h1 = new Hilo("Hilo 1", true);
        Hilo h2 = new Hilo("Hilo 2", false);

        Thread p1 = new Thread(h1);
        Thread p2 = new Thread(h2);

        p1.start();
        p2.start();
    }
}