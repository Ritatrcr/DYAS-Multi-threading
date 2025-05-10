package com.create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelProcessingExample {

    public static void main(String[] args) {
        // Lista de números a procesar
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        // Creamos un pool de hilos con 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Lista para almacenar las tareas (Callable)
        List<Callable<Integer>> tasks = new ArrayList<>();

        // Definimos las tareas: calcular el cuadrado de cada número
        for (int num : numbers) {
            // Crear una tarea SquareTask para cada número
            tasks.add(new SquareTask(num));
        }

        try {
            // Enviamos las tareas al pool y obtenemos los resultados
            List<Future<Integer>> results = executor.invokeAll(tasks);

            // Sumamos los resultados de las tareas
            int totalSum = 0;
            for (Future<Integer> result : results) {
                totalSum += result.get(); // Esperamos a que cada tarea termine y obtenemos el resultado
            }

            // Imprimimos el resultado final
            System.out.println("Suma de los cuadrados: " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Cerramos el pool de hilos
            executor.shutdown();
        }
    }
}
