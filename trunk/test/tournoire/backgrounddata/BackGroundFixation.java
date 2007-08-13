/*
 * BackGroundFixation.java
 * 
 * Created on 13.08.2007, 15:53:55
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tournoire.backgrounddata;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tournoire.AppException;

/**
 *
 * @author rca
 */
public class BackGroundFixation 
{

    public BackGroundFixation() 
    {
        writeBackgroundFiles();
    }
    
    
    public String tempDir;
    
    public void writeBackgroundFiles()
    {
        try
        {

            java.io.File dummyTemp = java.io.File.createTempFile("dummy", "");
            tempDir = dummyTemp.getParent();
            dummyTemp.delete();
            tmpSpielerCsv = new java.io.File(tempDir, "spieler.csv");
            tmpVerbaendeCsv = new java.io.File(tempDir, "verband.csv");
            tmpVereineCsv = new java.io.File(tempDir, "vereine.csv");

            java.io.PrintWriter writer = null;
            writer = new java.io.PrintWriter(tmpSpielerCsv);
            writer.println("\"ZPS\",\"Mgl-Nr\",\"Status\",\"Spielername\",\"Geschlecht\",\"Spielberechtigung\",\"Geburtsjahr\",\"Letzte-Auswertung\",\"DWZ\",\"Index\",\"FIDE-Elo\",\"FIDE-Titel\",\"FIDE-ID\",\"FIDE-Land\"");
            writer.println("\"24421\",\"201\",\"\",\"Volokitin,Andrej\",\"M\",\"A\",\"1986\",\"200715\",\"2698\",\"23\",\"2681\",\"GM\",\"14107090\",\"UKR\"");
            writer.println("\"24421\",\"135\",\"\",\"Bromberger,Stefan\",\"M\",\"D\",\"1982\",\"200730\",\"2461\",\"100\",\"2500\",\"IM\",\"4635248\",\"GER\"");
            writer.println("\"24420\",\"302\",\"P\",\"Bromberger,Stefan\",\"M\",\"D\",\"1982\",\"200730\",\"2461\",\"100\",\"2500\",\"IM\",\"4635248\",\"GER\"");
            writer.println("\"23017\",\"106\",\"\",\"Zwack,Hans\",\"M\",\"D\",\"1990\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");
            writer.println("\"23017\",\"107\",\"\",\"Zwick,Herbert\",\"M\",\"D\",\"1980\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");
            writer.println("\"21206\",\"252\",\"\",\"Zwack,Hansemann\",\"M\",\"D\",\"1958\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"");

            writer.close();

            writer = new java.io.PrintWriter(tmpVerbaendeCsv);
            writer.println("\"Verband\",\"LV\",\"Uebergeordnet\",\"Verbandname\"");
            writer.println("\"200\",\"2\",\"000\",\"Bayerischer Schachbund\"");
            writer.println("\"210\",\"2\",\"200\",\"Bezirk Mittelfranken\"");
            writer.println("\"211\",\"2\",\"210\",\"Kreis Mittelfranken-Mitte\"");
            writer.println("\"212\",\"2\",\"210\",\"Kreis Mittelfranken-Nord\"");
            writer.println("\"220\",\"2\",\"200\",\"München\"");
            writer.close();

            writer = new java.io.PrintWriter(tmpVereineCsv);
  
            writer.println("\"24420\",\"2\",\"244\",\"SC Starnberg\"");          
            writer.println("\"24421\",\"2\",\"244\",\"TV Tegernsee\"");
            writer.println("\"23017\",\"2\",\"230\",\"SC Vilshofen\"");
            writer.println("\"21206\",\"2\",\"212\",\"SF Fürth\"");
        
            writer.close();
        } catch (IOException ex)
        {
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }
    }
    
    public BackgroundDao createBackgroundDao()
    throws AppException 
    {        
        tournoire.backgrounddata.BackgroundDao instance = new tournoire.backgrounddata.BackgroundDao();
        instance.clear();
        instance.loadDsbCsvFiles(tempDir);
        
        return instance;
    }
    
    public void cleanUp()
    {        
        tmpSpielerCsv.delete();
        tmpVerbaendeCsv.delete();
        tmpVereineCsv.delete();
    }
    
    
    private File tmpSpielerCsv;
    private File tmpVerbaendeCsv;
    private File tmpVereineCsv;

}
