package es.ucm.povale.example;

import java.util.List;
import es.ucm.povale.reader.XMLParser;
import es.ucm.povale.assertion.Assertion;
import es.ucm.povale.environment.Environment;
import es.ucm.povale.plugin.Import;
import es.ucm.povale.views.FXMLController;
import es.ucm.povale.views.MainApp;
import javafx.application.Application;

/**
 *
 * @author PoVALE Team
 */
public class Main {

    private static Environment environment;

    public static void main(String[] args) {
        //launch(args);

        environment = new Environment();

        String XMLFile = "src/main/resources/existDocument.xml";
        XMLParser parser = new XMLParser();
        parser.parseXMLFile(XMLFile);

        List<String> plugins = parser.getMyPlugins();
        if (!plugins.get(0).equalsIgnoreCase("")) {
            for (String a : plugins) {
                Import plugin = new Import(a, environment);
            }
        }

        environment.addParamEditors();

        environment.addVariables(parser.getMyVars());

        List<Assertion> asserts = parser.getMyAsserts();

        FXMLController controller = new FXMLController();
        
        controller.setEnvironment(environment);
        controller.setAssertions(asserts);
        
        MainApp ms = new MainApp();
        
        ms.setController(controller);

        Application.launch(MainApp.class, args);

    }

}
