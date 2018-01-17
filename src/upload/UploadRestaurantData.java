package upload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddItemProperty;
import com.recombee.api_client.api_requests.AddPurchase;
import com.recombee.api_client.api_requests.Batch;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.api_requests.ResetDatabase;
import com.recombee.api_client.api_requests.SetItemValues;
import com.recombee.api_client.exceptions.ApiException;

import connection.Connection;

public class UploadRestaurantData {
	static RecombeeClient client = Connection.createRecombeeClient();
	private static Map<String, Object> itemValues = new HashMap<>();
	static ArrayList<Request> itemRequests = new ArrayList<>();

	
	public static void upload() throws ApiException {
		String line = "";
		
		//ItemProperties already in the recombee database as uploaded from the activities: type, name, city, topic, from and to.
		//Add additional two more properties to the recombee database.
		client.send(new AddItemProperty("address","string"));
		client.send(new AddItemProperty("rating","string"));
		
		try (BufferedReader br = new BufferedReader(new FileReader("restaurants.csv"))) {

		    while ((line = br.readLine()) != null) {
		    	System.out.println(line);
		        String[] row = line.split(";");
		        String id = row[0];
		        String type = row[1];
		        String name = row[2];
		        String city = row[4];
		        String topic = row[5];
		        String address = row[3];
		        String rating = row[6];
		        
		        itemValues.put("type",type);
		        itemValues.put("name",name);
		        itemValues.put("city", city);
		        itemValues.put("topic", topic);
		        itemValues.put("address", address);
		        itemValues.put("rating", rating);
		        Request r = new SetItemValues(id, itemValues).setCascadeCreate(true);
		        client.send(r);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} 
		return;
	}
	public static void main(String[] args) throws ApiException {
		UploadRestaurantData.upload();
	}
	
}
