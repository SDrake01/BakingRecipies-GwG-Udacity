package stevendrake.bakingrecipes.Network;

// This class will act as a singleton pattern for all volley request queue requests

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRequestQueue {

    private static VolleyRequestQueue singleInstance;
    private Context singleContext;
    private RequestQueue singleRequestQueue;

    private VolleyRequestQueue(Context context){
        singleContext = context;
        singleRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestQueue getInstance(Context context){
        if (singleInstance == null){
            singleInstance = new VolleyRequestQueue(context);
        }
        return singleInstance;
    }

    public RequestQueue getRequestQueue(){
        if (singleRequestQueue == null){
            singleRequestQueue = Volley.newRequestQueue(singleContext.getApplicationContext());
        }
        return singleRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
