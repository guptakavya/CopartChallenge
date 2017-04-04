package copartCoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class checkNearest {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		double inputLat = sc.nextDouble();
		double inputLon = sc.nextDouble();
		
		
		File dataset = new File("zip_codes_states.csv");
		sc = new Scanner(dataset);
		
		sc.nextLine();
		String line = sc.nextLine();
		String[] input = line.split(",");
		double disLat = Double.parseDouble(input[1]);
		double disLon = Double.parseDouble(input[2]);
		double minDis = distance(inputLat, inputLon, disLat, disLon);
		String minLocation = convertArrayToString(input);

		while (sc.hasNextLine()) {
			line = sc.nextLine();
			input = line.split(",");
			if (!input[1].equals("") && !input[2].equals("")) {
				if(input[1].charAt(0)=='"'){
					input[1] = input[1].substring(1, input[1].length()-1);
				}
				if(input[2].charAt(0)=='"'){
					input[2] = input[2].substring(1, input[2].length()-1);
				}
				disLat = Double.parseDouble(input[1]);
				disLon = Double.parseDouble(input[2]);
				double dis = distance(inputLat, inputLon, disLat, disLon);
				if (dis < minDis) {
					minDis = dis;
					minLocation = convertArrayToString(input);
				}
			}
		}

		System.out.println(minLocation);

	}

	private static String convertArrayToString(String[] input) {
		String ret = "";
		for (int i = 0; i < input.length; i++) {
			ret += input[i] + "  ";
		}
		return ret;
	}

	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

}
