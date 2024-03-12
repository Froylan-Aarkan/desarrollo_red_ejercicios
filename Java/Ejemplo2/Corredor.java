public class Corredor implements Runnable{

    private String avance;
    private long latencia;
    private char c;

    public Corredor(String avance, long latencia, char c) {
        this.avance = avance;
        this.latencia = latencia;
        this.c = c;
    }


    public Corredor(char c) {
        this.avance = "";
        this.latencia = (long) (Math.random() * 100);
        this.c = c;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            avance = avance + c;
            System.out.println(avance);
            try{
                Thread.sleep(latencia);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }   

        avance = avance + " terminé!!";
        System.out.println(avance);
    }
}