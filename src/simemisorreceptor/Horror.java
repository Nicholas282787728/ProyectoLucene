/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simemisorreceptor;

import cz.zcu.fav.kiv.jsim.JSimException;
import cz.zcu.fav.kiv.jsim.JSimInvalidParametersException;
import cz.zcu.fav.kiv.jsim.JSimProcess;
import cz.zcu.fav.kiv.jsim.JSimSimulation;
import cz.zcu.fav.kiv.jsim.JSimSimulationAlreadyTerminatedException;
import cz.zcu.fav.kiv.jsim.JSimSystem;
import cz.zcu.fav.kiv.jsim.JSimTooManyProcessesException;
import cz.zcu.fav.kiv.jsim.ipc.JSimMessage;
import cz.zcu.fav.kiv.jsim.ipc.JSimMessageBox;

/**
 *
 * @author Jano
 */
public class Horror extends JSimProcess
{
    public static final double LAMBDA = 1.0;

    private JSimMessageBox box13;

    // ------------------------------------------------------------------------------------------------------------------------------------

    public Horror(String name, JSimSimulation parent, JSimMessageBox messageBox, JSimProcess Emisor) throws JSimSimulationAlreadyTerminatedException, JSimInvalidParametersException, JSimTooManyProcessesException
    {
            super(name, parent);
            box13 = messageBox;
    } // constructor

    protected void life()
    {
            JSimMessage mensaje;
            double sleepTime;

            try
            {
                int inicio = SimEmisorReceptor.getTiempo();
                myReview Horror = SimEmisorReceptor.objetoFecha(13, inicio);
                
                while (true)
                {
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< leyendo bandeja mensajes de entrada <<");
                    mensaje = receiveMessageWithoutBlocking(box13);
                    if(inicio != SimEmisorReceptor.getTiempo()){
                        
                        /****************************************
                           LLAMAR FUNCION DE RANKING Y ESCRIBIR
                        *****************************************/
                        if(!Double.isNaN(SimEmisorReceptor.formulaRanking(Horror)))
                            message("Ranking anterior Horror: "+SimEmisorReceptor.formulaRanking(Horror));  
                        SimEmisorReceptor.setT13(SimEmisorReceptor.formulaRanking(Horror)+SimEmisorReceptor.getT13()-0.01);
                        
                        Horror = SimEmisorReceptor.objetoFecha(13, SimEmisorReceptor.getTiempo());
                    }
                    if (mensaje == null){
                        message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< bandeja vacia <<");
                    }
                    else{
                        message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< Recibiendo mensaje: " + mensaje.getData().toString());
                        Horror.setScore(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 0).toString()));
                        Horror.setC11(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 1).toString()));
                        Horror.setC12(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 2).toString()));
                        Horror.setC13(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 3).toString()));
                        Horror.setC21(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 4).toString()));
                        Horror.setC22(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 5).toString()));
                        Horror.setC23(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 6).toString()));
                        Horror.setParcial((Horror.getC11()*Horror.getScore()+Horror.getC21())/SimEmisorReceptor.alpha+SimEmisorReceptor.alpha*(Horror.getC12()*Horror.getScore()+Horror.getC22())+Horror.getC13()*Horror.getScore()+Horror.getC23());
                        Horror.setNumeroReviews(Horror.getNumeroReviews()+1);
                    }
                    sleepTime = JSimSystem.negExp(LAMBDA);
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + ": espero el siguiente mensaje");
                    hold(1);
                } // while
            } // try
            catch (JSimException e)
            {
                e.printStackTrace();
                e.printComment(System.err);
            } // catch
    } // life

} // class ReceivingProcess
