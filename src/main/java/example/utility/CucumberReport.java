package example.utility;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class would generate Cucumber report
 *
 * @author Di
 * @since 23/03/18
 */
public class CucumberReport {

    public static void main(String[] args) {
        File reportOutputDir = new File("build/cucumber-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("build/cucumber-reports/json-report/cucumber.json");
        Configuration configuration = new Configuration(reportOutputDir, "REST Assured Report");

        new ReportBuilder(jsonFiles, configuration).generateReports();
    }
}
