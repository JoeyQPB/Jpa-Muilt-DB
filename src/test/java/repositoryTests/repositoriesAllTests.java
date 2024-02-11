package repositoryTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientRepositoryPGTest.class, ProductRepositoryTest.class, ClientRepositoryMySQLTest.class})
public class repositoriesAllTests {

}
