/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Klasa programująca strzelanie przeciwnika w naszą planszę.
 */
public class EnemyShot implements Runnable {
    
    private Editor editor;
    private GameEngine gameEngine;
    private GameWindow windowGame;
    private int[][][] coordinatesOfShips = new int[17][14][22];
    private int board[][] = new int[14][22];
    
    private int planesToHit = 1;                   
    private int waterShipFourToHit=  1;       
    private int waterShipThreeToHit = 2;        
    private int waterShipTwoToHit = 3;         
    private int waterShipOneToHit = 4;        
    private int landShipFourToHit = 1;     
    private int landShipThreeToHit = 2;       
    private int landShipTwoToHit = 3;         
    
    private int[] sunkenFieldOFShips = new int[17];
    
    private int[] hitFieldX = new int[12];  
    private int[] hitFieldY = new int[12];
    private int hitButNotSunken = 0; 
    
    private boolean mishit = false;
    
    public int hitsFields = 0;
    
    public EnemyShot(Editor edytor, GameEngine silnik,GameWindow oknogry) { //TODO: uzupełnić java-doc.
        this.windowGame = oknogry;  
        this.gameEngine = silnik;
        this.editor = edytor;
        
        coordinatesOfShips=edytor.getCoordinatesOfShips();
        board = silnik.getBoard();
        ustalPola();
        
    }

    @Override
    public void run() { //TODO: uzupełnić java-doc.
       
    }
    
    private void checkField(int x, int y) { //TODO: uzupełnić java-doc.
        checkField(x,y,true);
    }
    
    /**
     * Metoda odpowiedzialna za załadowanie pliku (20 nie reprezentuje żadnego statku).
     */
    private void checkField(int x, int y, boolean komunikaty){
        boolean rysujObwodke = false;
        int typeOfShip = 20;  
      
        for (int i = 0 ; i< 17 ; i++){
            if (coordinatesOfShips[i][x][y]==2){
                typeOfShip = i;                  
                coordinatesOfShips[i][x][y]=0; 
                board[x][y] = 2; 
            }                  
        }
        if (typeOfShip!=20){
            if (sunkenFieldOFShips[typeOfShip]>1) 
                {sunkenFieldOFShips[typeOfShip]--;
                if(typeOfShip==0) {windowGame.jTextArea1.append("Komputer trafił Ci samolot. \n");}
                else if(typeOfShip==1) {windowGame.jTextArea1.append("Komputer trafił Ci statek wodny (4 pola). \n");}
                else if(typeOfShip==2 || typeOfShip==3) {windowGame.jTextArea1.append("Komputer trafił Ci statek wodny (3 pola). \n");}
                else if((typeOfShip>3) && (typeOfShip<7)) {windowGame.jTextArea1.append("Komputer trafił Ci statek wodny (2 pola).\n");}
                else if((typeOfShip>6) && (typeOfShip<11)) {windowGame.jTextArea1.append("Komputer trafił Ci statek wodny (1 pole). \n");}
                else if(typeOfShip==11) {windowGame.jTextArea1.append("Komputer trafił Ci statek lądowy (4 pola). \n");}
                else if((typeOfShip>11) && (typeOfShip<14)) {windowGame.jTextArea1.append("Komputer trafił Ci statek lądowy (3 pola). \n");}
                else if((typeOfShip>13) && (typeOfShip<17)) {windowGame.jTextArea1.append("Komputer trafił Ci statek lądowy (2 pola). \n");}
                strzelonoPole();
                hitButNotSunken++; 
                hitFieldX[hitButNotSunken] = x; hitFieldY[hitButNotSunken] = y; // ustwia wspolrzedne ostatnio trafionego pola
                }
            else if (sunkenFieldOFShips[typeOfShip]==1) 
                {sunkenFieldOFShips[typeOfShip]--;
                if(typeOfShip==0){ 
                    windowGame.jTextArea1.append("Komputer zatopił Ci samolot!\n");
                    planesToHit--;
                    windowGame.jLabel8.setForeground(Color.BLACK);
                }
                else if(typeOfShip==1){ 
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek wodny (4 pola). \n");
                        waterShipFourToHit--;
                        windowGame.jLabel1.setForeground(Color.BLACK);
                        }
                else if(typeOfShip==2 || typeOfShip==3){ 
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek wodny (3 pola). \n");
                        waterShipThreeToHit--;
                        windowGame.jLabel2.setForeground(Color.BLACK);
                        }
                else if((typeOfShip>3) && (typeOfShip<7)){ 
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek wodny (2 pola). \n");
                        waterShipTwoToHit--;
                        windowGame.jLabel3.setForeground(Color.BLACK);
                        }
                else if((typeOfShip>6) && (typeOfShip<11)){ 
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek wodny (1 pole).  \n");
                        waterShipOneToHit--;
                        windowGame.jLabel5.setForeground(Color.BLACK);
                        }
                else if(typeOfShip==11){
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek lądowy (4 pola). \n");
                        landShipFourToHit--;
                        windowGame.jLabel4.setForeground(Color.BLACK);
                        }
                else if((typeOfShip>11) && (typeOfShip<14)){
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek lądowy (3 pola). \n");
                        landShipThreeToHit--;
                        windowGame.jLabel7.setForeground(Color.BLACK);
                        }
                else if((typeOfShip>13) && (typeOfShip<17)){
                        windowGame.jTextArea1.append("Komputer zatopił Ci statek lądowy (2 pola). \n");
                        landShipTwoToHit--;
                        windowGame.jLabel6.setForeground(Color.BLACK);
                        }
                strzelonoPole();
                hitButNotSunken++; 
                hitFieldX[hitButNotSunken] = x; hitFieldY[hitButNotSunken] = y;
                rysujObwodke = true;
                }
            else windowGame.jTextArea1.append("Komputer próbował zestrzelić statek już zestrzelony. \n");

            }
        else{
            board[x][y] = 3; 
            if(komunikaty==true){
                windowGame.jTextArea1.append("Komputer nie trafił. \n"); ;}
            mishit = true;
            }
        gameEngine.hitField(x,y); 

        setSubs();
        
        if(rysujObwodke == true){
            selectCircuit();
            hitButNotSunken = 0;
        }    
    }
    
