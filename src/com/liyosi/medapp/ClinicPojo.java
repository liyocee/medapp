package com.liyosi.medapp;

import android.os.Parcel;
import android.os.Parcelable;


public class ClinicPojo implements Parcelable {
	int id;
	String name;
	String description;
	String address;
	String phone_number;
	String website;
	String lat;
	String lon;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public ClinicPojo(int id,String name,String description,String address,String phone,String website,String lat,String lon){
		this.id=id;
		this.name=name;
		this.description=description;
		this.address=address;
		this.phone_number=phone;
		this.website=website;
		this.lat=lat;
		this.lon=lon;
	}
	
	@Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 1;
    }
	@Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(phone_number);
        dest.writeString(website);
        dest.writeString(lat);
        dest.writeString(lon);
        
 
    }
	
	 public ClinicPojo(Parcel source) {
	        // TODO Auto-generated method stub
	        id = source.readInt();
	        name = source.readString();
	        description=source.readString();
	        address=source.readString();
	        phone_number=source.readString();
	        website=source.readString();  
	        lat=source.readString();
	        lon=source.readString();
	 
	    }
	 
	 @SuppressWarnings("rawtypes")
		public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
			 
		        @Override
		        public ClinicPojo createFromParcel(Parcel source) {
		            // TODO Auto-generated method stub
		            return new ClinicPojo(source);
		        }
		 
		        @Override
		        public ClinicPojo[] newArray(int size) {
		            // TODO Auto-generated method stub
		            return new ClinicPojo[size];
		        }
		    };
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}

}
