/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo208;

import static java.lang.Thread.yield;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/*
UNIVERSIDAD AUTONOMA DEL ESTADO DE MEXICO
CENTRO UNIVERSITARIO UAEM ZUMPANGO
INGENIERIA EN COMPUTACION 
PROGRAMACION AVANZADA 2018-B
ARCHIVO: ejercicio104.cpp
DESCRIPCION: imprimir todos los numeros pares positivos menores al valor introsucido por el usuario.
FECHA:23/08/018
ALUMNO(S): esteban lira alanis
PROFESOR: ASDRUBAL LOPEZ CHAU
 */
/**
 *
 * @author esteb
 */
public class Corredor implements Runnable {
    
    /**
     * variables de necesarias para los Threads
     */

    int w;
    int a = 0;
    volatile boolean pausa = false;
    JLabel label;
    JLabel label2;
    recursoCompartido RecursosCompartido;
    RecursoRelevo recursoRelevos;
    private int ancho;
    int auxAvance1, auxAvance2;
    CyclicBarrier b;
    static Resultados f;
    
    /**
     * Constructor  de los Hilos
     * @param label
     * @param label2
     * @param RecursosCompartido
     * @param recursoRelevos
     * @param ancho
     * @param b 
     */

    public Corredor(JLabel label, JLabel label2, recursoCompartido RecursosCompartido, RecursoRelevo recursoRelevos, int ancho, CyclicBarrier b) {
        this.label = label;
        this.label2 = label2;
        this.RecursosCompartido = RecursosCompartido;
        this.recursoRelevos = recursoRelevos;
        this.ancho = ancho;
        this.b = b;
        f = new Resultados();
    }
    
    /**
     * Funcion para lanzar los hilos
     */

    public void Start() {
        RecursosCompartido.setPau(true);
        new Thread(this).start();

    }

    @Override
    public void run() {
        proceso();
       
    }

    public void Pausar() {
        RecursosCompartido.setPau(false);
    }

    public void desPausar() {
        RecursosCompartido.setPau(true);
    }

    public void proceso() {
        Random r = new Random(System.nanoTime());
        int avance = 0;
        int avance2 = 0;
        while (avance < ancho) {
            if (RecursosCompartido.isPau()) {
                if (r.nextInt(100) < 50) {
                    avance++;
                } else {
                    avance += r.nextInt(3);
                }
                label.setLocation(avance, label.getY());
                try {
                    Thread.sleep(10 + r.nextInt(100));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Thread.sleep(10 + r.nextInt(100));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        recursoRelevos.setRelevo(label.getText());
        if (recursoRelevos.isFlagRelevo() == true) {
            while (avance2 < ancho) {
                if (RecursosCompartido.isPau()) {
                    if (r.nextInt(100) < 50) {
                        avance2++;
                    } else {
                        avance2 += r.nextInt(3);
                    }

                    label2.setLocation(ancho + avance2, label2.getY());
                    try {
                        Thread.sleep(10 + r.nextInt(100));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Thread.sleep(10 + r.nextInt(100));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (label2.getX() == (2*ancho)){
            RecursosCompartido.setNameWinner(label.getText());
            w = RecursosCompartido.Lugar();
            f.UP(label.getText(), w);
            yield();
        }
        try {
            b.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
