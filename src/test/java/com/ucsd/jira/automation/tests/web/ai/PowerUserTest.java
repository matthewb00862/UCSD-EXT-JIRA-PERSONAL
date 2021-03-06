package com.ucsd.jira.automation.tests.web.ai;

import com.ucsd.jira.automation.frameworksupport.AIUserTestCase;
import com.ucsd.jira.automation.frameworksupport.Groups;
import org.springframework.util.StopWatch;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.pwc.logging.service.LoggerService.*;

public class PowerUserTest extends AIUserTestCase {

    private static StopWatch visit = new StopWatch();
    private static Map<Integer, String> probabilityMap;

    static {
        probabilityMap = new HashMap();
        probabilityMap.put(9, "securityIntrusion");
        probabilityMap.put(5, "advancedSearch");
        probabilityMap.put(1, "quickStats");
        probabilityMap.put(9, "genericSearch");
    }

    @Override
    public void beforeMethod() {
        visit.start();
    }

    @Override
    public void afterMethod() {
    }

    /**
     * 1. Randomly decide what to do first
     * - First decision is going to be most likely so towards 10
     * 2. Based on that decision do something different (potentially similar but different activity)
     * 3. Do something else
     * 4. Do something else
     * <p>
     * Continually perform random number of times ( less than 30 seconds )
     * <p>
     * IMPORTANT: The more a decision is made the lower it's importance becomes.  As importance is reduced
     * than it should be avoided more often than more important decisions.
     */

    @Test(groups = {Groups.REAL_TEST, Groups.ACCEPTANCE_TEST})
    public void testPowerUser() {

        FEATURE("Maven Central Power User");
        SCENARIO("Perform common activities of an power user");
        GIVEN("My probable activities are defined for a specific type of user");
        WHEN("I perform these activities");

        THEN("I am able to perform random Maven Central functions");
        float decision = randomPercentageInRange(1, 10);
        performUserActivityBasedOnADecision(probabilityMap, visit, decision);

        decision = randomPercentageInRange(5, 7);
        performUserActivityBasedOnADecision(probabilityMap, visit, decision);

        decision = randomPercentageInRange(1, 4);
        performUserActivityBasedOnADecision(probabilityMap, visit, decision);

        decision = randomPercentageInRange(1, 10);
        performUserActivityBasedOnADecision(probabilityMap, visit, decision);

    }

}
