/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

import java.util.Random;

/*
 * Klasa odpowiedzialna za ustawianie losowo statków na polu.
 */
public class RandomField {
    private int[][] board ;
    
    /**
     * @temp - plansza tymczasowa.
     */ 
    private int[][] temp;  
    
    private int planes = 1;                  
    private int waterShipFour = 1;       
    private int waterShipThree = 2;       
    private int waterShipTwo = 3;         
    private int waterShipOne = 4;       
    private int landShipFour = 1;      
    private int landShipThree = 2;        
    private int landShipTwo = 3;
    
    /**
     * Tablica do przechowywania konkretnych statków
     * 9 nie oznacza żadnego statku, stąd to dałem.
     */ 
    private int[][][] coordinatesOfShips = new int[17][14][22]; 

    private int numberOfShips = 9;  
    private int floor;
    
    private static final byte WIDTH_BOARD = 14;
    private static final byte HEIGHT_BOARD = 22;
    
    private int[][] editor;
    
    private boolean addSucces = false;
    
    /**
     * Ustawianie losowego pola.
     */ 
    public RandomField() {
        board = new int[WIDTH_BOARD][HEIGHT_BOARD]; 
        temp = new int[WIDTH_BOARD][HEIGHT_BOARD];  
       
        for(int x = 0;  x < WIDTH_BOARD; x++) {
            for(int y = 0; y < HEIGHT_BOARD; y++) {
                if(y > 15) {
                    board[x][y] = 1;
                    temp[x][y] = 1;
                }
                else {
                    board[x][y] = 0;
                    temp[x][y] = 0;
                }
            }
        }
        editor = new int[5][5];  
            for(int x = 0 ; x < 5; x++) {
                for(int y = 0; y < 5; y++) {
                    editor[x][y] = 0;
                }
            } setShips();
        }
    
    /**
     * Metoda odpowiedzialna o informacji o tym, które meijsce na naszej planszy kliknięto.
     * Sprawdzamy też czy pozycja nie wychodzi poza zakres naszego pola do gry.
     * Sprawdza też czy w pobliżu nie ma innego statku.
     */
    public void setField(int x, int y) {  
                    for (int ytt = 0; ytt < 22; ytt++) {
                        for(int xtt = 0; xtt < 14; xtt++) {
                            temp[xtt][ytt] = board[xtt][ytt];
                        }
                    }
            int[][] wspolrzedne = new int[WIDTH_BOARD][HEIGHT_BOARD];

            boolean error = false;

            boolean position_xe_plus_1;
            boolean position_xe_minus_1;
            boolean position_ye_plus_1;
            boolean position_ye_minus_1;

            boolean position_xe_plus_1_ye_plus_1;
            boolean position_xe_minus_1_ye_minus_1;
            boolean position_ye_plus_1_xe_minus_1;
            boolean position_ye_minus_1_xe_plus_1;

            for(int xe = 0; xe < 5; xe++) {
                for(int ye = 0; ye < 5; ye++) {
                    if (editor[xe][ye] == 2) { 

                        if ((x-2+xe) >= WIDTH_BOARD || (y-2+ye) >= HEIGHT_BOARD || (y-2+ye)<0 || (x-2+xe)<0) { 
                            error = true; break; 
                        } 
                        if (x-2+xe>=13) position_xe_plus_1= true;                     
                        else position_xe_plus_1 = temp[x-2+xe+1][y-2+ye] != 2;
                        if (x-2+xe<=0) position_xe_minus_1 = true; 
                        else position_xe_minus_1 = temp[x-2+xe-1][y-2+ye] != 2;
                        if (y-2+ye>=21) position_ye_plus_1 = true;
                        else position_ye_plus_1 = temp[x-2+xe][y-2+ye+1] != 2;
                        if(y-2+ye<=0) position_ye_minus_1 = true;
                        else position_ye_minus_1 = temp[x-2+xe][y-2+ye-1] != 2;

                        if (x-2+xe>=13 || y-2+ye>=21) position_xe_plus_1_ye_plus_1= true;              
                        else position_xe_plus_1_ye_plus_1 = temp[x-2+xe+1][y-2+ye+1] != 2;
                        if (x-2+xe<=0 || y-2+ye<=0) position_xe_minus_1_ye_minus_1 = true;
                        else position_xe_minus_1_ye_minus_1 = temp[x-2+xe-1][y-2+ye-1] != 2;
                        if (y-2+ye>=21 || x-2+xe<=0) position_ye_plus_1_xe_minus_1 = true;
                        else position_ye_plus_1_xe_minus_1 = temp[x-2+xe-1][y-2+ye+1] != 2;
                        if(y-2+ye<=0 || x-2+xe>=13) position_ye_minus_1_xe_plus_1 = true;
                        else position_ye_minus_1_xe_plus_1 = temp[x-2+xe+1][y-2+ye-1] != 2;

                        if(position_xe_plus_1 && position_xe_minus_1 && position_ye_plus_1 && position_ye_minus_1 &&
                                position_xe_plus_1_ye_plus_1 && position_xe_minus_1_ye_minus_1 && 
                                position_ye_plus_1_xe_minus_1 && position_ye_minus_1_xe_plus_1) {

                                if (floor==9) {
                                    board[x-2+xe][y-2+ye] = editor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = editor[xe][ye]; }
                                else if (board[x-2+xe][y-2+ye]==floor) {
                                    board[x-2+xe][y-2+ye] = editor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = editor[xe][ye]; }  
                                else {
                                    error = true; 
                                    break;
                                }
                        }    
                        else { 
                            error = true; 
                            break;
                        }
                    }   
                if (error == true) 
                    break;
                }
              if (error == true) 
                  break;  
            }           
            if (error == true) {
                        for (int ytt = 0; ytt < 22; ytt++) {
                            for(int xtt = 0; xtt < 14; xtt++) {
                                board[xtt][ytt] = temp[xtt][ytt];
                            }
                        }
                    } 
            else {
                confirmShip(wspolrzedne); 
                addSucces=true;
            }                                             ///TODO ktory w tym miejscu bedzie przekazywany do edytora ktory go zatwierdzi i zapisze . Trzeba znalezc odpowiednia tablice do przechowywania aby pozniejsze poszukiwanie obiektów było łatwe. Pomysł na szybko -> dla kazdego statku osobna tablica wielkosci planszzy  i wszystkie te teablice w jednej tablicy. Łatwe przeszukiwanie ich bedzie.

           //TODO po zakonczonym powodzeniu dodawania przepisac wartosci danego statku do osobnej tablicy, a na poczatku gry ustalic jakiego typu statek dodajemy. Dodawac nalezy od najwiekszego do najmniejszego. Statki nalezy losowo obracac.
    }
    
