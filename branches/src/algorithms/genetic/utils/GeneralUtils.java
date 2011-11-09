package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtils {

	public static double nextRandomDouble(double min, double max) {
		return Math.random() * (max - min) + min;
	}

	public static int nextRandomIntModulo(int modulo) {
		if (modulo == 0) {
			return 0;
		} else {
			return (int) (nextRandomDouble(0, modulo - 1.0));
		}
	}

	public static List<Integer> getIntegersFromOneTo(int lastInt) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < lastInt + 1; i++) {
			list.add(i);
		}
		return list;
	}
	
	public static boolean getBoolean(double probabilty){
		if(probabilty>=1){
			return true;
		} else if(probabilty<=0){
			return false;
		} else {
			if(Math.random()<probabilty){
				return true;
			} else {
				return false;
			}
		}
	}
}
