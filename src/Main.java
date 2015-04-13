import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



public class Main {
	
	private static Scanner in;

	public static void main(String[] args) throws IOException{
		System.out.println("Welcome to speech tester");
		System.out.println("Please enter your text: ");
		in = new Scanner(System.in);
		String text = in.nextLine();
		System.out.println("Your text: " + text);
		com.mashape.unirest.http.HttpResponse response;
		try {
			response = (com.mashape.unirest.http.HttpResponse) Unirest.post("https://community-sentiment.p.mashape.com/text/")
					.header("X-Mashape-Key", "V8Q8Qp9sLWmshak3dJXBWMxovqeCp1Gn3gmjsnIWmM5WBmmYx6")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "application/json")
					.field("txt", text)
					.asJson();
			JsonNode body = (JsonNode) response.getBody();
			String answer = body.toString();
			JSONObject obj = new JSONObject(answer);
			JSONObject sentiment = obj.getJSONObject("result");
			
			 
			System.out.println("Reponse: Sentiment - " + sentiment.getString("sentiment") + " Confidence - " + sentiment.getString("confidence") );
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
