
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Microsoft User
 */
public class Watch extends Thread{
    boolean stopped=false;
    private Date startTime;
    private long totalTime=0;
    private JLabel tijddisplay=null;
    
public Watch(JLabel tijddisplay){
    this.tijddisplay=tijddisplay;
}
    
public synchronized void killThread(){
    stopped=true;
}    

public synchronized boolean threadIsRunning(){
    return !stopped;
}

public synchronized void startWatch(){
    if(startTime==null){
        startTime=new Date();
        notifyAll();
    }
}

public synchronized void resetWatch(){
    //starttijd op null
    startTime=null;
    //reset total tijd
    totalTime=0;
}
    
    
    private String getTime(){
        Calendar c = Calendar.getInstance();
        Date rightNow = new Date();
        c.setTime(new Date(rightNow.getTime()-startTime.getTime()+totalTime));
        int hour=c.get(c.HOUR);
        int min=c.get(c.MINUTE);
        int sec=c.get(c.SECOND);
        int msec=c.get(c.MILLISECOND)/10;
        
        String stHour, stMin, stSec, stMsec;
        stHour=""+hour;
        stMin=""+min;
        stSec=""+sec;
        stMsec=""+msec;
        
        String st=String.format("%02d:%02d:%02d:%02d",stHour, stMin,stSec,stMsec);
        return st;
        
    }
    
    public void run(){
        while(threadIsRunning()){
            
                tijddisplay.setText(getTime());           
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Watch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
