import java.util.*;
import java.util.function.Supplier;

public record Position(double latitude, double longitude, double altitude){
	
	Position change(double newLat, double newLon, double newAlt){
			
		return new Position(newLat, newLon, newAlt);
	}	
	
	List<Double> coordinate() {
		List<Double> t = new ArrayList<Double>();
		t.add(latitude);
		t.add(longitude);
		t.add(altitude);

		return t;
	}
	
	boolean higherAltThan(Position anotherPosition) {
		
		if(this.altitude > anotherPosition.altitude)
			return true;
		return false;
	}
	boolean lowerAltThan(Position anotherPosition) {
	
		if(this.altitude < anotherPosition.altitude)
			return true;
		return false;
	}
	boolean northOf(Position anotherPosition) {
		
		if(this.latitude > anotherPosition.latitude)
			return true;
		return false;
	
	}
	boolean southOf(Position anotherPosition) {
	
		if(this.latitude < anotherPosition.latitude)
			return true;
		return false;
	}

}

	
