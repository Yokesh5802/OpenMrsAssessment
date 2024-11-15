package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int failuerCount = 0;
	int limit = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (failuerCount < limit) {

			failuerCount++;
			return true;
		}

		return false;
	}

}
