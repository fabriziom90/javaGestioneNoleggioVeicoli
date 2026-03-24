package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;



@Suite
@SelectClasses({UserTest.class, CarTest.class, BikeTest.class})
public class SuiteTests{
	
}


