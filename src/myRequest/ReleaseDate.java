package myRequest;

import java.lang.*;

public class ReleaseDate implements Comparable<ReleaseDate> {
private int year = -1;
private int month = -1;
private int day = -1;
private String releaseString;

ReleaseDate(String releaseString) {
	releaseString = new String(releaseString);
	year = Integer.valueOf(releaseString.substring(0, 4));
	
	if (releaseString.length() > 9) {
		day = Integer.valueOf(releaseString.substring(9,10));
	} 
	
	if (releaseString.length() > 4) {
		month = Integer.valueOf(releaseString.substring(6,7));
	} 
}

@Override
public int compareTo(ReleaseDate o) {
	if(this == o) {
		return 0;
	}
	
	if(year != o.year) {
		return Integer.compare(year, o.year);
	} else if (month != o.month) {
		return Integer.compare(year, o.year);
	} else if (day != o.day){
		return Integer.compare(day, o.day);
	} else {
		return 0;
	}
}

@Override
public String toString() {
	String returnDateString =  new String(String.valueOf(year));
	return returnDateString;
}



}
