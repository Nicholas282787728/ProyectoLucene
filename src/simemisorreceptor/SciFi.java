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
public class SciFi extends JSimProcess
{
    public static final double LAMBDA = 1.0;

    private JSimMessageBox box18;

    // ------------------------------------------------------------------------------------------------------------------------------------

    public SciFi(String name, JSimSimulation parent, JSimMessageBox messageBox, JSimProcess Emisor) throws JSimSimulationAlreadyTerminatedException, JSimInvalidParametersException, JSimTooManyProcessesException
    {
            super(name, parent);
            box18 = messageBox;
    } // constructor

    protected void life()
    {
            JSimMessage mensaje;
            double sleepTime;

            try
            {
                int inicio = SimEmisorReceptor.getTiempo();
                myReview SciFi = SimEmisorReceptor.objetoFecha(18, inicio);
                
                while (true)
                {                    
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< leyendo bandeja mensajes de entrada <<");
                    mensaje = receiveMessageWithoutBlocking(box18);
                    
                    if(inicio != SimEmisorReceptor.getTiempo()){
                        
                        /****************************************
                           LLAMAR FUNCION DE RANKING Y ESCRIBIR
                        *****************************************/
                        if(!Double.isNaN(SimEmisorReceptor.formulaRanking(SciFi)))
                            message("Ranking anterior CienciaFiccion: "+SimEmisorReceptor.formulaRanking(SciFi));
                        SimEmisorReceptor.setT18(SimEmisorReceptor.formulaRanking(SciFi)+SimEmisorReceptor.getT18()-0.01);
                        
                        SciFi = SimEmisorReceptor.objetoFecha(18, SimEmisorReceptor.getTiempo());
                    }
                    
                    if (mensaje == null){
                        message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< bandeja vacia <<");
                    }
                    else{
                        message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "< Recibiendo mensaje: " + mensaje.getData().toString());
                        SciFi.setScore(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 0).toString()));
                        SciFi.setC11(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 1).toString()));
                        SciFi.setC12(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 2).toString()));
                        SciFi.setC13(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 3).toString()));
                        SciFi.setC21(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 4).toString()));
                        SciFi.setC22(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 5).toString()));
                        SciFi.setC23(Double.parseDouble(SimEmisorReceptor.retornaValor(mensaje.getData().toString(), 6).toString()));
                        SciFi.setParcial((SciFi.getC11()*SciFi.getScore()+SciFi.getC21())/SimEmisorReceptor.alpha+SimEmisorReceptor.alpha*(SciFi.getC12()*SciFi.getScore()+SciFi.getC22())+SciFi.getC13()*SciFi.getScore()+SciFi.getC23());
                        SciFi.setNumeroReviews(SciFi.getNumeroReviews()+1);
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
