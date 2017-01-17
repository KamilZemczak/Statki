/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */
package projektzaliczeniowynjpo;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 * Klasa reprezentująca pole przeciwnika.
 */
public class EnemyField extends javax.swing.JPanel implements Runnable, MouseListener  {
     
    /**
     * Zdefiniowanie rozmiarów pola oraz planszy do gry.
     */
    private static final byte WIDTH_BOARD = 14;
    private static final byte HEIGHT_BOARD = 22;
    private static final byte WIDTH_FIELD = 20, HEIGHT_FIELD = 20;
    private static final int WIDTH = WIDTH_BOARD * WIDTH_FIELD;
    private static final int HEIGHT = HEIGHT_BOARD * HEIGHT_FIELD;
    
    private EnemyShot enemyShot; 
    private Image image;
    private Graphics2D graphic;
    private Thread thread;
    private int lag = 100;
 
    private int board[][] = new int[WIDTH_BOARD][HEIGHT_BOARD];

    GameEngine gameEngine;  
    Editor editor;
    
    private int[][][] coordinatesOfShips = new int[17][14][22];
    
    /**
     * Zmienna zapamiętująca pola zestrzelonych statków.
     */ 
    private int[] hitsFields = new int[17];
    
    private int planesToHits = 1;                  
    private int waterShipFourToHits = 1;       
    private int waterShipThreeToHits = 2;        
    private int waterShipTwoToHits = 3;          
    private int waterShipOneToHits = 4;        
    private int landShipFourToHits = 1;     
    private int landShipThreeToHits = 2;        
    private int landShipTwoToHits = 3;        
    private GameWindow gameWindow;
    public boolean start = false;
    private boolean pause = false;
    private boolean mishit = false;
    private RandomField randomField;
    
    private int shotDownFields = 0;
    
  
    private int[] hitFieldX = new int[12];  // w tych zmiennych beda trzymane wspolrzedne pola trafionego ale nie zatopionego statku w celu trafienia okolicznych pół
    private int[] hitFieldY = new int[12];
    private int hitButNotSunken = 0; // ta zmienna mowi czy jakis statek zostal nieskonczony. Jesli statek zostanie zatopiony wartosc przyjmuje znow false
       
    public EnemyField(GameEngine gameEngine, Editor editor,GameWindow gameWindow) { //TODO: uzupełnić java-doc.
        super();
        this.gameWindow = gameWindow;  
        this.gameEngine = gameEngine;
        this.editor = editor;
        
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        graphic =(Graphics2D)image.getGraphics();          
    }
    
    public void setEnemyShot(EnemyShot enemyShot) {
    this.enemyShot = enemyShot;
    }
    
    /**
     * Faza uruchmieniowa, czeka aż kliknie się start.
     */ 
    @Override
    public void addNotify() { 
        super.addNotify();
        thread = new Thread(this);
        addMouseListener(this);
        thread.start();
    }

    @Override
    public void run() {   //TODO: uzupełnić java-doc.     
        for(int x = 0; x < WIDTH_BOARD; x++){
            for(int y = 0; y < HEIGHT_BOARD; y++){
                if(y > 15) {
                    board[x][y] = 1;
                }
                else {
                    board[x][y] = 0;
                }
            }
        } setFields();
       
        do {
            printGraphic();
            printOnScreen();
            try {
                thread.sleep(lag);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();          
            } 
        } while (true);
    }
       
    private void printGraphic() { //TODO: uzupełnić java-doc.
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        printBoard();        
    }
    
    private void printBoard() { //TODO: uzupełnić java-doc.
        for (int x = 0; x < WIDTH_BOARD; x++){
            for (int y = 0; y < HEIGHT_BOARD; y++) {
                if (board[x][y]==1) {
                    graphic.setColor(Color.GREEN);
                } 
                else if (board[x][y]==0) {
                    graphic.setColor(Color.BLUE);
                }
                else if (board[x][y]==2) {
                    graphic.setColor(Color.RED);
                }
                else if (board[x][y]==3) {
                    if(y < 16)graphic.setColor(Color.white);
                    else graphic.setColor(Color.white);
                }        
                graphic.fillRect(x * WIDTH_FIELD, y * HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);
                graphic.setColor(Color.LIGHT_GRAY);
                graphic.drawRect(x * WIDTH_FIELD, y * HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);                
            }   
        }
    }
    
    private void printOnScreen() { //TODO: uzupełnić java-doc.
        Graphics ekran = getGraphics();
        ekran.drawImage(image, 0, 0, null);
        ekran.dispose();
    }
    
    public void start() { //TODO: uzupełnić java-doc.
        randomField = new RandomField();
        coordinatesOfShips = randomField.getCoordinationsOfShips();
        start=true;
    }
    
    public void setPauza(boolean pauza) { //TODO: uzupełnić java-doc.
        this.pause = pauza;
    }
    
