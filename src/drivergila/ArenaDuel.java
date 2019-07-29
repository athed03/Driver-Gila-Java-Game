/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivergila;

import static drivergila.Menu.hgscore;
import static drivergila.Menu.pemain;
import static drivergila.Menu.phigh;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author aditj
 */
public class ArenaDuel extends javax.swing.JFrame {

    /**
     * Creates new form ArenaDuel
     */
    
    AudioClip bg;//suara
    
    JLabel jalan, mycar, garis1, garis2, tabrak, nilainya, pemainnya, cuing,a;//kepentingan interface
    
    JPanel ss;
    
    JButton lanjut, keluar;
    
    public static int nilai=0;//buat kondisi nambah kecepatan
    int xmobil=315, ymobil=600, speed=5;//pergerakan mobil, x buat lajur, y turunnya, speed jarak pindah(kecepatan
    
    static Timer timer;//timer thred
    
    int delay=10,i=0;//thread, i buat kondisi nambah kecepatan
    
    Scorenya score;
    
    private com.mysql.jdbc.Connection conn;
    private Statement stm;
    private ResultSet sql;
    
    public ArenaDuel() {
        
        addKeyListener(new KeyListener() {});//class buat kalo neken keybord mobil pindah
        //background set sound
        bg = Applet.newAudioClip(getClass().getResource("arenabg.wav"));
        bg.play();
        
        //ngatur interface
        ss=new JPanel();
        cuing = new JLabel(new ImageIcon(getClass().getResource("hore.gif")));
        jalan=new JLabel(new ImageIcon(getClass().getResource("jalan.png")));
        garis1=new JLabel(new ImageIcon(getClass().getResource("garis.png")));
        garis2=new JLabel(new ImageIcon(getClass().getResource("garis.png")));
        mycar=new JLabel(new ImageIcon(getClass().getResource(Menu.jmobil+"m.png")));
        tabrak=new JLabel(new ImageIcon(getClass().getResource("ledak.gif")));
        lanjut = new JButton(new ImageIcon(getClass().getResource("continue.png")));
        keluar = new JButton(new ImageIcon(getClass().getResource("exit.png")));
        nilainya=new JLabel();
        pemainnya=new JLabel();
        
        ss.setBackground(new java.awt.Color(0,0,0,150));
        
        pemainnya.setFont(new java.awt.Font("Simplifica", 0, 70)); // NOI18N
        pemainnya.setForeground(new java.awt.Color(252, 83, 89));
        pemainnya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pemainnya.setText("ATHED");
        
        nilainya.setFont(new java.awt.Font("Simplifica", 0, 250)); // NOI18N
        nilainya.setText("30");
        nilainya.setForeground(new java.awt.Color(252, 83, 89));
        nilainya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        a= new JLabel("NEW HIGH SOCORE");
        a.setFont(new java.awt.Font("Simplifica", 0, 50)); // NOI18N
        a.setForeground(new java.awt.Color(252, 83, 89));
        a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        add(a);
                        a.setBounds(100, 75, 500, 100);
        add(cuing);
        add(pemainnya);
        add(nilainya);
        add(keluar);
        add(lanjut);
        add(ss);
        
        pemainnya.setVisible(false);
        nilainya.setVisible(false);
        keluar.setVisible(false);
        lanjut.setVisible(false);
        ss.setVisible(false);
        cuing.setVisible(false);
        a.setVisible(false);
        
        //inisialisasi rintangan ada 5
        for(int i=0;i<5;i++){
            Menu.rint[i]=new JLabel();
            Menu.rintb[i]=new JLabel();
            add(Menu.rint[i]);
            add(Menu.rintb[i]);
        }
        
        add(tabrak);
        add(mycar);
        add(garis1);
        add(garis2);
        add(jalan);
        
        cuing.setBounds(150, 200, 300, 300);
        ss.setBounds(0,100,610,500);
        pemainnya.setBounds(50, 300, 500, 300);
        nilainya.setBounds(200, 75, 200, 500);
        lanjut.setBorder(null);
        lanjut.setOpaque(false);
        
        
        keluar.setContentAreaFilled(false);
        keluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        
        lanjut.setContentAreaFilled(false);
        lanjut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lanjut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lanjutActionPerformed(evt);
            }
        });
        
        lanjut.setBounds(50, 500, 210, 60);
        keluar.setOpaque(false);
        keluar.setBorder(null);
        
        keluar.setBounds(350, 500, 210, 60);
        jalan.setBounds(285, 0, 250, 690);
        garis1.setBounds(375, 0, 5, 690);
        garis2.setBounds(450, 0, 5, 690);
        
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double ScreenHeight = screenSize.getHeight();
        int x = ((int)screenWidth-getWidth())/2;
        int y = ((int)ScreenHeight-getHeight())/2;

        setLocation(x,y-30);
        //tambahan
        pemain.setText(Menu.pemainnya);
        try{
            conn= Database.koneksiDB();
            stm = conn.createStatement();
            sql = stm.executeQuery("SELECT pemain.nama From pemain,battle where (pemain.id=battle.p1 OR pemain.id=battle.p2) AND battle.id = ...");
            while(sql.next()){ 
                phigh=sql.getString("nama");
                if(Menu.pemainnya != sql.getString("nama")){
                    lawan.setText("pemain");
                }
            }
        }
        //sampe sini
        catch(SQLException e){
            System.out.println(e);
        }
        lawan.setText("xxxxx");
        //untuk timer thread bakal ngulang manggil class mulai setiap delay ms
        Mulaiin mulai=new Mulaiin();        
        timer=new Timer(delay, mulai);
        timer.start();//manggil class mulai
    }
    
    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    //klik exit
        score.keluar();
    }          
    private void lanjutActionPerformed(java.awt.event.ActionEvent evt) {                                         
    //klik continue
        score.lanjut();
        new Menu().setVisible(true);
        dispose();
    }
    
    //buat pindah lokasi mobil kalo teken keyboard kiri kanan
    private class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(xmobil>315)
                        xmobil -= 75;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (xmobil<465)
                        xmobil += 75;
                    break;
                case KeyEvent.VK_P:
                    try {
                        timer.stop();
                        JOptionPane.showMessageDialog(null, "Game Pause\nTekan OK untuk melanjutkan");
                        timer.start();
                    } catch (Exception evt) {
                    }
                default:
                    break;
            }
        }  
    }

    //class timer thread
    public class Mulaiin implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            nilai++;
            //kondisi buat nambah kecepatan tiap 5 detik makanya ada if(i%10)
            if(nilai%(1000/delay) == 0){
                i++;
                if((i%5)==0 && speed<15 && i!=0){
                    Rintangan.gapa+=10;
                    Rintangan.gapb+=15;
                    speed++;
                }
            }
            score1.setText(""+i);//cetak score ngikutin waktu
            new Rintangan(speed);//manggil class rintangan
            
            mycar.setBounds(xmobil,ymobil,50,80);//nampilin mobil
            
            //kondisi kalo mobil nabrak
            if(((Rintangan.xr[0]+70)>ymobil && (Rintangan.posisi[0]==xmobil || Rintangan.posisib[0]==xmobil)) ||
                        (Rintangan.xr[1]+70>ymobil && (Rintangan.posisi[1]==xmobil || Rintangan.posisib[1]==xmobil)) ||
                        (Rintangan.xr[2]+70>ymobil && (Rintangan.posisi[2]==xmobil || Rintangan.posisib[2]==xmobil)) ||
                        (Rintangan.xr[3]+70>ymobil && (Rintangan.posisi[3]==xmobil || Rintangan.posisib[3]==xmobil)) ||
                        (Rintangan.xr[4]+70>ymobil && (Rintangan.posisi[4]==xmobil || Rintangan.posisib[4]==xmobil))){
                pemainnya.setVisible(true);
                pemainnya.setText(Menu.pemainnya);
                nilainya.setVisible(true);
                nilainya.setText(""+i);
                keluar.setVisible(true);
                lanjut.setVisible(true);
                ss.setVisible(true);
                
                score=new Scorenya();
                
                timer.stop();//timer thread berenti
                bg.stop();  //backround berenti
                tabrak.setBounds(xmobil-20,ymobil-10,100,100);//set gif ledakan
                
                bg = Applet.newAudioClip(getClass().getResource("nabrak.wav"));//bunyi nabarak set
                bg.play();
                if(Menu.hgscore<i){
                    a.setVisible(true);
                    cuing.setVisible(true);
                }
                try {//input score pemain terbaru hanya jika score sekarangnya lebih bessar dari score sebeumnya
                    Connection conn = Database.koneksiDB();
                    Statement stm = conn.createStatement();
                    if(score.hgScore()<i){
                        String s = "UPDATE `pemain` SET `score`= "+i+" WHERE nama='"+Menu.pemainnya+"'";
                        stm.executeUpdate(s);
                    }
                } catch (Exception evt) {
                    System.out.println(evt);
                }
                
                //semua variabel static di rintangan.java diset seperti semula
                Rintangan.gapa=175;
                Rintangan.gapb=185;
                for(int i=0;i<5;i++){
                    Rintangan.xr[i]=-70;
                    Rintangan.posisi[i]=-70;
                    Rintangan.posisib[i]=-70;}
                speed=5;
                Rintangan.jalan=false;
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        score1 = new javax.swing.JLabel();
        lawan = new javax.swing.JLabel();
        pemain = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(15, 54, 85));
        jLabel3.setFont(new java.awt.Font("Simplifica", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lawan");
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 160, 40));

        jLabel2.setBackground(new java.awt.Color(15, 54, 85));
        jLabel2.setFont(new java.awt.Font("Simplifica", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("{}{}{}{}{}{}"); // NOI18N
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 180, 90));

        score1.setFont(new java.awt.Font("Simplifica", 0, 50)); // NOI18N
        score1.setForeground(new java.awt.Color(255, 255, 255));
        score1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score1.setText("jLabel2");
        getContentPane().add(score1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 160, 70));

        lawan.setFont(new java.awt.Font("Simplifica", 0, 50)); // NOI18N
        lawan.setForeground(new java.awt.Color(255, 255, 255));
        lawan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lawan.setText("jLabel2");
        getContentPane().add(lawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 160, 70));

        pemain.setFont(new java.awt.Font("Simplifica", 0, 32)); // NOI18N
        pemain.setText("jLabel2");
        getContentPane().add(pemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 120, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/main.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArenaDuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArenaDuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArenaDuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArenaDuel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArenaDuel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lawan;
    private javax.swing.JLabel pemain;
    private javax.swing.JLabel score1;
    // End of variables declaration//GEN-END:variables
}
