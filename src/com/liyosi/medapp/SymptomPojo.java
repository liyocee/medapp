package com.liyosi.medapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SymptomPojo implements Parcelable{
	int id;
	String cause;
	String symptoms;
	String name;
	String treatment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	} 
	public SymptomPojo(int id, String name, String cause, String treatment, String symptoms){
		this.id=id;
		this.name=name;
		this.cause=cause;
		this.treatment=treatment;
		this.symptoms=symptoms;
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
	        dest.writeString(cause);
	        dest.writeString(treatment);
	        dest.writeString(symptoms);
	 
	    }
	 public SymptomPojo(Parcel source) {
	        // TODO Auto-generated method stub
	        id = source.readInt();
	        name = source.readString();
	        treatment=source.readString();
	        cause=source.readString();
	        symptoms=source.readString();
	 
	    }
	 

	 
	 @SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		 
	        @Override
	        public SymptomPojo createFromParcel(Parcel source) {
	            // TODO Auto-generated method stub
	            return new SymptomPojo(source);
	        }
	 
	        @Override
	        public SymptomPojo[] newArray(int size) {
	            // TODO Auto-generated method stub
	            return new SymptomPojo[size];
	        }
	    };




}
