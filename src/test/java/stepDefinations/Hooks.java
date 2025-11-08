package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;
import resources.utils;

public class Hooks {
	
	//runs before each scenario
	@Before()
	public void addPlacePayload() throws IOException
	{
		if(placeValidationStepDefination.reqSpec==null)
		{
		utils util = new utils();
		placeValidationStepDefination.reqSpec= util.requestSpecification();
		}
		
	}

}
