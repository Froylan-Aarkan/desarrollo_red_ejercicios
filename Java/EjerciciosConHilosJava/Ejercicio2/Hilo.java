public class Hilo implements Runnable {

    private boolean esHola;

    public Hilo(boolean esHola) {
        this.esHola = esHola;
    }

    @Override
    public void run() {
        while(true){
            if(esHola){
                System.out.print("Hola ");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.print("Mundo ");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  
        }      
    }
}