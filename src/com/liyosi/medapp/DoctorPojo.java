package com.liyosi.medapp;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorPojo implements Parcelable{
	int id;
	String name;
	String location;
	String phone_number;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public DoctorPojo(int id, String name,String location,String phone_number){
		this.id=id;
		this.name=name;
		this.location=location;
		this.phone_number=phone_number;
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
	        dest.writeString(location);
	        dest.writeString(phone_number);
	        
	 
	    }
	 
	 public DoctorPojo(Parcel source) {
	        // TODO Auto-generated method stub
	        id = source.readInt();
	        name = source.readString();
	        location=source.readString();
	        phone_number=source.readString();
	        
	 
	    }
	 
	 @SuppressWarnings("rawtypes")
		public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
			 
		        @Override
		        public DoctorPojo createFromParcel(Parcel source) {
		            // TODO Auto-generated method stub
		            return new DoctorPojo(source);
		        }
		 
		        @Override
		        public DoctorPojo[] newArray(int size) {
		            // TODO Auto-generated method stub
		            return new DoctorPojo[size];
		        }
		    };

}
