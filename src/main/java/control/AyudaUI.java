package control;
public class AyudaUI{
    private static String xmlTitle;
    
    public static String getXmlTitle() {
        try {
            if (!(AyudaUI.xmlTitle.contains(".xml"))){
                AyudaUI.xmlTitle += ".xml";
            }
            return AyudaUI.xmlTitle;
        } catch (Exception e) {
            return "archivoXML.xml";
        }
    }

    public static void setXmlTitle(String xmlTitle) {
        if (xmlTitle == null)
            xmlTitle="archivoXML";
        AyudaUI.xmlTitle = xmlTitle;
    }
}