import threading
import os

def taks1():
    print("Tarea 1 asignada al Thread: {}".format(threading.current_thread().name))
    print("ID del proceso corriendo la tarea 1: {}".format(os.getpid()))

def task2():
    print("Tarea 2 asignada al Thread: {}".format(threading.current_thread().name))
    print("ID del proceso corriendo la tarea 2: {}".format(os.getpid()))

if __name__ == "__main__":
    print("ID del proceso corriendo el programa main: {}".format(os.getpid()))
    print("Nombre del Main Thread: {}".format(threading.current_thread().name))

    t1 = threading.Thread(target=taks1,name="Hilo 1")
    t2 = threading.Thread(target=task2, name="Hilo 2")

    t1.start()
    t2.start()

    print("Programa terminado!")