package reporter;

import java.util.*;

import org.testng.*;
import org.testng.xml.*;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {

        //Iterating over each suite included in the test
        for (ISuite suite : suites) {

            //Following code gets the suite name
            String suiteName = suite.getName();
            List<ITestNGMethod> methodNames = suite.getAllMethods();

            //Getting the results for the said suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            int i = 0;
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                System.out.println("Passed tests for suite '" + suiteName + "': Test Case '" + methodNames.get(i).getMethodName() +
                        "' is:" + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests for suite '" + suiteName + "': Test Case '" + methodNames.get(i).getMethodName() +
                        "' is:" + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests for suite '" + suiteName + "': Test Case '" + methodNames.get(i).getMethodName() +
                        "' is:" + tc.getSkippedTests().getAllResults().size());
                i++;
            }
        }
    }
}