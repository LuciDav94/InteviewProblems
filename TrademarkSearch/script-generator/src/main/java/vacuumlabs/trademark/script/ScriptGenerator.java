package vacuumlabs.trademark.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vacuumlabs.trademark.script.model.TradeMarkXml;
import vacuumlabs.trademark.script.model.Transaction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ScriptGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptGenerator.class);

    public static final String FILTER_WORD = "Word";
    public static final String ARCHIVE_STARTING_WITH = "EUTMS";

    public void generateTableScript(Path outputFile) throws IOException {
        String tableScript = "CREATE TABLE IF NOT EXISTS trademark (\n" +
                " id SERIAL PRIMARY KEY, \n" +
                " word_mark_specification character varying(255), \n" +
                " registration_date timestamp without time zone, \n" +
                " expiry_date timestamp without time zone, \n" +
                " mark_current_status_date timestamp without time zone, \n" +
                " application_number character varying(255) \n" +
                " );";
        writeToFile(outputFile, tableScript);
        writeToFile(outputFile, "CREATE INDEX IF NOT EXISTS trademark_word_mark_specification " +
                "ON trademark USING btree (word_mark_specification);");
    }

    public void generateValuesScript(String rootPath, Path outputFile) throws IOException {
        Files.walk(Paths.get(rootPath)).filter(Files::isRegularFile).forEach(p -> {
            File file = new File(p.toUri());
            if (file.getName().contains(ARCHIVE_STARTING_WITH)) {
                ZipFile zipFile;
                try {
                    zipFile = new ZipFile(file.getAbsolutePath());
                } catch (IOException e) {
                    LOGGER.error("Failed to convert to zip file");
                    return;
                }

                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    try {
                        InputStream stream = zipFile.getInputStream(entry);

                        BufferedReader br = new BufferedReader(new InputStreamReader(stream, UTF_8.name()));
                        JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);
                        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                        Transaction model = (Transaction) unmarshaller.unmarshal(br);
                        TradeMarkXml tradeMarkXml = model.getTradeMark();
                        if (tradeMarkXml != null && tradeMarkXml.getMarkFeature().equals(FILTER_WORD)) {
                            String script = "INSERT INTO trademark(word_mark_specification, " +
                                    "registration_date, expiry_date, mark_current_status_date, application_number) VALUES ('" +
                                    tradeMarkXml.getWordMarkSpecification().getMarkVerbalElementText()
                                            .replace("'", "''") + "',";
                            if (tradeMarkXml.getRegistrationDate() != null) {
                                script += "'" + tradeMarkXml.getRegistrationDate() + "',";
                            } else {
                                script += "null,";
                            }
                            if (tradeMarkXml.getExpiryDate() != null) {
                                script += "'" + tradeMarkXml.getExpiryDate() + "',";
                            } else {
                                script += "null,";
                            }
                            if (tradeMarkXml.getMarkCurrentStatusDate() != null) {
                                script += "'" + tradeMarkXml.getMarkCurrentStatusDate() + "',";
                            } else {
                                script += "null,";
                            }
                            script += "'" + tradeMarkXml.getApplicationNumber() + "');";
                            writeToFile(outputFile, script);
                        }
                    } catch (IOException | JAXBException e) {
                        LOGGER.error("Failed to read entry file", e);
                    }
                }
            }
        });
    }

    public void writeToFile(Path path, String textToWrite) throws IOException {
        Files.write(path, (textToWrite + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.APPEND);
    }
}