    public boolean getPauza() { //TODO: uzupełnić java-doc.
        return pause;
    }
    
    public boolean getPudlo() { //TODO: uzupełnić java-doc.
        return mishit;
    }
    
    private void checkField(int x, int y) { //TODO: uzupełnić java-doc.
        checkField(x,y,true);
    }
    
    private void checkField(int x, int y, boolean communicats) { //TODO: uzupełnić java-doc.
        
        boolean drawCircuit = false;
        int rodzajStatku = 20; 
       
        if(start == false) gameWindow.jTextArea1.append("Aby rozpocząć naciśnij przycisk Start. \n");
        else if(pause==true) gameWindow.jTextArea1.append("Poczekaj, przeciwnik nie zakończył jeszcze ruchu \n");
            else{
            if (board[x][y] == 2 || board[x][y] == 3){if(communicats==true)gameWindow.jTextArea1.append("To pole zostało już przez Ciebie trafione! \n");}
            else{
                for (int i = 0 ; i< 17 ; i++){
                    if (coordinatesOfShips[i][x][y]==2){
                        rodzajStatku = i;                  
                        coordinatesOfShips[i][x][y]=0; 
                        board[x][y] = 2; 
                    }                  
                }
                if (rodzajStatku!=20){
                    if (hitsFields[rodzajStatku]>1) 
                        {hitsFields[rodzajStatku]--;
                        if(rodzajStatku==0){ gameWindow.jTextArea1.append("Trafiłeś samolot! \n");}
                        else if(rodzajStatku==1) {gameWindow.jTextArea1.append("Trafiłeś statek wodny (4 pola). \n"); }
                        else if(rodzajStatku==2 || rodzajStatku==3){gameWindow.jTextArea1.append("Trafiłeś statek wodny (3 pola). \n"); }
                        else if((rodzajStatku>3) && (rodzajStatku<7)){ gameWindow.jTextArea1.append("Trafiłeś statek wodny (2 pola). \n"); }
                        else if((rodzajStatku>6) && (rodzajStatku<11)){gameWindow.jTextArea1.append("Trafiłeś statek wodny (1 pole). \n"); }
                        else if(rodzajStatku==11){ gameWindow.jTextArea1.append("Trafiłeś statek lądowy (4 pola). \n"); }
                        else if((rodzajStatku>11) && (rodzajStatku<14)){ gameWindow.jTextArea1.append("Trafiłeś statek lądowy (3 pola). \n"); }
                        else if((rodzajStatku>13) && (rodzajStatku<17)){ gameWindow.jTextArea1.append("Trafiłeś statek lądowy (2 pola). \n"); }
                        strzelonoPole();
                        hitButNotSunken++; 
                        hitFieldX[hitButNotSunken] = x; hitFieldY[hitButNotSunken] = y;
                        }
                    else if (hitsFields[rodzajStatku]==1) {
                        hitsFields[rodzajStatku]--;
                        if(rodzajStatku==0) { 
                            gameWindow.jTextArea1.append("Zatopiono Samolot! \n");
                            planesToHits--;
                            gameWindow.jLabel10.setForeground(Color.BLACK);
                        }
                        else if(rodzajStatku==1) { 
                                gameWindow.jTextArea1.append("Zatopiłeś statek wodny (4 pola). \n");
                                waterShipFourToHits--;
                                gameWindow.jLabel11.setForeground(Color.BLACK);
                                }
                        else if(rodzajStatku==2 || rodzajStatku==3) { 
                                gameWindow.jTextArea1.append("Zatopiłeś statek wodny (3 pola). \n");
                                waterShipThreeToHits--;
                                gameWindow.jLabel12.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>3) && (rodzajStatku<7)) { 
                                gameWindow.jTextArea1.append("Zatopiłeś statek wodny (2 pola). \n");
                                waterShipTwoToHits--;
                                gameWindow.jLabel13.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>6) && (rodzajStatku<11)) { 
                                gameWindow.jTextArea1.append("Zatopiłeś statek wodny (1 pole). \n");
                                waterShipOneToHits--;
                                gameWindow.jLabel15.setForeground(Color.BLACK);
                                }
                        else if(rodzajStatku==11) {
                                gameWindow.jTextArea1.append("Zatopiłeś statek lądowy (4 pola). \n");
                                landShipFourToHits--;
                                gameWindow.jLabel14.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>11) && (rodzajStatku<14)) {
                                gameWindow.jTextArea1.append("Zatopiłeś statek lądowy (3 pola). \n");
                                landShipThreeToHits--;
                                gameWindow.jLabel9.setForeground(Color.BLACK);
                                }
                        else if((rodzajStatku>13) && (rodzajStatku<17)) {
                                gameWindow.jTextArea1.append("Zatopiłęś statek lądowy (2 pola). \n");
                                landShipTwoToHits--;
                                gameWindow.jLabel16.setForeground(Color.BLACK);
                                }
                        strzelonoPole();
                        hitButNotSunken++; 
                        hitFieldX[hitButNotSunken] = x; hitFieldY[hitButNotSunken] = y;
                        drawCircuit = true;
                        }
                    else gameWindow.jTextArea1.append("Błąd! Strzelono statek już zatopiony. \n"); 
                }
                else {
                    board[x][y] = 3; 
                    if(communicats==true) { }                    
                    } setSubs();
                
