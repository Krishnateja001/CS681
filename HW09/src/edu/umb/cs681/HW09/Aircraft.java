import java.util.*;
import java.util.function.Supplier;

public class Aircraft {

	private Position position;
	
	public Aircraft(Position pos) {this.position = pos;}

	public void setPosition(double newLat, double newLong, double newALt) {
		
		this.position = this.position.change(newLat, newLong, newALt);
	}

	public Position getPosition(){

		return position;
	}

}
	
