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
public class RecursoRelevo {
    
    private boolean flagWinner = false;
    private String NameWinner;
    private JLabel jlabel;

    public RecursoRelevo(JLabel jlabel) {
        this.jlabel = jlabel;
    }

    public boolean isFlagRelevo() {
        return flagWinner;
    }

    public synchronized void setFlagRelevo(boolean flagWinner) {
        this.flagWinner = flagWinner;
    }

    public String getRelevo() {
        return NameWinner;
    }
    
    public synchronized void setRelevo(String NameWinner) {
        
        flagWinner = true;
    }

}
