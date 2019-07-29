package drivergila;

import static drivergila.Arena.timer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author aditj
 */
public class Scorenya extends OpsiScore{
    public Scorenya(){
        
    }
    
    void keluar(){
        AudioClip bg = Applet.newAudioClip(getClass().getResource("exitb.wav"));
        bg.play();
        Thread t=new Thread();
        try{
            t.sleep(2000);
        }catch(Exception dvt){
            
        }
        System.exit(0);
    }
    
    void lanjut(){
        AudioClip bg = Applet.newAudioClip(getClass().getResource("buttoneffect.wav"));
        bg.play();
        Thread t=new Thread();
        try{
            t.sleep(2000);
        }catch(Exception dvt){
            
        }
    }
}
