Large file processor sample project (OSGi)
=========================================

How to use this project

	0) set the working folder where the data files in the blueprint.xml file
	
	<cm:property-placeholder id="globalprops.placeholder"
		persistent-id="globalprops">
		<cm:default-properties>
			<cm:property name="fileWorkingFolder"
				value="##YOUR WORKING FOLDER##/" />
		</cm:default-properties>
	</cm:property-placeholder>
	
	1) start the project locally :
		mvn clean package camel:run
	
	2) you can generate big files using the sample data by calling the following rest webservices
	
	To generate a big CSV file 
	http://yourIP:7123/fileprocessor/gen/{fileSizeInMegaBytes}
	
	i.e.  http://yourIP:7123/fileprocessor/gen/100MB.csv
	
	To generate a big XML files
	http://yourIP:7123/fileprocessor/genxml/{fileSizeInMegaBytes}
	i.e.  http://yourIP:7123/fileprocessor/genxml/100MB.xml

	3) run tests by using the rest services available
	
		1	File + Built in tokenizer
		http://yourIP:7123/fileprocessor/tokenize/100MB.csv
		
		2	BufferedReader + readline
		http://yourIP:7123/fileprocessor/bufferedread/100MB.csv
		
		3	File + Built in tokenizer + 1 thread	csv->java
		http://yourIP:7123/fileprocessor/tokenizeparse/100MB.csv
		
		4	File + Built in tokenizer + 4 thread	csv->java
		http://yourIP:7123/fileprocessor/tokenizeparsemulti/100MB.csv
		
		5	BufferedReader + readline + 1 thread	csv->java
		http://yourIP:7123/fileprocessor/bufferedreadparsemono/100MB.csv
		
		6	BufferedReader + readline + 4 thread	csv->java
		http://yourIP:7123/fileprocessor/bufferedreadparse/100MB.csv
		
		7	BufferedReader + readline + 1 thread	csv->java	Java -> csv
		http://yourIP:7123/fileprocessor/bufferedreadparsewritemono/100MB.csv
		
		8	BufferedReader + readline + 4 thread	csv->java	Java -> csv
		http://yourIP:7123/fileprocessor/bufferedreadparsewritemulti/100MB.csv
		
		9	BufferedReader + readline + 1 thread	csv->String	String->csv
		http://yourIP:7123/fileprocessor/bufferedreadparsewritemonorawtext/100MB.csv
		
		10	BufferedReader + readline + 4 thread	csv->String	String->csv
		http://yourIP:7123/fileprocessor/bufferedreadparsewritemultirawtext/100MB.csv
		
		11	File + Built in tokenizer + 1 thread	xml->dom	Dom->xml
		http://yourIP:7123/fileprocessor/tokenizexmlmono/100MB.xml
		
		12	File + Built in tokenizer + 4 thread	xml->dom	Dom->xml
		http://yourIP:7123/fileprocessor/tokenizexmlmulti/100MB.xml
	
	4) while running test you can turn on a Jconsole to see heap usage
	
	

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You can run the following command from its shell:

    osgi:install -s mvn:com.redhat.pocs/large-file-processor/1.0-SNAPSHOT

To deploy on fabric8

    fabric8:deploy

For more help see the Apache Camel documentation

    http://camel.apache.org/

