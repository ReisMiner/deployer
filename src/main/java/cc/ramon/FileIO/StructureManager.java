package cc.ramon.FileIO;

import cc.ramon.Model.FileData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class StructureManager {

    private final String filePath = "bot_data.json";
    private final File storageFile;

    public StructureManager() {
        storageFile = new File(filePath);
    }

    /**
     * Checks if files are there
     *
     * @return false if files not there, true if they are
     */
    public FileData checkStructure() {
        if (!storageFile.exists())
            return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(storageFile, FileData.class);
        } catch (Exception ex) {
            return null;
        }


    }

    /**
     * Creates File Structure
     *
     * @return true if structure was created. if not false
     */
    public boolean makeStructure() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(storageFile, new FileData(""));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Call on startup.
     * Checks file structure and makes it if not there yet.
     */
    public FileData setup() {
        FileData data = checkStructure();
        if (data != null)
            return data;

        if (makeStructure()) {
            return checkStructure();
        } else {
            System.out.println("StorageFile Could not be created!");
            return null;
        }

    }
}
