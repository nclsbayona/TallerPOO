package control;
public class AyudaUI2{
    private static String xmlTitle;

    public static String getXmlTitle() {
        try {
            if (!(AyudaUI2.xmlTitle.contains(".xml"))){
                AyudaUI2.xmlTitle += ".xml";
            }
            return AyudaUI2.xmlTitle;
        } catch (Exception e) {
            return "archivoXML.xml";
        }
    }

    public static void setXmlTitle(String xmlTitle) {
        if (xmlTitle == null)
            xmlTitle="archivoXML";
        AyudaUI2.xmlTitle = xmlTitle;
    }
}