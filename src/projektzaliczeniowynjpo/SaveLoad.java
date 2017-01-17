/*
 * Projekt z NJPO (15.01.2017). 
 * Kamil Zemczak.
 */

package projektzaliczeniowynjpo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Klasa odpowiedzialna za zapis oraz ładowanie.
 */
public class SaveLoad implements Serializable {
    private GameEngine gameEngine;
    private Editor editor;
    
    public SaveLoad(GameEngine ganeEngine, Editor editor) {
        this.gameEngine = ganeEngine;
        this.editor = editor;
    }
    
    /**
     * Metoda odpowiedzialna za zapis.
     */    
    public void Save(String file_name) throws IOException{
             ObjectOutputStream file = null;
        try {
            file = new ObjectOutputStream(new FileOutputStream(file_name));
            file.writeObject(gameEngine.getBoard());
            file.writeObject(gameEngine.getTemp()); 
            file.writeObject(gameEngine.getChanges()); //TODO: zmienić nazwę zmiennej bo mylna.
            file.writeObject(editor.getCoordinatesOfShips());  
            file.writeObject(editor.getNumberOfShipsBuff()); 
            file.writeObject(editor.getZmian()); //TODO: wymyśleć nazwę zmiennej po angielsku.                     
            file.flush();
        }
        finally {
            if(file!= null)
                file.close();
        }    
    }
    
    /**
     * Metoda odpowiedzialna za załadowanie pliku.
     */
    public void Load(String file_name) throws IOException, ClassNotFoundException{
        ObjectInputStream file2 = null;
        int[][] board = null;
        int[][][] temp = null;
        int szmian = 0; //TODO: wymyśleć nazwę zmiennej po angielsku.
        
        int[][][] coordinatesOfShips = null;
        int[][] numberOfShipsBuff = null;
        int ezmian = 0; //TODO: wymyśleć nazwę zmiennej po angielsku.
        
        try {
            file2 = new ObjectInputStream(new FileInputStream(file_name));
            board = (int[][])file2.readObject();
            temp = (int[][][])file2.readObject();
            szmian = (int)file2.readObject();
            
            coordinatesOfShips = (int[][][])file2.readObject();
            numberOfShipsBuff = (int[][])file2.readObject();
            ezmian = (int)file2.readObject(); //TODO: wymyśleć nazwę zmiennej po angielsku.
        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        }
        finally {
            if(file2!= null)
                file2.close();
        }
        
        gameEngine.setBoard(board);
        gameEngine.setTemp(temp);
        gameEngine.setChanges(szmian);
        
        editor.setZmian(ezmian);
        editor.setCoordinatesOfShips(coordinatesOfShips);
        editor.setNumberOfShipsBuff(numberOfShipsBuff);       
    }
}
