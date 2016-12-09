package org.example.jersey.messangerRestApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.jersey.messangerRestApp.database.DatabaseClass;
import org.example.jersey.messangerRestApp.model.Profile;

public class ProfileService {
private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("Syed", new Profile(1L,"Syed","Samuel", "Jackson"));
		profiles.put("Talib", new Profile(2L,"Talib","Struat", "Little"));
		profiles.put("Saifi", new Profile(2L,"Saifi","Struat", "Little"));
	}
	
	public List<Profile> getAllProfile(){
		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String shortname){
		return profiles.get(shortname);
	}
	
	public Profile updateProfile(Profile prof){
		if(prof.getShortname().isEmpty())
			return null;
		profiles.put(prof.getShortname(), prof);
		return prof;
	}
	
	public Profile addProfile(Profile prof){
		prof.setId(profiles.size()+1);
		profiles.put(prof.getShortname(), prof);
		return prof;
	}
	
	public void removeProfile(String shortname){
		profiles.remove(shortname);
	}

}
