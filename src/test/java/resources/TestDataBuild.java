package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.addPlace;
import pojo.location;

public class TestDataBuild {

	public addPlace addPlacePayload(String name,String language,String address) {
		addPlace p=new addPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+91)9838933937");
		p.setWebsite("http://google.com");
		
		List<String> mylisttypes=new ArrayList<String>();
		mylisttypes.add("shoe park");
		mylisttypes.add("shop");
		p.setTypes(mylisttypes);
		
		location locate=new location();
		locate.setLng(33.427362);
		locate.setLat(-38.383494);
		 p.setLocation(locate);
		 return p;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
