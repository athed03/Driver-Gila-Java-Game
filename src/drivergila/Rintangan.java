package drivergila;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rintangan {
    
    int  akhir=670, awal=-70;//batas muncul sama tenggelam rintangn
    public static int gapa=175,gapb=185;//jarak antar rintangan
    int[] jrint=new int[5];//jenis rintangan
    int[] jrintb=new int[5];//jenis rintangan sejajar rint
    
    public Random rand;//buat ngambil nilai random
    
    protected static int[] xr={-70,-70,-70,-70,-70};//buat animasi rintangan turun 
    
    protected static int[] posisi={-70,-70,-70,-70,-70};//lajur rint
    protected static int[] posisib={-70,-70,-70,-70,-70};//lajur rintb
    
    static boolean jalan=false;//set awal main
    
    public Rintangan(int j){//ngatur urutan rintangan yg muncul
        rand=new Random();
        if((xr[3]>gapa && xr[3]<gapb) || posisi[4]>=0){
            rintangan(j,4);
        }
        if((xr[2]>gapa && xr[2]<gapb) || posisi[3]>=0){
            rintangan(j,3);
        }
        if((xr[1]>gapa && xr[1]<gapb) || posisi[2]>=0){
            rintangan(j,2);
        }
        if((xr[0]>gapa && xr[0]<gapb) || posisi[1]>=0){
            rintangan(j,1);
        }
        if((xr[4]>gapa && xr[4]<gapb) || posisi[0]>=0){
            rintangan(j,0);
        }
        if(!jalan){
            rintangan(j,0);
            jalan=true;
        }
    }
    
    public void rintangan(int j, int i){
        if(posisi[i]==-70){
            posisi[i]=posisinya();//nyari posisi lajur
            jrint[i]=new Random().nextInt(3);//nyari jenis rintangan
            Menu.rint[i].setIcon(new javax.swing.ImageIcon(getClass().
                    getResource("/drivergila/t"+jrint[i]+".png")));//ngambil gambar
            do{
                if(rand.nextInt(2)!=0){//nentuin ada rintangan sejalur apa nggak
                    posisib[i]=posisinya();
                    jrintb[i]=new Random().nextInt(3);
                Menu.rintb[i].setIcon(new javax.swing.ImageIcon(getClass().
                        getResource("/drivergila/t"+jrintb[i]+".png")));
                }
            }while(posisib[i]==posisi[i]);
        }
        if(xr[i]>akhir){//kalo dia udah tenggelam di set lagi posisi horizontal
            posisi[i]=-70;
            posisib[i]=-70;
            xr[i]=awal;
        }
        //nampilin
        Menu.rint[i].setBounds(posisi[i],xr[i],50,80);
        Menu.rintb[i].setBounds(posisib[i],xr[i],50,80);
        //Arena.rintb[i].setText("b");
        xr[i]+=j;
    }
    
    public int posisinya(){//nyari lajur
        int n=rand.nextInt(3);
        if (n==0)
            return 315;
        else if(n==1)
            return 390;
        else if(n==2)
            return 465;
        else
            return 0;
    }
}
