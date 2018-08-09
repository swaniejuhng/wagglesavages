import java.sql.Timestamp;

public class Waggle {
    private String waggle_id;
    private String longitude;
    private String latitude;
    private String date_created;
    private String location;

    public String getWaggle_id() {
        return waggle_id;
    }

    public void setWaggle_id(String waggle_id) {
        this.waggle_id = waggle_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDate_created() {
		return date_created;
	}
    
    public void setDate_created(String string) {
		this.date_created = string;
	}
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getWaggle_id();
	}
    
    
}
