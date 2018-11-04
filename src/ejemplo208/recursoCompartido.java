/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo208;

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
public class recursoCompartido {
    /**
     * - variables necesarias par sincronizar los threads
     */
    public static int i=1;

    private boolean flagWinner = false;
    private String NameWinner;
    private JLabel jlabel;
    public static boolean pau;
    
    /**
     * Funciones de sincronizacion 
     */

    public recursoCompartido(JLabel jlabel) {
        this.jlabel = jlabel;
    }
    
    public int Lugar(){
         
        return i;
    }

    public boolean isFlagWinner() {
        return flagWinner;
    }

    public synchronized void setFlagWinner(boolean flagWinner) {
        this.flagWinner = flagWinner;
    }
     public synchronized void setPau(boolean pau) {
        this.pau = pau;
    }
    public boolean isPau() {
        return pau;
    } 

    public String getNameWinner() {
        return NameWinner;
    }
    
    public synchronized void setNameWinner(String NameWinner) {
        if (!isFlagWinner()) {
            this.NameWinner = NameWinner;
            flagWinner = true;
            System.out.println("El equipo ganador es: " + NameWinner);
            jlabel.setText(NameWinner);
         
        }else{
            i++;
            System.out.println("Somos el equipo "+NameWinner+",quedamos en "+ i +".lugar >:v!!!");
        }
    }
}
