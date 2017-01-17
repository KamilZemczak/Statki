/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

/*
 * Klasa odpowiedzialna za nasz silnik gry.
 */
public class GameEngine {
    
    private int[][] board;
    private int[][] editor;
 
    private int[][][] temp;
    private int changes = 0;
    private static final byte WIDTH_BOARD = 14;
    private static final byte HEIGHT_BOARD = 22;
    private Editor Edytor;
    private int floor;
    private GameWindow windowGame;
     
    public GameEngine(Editor Edytor, GameWindow oknogry) {
        this.windowGame = windowGame;  
        this.Edytor = Edytor;
        board = new int[WIDTH_BOARD][HEIGHT_BOARD];       
        temp = new int[10][WIDTH_BOARD][HEIGHT_BOARD];
        cleanBoard();                                                    
    }
    
   /*
    * Funkcja czyszcząca ekran.
    */
    public void cleanBoard() {
         for(int x = 0 ; x<WIDTH_BOARD ; x++){
            for(int y=0; y<HEIGHT_BOARD ; y++){
                if(y>15){
                    board[x][y] = 1;
                    temp[changes][x][y] = 1;
                }
                else {
                    board[x][y] = 0;

                    temp[changes][x][y] = 0;
                }
            }
        } 
    }
    
    public void clean() { //TODO: uzupełnić java-doc.
        for (int i = 9; i >=0; i--){
            this.changes = i;
            cleanBoard();
        } 
    }
       
    public void setField(int x, int y) {      //TODO: uzupełnić java-doc.
        if (Edytor.checkEmpty()==false){windowGame.jTextArea1.append("Nie można już postawić statków tego typu, ilość została wyczerpana. \n");} 
        else{   

            memoryAdd();
            
            int[][] wspolrzedne = new int[WIDTH_BOARD][HEIGHT_BOARD];

            floor = Edytor.getFloor();
            editor = Edytor.getEditor();
            boolean error = false;

            boolean position_xe_plus_1;
            boolean position_xe_minus_1;
            boolean position_ye_plus_1;
            boolean position_ye_minus_1;

            boolean position_xe_plus_1_ye_plus_1;
            boolean position_xe_minus_1_ye_minus_1;
            boolean position_ye_plus_1_xe_minus_1;
            boolean position_ye_minus_1_xe_plus_1;



            for(int xe = 0 ; xe<5 ; xe++){
                for(int ye=0; ye<5 ; ye++){
                    if (editor[xe][ye] == 2){

                        if ((x-2+xe) >= WIDTH_BOARD || (y-2+ye) >= HEIGHT_BOARD || (y-2+ye)<0 || (x-2+xe)<0  ){System.out.println("Próbujesz umieścić pojazd poza obszarem mapy!");error = true; break;}  


                        if (x-2+xe>=13) position_xe_plus_1= true;                        
                        else position_xe_plus_1 = temp[changes][x-2+xe+1][y-2+ye] != 2;
                        if (x-2+xe<=0) position_xe_minus_1 = true;  
                        else position_xe_minus_1 = temp[changes][x-2+xe-1][y-2+ye] != 2;  
                        if (y-2+ye>=21) position_ye_plus_1 = true;
                        else position_ye_plus_1 = temp[changes][x-2+xe][y-2+ye+1] != 2;
                        if(y-2+ye<=0) position_ye_minus_1 = true;
                        else position_ye_minus_1 = temp[changes][x-2+xe][y-2+ye-1] != 2;


                        if (x-2+xe>=13 || y-2+ye>=21) position_xe_plus_1_ye_plus_1= true;                
                        else position_xe_plus_1_ye_plus_1 = temp[changes][x-2+xe+1][y-2+ye+1] != 2;
                        if (x-2+xe<=0 || y-2+ye<=0) position_xe_minus_1_ye_minus_1 = true;
                        else position_xe_minus_1_ye_minus_1 = temp[changes][x-2+xe-1][y-2+ye-1] != 2;
                        if (y-2+ye>=21 || x-2+xe<=0) position_ye_plus_1_xe_minus_1 = true;
                        else position_ye_plus_1_xe_minus_1 = temp[changes][x-2+xe-1][y-2+ye+1] != 2;
                        if(y-2+ye<=0 || x-2+xe>=13) position_ye_minus_1_xe_plus_1 = true;
                        else position_ye_minus_1_xe_plus_1 = temp[changes][x-2+xe+1][y-2+ye-1] != 2;


                        if(position_xe_plus_1 && position_xe_minus_1 && position_ye_plus_1 && position_ye_minus_1 &&
                                position_xe_plus_1_ye_plus_1 && position_xe_minus_1_ye_minus_1 && 
                                position_ye_plus_1_xe_minus_1 && position_ye_minus_1_xe_plus_1){


                                if (floor==9){
                                    board[x-2+xe][y-2+ye] = editor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = editor[xe][ye];
                                    }
                                else if (board[x-2+xe][y-2+ye]==floor)
                                    {board[x-2+xe][y-2+ye] = editor[xe][ye];
                                    wspolrzedne[x-2+xe][y-2+ye] = editor[xe][ye];} 
                                else {windowGame.jTextArea1.append("Niewłaściwy typ statku w stosunku do podłoża \n");error = true; break;}


                        }    
                        else {windowGame.jTextArea1.append("Statek nie może dotykać innych statków. \n");error = true; break;}
                    }   
                if (error == true) break;
                }
              if (error == true) break;  
            }
            if (error == true) {back(); } 
            else Edytor.confirmShip(wspolrzedne);                                                                  

        }
    }
      
    public void memoryAdd() { //TODO: uzupełnić java-doc.
        
        if (changes==9){                                     
            for (int i = 0 ; i <9 ; i++){
                for(int xs = 0 ; xs<WIDTH_BOARD ; xs++){
                    for(int ys=0; ys<HEIGHT_BOARD ; ys++){          
                        temp[i][xs][ys]=temp[i+1][xs][ys];   
                    }
                }
            }
            changes--;                                     
        }
        changes++;
        for(int xs = 0 ; xs<WIDTH_BOARD ; xs++){
            for(int ys=0; ys<HEIGHT_BOARD ; ys++){
                temp[changes][xs][ys]=board[xs][ys];
            }
        }
    
    }
      
    public void back() { //TODO: uzupełnić java-doc.
        if (changes>=1){
            for(int x = 0 ; x<WIDTH_BOARD ; x++){
                for(int y=0; y<HEIGHT_BOARD ; y++){
                    board[x][y] = temp[changes][x][y];
                }
            }
            changes--;
        }
    }
    
    public void hitField(int x, int y) { //TODO: uzupełnić java-doc.
        if (board[x][y]==2) board[x][y]=4;
        else board[x][y]=3;  
    }
                                          
    public int[][] getBoard() { //TODO: uzupełnić java-doc.
        return board;
    }
    
    public int[][][] getTemp() { //TODO: uzupełnić java-doc.
        return temp;
    }
    
    public int getChanges() { //TODO: uzupełnić java-doc.
        return changes;
    }
      
    public void setTemp(int[][][] temp) { //TODO: uzupełnić java-doc.
        this.temp = temp;
    }
    
    public void setBoard(int[][] board) { //TODO: uzupełnić java-doc.
        this.board = board;
    }
    
    public void setChanges(int changes) { //TODO: uzupełnić java-doc.
        this.changes = changes;
    }
    
    public void clean1(){
        changes = 0;
        cleanBoard();
    }
}