    /**
     * Setter dla wodnego statku (4 polowego).
     */ 
    public void setWaterShipFour() {
        floor = 0;
        edytorWyzeroj();
     
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        editor[4][2] = 2;
        numberOfShips=1;
    }
    
    /**
     * Setter dla wodnego statku (3 polowego).
     */ 
    public void setWaterShipThree() {
        floor = 0;
        edytorWyzeroj();
 
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        numberOfShips=2;
    }
    
     /**
     * Setter dla wodnego statku (2 polowego).
     */ 
    public void setWaterShipTwo() {
        floor = 0;
        edytorWyzeroj();
     
        editor[1][2] = 2;
        editor[2][2] = 2;
        numberOfShips=3;
    }
    
     /**
     * Setter dla wodnego statku (1 polowego).
     */ 
    public void setWaterShipOne() {
        floor = 0;
        edytorWyzeroj();
  
        editor[2][2] = 2;
        numberOfShips=4;
    }
    
    /**
     * Setter dla lądowego statku (4 polowego).
     */ 
     public void setLandShipFour() {
        floor = 1;
        edytorWyzeroj();
       
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        editor[4][2] = 2;
        numberOfShips=5;
    }
    
    /**
     * Setter dla lądowego statku (3 polowego).
     */  
    public void setLandShipThree() {
        floor = 1;
        edytorWyzeroj();
      
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        numberOfShips=6;
    }
    
    /**
     * Setter dla lądowego statku (2 polowego).
     */ 
    public void setLandShipTwo() {
        floor = 1;
        edytorWyzeroj();
       
        editor[1][2] = 2;
        editor[2][2] = 2;
        numberOfShips=7;
    }
    
    /**
     * Setter dla samolotu.
     */ 
    public void setPlane() {
        floor = 0;
        edytorWyzerojT();
   
        editor[2][0] = 2;
        editor[2][1] = 2;
        editor[2][2] = 2;
        editor[2][3] = 2;
        editor[2][4] = 2;
        editor[0][4] = 2;
        editor[1][4] = 2;
        editor[3][4] = 2;
        editor[4][4] = 2;
        editor[4][3] = 2;
        editor[0][3] = 2;
        floor = 9;
        numberOfShips = 0;
    }
    
