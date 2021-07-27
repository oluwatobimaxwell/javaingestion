# Elasticsearch Data Training Ingestion!
This project is to demonstrate ingestion of data from a static file into elasticsearch through Java Spring Data Elasticsearch.
The repository contains:
1. Source codes
2. Compile .jar file (for immediate deployment)
3. Kibana Dashboard files

# Instant Setup Instruction
Before start please install elasticsearch and kibana, and start up both:

**App Setup**
1. Download  **javaingestion-0.0.1.jar** from 
https://github.com/oluwatobimaxwell/javaingestion/blob/master/jar/javaingestion-0.0.1.jar
2. From the download directory where **javaingestion-0.0.1.jar**  is located run the below command to start running

    
	      java -jar javaingestion-0.0.1.jar

3. The springboot project will be started on Port 8080


**Kibana - Index Pattern & Dashboard**
The App auto generate random data and ingest every 15mins. You can also manual run the generation and ingestion function by run the get request below:

    http://localhost:8080/api/device/generate

This generates randomly between 3 to 10 device data, with about 25 to 75 for each of contacts, image files, document files, sms messages, incoming calls, outgoing calls and missed calls data.

On first run an index (**devices** ) is created. Proceed to create index pattern as described below.
1. From Kibana stack managment, click *Index Patterns*
2. Create Index Pattern
3. Enter index name *devices*
4. Select timestamp for timestamp field
5. Click show advance settings
	Enter the string below for custom index pattern ID
	 `b8306ee0-ee23-11eb-b886-0d18f0efadb0`
6. Complete the process and proceed to Dashboard setup

After index pattern is created, you can now import the dashboard components. Follow the instruction below to do that.
	7. Run the following HTTP POST Request 
	
	POST api/kibana/dashboards/import
	Body -> Past the content of:  **device-list.json**
	
	POST api/kibana/dashboards/import
	Body -> Past the content of:  **device-content.json**

On successful request, you should have the dashboards created with some data from elasticsearch.

Thank you!
