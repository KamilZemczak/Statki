/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

//FIXME: Nagle przestało działać strzelanie przeciwnika (gra z komputerem), brak czasu by to poprawić.
//FIXME: Brak informacji o liczbie strzałów.
//FIXME: Zapis i odczyt nie do końca działa.
//FIXME: Ze względu na brak czasu nie wszystkie wzorce projektowe zostały wykorzystane.

/*
 * Klasa designu naszego programu.
 */
public class GameWindow extends javax.swing.JFrame  {

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension rozmEkranu = kit.getScreenSize();
    public Editor editor = new Editor(GameWindow.this);
    public GameEngine gameEngine = new GameEngine(editor, this);
    public GamerField gra = new GamerField(gameEngine, editor);
    public SaveLoad save_load = new SaveLoad(gameEngine, editor);
    public boolean start = false;
    public EnemyShot enemyShot;
    
    public EnemyField game_aim = new EnemyField(gameEngine, editor, GameWindow.this);
    
    public GameWindow() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = gra;
        waterShipFourButton = new javax.swing.JButton();
        waterShipThreeButton = new javax.swing.JButton();
        waterShipTwoButton = new javax.swing.JButton();
        waterShipOneButton = new javax.swing.JButton();
        landShipTwoButton = new javax.swing.JButton();
        landShipFourButton = new javax.swing.JButton();
        landShipThreeButton = new javax.swing.JButton();
        planeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jPanel_Celowanie = game_aim;
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        randomMapButton = new javax.swing.JButton();
        cleanMapButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        newGameButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        enemyStatisticLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        playerStatisticLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Users\\Seweryn\\Documents\\NetBeansProjects\\ProjektZaliczeniowyv1"));
        jFileChooser1.setFileFilter(new FileNameExtensionFilter("Zapisy Gry", "game"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 280, 440));

