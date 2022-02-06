package edu.uoc.pac3.tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({AnimalTest.class, FarmTest.class})
class MinimumCheck {
	

}
