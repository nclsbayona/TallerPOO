package control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import model.AnhoLanzamiento;
import model.Catalogo;
import model.Pelicula;

public class CSVtoXML {
    private static String csvString;
    private static String xmlString;

    public static String getCsvString() {
        return csvString;
    }

    public static void setCsvString(String csvString1) {
        csvString = csvString1;
    }

    public static String getXmlString() {
        return xmlString;
    }

    public static void setXmlString(String xmlString1) {
        xmlString = xmlString1;
    }

    public static void writeXML(Catalogo catalogo) {
        try (FileWriter fw = new FileWriter(getXmlString())) {
            JAXBContext context = JAXBContext.newInstance(Catalogo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalogo, fw);
        } catch (Exception e) {
            System.out.println("Error writing XML" + '\n' + e.getMessage() + '\n');
            e.printStackTrace();
        }
    }

    public static void convert(String[] headers) {
        Catalogo catalogo = new Catalogo();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        try (FileReader fReader = new FileReader(getCsvString());
                CSVParser parser = new CSVParser(fReader, CSVFormat.DEFAULT.withHeader(headers).withQuote('"'))) {

            List<CSVRecord> filas = parser.getRecords();
            filas.remove(0);
            for (CSVRecord fila : filas) {
                LocalDate time = null;
                try {
                    try {
                        String[] arrS = fila.get(2).split("-");
                        String temporal = fila.get(2).replace(arrS[2], "20" + arrS[2]);
                        time = LocalDate.parse(temporal, df);
                    } catch (Exception e) {
                        try {
                            time = LocalDate.of(Integer.valueOf(fila.get(2)), Month.JANUARY, 1);
                        } catch (Exception e2) {
                            try{
                                    time = LocalDate.of(Integer.valueOf(fila.get(2)), Month.JANUARY, 1);
                            }catch (Exception e3) {
                                time = LocalDate.of(Integer.valueOf(2017), Month.JANUARY, 1);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error en time\n" + e.getMessage());
                    System.err.println(fila.get(2));
                }
                Pelicula pelicula = new Pelicula(fila.get(0), fila.get(1), localDateToDate(time), fila.get(4),
                        fila.get(6), fila.get(7), fila.get(8), fila.get(9), fila.get(3));
                // System.out.println(pelicula);
                Calendar c = Calendar.getInstance();
                c.setTime(pelicula.getFechaLanzamiento());
                boolean valid = false;
                for (AnhoLanzamiento anhoslLanzamiento : catalogo.getAnhoLanzamientos()) {
                    if (anhoslLanzamiento.getAnhoLanzamiento() == c.get(Calendar.YEAR)) {
                        valid = true;
                        anhoslLanzamiento.getPeliculasLista().add(pelicula);
                    }
                }
                if (!valid) {
                    AnhoLanzamiento anho = new AnhoLanzamiento(c.get(Calendar.YEAR));
                    anho.getPeliculasLista().add(pelicula);
                    catalogo.addAnhoLanzamientos(anho);
                }
            }
            catalogo.setFechaGenerado(Calendar.getInstance().getTime());
            // System.out.println(catalogo);
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
        } catch (Exception e) {
            System.out.println("Error reading CSV" + '\n' + e.getMessage());
        }
        while (getXmlString() == null)
            ;
        writeXML(catalogo);
    }

    private static Date localDateToDate(LocalDate ld) {
        ZoneId zi = ZoneId.systemDefault();

        return Date.from(ld.atStartOfDay(zi).toInstant());
    }

    public static void setBoth(String csvRoute, String route) {
        setCsvString(csvRoute);
        setXmlString(route+".xml");
    }
}