        waterShipFourButton.setText("Wodny (4)");
        waterShipFourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterShipFourButtonActionPerformed(evt);
            }
        });
        getContentPane().add(waterShipFourButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 90, 30));

        waterShipThreeButton.setText("Wodny (3)");
        waterShipThreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterShipThreeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(waterShipThreeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 90, 30));

        waterShipTwoButton.setText("Wodny (2)");
        waterShipTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterShipTwoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(waterShipTwoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 90, 30));

        waterShipOneButton.setText("Wodny (1)");
        waterShipOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterShipOneButtonActionPerformed(evt);
            }
        });
        getContentPane().add(waterShipOneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 90, 30));

        landShipTwoButton.setText("Lądowy (2)");
        landShipTwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                landShipTwoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(landShipTwoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 90, 30));

        landShipFourButton.setText("Lądowy (4)");
        landShipFourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                landShipFourButtonActionPerformed(evt);
            }
        });
        getContentPane().add(landShipFourButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 90, 30));

        landShipThreeButton.setText("Lądowy (3)");
        landShipThreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                landShipThreeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(landShipThreeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 90, 30));

        planeButton.setText("Samolot (T)");
        planeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(planeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, 110, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 10, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 20, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 20, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("1");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 20, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("4");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 26, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 20, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("2");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 20, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("1");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 20, 20));

        saveButton.setText("Zapisz");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        getContentPane().add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 790, 180, 30));

        loadButton.setText("Wczytaj");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        getContentPane().add(loadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 790, 180, 30));

        javax.swing.GroupLayout jPanel_CelowanieLayout = new javax.swing.GroupLayout(jPanel_Celowanie);
        jPanel_Celowanie.setLayout(jPanel_CelowanieLayout);
        jPanel_CelowanieLayout.setHorizontalGroup(
            jPanel_CelowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel_CelowanieLayout.setVerticalGroup(
            jPanel_CelowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_Celowanie, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 300, 452));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("2");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 26, 41));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("1");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 26, 42));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("1");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 10, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("2");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 20, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setText("3");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 10, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setText("1");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 20, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setText("4");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 26, 41));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setText("3");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 20, 20));

        startButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        startButton.setText("START");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 750, 180, 30));

        randomMapButton.setText("Wygeneruj losową mapę");
        randomMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomMapButtonActionPerformed(evt);
            }
        });
        getContentPane().add(randomMapButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 750, 180, 30));

        cleanMapButton.setText("Wyczyść mapę");
        cleanMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanMapButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cleanMapButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 790, 180, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 950, -1));

        jLabel18.setText("Mapa przeciwnika:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 120, 20));

        newGameButton.setText("Nowa Gra");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(newGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 750, 180, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 10));

        enemyStatisticLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enemyStatisticLabel.setText("Statystki przeciwnika");
        getContentPane().add(enemyStatisticLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 40, 440));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 480, 20));

        playerStatisticLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playerStatisticLabel.setText("Statystki gracza");
        getContentPane().add(playerStatisticLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel21.setText("Twoja mapa:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 90, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Statki dodatkowe:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel28.setText("Pozotało do wyboru / Pozostało u przeciwnika");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel29.setText("Statki przeznaczone do pola wodnego:");
        getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel30.setText("Statki przeznaczone do pola lądowego:");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel31.setText("Liczby w nawiasie oznaczają ich rozmiar (np. (4) - 4 pola).");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("   /");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 30, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("   /");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 30, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("   /");
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 30, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("   /");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 30, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("   /");
        getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 30, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("   /");
        getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 30, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("   /");
        getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 30, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("   /");
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 30, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 980, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel41.setText("MENU:");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 710, -1, -1));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 980, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel42.setText("LOGI / INFORMACJE NA TEMAT GRY:");
        getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        imagePanel.setLayout(null);

        imageLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Kamil Zemczak\\Desktop\\ship_PNG5405.png")); // NOI18N
        imagePanel.add(imageLabel);
        imageLabel.setBounds(40, 0, 370, 130);

        getContentPane().add(imagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 700, 390, 120));

        jLabel19.setText("Pomysł na tą logi zaczęrpnąłem od kolegi z roku wyżej (+porandiki), tak samo koncepcje logiki silnika gry itd.");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 560, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void waterShipFourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterShipFourButtonActionPerformed
        editor.setWaterShipFour();        
    }//GEN-LAST:event_waterShipFourButtonActionPerformed

    private void waterShipThreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterShipThreeButtonActionPerformed
        editor.setWaterShipThree();
    }//GEN-LAST:event_waterShipThreeButtonActionPerformed

    private void waterShipTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterShipTwoButtonActionPerformed
        editor.setWaterShipTwo();
    }//GEN-LAST:event_waterShipTwoButtonActionPerformed

    private void waterShipOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waterShipOneButtonActionPerformed
        editor.setWaterShipOne();        
    }//GEN-LAST:event_waterShipOneButtonActionPerformed

    private void landShipTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_landShipTwoButtonActionPerformed
        editor.setLandShipTwo();        
    }//GEN-LAST:event_landShipTwoButtonActionPerformed

    private void landShipFourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_landShipFourButtonActionPerformed
        editor.setLandShipFour();       
    }//GEN-LAST:event_landShipFourButtonActionPerformed

    private void landShipThreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_landShipThreeButtonActionPerformed
        editor.setLandShipThree();        
    }//GEN-LAST:event_landShipThreeButtonActionPerformed

    private void planeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planeButtonActionPerformed
        editor.setPlane(); 
    }//GEN-LAST:event_planeButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int wysOkno = jFileChooser1.showSaveDialog(this); 
        String nazwa_pliku = "";
        if(wysOkno == javax.swing.JFileChooser.APPROVE_OPTION){           
            java.io.File plik = jFileChooser1.getSelectedFile();
            nazwa_pliku = plik.toString( );
            try {
                save_load.Save(nazwa_pliku);       
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }    
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        
        int wysOkno = jFileChooser1.showOpenDialog(this); 
        String nazwa_pliku = "";
        if(wysOkno == javax.swing.JFileChooser.APPROVE_OPTION){           
            java.io.File plik = jFileChooser1.getSelectedFile();
            nazwa_pliku = plik.toString( );
            try {
                save_load.Load(nazwa_pliku);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            } catch (ClassNotFoundException ex) {
               System.out.println(ex.toString());
            }
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        
        enemyShot = new EnemyShot(editor, gameEngine, this);
        game_aim.setEnemyShot(enemyShot);
        game_aim.start();
        start = true;  
    }//GEN-LAST:event_startButtonActionPerformed

    private void randomMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomMapButtonActionPerformed
        RandomField mapa = new RandomField(); 
        jTextArea1.append("Wylosowano losową mapę! \n");
        gameEngine.clean();
        editor.zeroj();  
        gameEngine.setBoard(mapa.getBoard());
        editor.setCoordinatesOfShips(mapa.getCoordinationsOfShips());
        editor.setNumberOfShips(mapa.getNumberOfShips());   
        editor.setZmian(0);
        editor.setNumberOfShipsBuff(mapa.getNumberOfShipsBuff());
        
    }//GEN-LAST:event_randomMapButtonActionPerformed

    private void cleanMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanMapButtonActionPerformed
        gameEngine.clean();
        editor.zeroj();        
    }//GEN-LAST:event_cleanMapButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        editor.clean();
        game_aim.clean();
        gameEngine.clean1();
        enemyShot.clean(); 
        
        jLabel1.setForeground(Color.black);
        jLabel2.setForeground(Color.black);
        jLabel3.setForeground(Color.black);
        jLabel4.setForeground(Color.black);
        jLabel5.setForeground(Color.black);
        jLabel6.setForeground(Color.black);
        jLabel7.setForeground(Color.black);
        jLabel8.setForeground(Color.black);
        jLabel9.setForeground(Color.black);
        jLabel10.setForeground(Color.black);
        jLabel11.setForeground(Color.black);
        jLabel12.setForeground(Color.black);
        jLabel13.setForeground(Color.black);
        jLabel14.setForeground(Color.black);
        jLabel15.setForeground(Color.black);
        jLabel16.setForeground(Color.black);
        
        jLabel1.setText("1");
        jLabel2.setText("2");
        jLabel3.setText("3");
        jLabel4.setText("1");
        jLabel5.setText("4");
        jLabel6.setText("3");
        jLabel7.setText("2");
        jLabel8.setText("1");
        jLabel9.setText("2");
        jLabel10.setText("1");
        jLabel11.setText("1");
        jLabel12.setText("2");
        jLabel13.setText("3");
        jLabel14.setText("1");
        jLabel15.setText("4");
        jLabel16.setText("3");
        
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
      
    }//GEN-LAST:event_jPanel1AncestorAdded
    
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
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
                
            }
        });
       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cleanMapButton;
    private javax.swing.JLabel enemyStatisticLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JFileChooser jFileChooser1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JOptionPane jOptionPane1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Celowanie;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton landShipFourButton;
    private javax.swing.JButton landShipThreeButton;
    private javax.swing.JButton landShipTwoButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton planeButton;
    private javax.swing.JLabel playerStatisticLabel;
    private javax.swing.JButton randomMapButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton waterShipFourButton;
    private javax.swing.JButton waterShipOneButton;
    private javax.swing.JButton waterShipThreeButton;
    private javax.swing.JButton waterShipTwoButton;
    // End of variables declaration//GEN-END:variables
}