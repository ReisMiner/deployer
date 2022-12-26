package cc.ramon.FileIO;

import cc.ramon.Model.FileData;
import cc.ramon.Model.JobConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StructureManager {

    private final File storageFile;
    private static StructureManager instance = null;
    public FileData fileData = null;

    private StructureManager() {
        String filePath = "bot_data.json";
        storageFile = new File(filePath);
    }

    public static StructureManager getInstance() {
        if (instance == null)
            instance = new StructureManager();
        return instance;
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
            fileData = mapper.readValue(storageFile, FileData.class);
            return fileData;
        } catch (Exception ex) {
            System.out.println("Error while reading the file");
            ex.printStackTrace();
            return null;
        }


    }

    /**
     * Creates File Structure
     *
     * @return true if structure was created. if not false
     */
    public boolean makeStructure() {
        JobConfig jobConfig = new JobConfig(null, null, null);
        FileData data = new FileData(-1, new ArrayList<>(List.of(jobConfig)));
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(storageFile, data);
            fileData = data;
        } catch (Exception e) {
            System.out.println("Error while creating the file");
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
        if (checkStructure() != null)
            return fileData;

        if (makeStructure()) {
            return checkStructure();
        } else {
            System.out.println("StorageFile Could not be created!");
            return null;
        }
    }

    public void update(FileData data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(storageFile, data);
            fileData = data;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
