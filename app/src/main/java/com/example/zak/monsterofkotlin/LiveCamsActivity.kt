package com.example.zak.monsterofkotlin

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cams_layout.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.widget.EditText
import kotlinx.android.synthetic.main.content_main.*


class LiveCamsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    private var trafficCams: MutableList<TrafficCamera> = mutableListOf<TrafficCamera>()

    lateinit var errorMessage: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_cams)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        errorMessage = findViewById(R.id.errorMessage)

        viewManager = LinearLayoutManager(this@LiveCamsActivity)
        viewAdapter = CameraAdapter(trafficCams, this)

        recyclerView = findViewById<RecyclerView>(R.id.cam_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }//end of recyclerView

        if(isNetworkAvaliable(this@LiveCamsActivity)){
            GetJSON(this).execute("https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2")
        } else {
            errorMessage.setText("Network Connection Unavailable")
            Toast.makeText(this@LiveCamsActivity, "No network is available", Toast.LENGTH_SHORT).show()
        }

    }

    /*
       http://corochann.com/fast-easy-parcelable-implementation-with-android-studio-parcelable-plugin-641.html
       https://www.sitepoint.com/transfer-data-between-activities-with-android-parcelable/
    */

    // Moving to a simpler TrafficCamera object class.

    //check connectivity function
    fun isNetworkAvaliable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                Toast.makeText(context, activeNetwork.typeName, Toast.LENGTH_SHORT).show()
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                Toast.makeText(context, activeNetwork.typeName, Toast.LENGTH_SHORT).show()
            }
            return true
        }
        else {
            return false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_live_cams, menu)//same menu as the movie list
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this@LiveCamsActivity, "Settings Menu Option Selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_share -> {
                Toast.makeText(this@LiveCamsActivity, "You have selected to share the traffic cams", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_about -> {
                val intent = Intent(this@LiveCamsActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_search -> {
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }


    /*
        https://www.tutorialspoint.com/android/android_json_parser.htm
    */

    private inner class GetJSON(var mContext: LiveCamsActivity) : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(this@LiveCamsActivity, "JSON data is downloading", Toast.LENGTH_LONG).show()
        }

        override fun doInBackground(vararg params: String): String {

            //create connection and reader
            var connection: HttpURLConnection? = null
            var reader: BufferedReader? = null

            try {
                val url = URL(params[0])
                connection = url.openConnection() as HttpURLConnection
                connection.connect()

                val inputStream = connection.getInputStream()

                reader = BufferedReader(InputStreamReader(inputStream))

                val buffer = StringBuffer()
                var jsonLine = reader.readLine()

                while ((jsonLine) != null) {
                    buffer.append(jsonLine + "\n")
                    //Log.d("Response: ", "> $line")   //here u ll get whole response...... :-)
                    jsonLine = reader.readLine()
                }//end of while
                return buffer.toString()

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (connection != null) {
                    connection.disconnect()
                }
                try {
                    if (reader != null) {
                        reader.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }//end of finally

            return "" //return empty string with no connection or data

        }//end of doInBackground

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            //JSON object from url
            var jObj: JSONObject = JSONObject(result)
            // gets the array of features which is full list
            var coords: JSONArray = jObj.getJSONArray("Features")

            for(i in 0..(coords.length()-1)) {//Number of JSON objects
                val tempJason = JSONObject(coords[i].toString())

                val cameraNumber = JSONArray(tempJason.getString("Cameras")).length() - 1

                for(j in 0..cameraNumber) {

                    val camera:TrafficCamera = TrafficCamera()//a camera
                    camera.name = JSONArray(tempJason.getString("Cameras")).getJSONObject(j).getString("Description")
                    camera.imageURL = JSONArray(tempJason.getString("Cameras")).getJSONObject(j).getString("ImageUrl")
                    camera.type = JSONArray(tempJason.getString("Cameras")).getJSONObject(j).getString("Type")

                    //add each camera to list of cameras
                    trafficCams.add(camera)

                }
            }

            viewAdapter.notifyDataSetChanged();
        }
    }

    class CameraAdapter(private val camArray: MutableList<TrafficCamera>, private val mContext: LiveCamsActivity) :
            RecyclerView.Adapter<CameraAdapter.ViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var camNameView: TextView
            var camView: ImageView

            init {
                camNameView = itemView.findViewById(R.id.camNameView)
                camView = itemView.findViewById(R.id.camView)
            }//end of init
        }//end of inner class

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup,
                                        viewType: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cams_layout, viewGroup, false)
            return ViewHolder(v)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            var completeURL: String = ""

            if(camArray[position].type.equals("sdot")){
                completeURL = "http://www.seattle.gov/trafficcams/images/" + camArray[position].imageURL
            }else if(camArray[position].type.equals("wsdot")){
                completeURL = "http://images.wsdot.wa.gov/nw/" + camArray[position].imageURL
            }

            viewHolder.camNameView.text = camArray[position].name

            Picasso.get()
                    .load(completeURL)
                    .fit()
                    .centerInside()
                    .into(viewHolder.itemView.camView);

        }

        // Gets the size by the whole dataset number
        override fun getItemCount() = camArray.size-1
    }

}
