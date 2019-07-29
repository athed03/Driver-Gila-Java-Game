package drivergila;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author aditj
 */
public abstract class OpsiScore {
    int hgScore(){
        int x=0;//buat nampung score
                    try {//ngambil score dari pemain buat dibandingin sama score sekarang
                            Connection conn = Database.koneksiDB();
                            String s = "SELECT score FROM pemain where nama='"+Menu.pemainnya+"'";                            
                            Statement stm = conn.createStatement();
                            ResultSet sql = stm.executeQuery(s);
                            while(sql.next()){
                                x=sql.getInt("score");
                            }
                        } catch (Exception evt) {
                            System.out.println(evt);
                        }
                    return x;
    }
    
    abstract void lanjut();
    abstract void keluar();
}
