package edu.gatech.seclass.jobcompare6300.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DBControllerTest {

    private DBController dbController;

    @Before
    public void setUp(){
        Context context = ApplicationProvider.getApplicationContext();
        dbController = DBController.getInstance(context);
        assert dbController != null;
        dbController.clearAndRecreate();
        //Adapted from here:
        //https://github.com/elye/demo_simpledb_test/blob/master/app/src/test/java/com/elyeproj/simpledb/ExampleUnitTest.kt
    }

    @After
    public void tearDown(){
        Context context = ApplicationProvider.getApplicationContext();
        dbController = DBController.getInstance(context);
        assert dbController != null;
        dbController.clearDb();
    }

    @Test
    public void insertComparisonSetting() {
        //0 Verify invalid input is correctly handled
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        try {
            dbController.insertComparisonSetting(null);
            dbController.insertComparisonSetting(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        assertFalse("Invalid input should not throw an exception", exceptionCaught);

        //1 Verify table is empty at the outset
        final List<Map<String, String>> listBeforeInsert = dbController.getAllComparisonSettings();
        assertTrue("List before insert should be empty", listBeforeInsert.isEmpty());

        //2 Insert a row and verify it worked
        map.put("yearlySalaryWeight","1");
        map.put("yearlyBonusWeight","1");
        map.put("weeklyTeleworkDaysWeight","1");
        map.put("leaveTimeWeight","1");
        map.put("gymMembershipAllowanceWeight","1");
        dbController.insertComparisonSetting(map);
        final List<Map<String, String>> listAfterInsert1 = dbController.getAllComparisonSettings();
        assertFalse("List after insert #1 should not be empty", listAfterInsert1.isEmpty());

        //3 Try to insert a second row, verify it didn't work
        final int list1Size = listAfterInsert1.size();
        map = new HashMap<>();
        map.put("yearlySalaryWeight","2");
        map.put("yearlyBonusWeight","2");
        map.put("weeklyTeleworkDaysWeight","2");
        map.put("leaveTimeWeight","2");
        map.put("gymMembershipAllowanceWeight","2");
        dbController.insertComparisonSetting(map);
        final List<Map<String, String>> listAfterInsert2 = dbController.getAllComparisonSettings();
        assertFalse("List after insert #2 should not be empty", listAfterInsert2.isEmpty());
        assertEquals("List after insert #2 should be the same size as list after insert #1",
                list1Size, listAfterInsert2.size());
    }

    @Test
    public void insertJob() {
        //0 Verify invalid input is correctly handled
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        try {
            dbController.insertJob(null);
            dbController.insertJob(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        assertFalse("Invalid input should not throw an exception", exceptionCaught);

        //1 Verify table is empty at the outset
        final List<Map<String, String>> listBeforeInsert = dbController.getAllJobs();
        assertTrue("List before insert should be empty", listBeforeInsert.isEmpty());

        //2 Insert a row and verify it worked
        map.put("title","title1");
        map.put("company","company1");
        map.put("location","location1");
        map.put("costOfLiving","1");
        map.put("yearlySalary","1");
        map.put("yearlyBonus","1");
        map.put("weeklyTeleworkDays","1");
        map.put("leaveTime","1");
        map.put("gymMembershipAllowance","1");
        map.put("isCurrent","false");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert1 = dbController.getAllJobs();
        assertFalse("List after insert should not be empty", listAfterInsert1.isEmpty());

        //3 Try to insert a second row, verify it worked
        final int list1Size = listAfterInsert1.size();
        map = new HashMap<>();
        map.put("title","title2");
        map.put("company","company2");
        map.put("location","location2");
        map.put("costOfLiving","2");
        map.put("yearlySalary","2");
        map.put("yearlyBonus","2");
        map.put("weeklyTeleworkDays","2");
        map.put("leaveTime","2");
        map.put("gymMembershipAllowance","2");
        map.put("isCurrent","true");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert2 = dbController.getAllJobs();
        assertFalse("List after insert #2 should not be empty", listAfterInsert2.isEmpty());
        assertTrue("List after insert #2 should not be the same size as list after insert #1",
                list1Size < listAfterInsert2.size());
    }

    @Test
    public void updateComparisonSetting() {
        //0 Verify invalid input is correctly handled
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        int result1 = -1;
        int result2 = -1;
        try {
            result1 = dbController.updateComparisonSetting(null);
            result2 = dbController.updateComparisonSetting(map);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        assertFalse("Invalid input should not throw an exception", exceptionCaught);
        assertEquals("Result from null update should be 0", result1, 0);
        assertEquals("Result from empty update should be 0", result2, 0);

        //1 Insert a row and verify it worked
        map.put("yearlySalaryWeight","1");
        map.put("yearlyBonusWeight","1");
        map.put("weeklyTeleworkDaysWeight","1");
        map.put("leaveTimeWeight","1");
        map.put("gymMembershipAllowanceWeight","1");
        dbController.insertComparisonSetting(map);
        final List<Map<String, String>> listAfterInsert = dbController.getAllComparisonSettings();
        assertFalse("List after insert (before update) should not be empty", listAfterInsert.isEmpty());
        assertEquals("List should have only 1 item", listAfterInsert.size(), 1);
        map = listAfterInsert.get(0);
        assertTrue("Map in list should have entry for yearlySalaryWeight",
                map.containsKey("yearlySalaryWeight"));
        assertEquals("yearlySalaryWeight should equal 1 before update",
                map.get("yearlySalaryWeight"), "1");

        //2 Do update and confirm it worked
        map = new HashMap<>();
        map.put("yearlySalaryWeight","2");
        map.put("yearlyBonusWeight","2");
        map.put("weeklyTeleworkDaysWeight","2");
        map.put("leaveTimeWeight","2");
        map.put("gymMembershipAllowanceWeight","2");
        final int result = dbController.updateComparisonSetting(map);
        assertEquals("Result from update should be 1", result, 1);
        final List<Map<String, String>> listAfterUpdate = dbController.getAllComparisonSettings();
        assertFalse("List after update should not be empty", listAfterUpdate.isEmpty());
        assertEquals("List should have only 1 item", listAfterUpdate.size(), 1);
        map = listAfterUpdate.get(0);
        assertTrue("Map in list should have entry for yearlySalaryWeight",
                map.containsKey("yearlySalaryWeight"));
        assertEquals("yearlySalaryWeight should equal 2 after update",
                map.get("yearlySalaryWeight"), "2");
    }

    @Test
    public void updateJob() {
        //0 Verify invalid input is correctly handled
        boolean exceptionCaught = false;
        Map<String, String> map = new HashMap<>();
        int result1 = -1;
        int result2 = -1;
        try {
            result1 = dbController.updateJob(null, 0);
            result2 = dbController.updateJob(map, 0);
        }catch (Exception exception){
            exceptionCaught = true;
        }
        assertFalse("Invalid input should not throw an exception", exceptionCaught);
        assertEquals("Result from null update should be 0", result1, 0);
        assertEquals("Result from empty update should be 0", result2, 0);

        //1 Insert a row and verify it worked
        map.put("title","title1");
        map.put("company","company1");
        map.put("location","location1");
        map.put("costOfLiving","1");
        map.put("yearlySalary","1");
        map.put("yearlyBonus","1");
        map.put("weeklyTeleworkDays","1");
        map.put("leaveTime","1");
        map.put("gymMembershipAllowance","1");
        map.put("isCurrent","false");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert = dbController.getAllJobs();
        assertFalse("List after insert (before update) should not be empty", listAfterInsert.isEmpty());
        assertEquals("List should have only 1 item", listAfterInsert.size(), 1);
        map = listAfterInsert.get(0);
        assertTrue("Map in list should have entry for title",
                map.containsKey("title"));
        assertEquals("title should equal title1 before update",
                map.get("title"), "title1");

        //2 Do update and confirm it worked
        final int id = Integer.parseInt(Objects.requireNonNull(map.get("jobId")));
        map = new HashMap<>();
        map.put("title","title2");
        map.put("company","company2");
        map.put("location","location2");
        map.put("costOfLiving","2");
        map.put("yearlySalary","2");
        map.put("yearlyBonus","2");
        map.put("weeklyTeleworkDays","2");
        map.put("leaveTime","2");
        map.put("gymMembershipAllowance","2");
        map.put("isCurrent","true");
        final int result = dbController.updateJob(map, id);
        assertEquals("Result from update should be 1", result, 1);
        final List<Map<String, String>> listAfterUpdate = dbController.getAllJobs();
        assertFalse("List after update should not be empty", listAfterUpdate.isEmpty());
        assertEquals("List should have only 1 item", listAfterUpdate.size(), 1);
        map = listAfterUpdate.get(0);
        assertTrue("Map in list should have entry for title",
                map.containsKey("title"));
        assertEquals("title should equal title2 after update",
                map.get("title"), "title2");
    }

    @Test
    public void getAllComparisonSettings() {
        //1 Verify table is empty at the outset
        final List<Map<String, String>> listBeforeInsert = dbController.getAllComparisonSettings();
        assertTrue("List before insert should be empty", listBeforeInsert.isEmpty());

        //2 Insert a row and verify it worked
        Map<String, String> map = new HashMap<>();
        map.put("yearlySalaryWeight","1");
        map.put("yearlyBonusWeight","1");
        map.put("weeklyTeleworkDaysWeight","1");
        map.put("leaveTimeWeight","1");
        map.put("gymMembershipAllowanceWeight","1");
        dbController.insertComparisonSetting(map);
        final List<Map<String, String>> listAfterInsert1 = dbController.getAllComparisonSettings();
        assertFalse("List after insert #1 should not be empty", listAfterInsert1.isEmpty());

        //3 Try to insert a second row, verify it didn't work
        final int list1Size = listAfterInsert1.size();
        map = new HashMap<>();
        map.put("yearlySalaryWeight","2");
        map.put("yearlyBonusWeight","2");
        map.put("weeklyTeleworkDaysWeight","2");
        map.put("leaveTimeWeight","2");
        map.put("gymMembershipAllowanceWeight","2");
        dbController.insertComparisonSetting(map);
        final List<Map<String, String>> listAfterInsert2 = dbController.getAllComparisonSettings();
        assertFalse("List after insert #2 should not be empty", listAfterInsert2.isEmpty());
        assertEquals("List after insert #2 should be the same size as list after insert #1",
                list1Size, listAfterInsert2.size());
    }

    @Test
    public void getAllJobs() {
        //1 Verify table is empty at the outset
        final List<Map<String, String>> listBeforeInsert = dbController.getAllJobs();
        assertTrue("List before insert should be empty", listBeforeInsert.isEmpty());

        //2 Insert a row and verify it worked
        Map<String, String> map = new HashMap<>();
        map.put("title","title1");
        map.put("company","company1");
        map.put("location","location1");
        map.put("costOfLiving","1");
        map.put("yearlySalary","1");
        map.put("yearlyBonus","1");
        map.put("weeklyTeleworkDays","1");
        map.put("leaveTime","1");
        map.put("gymMembershipAllowance","1");
        map.put("isCurrent","false");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert1 = dbController.getAllJobs();
        assertFalse("List after insert should not be empty", listAfterInsert1.isEmpty());

        //3 Try to insert a second row, verify it worked
        final int list1Size = listAfterInsert1.size();
        map = new HashMap<>();
        map.put("title","title2");
        map.put("company","company2");
        map.put("location","location2");
        map.put("costOfLiving","2");
        map.put("yearlySalary","2");
        map.put("yearlyBonus","2");
        map.put("weeklyTeleworkDays","2");
        map.put("leaveTime","2");
        map.put("gymMembershipAllowance","2");
        map.put("isCurrent","true");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert2 = dbController.getAllJobs();
        assertFalse("List after insert #2 should not be empty", listAfterInsert2.isEmpty());
        assertTrue("List after insert #2 should not be the same size as list after insert #1",
                list1Size < listAfterInsert2.size());
    }

    @Test
    public void getNextAvailableJobId() {
        //1 Insert a row and verify it worked
        Map<String, String> map = new HashMap<>();
        map.put("title","title1");
        map.put("company","company1");
        map.put("location","location1");
        map.put("costOfLiving","1");
        map.put("yearlySalary","1");
        map.put("yearlyBonus","1");
        map.put("weeklyTeleworkDays","1");
        map.put("leaveTime","1");
        map.put("gymMembershipAllowance","1");
        map.put("isCurrent","false");
        dbController.insertJob(map);
        final List<Map<String, String>> listAfterInsert = dbController.getAllJobs();
        assertFalse("List after insert should not be empty", listAfterInsert.isEmpty());

        //2 Get this id and confirm next = this + 1
        map = listAfterInsert.get(0);
        final int id = Integer.parseInt(Objects.requireNonNull(map.get("jobId")));
        final int next = dbController.getNextAvailableJobId();
        assertEquals("Next available id should equal current + 1", id + 1, next);
    }
}
