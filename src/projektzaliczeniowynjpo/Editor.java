/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

import java.util.Calendar;

/**
 * Klasa odpowiedzialna za ustawianie statków.
 */
public class Editor {
    private int[][] editor;
    
    /**
     * @typeOfFloor - rodzaj (0, 1, 2) podłoża na którym ustawiany jest statek.
     * 0 = water.
     * 1 = land.
     * 9 = anything.
     */
    private int typeOfFloor =  0;  
    
    /**
     * Określa czy z edytora (editor) zostało usunięte pole statku. Odblokowuje to możliwość edycji statku.
     */
    private boolean downloaded = false; 
    private boolean editable = true;
    private GameWindow gameWindow;
    
    private int planes = 1;                  
    private int waterShipFour = 1;       
    private int waterShipThree=  2;         
    private int waterShipTwo = 3;         
    private int waterShipOne = 4;       
    private int landShipFour = 1;      
    private int landShipThree = 2;      
    private int landShipTwo = 3;        
    
    /**
     * Tablica do przechowywania współrzędnych jednego konkretnego statku.
     */
    private int[][][] coordinatesOfShips = new int[17][14][22];                                                              
    private int numberOfShip = 9;  //TODO: 9 NIE SYMBOLIZUJE ZADNEGO RODZAJU POJAZDU, WIEC NIECH BEDZIE NA STARCIE ABY aplikacja blednie nie odejmowala liczby w przypadku gdy zadny pojazd nie zostanie wybrany. Zrobione poniewaz null dla inta = 0 a 0 symbolizowalo samolot.
    
    private int[][] numberOfShipsBuff = new int[10][8];
    private int changes = 0;
        
    public Editor(GameWindow gameWindow) { //TODO: uzpełnić javadoc.
        this.gameWindow = gameWindow;    
        editor = new int[5][5];  
            for(int x = 0 ; x<5 ; x++) {
                for(int y=0; y<5 ; y++) {
                editor[x][y] = 0;
                }
            }
        numberOfShipsBuff[changes][0] = planes;
        numberOfShipsBuff[changes][1] = waterShipFour;   
        numberOfShipsBuff[changes][2] = waterShipThree;      
        numberOfShipsBuff[changes][3] = waterShipTwo;          
        numberOfShipsBuff[changes][4] = waterShipOne;        
        numberOfShipsBuff[changes][5] = landShipFour;       
        numberOfShipsBuff[changes][6] = landShipThree;         
        numberOfShipsBuff[changes][7] = landShipTwo;
        cleanCoordinates();
    }
    
