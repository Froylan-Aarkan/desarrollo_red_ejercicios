public class Hilo implements Runnable{
    private String nombre;
    private boolean esUnoACien;
        
    public Hilo(String nombre, boolean esUnoACien) {
        this.nombre = nombre;
        this.esUnoACien = esUnoACien;
    }

    @Override
    public void run() {
        if(esUnoACien){
            for(int i = 1; i < 101; i++){
                System.out.println(nombre + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(nombre + ": Terminé!");
        }else{
            for(int i = 100; i > 0; i--){
                System.out.println(nombre + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }   
            System.out.println(nombre + ": Terminé!");
        }
    }
}