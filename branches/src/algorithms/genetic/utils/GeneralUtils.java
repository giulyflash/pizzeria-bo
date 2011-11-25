package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	public static List<Integer> getIntegerFromZeroTo(int lastInt) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < lastInt; i++) {
			list.add(i);
		}
		return list;
	}
	
	public static boolean generateBoolean(double probabilty){
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