    public void edytorWyzerojT() { //TODO: wymyśleć nazwę metody po angielsku
        edytorWyzeroj();
        floor = 1;
        int coDruga = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if(coDruga%2==1)
                editor[x][y] = floor;
                coDruga++;
            }
        } 
    }
    
    public void edytorWyzeroj() { //TODO: wymyśleć nazwę metody po angielsku
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++){ 
                editor[x][y] = floor;
            }
        }
    }
    
    public void obrocEdytorP() { //TODO: wymyśleć nazwę metody po angielsku
        int[][] temp = new int[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                temp[4-x][y] = editor[y][x];
            }
        }
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                editor[x][y] = temp[x][y];
            }
        }
    }
    
    /**
     * Metoda potierdzająca położenie statku.
     */ 
    public void confirmShip(int[][] coordinates) {
        switch(numberOfShips) {
            case 0:
                coordinatesOfShips[0] = coordinates;
                planes--; 
                break;
            case 1: coordinatesOfShips[1] = coordinates;
                waterShipFour--; 
                break;
            case 2: if(waterShipThree==2) {
                        coordinatesOfShips[2]=coordinates;
                        waterShipThree--;
                        break; 
                    }
                    else {
                        coordinatesOfShips[3]=coordinates; 
                        waterShipThree--; 
                        break; 
                    }
            case 3: if(waterShipTwo==3) {
                        coordinatesOfShips[4]=coordinates;
                        waterShipTwo--;
                        break;
                    }
                    else if(waterShipTwo==2) {
                        coordinatesOfShips[5]=coordinates;
                        waterShipTwo--;
                        break;
                    }
                    else {
                        coordinatesOfShips[6]=coordinates;
                        waterShipTwo--;
                        break;
                    }
            case 4: if(waterShipOne==4) {
                        coordinatesOfShips[7]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else if(waterShipOne==3) {
                        coordinatesOfShips[8]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else if(waterShipOne==2) {
                        coordinatesOfShips[9]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else {
                        coordinatesOfShips[10]=coordinates;
                        waterShipOne--;
                        break;
                    }
            case 5: coordinatesOfShips[11] = coordinates;
                landShipFour--; 
                break;
            case 6: if(landShipThree==2) {
                        coordinatesOfShips[12]=coordinates;
                        landShipThree--;
                        break; 
                    }
                    else {
                        coordinatesOfShips[13]=coordinates; 
                        landShipThree--; 
                        break; 
                    }
            case 7: if(landShipTwo==3) {
                        coordinatesOfShips[14]=coordinates;
                        landShipTwo--;
                        break;
                    }
                    else if(landShipTwo==2) {
                        coordinatesOfShips[15]=coordinates;
                        landShipTwo--;
                        break;
                    }
                    else {
                        coordinatesOfShips[16]=coordinates;
                        landShipTwo--;
                        break;
                    }
                }
            }
    
    /**
     * Metoda odpowiadająca za losowe ustawianie statków na planszy.
     */ 
    public void setShips() { 
     Random ran = new Random();
     int x;
     int y;
     int rotate;
     
     setPlane();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (4 polowego).
     */ 
     setLandShipFour();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
    
    /**
     * Ustawienie wodnego statku (4 polowego).
     */ 
     setWaterShipFour();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (3 polowego).
     */
     setLandShipThree();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (3 polowego).
     */
     setLandShipThree();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (2 polowego).
     */
     setLandShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (2 polowego).
     */
     setLandShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie lądowego statku (2 polowego).
     */
     setLandShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (3 polowego).
     */
      setWaterShipThree();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (3 polowego).
     */
     setWaterShipThree();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (2 polowego).
     */
     setWaterShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (2 polowego).
     */
     setWaterShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (2 polowego).
     */
     setWaterShipTwo();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        rotate = ran.nextInt(2);
        if(rotate == 1) obrocEdytorP();
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (1 polowego).
     */
     setWaterShipOne();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (1 polowego).
     */
     setWaterShipOne();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (1 polowego).
     */
     setWaterShipOne();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setField(x,y);  
    }
     
    /**
     * Ustawienie wodnego statku (1 polowego).
     */
     setWaterShipOne();
     addSucces = false;
     while(addSucces == false) {
        x = ran.nextInt(14);
        y = ran.nextInt(22);
        setField(x,y);  
    }
}
    
    public int[][] getBoard() { //TODO: uzupełnić java-doc
        return board;
    }
    
    /**
     * Metoda odczytująca i zwracająca ilości statków.
     */ 
    public int[] getNumberOfShips() {
        int[] numberOfShips = new int[8];
        numberOfShips[0] = planes;  
        numberOfShips[1] = waterShipFour;   
        numberOfShips[2] = waterShipThree;      
        numberOfShips[3] = waterShipTwo;          
        numberOfShips[4] = waterShipOne; 
        numberOfShips[5] = landShipFour;       
        numberOfShips[6] = landShipThree;        
        numberOfShips[7] = landShipTwo;
        return numberOfShips;
    }
    
    /**
     * Metoda zwracająca współrzędne statków.
     */ 
    public int[][][] getCoordinationsOfShips() {
        return coordinatesOfShips;
    }
    
    public int[][] getNumberOfShipsBuff() { //TODO: uzupełnić java-doc
        
        int[][] numberOfShipsBuff = new int[10][8];
        for(int changes = 0; changes < 10; changes++) {
            for(int i = 0; i < 8; i++) {
                numberOfShipsBuff[changes][i] = 0;   
            }
        } return numberOfShipsBuff;
    }
}