    public int[][] getEditor() { //TODO: uzpełnić javadoc.
        return editor;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku wodnego (4 pola) na planszy ręcznie.
     */    
    public void setWaterShipFour() {
        editable = true;
        typeOfFloor = 0;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        editor[4][2] = 2;
        numberOfShip = 1;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku wodnego (3 pola) na planszy ręcznie.
     */    
    public void setWaterShipThree() {
        editable = true;
        typeOfFloor = 0;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        numberOfShip=2;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku wodnego (2 pola) na planszy ręcznie.
     */    
    public void setWaterShipTwo() {
        editable = false;
        typeOfFloor = 0;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        numberOfShip=3;
    }
    
        
    /**
     * Metoda odpowiedzialna za ustawianie statku wodnego (1 pole) na planszy ręcznie.
     */ 
    public void setWaterShipOne() {
        editable = false;
        typeOfFloor = 0;
        editClean();
        editor[2][2] = 2;
        numberOfShip=4;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku lądowego (4 pola) na planszy ręcznie.
     */ 
     public void setLandShipFour() {
        editable = true;
        typeOfFloor = 1;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        editor[4][2] = 2;
        numberOfShip=5;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku lądowego (3 pola) na planszy ręcznie.
     */ 
    public void setLandShipThree() {
        editable = true;
        typeOfFloor = 1;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        editor[3][2] = 2;
        numberOfShip=6;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie statku lądowego (2 pola) na planszy ręcznie.
     */ 
    public void setLandShipTwo() {
        editable = false;
        typeOfFloor = 1;
        editClean();
        editor[1][2] = 2;
        editor[2][2] = 2;
        numberOfShip=7;
    }
    
    /**
     * Metoda odpowiedzialna za ustawianie samolotu na planszy ręcznie.
     */ 
    public void setPlane() {
        editable = false;
        typeOfFloor = 0;
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
        typeOfFloor=9;
        numberOfShip=0;
    }
    
    public void editClean() { //TODO: wymyśleć angielską nazwę funkcji
        for (int x = 0; x < 5; x++) {
            for (int y = 0 ; y<5 ; y++) {
                editor[x][y] = typeOfFloor;
            }
        }
    }
     
    public void edytorWyzerojT() { //TODO: wymyśleć angielską nazwę funkcji 
        editClean();
        typeOfFloor = 1;
        int coDruga = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5 ;y++) {
                if(coDruga%2==1)
                editor[x][y] = typeOfFloor;
                coDruga++;
            }
        }
    }
    
    /**
     * Metoda odpowiedzialna za zmianę pola w edytorze.
     * Sprawdzamy tu również, czy możliwe jest pobranie statku.
     * Sprawdzanie polega na tym, czy pole które pobieramy "jest statkiem", czy statek jest zapamiętany (memory),
     * oraz czzy po zabraniu któryś element statku nie zostanie samotny.
     */ 
     public void setEditor(int x, int y) { // zmiania pola w edytorze
        if (editable == true){
            boolean position_x_plus_1;
            if (x==4) position_x_plus_1= false;
            else position_x_plus_1 = editor[x+1][y] != typeOfFloor;

            boolean position_x_minus_1;
            if (x==0) position_x_minus_1 = false;
            else position_x_minus_1 = editor[x-1][y] != typeOfFloor;

            boolean position_y_plus_1;
            if (y==4) position_y_plus_1 = false;
            else position_y_plus_1 = editor[x][y+1] != typeOfFloor;

            boolean position_y_minus_1;
            if(y==0) position_y_minus_1 = false;
            else position_y_minus_1 = editor[x][y-1] != typeOfFloor;


            if (downloaded == false && editor[x][y] != typeOfFloor && checkShipAlone(x,y)==false) {  
                editor[x][y]=typeOfFloor;                                                        
                downloaded = true;
            }  
            
            /**
             * Kawałek kodu poniżej sprawdza, czy możliwe jest położenie elementu statku.
             * Sprawdzamy sobie czy w pobliskich polach jest jakiś element statku do którego może zostać dodany nowy element.
             * Sprawdzamy również czy statek nie zostanie rozdzielony na pół albo więcej częśc. 
             * Sprawdzamy tu również, czy możliwe jest pobranie statku.
             * Sprawdzanie polega na tym, czy pole które pobieramy "jest statkiem", czy statek jest zapamiętany (memory),
             * oraz czzy po zabraniu któryś element statku nie zostanie samotny.
             */
            else if (downloaded == true && editor[x][y] == typeOfFloor &&                     
                    ( position_x_plus_1 || position_x_minus_1         
                    || position_y_plus_1 || position_y_minus_1 )){ 
                editor[x][y]=2;
                downloaded = false;
            }
            else if ( checkShipAlone(x,y)==true){}  
            else if (downloaded == true  ){}
        }
        else{}
       
    }
    
    private boolean checkShipAlone(int x, int y) { //TODO: uzpełnić javadoc.
        int[][] temp = new int[5][5];
        for (int xt = 0; xt < 5; xt++) {
            for (int yt = 0; yt < 5;yt++) {
                temp[xt][yt] = editor[xt][yt];
            }
        }
        temp[x][y]=typeOfFloor;
        
        boolean pozycja_xt_plus_1;
        boolean pozycja_xt_minus_1;
        boolean pozycja_yt_plus_1;
        boolean pozycja_yt_minus_1;
        
        
        for (int xt = 0; xt < 5; xt++) {
            for (int yt = 0; yt < 5; yt++) {
                if(temp[xt][yt]==2) {
                    
                    if (xt==4) pozycja_xt_plus_1= true;
                    else pozycja_xt_plus_1 = temp[xt+1][yt] == typeOfFloor;
                    if (xt==0) pozycja_xt_minus_1 = true;
                    else pozycja_xt_minus_1 = temp[xt-1][yt] == typeOfFloor;
                    if (yt==4) pozycja_yt_plus_1 = true;
                    else pozycja_yt_plus_1 = temp[xt][yt+1] == typeOfFloor;
                    if(yt==0) pozycja_yt_minus_1 = true;
                    else pozycja_yt_minus_1 = temp[xt][yt-1] == typeOfFloor;
                    
                    if(pozycja_xt_plus_1 && pozycja_xt_minus_1 && pozycja_yt_plus_1 && pozycja_yt_minus_1){ 
                        return true;
                    }
                }
            }
        } return false;
    }
    
    public void obrocEdytorP() { //TODO: zastanowić się czy nie do usunięcia
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[4-x][y] = editor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++) { //TODO: zastanowić się czy nie do usunięcia
            for (int y = 0; y<5 ;y++){
                editor[x][y] = temp[x][y];
            }
        }
    }
    
    public void obrocEdytorL() {  //TODO: zastanowić się czy nie do usunięcia
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[x][4-y] = editor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                editor[x][y] = temp[x][y];
            }
        }
    }
    
    public void odbijEdytor() { //TODO: zastanowić się czy nie do usunięcia
        int[][] temp = new int[5][5];
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                temp[x][y] = editor[y][x];
            }
        }
        for (int x = 0; x<5 ;x++){
            for (int y = 0; y<5 ;y++){
                editor[x][y] = temp[x][y];
            }
        }
    }
    
    public int getFloor() { //TODO: uzupełnić java-doc
        return typeOfFloor;
    }
    
    public void setSubs() { //TODO: uzupełnić java-doc
        gameWindow.jLabel1.setText(""+waterShipFour);
        gameWindow.jLabel2.setText(""+waterShipThree);
        gameWindow.jLabel3.setText(""+waterShipTwo);
        gameWindow.jLabel5.setText(""+waterShipOne);
        gameWindow.jLabel4.setText(""+landShipFour);
        gameWindow.jLabel7.setText(""+landShipThree);
        gameWindow.jLabel6.setText(""+landShipTwo);
        gameWindow.jLabel8.setText(""+planes);
    }
    
    public void confirmShip(int[][] coordinates) { //TODO: uzupełnić java-doc
        switch(numberOfShip){
            case 0:
                coordinatesOfShips[0] = coordinates;
                planes--; break;
            case 1:coordinatesOfShips[1] = coordinates;
                waterShipFour--; break;
            case 2: if(waterShipThree==2){
                        coordinatesOfShips[2]=coordinates;
                        waterShipThree--;
                        break;}
                    else{
                        coordinatesOfShips[3]=coordinates; 
                        waterShipThree--; 
                        break;}
            case 3: if(waterShipTwo==3){
                        coordinatesOfShips[4]=coordinates;
                        waterShipTwo--;
                        break;
                    }
                    else if(waterShipTwo==2){
                        coordinatesOfShips[5]=coordinates;
                        waterShipTwo--;
                        break;
                    }
                    else {
                        coordinatesOfShips[6]=coordinates;
                        waterShipTwo--;
                        break;
                    }
            case 4:if(waterShipOne==4){
                        coordinatesOfShips[7]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else if(waterShipOne==3){
                        coordinatesOfShips[8]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else if(waterShipOne==2){
                        coordinatesOfShips[9]=coordinates;
                        waterShipOne--;
                        break;
                    }
                    else {
                        coordinatesOfShips[10]=coordinates;
                        waterShipOne--;
                        break;
                    }
            case 5:coordinatesOfShips[11] = coordinates;
                landShipFour--; break;
            case 6:if(landShipThree==2){
                        coordinatesOfShips[12]=coordinates;
                        landShipThree--;
                        break;}
                    else{
                        coordinatesOfShips[13]=coordinates; 
                        landShipThree--; 
                        break;}
            case 7:if(landShipTwo==3){
                        coordinatesOfShips[14]=coordinates;
                        landShipTwo--;
                        break;
                    }
                    else if(landShipTwo==2){
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
        memoryAdd();
        setSubs();
    }
    
    public boolean checkEmpty() { //TODO: uzupełnić java-doc
        boolean notEmpty = true;
        switch(numberOfShip) {
            case 0:if (planes == 0)               notEmpty = false; break;                 
            case 1:if (waterShipFour == 0)   notEmpty = false; break;        
            case 2:if (waterShipThree == 0)     notEmpty = false; break;         
            case 3:if (waterShipTwo == 0)      notEmpty = false; break;          
            case 4:if (waterShipOne == 0)    notEmpty = false; break;         
            case 5:if (landShipFour == 0)  notEmpty = false; break;       
            case 6:if (landShipThree == 0)    notEmpty = false; break;         
            case 7:if (landShipTwo == 0)     notEmpty = false; break;         
        } return notEmpty;
    }  
    
    /*
     * Metoda odpowiedzialna za ładowanie statku do schowka / pamięci.
     */
    public void memoryAdd() { //TODO: uzupełnić java-doc
        if (changes==9){                                          
            for (int i = 0 ; i <9 ; i++){
                for (int j = 0 ; j<8 ; j++){
                    numberOfShipsBuff[i][j]=numberOfShipsBuff[i+1][j];  
                }
            }
        changes--;                                             
        }
        
        changes++;                                                
        numberOfShipsBuff[changes][0] = planes;
        numberOfShipsBuff[changes][1] = waterShipFour;   
        numberOfShipsBuff[changes][2] = waterShipThree;      
        numberOfShipsBuff[changes][3] = waterShipTwo;          
        numberOfShipsBuff[changes][4] = waterShipOne;        
        numberOfShipsBuff[changes][5] = landShipFour;       
        numberOfShipsBuff[changes][6] = landShipThree;         
        numberOfShipsBuff[changes][7] = landShipTwo;   
    }
    
    public void back() { //TODO: uzupełnić java-doc
        
        if (changes>=1){
            changes--;
            planes                = numberOfShipsBuff[changes][0];  
            waterShipFour    = numberOfShipsBuff[changes][1];   
            waterShipThree      = numberOfShipsBuff[changes][2];      
            waterShipTwo       = numberOfShipsBuff[changes][3];          
            waterShipOne     = numberOfShipsBuff[changes][4]; 
            landShipFour   = numberOfShipsBuff[changes][5];       
            landShipThree     = numberOfShipsBuff[changes][6];        
            landShipTwo      = numberOfShipsBuff[changes][7]; 
            setSubs();                                             
        }
        else gameWindow.jTextArea1.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+":"+Calendar.getInstance().get(Calendar.SECOND)+":"+Calendar.getInstance().get(Calendar.MILLISECOND)+"  "+"Można cofnąć tylko do 10 ruchów! \n");
        
    }
    
    public void cleanCoordinates() { //TODO: uzupełnić java-doc
        for (int i = 0 ; i< 17 ; i++){
            for (int x = 0 ; x<14 ; x++){
                for (int y = 0 ; y<22 ; y++){
                        coordinatesOfShips[i][x][y]=0; 
                }
            }
        }
    }
    
    public void zeroj() { //TODO: uzupełnić java-doc + nazwa
        cleanCoordinates();
        planes = 1;                  
        waterShipFour = 1;       
        waterShipThree = 2;         
        waterShipTwo = 3;          
        waterShipOne = 4;        
        landShipFour = 1;      
        landShipThree = 2;        
        landShipTwo = 3;         
        setSubs();
    }
 
    public int[][][] getCoordinatesOfShips() { //TODO: uzupełnić java-doc
        return coordinatesOfShips;
    }
    public int[][] getNumberOfShipsBuff() { //TODO: uzupełnić java-doc
        return numberOfShipsBuff;
    } 
    public int getZmian() { //TODO: uzupełnić java-doc + nazwa 
        return changes;
    }
    
    public void setCoordinatesOfShips(int[][][] coordinatesOfShips) { //TODO: uzupełnić java-doc
        this.coordinatesOfShips = coordinatesOfShips;
    }
    public void setNumberOfShipsBuff(int[][] numberOfShipsBuff) { //TODO: uzupełnić java-doc
        this.numberOfShipsBuff = numberOfShipsBuff;
            planes = numberOfShipsBuff[changes][0];  
            waterShipFour = numberOfShipsBuff[changes][1];   
            waterShipThree = numberOfShipsBuff[changes][2];      
            waterShipTwo = numberOfShipsBuff[changes][3];          
            waterShipOne = numberOfShipsBuff[changes][4]; 
            landShipFour = numberOfShipsBuff[changes][5];       
            landShipThree = numberOfShipsBuff[changes][6];        
            landShipTwo = numberOfShipsBuff[changes][7];
            setSubs();
    } 
    
    public void setZmian(int zmian) {
        this.changes = zmian;
    }
    
    public void setNumberOfShips(int[] iloscistatkow){  
        this.planes = iloscistatkow[0];  
        this.waterShipFour = iloscistatkow[1];   
        this.waterShipThree = iloscistatkow[2];      
        this.waterShipTwo = iloscistatkow[3];          
        this.waterShipOne = iloscistatkow[4]; 
        this.landShipFour = iloscistatkow[5];       
        this.landShipThree  = iloscistatkow[6];        
        this.landShipTwo = iloscistatkow[7];
        setSubs();
    }
    
    public void clean() { //TODO: uzupełnić java-doc
        planes = 1;                   
        waterShipFour = 1;      
        waterShipThree = 2;       
        waterShipTwo = 3;          
        waterShipOne = 4;       
        landShipFour = 1;      
        landShipThree = 2;        
        landShipTwo = 3;        
        numberOfShip = 9;  
        changes = 0;
        for(int x = 0; x < 5; x++) {
            for(int y=0; y<5 ; y++) {
            editor[x][y] = 0;
            }
        }
        numberOfShipsBuff[changes][0] = planes;
        numberOfShipsBuff[changes][1] = waterShipFour;   
        numberOfShipsBuff[changes][2] = waterShipThree;      
        numberOfShipsBuff[changes][3] = waterShipTwo;          
        numberOfShipsBuff[changes][4] = waterShipOne;        
        numberOfShipsBuff[changes][5] = landShipFour;       
        numberOfShipsBuff[changes][6] = landShipThree;         
        numberOfShipsBuff[changes][7] = landShipTwo;
        cleanCoordinates();
    } 
}
