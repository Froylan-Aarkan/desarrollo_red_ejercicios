namespace AppConHilos2;

using System;
using System.Threading;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Froylan De Jesus Alvarez Rodriguez: Desarrollo de sistemas en red.");

        Thread currentThread = Thread.CurrentThread;
        currentThread.Name = "Hilo principal";
        currentThread.Priority = ThreadPriority.Lowest;
        currentThread.IsBackground = false;

        Console.WriteLine("Thread Id: {0}", currentThread.ManagedThreadId);
        Console.WriteLine("Thread Name: {0}", currentThread.Name);
        Console.WriteLine("Thread State: {0}", currentThread.ThreadState);
        Console.WriteLine("Es un thread background: {0}", currentThread.IsBackground);
        Console.WriteLine("Priority: {0}", currentThread.Priority);
        Console.WriteLine("Culture: {0}", currentThread.CurrentCulture.Name);
        Console.WriteLine("UI Culture: {0}", currentThread.CurrentUICulture.Name);
        Console.WriteLine();

        //Crear un hilo secundario pasando un delegado ThreadStart
        Thread workerThread = new Thread(new ParameterizedThreadStart(Print));
        workerThread.Name = "Hilo de print";
        CancellationTokenSource cts = new CancellationTokenSource();
        //Iniciar hilo secundario con token
        workerThread.Start(cts.Token);

        //Hilo principal: Imprime de 1 a 10 cada 0.2 segundos.

        for (int i = 0; i < 10; i++)
        {
            Console.WriteLine($"Principal thread: {i}");
            Thread.Sleep(200);
        }

        if(workerThread.IsAlive)
        {
            cts.Cancel();
        }
    }

    static void Print(object? obj)
    {
        if(obj == null)
        {
            return;
        }

        CancellationToken token = (CancellationToken)obj;

        Thread currentThread = Thread.CurrentThread;
        currentThread.Priority = ThreadPriority.Highest;
        currentThread.IsBackground = false;

        Console.WriteLine("Thread Id: {0}", currentThread.ManagedThreadId);
        Console.WriteLine("Thread Name: {0}", currentThread.Name);
        Console.WriteLine("Thread State: {0}", currentThread.ThreadState);
        Console.WriteLine("Es un thread background: {0}", currentThread.IsBackground);
        Console.WriteLine("Priority: {0}", currentThread.Priority);
        Console.WriteLine("Culture: {0}", currentThread.CurrentCulture.Name);
        Console.WriteLine("UI Culture: {0}", currentThread.CurrentUICulture.Name);
        Console.WriteLine();

        for(int i = 11; i < 20; i++)
        {
            if(token.IsCancellationRequested)
            {
                Console.WriteLine("En la iteración {0}, la cancelación ha sido solicitada...", i);
                break;
            }

            Console.WriteLine($"Print thread: {i}");
            Thread.Sleep(1000);
        }
    }
}