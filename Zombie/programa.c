#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
    int pid = fork();
    printf("Id del proceso: %d\n", pid);

    if(pid > 0){
        sleep(20);
    }else if(pid == 0){
        printf("\nProceso zombie creado con exito!");
        printf("\nEstará activo durante 20 segundos\n");
    }else{
        printf("\nLo sentimos! El proceso hijo no pudo ser creado...");
    }

    return 0;
}