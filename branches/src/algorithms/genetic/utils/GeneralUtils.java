package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {
	
	public static double nextDouble(double min, double max){
		return Math.random()*(max-min)+min;
	}
	
	
	public static List<Integer> getIntegersFromOneTo(int lastInt){
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < lastInt+1; i++) {
			list.add(i);
		}
		return list;
	}
}
