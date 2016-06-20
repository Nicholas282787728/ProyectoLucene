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
import cz.zcu.fav.kiv.jsim.ipc.JSimAssymetricMessage;
import cz.zcu.fav.kiv.jsim.ipc.JSimIndirectMessage;
import cz.zcu.fav.kiv.jsim.ipc.JSimMessageBox;
import cz.zcu.fav.kiv.jsim.ipc.JSimMessageForReceiver;

/**
 *
 * @author Jano
 */
public class Emisor  extends JSimProcess
{
	public static final double LAMBDA = 1.0;

	private JSimMessageBox box;
        
        Action receiverT1;
        Adventure receiverT2;
        Animation receiverT3;
        Biography receiverT4;
        Comedy receiverT5;
        Crime receiverT6;
        Documentary receiverT7;
        Drama receiverT8;
        Family receiverT9;
        Fantasy receiverT10;
        FilmNoir receiverT11;
        History receiverT12;
        Horror receiverT13;
        Music receiverT14;
        Musical receiverT15;
        Mystery receiverT16;
        Romance receiverT17;
        SciFi receiverT18;
        Sport receiverT19;
        Thriller receiverT20;
        War receiverT21;
        Western receiverT22;

	// ------------------------------------------------------------------------------------------------------------------------------------

	public Emisor(String name, JSimSimulation parent, JSimMessageBox messageBox) throws JSimSimulationAlreadyTerminatedException, JSimInvalidParametersException, JSimTooManyProcessesException
	{
		super(name, parent);
                
		box = messageBox;
	} // constructor

    public Action getReceiverT1() {
        return receiverT1;
    }

    public void setReceiverT1(Action receiverT1) {
        this.receiverT1 = receiverT1;
    }

    public Adventure getReceiverT2() {
        return receiverT2;
    }

    public void setReceiverT2(Adventure receiverT2) {
        this.receiverT2 = receiverT2;
    }

    public Animation getReceiverT3() {
        return receiverT3;
    }

    public void setReceiverT3(Animation receiverT3) {
        this.receiverT3 = receiverT3;
    }

    public Biography getReceiverT4() {
        return receiverT4;
    }

    public void setReceiverT4(Biography receiverT4) {
        this.receiverT4 = receiverT4;
    }

    public Comedy getReceiverT5() {
        return receiverT5;
    }

    public void setReceiverT5(Comedy receiverT5) {
        this.receiverT5 = receiverT5;
    }

    public Crime getReceiverT6() {
        return receiverT6;
    }

    public void setReceiverT6(Crime receiverT6) {
        this.receiverT6 = receiverT6;
    }

    public Documentary getReceiverT7() {
        return receiverT7;
    }

    public void setReceiverT7(Documentary receiverT7) {
        this.receiverT7 = receiverT7;
    }

    public Drama getReceiverT8() {
        return receiverT8;
    }

    public void setReceiverT8(Drama receiverT8) {
        this.receiverT8 = receiverT8;
    }

    public Family getReceiverT9() {
        return receiverT9;
    }

    public void setReceiverT9(Family receiverT9) {
        this.receiverT9 = receiverT9;
    }

    public Fantasy getReceiverT10() {
        return receiverT10;
    }

    public void setReceiverT10(Fantasy receiverT10) {
        this.receiverT10 = receiverT10;
    }

    public FilmNoir getReceiverT11() {
        return receiverT11;
    }

    public void setReceiverT11(FilmNoir receiverT11) {
        this.receiverT11 = receiverT11;
    }

    public History getReceiverT12() {
        return receiverT12;
    }

    public void setReceiverT12(History receiverT12) {
        this.receiverT12 = receiverT12;
    }

    public Horror getReceiverT13() {
        return receiverT13;
    }

    public void setReceiverT13(Horror receiverT13) {
        this.receiverT13 = receiverT13;
    }

    public Music getReceiverT14() {
        return receiverT14;
    }

    public void setReceiverT14(Music receiverT14) {
        this.receiverT14 = receiverT14;
    }

    public Musical getReceiverT15() {
        return receiverT15;
    }

    public void setReceiverT15(Musical receiverT15) {
        this.receiverT15 = receiverT15;
    }

    public Mystery getReceiverT16() {
        return receiverT16;
    }

    public void setReceiverT16(Mystery receiverT16) {
        this.receiverT16 = receiverT16;
    }

    public Romance getReceiverT17() {
        return receiverT17;
    }

    public void setReceiverT17(Romance receiverT17) {
        this.receiverT17 = receiverT17;
    }

    public SciFi getReceiverT18() {
        return receiverT18;
    }

    public void setReceiverT18(SciFi receiverT18) {
        this.receiverT18 = receiverT18;
    }

    public Sport getReceiverT19() {
        return receiverT19;
    }

    public void setReceiverT19(Sport receiverT19) {
        this.receiverT19 = receiverT19;
    }

    public Thriller getReceiverT20() {
        return receiverT20;
    }

    public void setReceiverT20(Thriller receiverT20) {
        this.receiverT20 = receiverT20;
    }

    public War getReceiverT21() {
        return receiverT21;
    }

    public void setReceiverT21(War receiverT21) {
        this.receiverT21 = receiverT21;
    }

    public Western getReceiverT22() {
        return receiverT22;
    }

    public void setReceiverT22(Western receiverT22) {
        this.receiverT22 = receiverT22;
    }
    
    
        
	protected void life()
	{
            JSimAssymetricMessage mensaje;
            //int tiempo = 19990419;
            double sleepTime;
            int i = 0;
            mensaje = null;
            try
            {
                while (true)
                {
                    SimEmisorReceptor.setTiempo(SimEmisorReceptor.getTiempo());
                    if(i==0){
                        mensaje = new JSimMessageForReceiver(receiverT1, (SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo())+", topico1"));
                        sendMessageWithoutBlocking(mensaje, box);
                    }
                    
                    if(i==1){
                        mensaje = new JSimMessageForReceiver(receiverT2, (SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo())+", topico2"));
                        sendMessageWithoutBlocking(mensaje, box);
                        mensaje = new JSimMessageForReceiver(receiverT3, (SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo())+", topico3.1"));
                        sendMessageWithoutBlocking(mensaje, box);
                    }
                    if(i==2){
                        mensaje = new JSimMessageForReceiver(receiverT3, (SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo())+", topico3.2"));
                        sendMessageWithoutBlocking(mensaje, box);
                    }
                    //mensaje = new JSimIndirectMessage(new String("(0.0,topico1,[1,0,0],[1,0,0])"));
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "> Enviando mensaje '" + mensaje.getData().toString() + "' a la caja de mensajes compartidos.");
                    
                    //sendMessageWithBlocking(mensaje, box);
                    
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + "> Mensaje enviado.");
                    //tiempo++;
                    //sleepTime = JSimSystem.negExp(LAMBDA);
                    message(SimEmisorReceptor.FormatoFecha(SimEmisorReceptor.getTiempo()) + " - " + getName() + ": hibernando por " + 1 + " día.");
                    sleepTime = SimEmisorReceptor.getTiempo()+1;
                    hold(1);
                    i++;
                } // while
            } // try
            catch (JSimException e)
            {
                    e.printStackTrace();
                    e.printComment(System.err);
            } // catch
	} // life

} // class SendingProcess