                if(drawCircuit == true) {
                   selectCircuit();
                   hitButNotSunken = 0;
                }
            }
        }
    }
    
    public void selectCircuit() { //TODO: uzupełnić java-doc + poprawić przejrzystość.
        try { 
            Thread.sleep(1000); 
        } catch(InterruptedException e){}
        int x = 0;
        int y = 0;
     
        for(int proba = hitButNotSunken; proba > 0; proba--) {
            for(int i = 0; i<8; i++) {
                switch(i) {
                    case 0:{x=hitFieldX[proba]-1; y=hitFieldY[proba]-1; break;} //TODO: Zmienić składnię kodu.
                    case 1:{x++;break;}
                    case 2:{x++;break;}
                    case 3:{y++;break;}
                    case 4:{y++;break;}
                    case 5:{x--;break;}
                    case 6:{x--;break;}
                    case 7:{y--;break;}
                }
                if ((x < 14 && x >= 0 && y < 22 && y >= 0) && board[x][y] != 4 && board[x][y] != 3) {
                      
                    checkField(x,y,false);
                }   
            }
        }
    }
    
    public void strzelonoPole() { //TODO: uzupełnić java-doc + zmienić nazwę funkcji.
        shotDownFields++;
        if(shotDownFields>=47){
           JOptionPane.showMessageDialog(null, "Gratuluję, wygrałeś!"); 
           start=false;
        }
    }
      
    private void setFields() { //TODO: uzupełnić java-doc.
        hitsFields[0]=11;                   
        hitsFields[1]=4;                      
        hitsFields[2]=3;  
        hitsFields[3]=3;                      
        hitsFields[4] = 2;  
        hitsFields[5] = 2;  
        hitsFields[6] = 2;                  
        hitsFields[7] = 1; 
        hitsFields[8] = 1; 
        hitsFields[9] = 1; 
        hitsFields[10] = 1;                   
        hitsFields[11] = 4;                  
        hitsFields[12] = 3; 
        hitsFields[13] = 3;                   
        hitsFields[14] = 2;
        hitsFields[15] = 2;
        hitsFields[16] = 2;                   
    }
                                                                                  
    public void setSubs() { //TODO: uzupełnić java-doc.
        gameWindow.jLabel11.setText(""+waterShipFourToHits);
        gameWindow.jLabel12.setText(""+waterShipThreeToHits);
        gameWindow.jLabel13.setText(""+waterShipTwoToHits);
        gameWindow.jLabel15.setText(""+waterShipOneToHits);
        gameWindow.jLabel14.setText(""+landShipFourToHits);
        gameWindow.jLabel9.setText(""+landShipThreeToHits);
        gameWindow.jLabel16.setText(""+landShipTwoToHits);
        gameWindow.jLabel10.setText(""+planesToHits);
    }
    
    public void clean() { //TODO: uzupełnić java-doc + poprawić przejrzystość kodu.
       planesToHits = 1;                
       waterShipFourToHits = 1;      
       waterShipThreeToHits = 2;         
       waterShipTwoToHits = 3;          
       waterShipOneToHits = 4;     
       landShipFourToHits = 1;    
       landShipThreeToHits = 2;        
       landShipTwoToHits = 3;         
       start = false;
       pause = false;
       mishit = false;
       shotDownFields = 0;
       hitButNotSunken = 0; 
       for(int x = 0 ; x<WIDTH_BOARD ; x++){
            for(int y=0; y<HEIGHT_BOARD ; y++){
                if(y>15){
                    board[x][y] = 1;
                }
                else {
                    board[x][y] = 0;
                }
            }
        }
        setFields();
    }
    
    /**
     * Pobiera pole kliknięcia myszki i zmienia współrzędne na wartość tablicy, przechwytuje te kliknięcia.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getY()<HEIGHT_BOARD*HEIGHT_FIELD && me.getX()<WIDTH_BOARD*WIDTH_FIELD){  
            int x = me.getX()/WIDTH_FIELD; 
            int y = me.getY()/HEIGHT_FIELD;
            checkField(x,y);                                                     
        }
        
        if(start==true){
            while(pause==true){
                if(enemyShot.getMishit()==false){
                    enemyShot.randomShot();
                    if(enemyShot.hitsFields>=47){start=false;}
                }
                else {pause=false; enemyShot.setMishit(false);
                }
            }
        }
    }
 
    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    @Override
    public void mouseReleased(MouseEvent me) {}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
