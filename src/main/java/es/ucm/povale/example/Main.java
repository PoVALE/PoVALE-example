package es.ucm.povale.example;

import java.util.List;
import es.ucm.povale.reader.XMLParser;
import es.ucm.povale.assertion.Assertion;
import es.ucm.povale.environment.Environment;
import es.ucm.povale.plugin.Import;
import es.ucm.povale.views.FXMLController;
import es.ucm.povale.views.MainApp;
import java.io.InputStream;
import javafx.application.Application;

/**
 *
 * @author PoVALE Team
 */
public class Main {

    private static Environment environment;
    private static MainApp mainApp;
    private static FXMLController controller;
    private static List<Assertion> asserts;

    public static void main(String[] args) {

        environment = new Environment();

        //String XMLFile = "src/main/resources/existDocument.xml";
        InputStream is = Main.class.getClassLoader().getResourceAsStream("existDocument.xml");
        System.out.println(is);
        XMLParser parser = new XMLParser();
        parser.parseXMLFile(is);

        List<String> plugins = parser.getMyPlugins();
        if (!plugins.get(0).equalsIgnoreCase("")) {
            for (String a : plugins) {
                Import plugin = new Import(a, environment);
            }
        }

        environment.addParamEditors();

        environment.addVariables(parser.getMyVars());

        asserts = parser.getMyAsserts();

        /*
        controller = new FXMLController();
        
        controller.setAssertions(asserts);
        
        controller.setEnvironment(environment);
        */
        
     //   MainApp main = new MainApp();
        
//        main.setController(controller);
        
        Application.launch(MainApp.class, args);

    }

}
