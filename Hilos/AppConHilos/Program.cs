namespace AppConHilos;

class Program
{
    public static void DoTrabajoPesado()
    {
        Console.WriteLine("DoTrabajoPesado: Estoy levantando un camión!!");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: Cansado! Necesito una siesta de 3 seg.");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 1...");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 2...");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 3...");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: Ya desperté.");
    }

    public static void DoAlgo()
    {
        Console.WriteLine("DoAlgo: Oye! haciendo algo aquí!");
        for(int i = 0; i < 20; i++)
        {
            Console.WriteLine($"{i}");
        }

        Console.WriteLine();
        Console.WriteLine("DoAlgo: Ya terminé.");
    }

    static void Main(string[] args)
    {
        Console.WriteLine("Froylan De Jesus Alvarez Rodriguez: Desarrollo de sistemas en red");
        Console.WriteLine("El main thread comienza aquí.");

        // Crea un hilo y llama a un método en segundo plano.
        Thread backgroundThread = new Thread(new ThreadStart(Program.DoTrabajoPesado));
        // Inicia el hilo
        backgroundThread.Start();

        Program.DoAlgo();

        Console.WriteLine("El main thread termina aquí.");
    }
}
