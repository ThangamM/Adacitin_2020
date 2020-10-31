package app.runner;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import app.base.Base;
import app.execution.Execution;



	@RunWith(Suite.class)
	@Suite.SuiteClasses({Execution.class})
	public class Runner {
		
	}


