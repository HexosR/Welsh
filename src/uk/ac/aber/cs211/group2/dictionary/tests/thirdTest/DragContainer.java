package uk.ac.aber.cs211.group2.dictionary.tests.thirdTest;

import javafx.scene.input.DataFormat;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Graff - https://github.com/joelgraff
 */

public class DragContainer implements Serializable {

	public static final DataFormat AddNode =
			new DataFormat("application.DragIcon.add");

	public static final DataFormat AddLink =
			new DataFormat("application.NodeLink.add");
	
	private final List<Pair<String, Object>> mDataPairs = new ArrayList<Pair<String, Object>>();
	
	public void addData (String key, Object value) {
		mDataPairs.add(new Pair<String, Object>(key, value));
	}
	
	public <T> T getValue (String key) {
		
		for (Pair<String, Object> data: mDataPairs) {
			
			if (data.getKey().equals(key))
				return (T) data.getValue();
				
		}
		
		return null;
	}
}
