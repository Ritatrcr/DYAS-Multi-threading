package com.create;

import java.util.concurrent.Callable;

public class SquareTask implements Callable<Integer> {
    private int num;

    public SquareTask(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        // Imprime el nombre del hilo que está ejecutando la tarea
        System.out.println("Hilo " + Thread.currentThread().getName() + " está procesando el número: " + num);
        
        // Calcula el cuadrado del número
        return num * num;
    }
}
