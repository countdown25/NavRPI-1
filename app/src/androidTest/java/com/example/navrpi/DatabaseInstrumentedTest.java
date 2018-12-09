package com.example.navrpi;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.os.SystemClock.sleep;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {

    //these are junit instrumented tests for all 5 database classes.
    //for Room to properly work, they must be run on an android device (real or emulated)

    @Test
    public void ProfessorTests() {
        ProfessorDao professorDao = ProfessorDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).professorDao();
        sleep(500);
        //we make the database, and then add some test data
        professorDao.insert(new Professor("Joe","Miller","Walker"));
        professorDao.insert(new Professor("Danny","Miller","Walker"));
        professorDao.insert(new Professor("Mike","Miller","Walker"));
        professorDao.insert(new Professor("John","Smith","Walker"));
        professorDao.insert(new Professor("Jack","Smith","Walker"));
        professorDao.insert(new Professor("Kyle","Fleming","Walker"));
        professorDao.insert(new Professor("Connor","Korver","Walker"));
        List<Professor> profs = professorDao.getAllProfessors();
        for (int i = 0; i < profs.size(); i++) { //we make sure all professors are in walker (which is true for all of our data + test data)
            assertEquals("Walker",profs.get(i).getNode().substring(0,6));
        }
        profs = professorDao.searchLastName("Miller");
        assertTrue(profs.size() > 0); //we check that this got our Millers
        for (int i = 0; i < profs.size(); i++) {
            assertEquals("Miller", profs.get(i).getLastName()); //we make sure these people are Miller
        }
        professorDao.deleteAll();
    }

    @Test
    public void BuildingTests() {
        BuildingDao buildingDao = BuildingDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).buildingDao();
        sleep(500);
        List<Building> buildings = buildingDao.getAllBuildings();
        for (int i = 0; i < buildings.size(); i++) { //all of our buildings have lats between 42 and 43, and lngs between -74 and -73, so we test for that
            assertTrue(buildings.get(i).getLat() > 42.0 && buildings.get(i).getLat() < 43.0);
            assertTrue(buildings.get(i).getLng() < -73.0 && buildings.get(i).getLng() > -74.0);
        }
        buildings = buildingDao.searchBuild("Walker"); //we then test that we can get individual buildings
        assertEquals(1, buildings.size());
        assertEquals("Walker", buildings.get(0).getName());
        buildingDao.deleteAll();
    }

    @Test
    public void ClassroomTests() {
        ClassroomDao classroomDao = ClassroomDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).classroomDao();
        sleep(500);
        List<Classroom> rooms = classroomDao.getAllRooms();
        for (int i = 0; i < rooms.size(); i++) { //we make sure all room's nodes corespons to their buildings
            assertEquals(rooms.get(i).getNode().substring(0,6), rooms.get(i).getBuilding());
        }
        classroomDao.deleteAll();
        classroomDao.insert(new Classroom("113","DCC","DCC"));
        classroomDao.insert(new Classroom("115","DCC","DCC"));
        classroomDao.insert(new Classroom("117","DCC","DCC"));
        classroomDao.insert(new Classroom("114","DCC","DCC"));
        classroomDao.insert(new Classroom("227","DCC","DCC"));
        classroomDao.insert(new Classroom("233","DCC","DCC"));
        classroomDao.insert(new Classroom("308","DCC","DCC"));
        classroomDao.insert(new Classroom("337","DCC","DCC"));
        rooms = classroomDao.searchBuilding("DCC");
        for (int i = 0; i < rooms.size(); i++) { //we check that the search works
            assertEquals("DCC", rooms.get(i).getBuilding());
        }
        classroomDao.deleteAll();
    }

    @Test
    public void NodesTests() {
        NodeDao nodeDao = NodeDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).nodeDao();
        sleep(500);
        List<MapNode> nodes = nodeDao.getAllNodes();
        for (int i = 0; i < nodes.size(); i++) { //we check that all nodes are hallways or bathrooms
            assertTrue("hallway".equals(nodes.get(i).getNodeType()) || "bathroom".equals(nodes.get(i).getNodeType()));
            assertEquals("Walker", nodes.get(i).getBuilding());
        }
        nodes = nodeDao.searchBuildFloor("Walker", 6);
        for (int i = 0; i < nodes.size(); i++) { //we test that this search worked correctly
            assertEquals("Walker",nodes.get(i).getBuilding());
            assertEquals(6,nodes.get(i).getFloor());
        }
    }

    @Test
    public void VerticiesTests() {
        VerticiesDao verticiesDao = VerticiesDatabase.getDatabase(InstrumentationRegistry.getTargetContext()).VerticiesDao();
        sleep(500);
        List<Verticies> verticies = verticiesDao.getAllEdges();
        for (int i = 0; i < verticies.size(); i++) { //we check that all verticies are within walker (true for our data), and that their distance is 1 (true for our data)
            assertEquals("Walker", verticies.get(i).getSource().substring(0,6));
            assertEquals("Walker", verticies.get(i).getDest().substring(0,6));
            assertEquals(1, verticies.get(i).getDistance());
        }
        verticiesDao.DeleteAll();
    }
}
