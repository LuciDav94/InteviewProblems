package vacuumlabs.trademark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vacuumlabs.trademark.script.ScriptGenerator;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ApplicationInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInit.class);

    @Value("${path.to.folder.data}")
    private String pathToFolderData;

    @Value("${path.to.script.sql}")
    private String pathToScriptFile;

    @PostConstruct
    public void initMethod() {
        LOGGER.info("Start generating script.sql..");
        Path outputPath;
        try {
            outputPath = Paths.get(pathToScriptFile);
            clearFile();
        } catch (IOException e) {
            LOGGER.error("EXIT!!! No output file present. Please create script.src under 'path.to.script.sql' location");
            return;
        }

        ScriptGenerator dbScriptGenerator = new ScriptGenerator();
        try {
            dbScriptGenerator.generateTableScript(outputPath);
            dbScriptGenerator.generateValuesScript(pathToFolderData, outputPath);
            LOGGER.info("Finished generating script.sql");
        } catch (IOException e) {
            LOGGER.error("Failed to generate script", e);
        }
    }

    public void clearFile() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathToScriptFile));
        writer.write("");
        writer.flush();
    }
}
