

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Plans {

	static int counter = 0;
	
    public static void main(String[] args) 
    {
        
    	ArrayList<Plan> plans = new ArrayList<Plan>();
        
    	get("/plan", (request, response) -> 
        {
            response.status(200);
            response.header("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            return gson.toJson(plans);
        });
    	
        get("/plan/:id", (request, response) -> 
        {
        	String result = null;
            response.header("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            for (Plan plan : plans) {
                if(plan.getid().equals(request.params(":id"))){
                	result = gson.toJson(plan);
                }
            }
            if(result == null){
            	response.status(405);
            }
            else{
            	response.status(200);
            }
            
            return result;
        });
        
        get("/plan/:id", (request, response) -> 
        {
            
            response.header("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            for (Plan plan : plans) {
                if(plan.getid().equals(request.params(":id"))){
                	response.status(200);
                	return gson.toJson(plan);
                }
            }
            response.status(405);
            return "";
        });
     
        post("/plan", (request, response) -> 
        {
        	if(request.body() == null){
        		response.status(405);
        		return "";
        	}
            response.status(200);
            response.header("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            Plan plan = gson.fromJson(request.body(), Plan.class);
            counter++;
            plan.setid("" + counter);
            plans.add(plan);
            return "";
        });

        post("/plan/:id", (request, response) -> 
        {
            response.status(201);
            response.header("Content-Type", "application/json");
            Gson gson = new GsonBuilder().create();
            Plan newPlan = gson.fromJson(request.body(), Plan.class);
            if(request.params(":id") == null){
            	response.status(405);
            	return "";
            }
            for(int i = 0; i < plans.size(); i++)
            {
            	Plan plan = plans.get(i);
                if(plan.getid().equals(request.params(":id"))){
                    response.status(200);
                    
                    Plan p = gson.fromJson(request.body(), Plan.class);
                    p.setid("" + (i+1));
                    plans.remove(i);
                    plans.add(p);
                	return "";
                }
            }
            response.status(404);
            return "";
        });
       
        
        
        delete("/plan/:id", (request, response) -> 
        {
        	if(request.params(":id") == null)
        	{
        		response.status(405);
        		return "";
        	}
        	
            for (Plan plan: plans){
                if(plan.getid().equals(request.params(":id")))
                {
                	response.status(200);
                    plans.remove(plan);
                    return "";
                }
            }
            response.status(404);
            return "";
        });
    }
}
