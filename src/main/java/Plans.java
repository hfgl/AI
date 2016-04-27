

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Plans {

    public static void main(String[] args) 
    {
        
    	ArrayList<Plan> plans = new ArrayList<Plan>();
        
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
            for (Plan plan : plans) {
                if(plan.getid().equals(request.params(":id"))){
                    response.status(200);
                	plan = newPlan;
                	return "";
                }
            }
            response.status(404);
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
            plans.add(plan);
            return "";
        });
        
        delete("/plan/:id", (request, response) -> 
        {
        	if(request.queryParams("id") == null)
        	{
        		response.status(405);
        		return "";
        	}
            for (Plan plan: plans){
                if(plan.getid().equals(request.queryParams("id")))
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
