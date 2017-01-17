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

/*
 * Klasa odpowiedzialna za pole gracza (czyli nas).
 */
public class GamerField extends javax.swing.JPanel implements Runnable, MouseListener  {
     
    private static final byte WIDTH_BOARD = 14;
    private static final byte HEIGHT_BOARD = 22;
    private static final byte WIDTH_FIELD = 20, HEIGHT_FIELD = 20;
    private static final int WIDTH = WIDTH_BOARD * WIDTH_FIELD + 130;
    private static final int HEIGHT = (HEIGHT_BOARD * HEIGHT_FIELD)+15;
    
    private Image image;
    private Graphics2D graphic;
    private Thread thread;
   
    /**
     * Zmienna dla opóźnienia (lag).
     */ 
    private int lag = 100;
 
    private int board[][];
    private int[][] editor;
    
    /**
     * Zmienna dla silnika gry.
     */
    GameEngine gameEngine; 
    Editor Editor;
    
    public GamerField(GameEngine gameEngine, Editor Editor) { //TODO: uzupełnić java-doc.
        super();
        this.gameEngine = gameEngine;
        this.Editor = Editor;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        graphic =(Graphics2D)image.getGraphics();   
        
    }
    
    /**
     * Faza uruchmieniowa, czeka aż kliknie się start.
     */ 
    @Override
    public void addNotify(){ 
        super.addNotify();
        thread = new Thread(this);
        addMouseListener(this);
        thread.start();
    }
    
    @Override
    public void run() { //TODO: napisać java-doca.                                 
        do {
            board = gameEngine.getBoard();
            editor = Editor.getEditor();
            printGraphic();
            printOnScren();
            try {
                thread.sleep(lag);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();          
            } 
        } while (true);
    }
        
    private void printGraphic() { //TODO: napisać java-doca.
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        printBoard();  
    }
        
    private void printBoard() { //TODO: napisać java-doca.
        for (int x = 0; x < WIDTH_BOARD; x++) {
            for (int y=0; y < HEIGHT_BOARD; y++) {
                if (board[x][y]==1) {
                    graphic.setColor(Color.GREEN);
                } 
                else if (board[x][y]==0) {
                    graphic.setColor(Color.BLUE);
                }
                else if (board[x][y]==2) {
                    graphic.setColor(Color.BLACK);
                }
                else if (board[x][y]==3) {
                    if(y < 16)graphic.setColor(Color.white);
                    else graphic.setColor(Color.white);
                }
                else if (board[x][y]==4) {
                    graphic.setColor(Color.RED); // TODO: zmienić kolor trafiony statek
                }
          
                graphic.fillRect(x*WIDTH_FIELD, y*HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);
                graphic.setColor(Color.LIGHT_GRAY);
                graphic.drawRect(x*WIDTH_FIELD, y*HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);  
            }   
        }
        
        for (int x = 0; x < 5; x++ ){
            for (int y = 0; y < 5; y++) {               
                if (editor[x][y]==2) {
                    graphic.setColor(Color.BLACK);
                } 
                else if (editor[x][y]==0) {
                    graphic.setColor(Color.BLUE);
                }
                else if (editor[x][y]==1) {
                    graphic.setColor(Color.GREEN);
                }    
                graphic.fillRect((x+15)*WIDTH_FIELD , y*HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);
                graphic.setColor(Color.LIGHT_GRAY);
                graphic.drawRect((x+15)*WIDTH_FIELD, y*HEIGHT_FIELD, WIDTH_FIELD, HEIGHT_FIELD);  
            }   
        }
    }
       
    private void printOnScren() {  //TODO: napisać java-doca.
        Graphics ekran = getGraphics();
        ekran.drawImage(image, 0, 0, null);
        ekran.dispose();
    }  
    
    /**
     * Funkcja odpowiezialna za pobranie pola kliknięcia myszki i zmiana współrzędnych na wartości tablicy
     * Wychwytuje kliknięcie na plansze, przekazuje silnikowy gry w który punkt planszy kliknięto.
     * Przechwytuje kliknięcie na edytor.
     * Przekazuje edytorowi (editor) informacje o tm w który punkt owego edytora kliknięto.
     */ 
    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getY() < HEIGHT_BOARD*HEIGHT_FIELD && me.getX() < WIDTH_BOARD*WIDTH_FIELD) {  
            int x = me.getX()/WIDTH_FIELD; 
            int y = me.getY()/HEIGHT_FIELD;
            gameEngine.setField(x,y);                                                        
            
        } 
        else if (me.getY()<5*HEIGHT_FIELD && me.getX()>15*WIDTH_FIELD && me.getX()<20*WIDTH_FIELD) {  
            int x = (me.getX()-(15*WIDTH_FIELD))/WIDTH_FIELD; 
            int y = me.getY()/HEIGHT_FIELD;
            Editor.setEditor(x,y);                                                      
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
