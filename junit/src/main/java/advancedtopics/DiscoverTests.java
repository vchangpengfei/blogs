package advancedtopics;

import cha.pao.fan.StandardTests;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class DiscoverTests {
    public static void main(String args[])
    {
        LauncherDiscoveryRequest request= LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage("cha.pao.fan"),
                        DiscoverySelectors.selectClass(StandardTests.class)).
                        filters(ClassNameFilter.excludeClassNamePatterns(".*Tests")).
                build();
        Launcher launcher= LauncherFactory.create();

        SummaryGeneratingListener listener=new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        TestExecutionSummary summary=listener.getSummary();
        System.out.println(summary);
    }
}