    public boolean getMishit() { //TODO: uzupełnić java-doc.
        return mishit;
    }
    
    public void setMishit(boolean pudlo) { //TODO: uzupełnić java-doc.
        this.mishit = pudlo;
    }
    
    public void strzelonoPole() { //TODO: uzupełnić java-doc.
        hitsFields++;
        if(hitsFields>=47) {
            JOptionPane.showMessageDialog(null, "Przegrałeś! Wygrał komputer!"); 
        }
    }
    
    private void ustalPola() { //TODO: uzupełnić java-doc.
        sunkenFieldOFShips[0]=11;                    
        sunkenFieldOFShips[1]=4;                 
        sunkenFieldOFShips[2]=3;  
        sunkenFieldOFShips[3]=3;                      
        sunkenFieldOFShips[4] = 2;  
        sunkenFieldOFShips[5] = 2;  
        sunkenFieldOFShips[6] = 2;                   
        sunkenFieldOFShips[7] = 1; 
        sunkenFieldOFShips[8] = 1; 
        sunkenFieldOFShips[9] = 1; 
        sunkenFieldOFShips[10] = 1;                   
        sunkenFieldOFShips[11] = 4;                
        sunkenFieldOFShips[12] = 3; 
        sunkenFieldOFShips[13] = 3;                  
        sunkenFieldOFShips[14] = 2;
        sunkenFieldOFShips[15] = 2;
        sunkenFieldOFShips[16] = 2;                   
    
    }
    
    public void setSubs() { //TODO: uzupełnić java-doc.
        windowGame.jLabel1.setText(""+(1-waterShipFourToHit));
        windowGame.jLabel2.setText(""+(2-waterShipThreeToHit));
        windowGame.jLabel3.setText(""+(3-waterShipTwoToHit));
        windowGame.jLabel5.setText(""+(4-waterShipOneToHit));
        windowGame.jLabel4.setText(""+(1-landShipFourToHit));
        windowGame.jLabel7.setText(""+(2-landShipThreeToHit));
        windowGame.jLabel6.setText(""+(3-landShipTwoToHit));
        windowGame.jLabel8.setText(""+(1-planesToHit));
    }
   
    public void randomShot() { //TODO: uzupełnić java-doc.
        mishit = false;
        Random m = new Random();
        boolean trafiony = true;
        int x = 0;
        int y = 0;
        int proba = 0;
        if(hitButNotSunken>0){
            while(trafiony){
                proba = 1+m.nextInt(hitButNotSunken); 
                
                x = hitFieldX[proba] - 1 + m.nextInt(3);
                if( x == hitFieldX[proba]){
                    y = hitFieldY[proba] - 1 + m.nextInt(3);
                }
                else{
                    y = hitFieldY[proba];
                }
                
                if ((x<14 && x>=0 && y<22 && y>=0) && board[x][y] != 4 && board[x][y] != 3){
                    trafiony = false;
                }   
            }
        
        }
        else {
            while(trafiony){
                x = m.nextInt(14);
                y = m.nextInt(22);
                if (board[x][y] != 4 && board[x][y] != 3) {
                    trafiony = false;
                }   
            }
        }        
        try{Thread.sleep(1000);} catch(InterruptedException e){}
        checkField(x,y);
    }
    
    public void selectCircuit() { //TODO: uzupełnić java-doc.
        try { 
            Thread.sleep(1000); 
        } catch(InterruptedException e){}
        int x = 0;
        int y = 0;
     
        for(int proba = hitButNotSunken ; proba>0 ; proba--) {
            for(int i = 0 ; i<8 ; i++){
                switch(i){
                    case 0: { x=hitFieldX[proba]-1; y=hitFieldY[proba]-1; break;}
                    case 1: {x++;break;}
                    case 2: { x++;break;}
                    case 3: { y++;break;}
                    case 4:{ y++;break;}
                    case 5:{ x--;break;}
                    case 6:{ x--;break;}
                    case 7:{ y--;break;}
                }
                if ((x<14 && x>=0 && y<22 && y>=0) && board[x][y] != 4 && board[x][y] != 3){
                    checkField(x,y,false);
                }   
            }
        }
    }
    
    public void clean() { //TODO: uzupełnić java-doc.
        planesToHit = 1;                  
        waterShipFourToHit = 1;       
        waterShipThreeToHit = 2;      
        waterShipTwoToHit = 3;         
        waterShipOneToHit = 4;        
        landShipFourToHit = 1;    
        landShipThreeToHit = 2;        
        landShipTwoToHit = 3;  
        mishit = false;
        hitsFields=0;
        coordinatesOfShips=editor.getCoordinatesOfShips();
        board = gameEngine.getBoard();
    }
}
