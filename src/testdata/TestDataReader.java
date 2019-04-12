package testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import utility.Constant;

public class TestDataReader {
	
	Properties p = new Properties();
	public Properties getTestData() {
		try {
		File file = new File(Constant.testDataPath);
		FileInputStream stream = new FileInputStream(file);
		p.load(stream);
		return p;
		}catch(IOException ex) {
			System.out.println("Exception occurred while reading test data : "+ex.getMessage());
			return null;
		}
	}

}
