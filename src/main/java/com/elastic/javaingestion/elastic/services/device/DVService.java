package com.elastic.javaingestion.elastic.services.device;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class DVService {
    private final ContentBuilder contentBuilder;


    @Autowired
    public DVService(ContentBuilder contentBuilder){
        this.contentBuilder = contentBuilder;
    }

    public ActionListener<BulkResponse> getListener() {
        return listener;
    }

    ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
        @Override
        public void onResponse(BulkResponse bulkResponse) {
            System.out.println("SUCCESS");
            System.out.println(bulkResponse);
        }

        @Override
        public void onFailure(Exception e) {
            System.out.println("FAILED");
            e.printStackTrace();
        }
    };

    public static RestHighLevelClient getClient() throws UnknownHostException {
        final ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(config).rest();
    }

    public void bulkSaving(List<JSONObject> devices){
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueMinutes(5));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        devices.forEach(device -> {
            JSONObject general_info = new JSONObject(device.get("general_information").toString());
            IndexRequest doc = new IndexRequest("devices_test");
            try {
                doc.source(contentBuilder.generalInformation(general_info).endObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.add(doc);

            JSONArray contacts = new JSONArray(device.get("contacts").toString());
            JSONArray sms_messages = new JSONArray(device.get("sms_messages").toString());
            JSONArray image_files = new JSONArray(device.get("image_files").toString());
            JSONArray document_files = new JSONArray(device.get("documents_files").toString());
            JSONArray outgoing_calls = new JSONArray(device.get("outgoing_calls").toString());
            JSONArray incoming_calls = new JSONArray(device.get("incoming_calls").toString());
            JSONArray missed_calls = new JSONArray(device.get("missed_calls").toString());

            incoming_calls.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.phonecall(new JSONObject(contact.toString()), general_info, "incoming_call_"));
                request.add(doc_c);
            });

            missed_calls.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.phonecall(new JSONObject(contact.toString()), general_info, "missed_call_"));
                request.add(doc_c);
            });

            outgoing_calls.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.phonecall(new JSONObject(contact.toString()), general_info, "outgoing_call_"));
                request.add(doc_c);
            });

            document_files.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.documentfile(new JSONObject(contact.toString()), general_info));
                request.add(doc_c);
            });

            image_files.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.imagefile(new JSONObject(contact.toString()), general_info));
                request.add(doc_c);
            });

            sms_messages.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.sms_message(new JSONObject(contact.toString()), general_info));
                request.add(doc_c);
            });

            contacts.forEach(contact -> {
                IndexRequest doc_c = new IndexRequest("devices_test");
                doc_c.source(contentBuilder.contact(new JSONObject(contact.toString()), general_info));
                request.add(doc_c);
            });
        });
        try {

            getClient().bulkAsync(request, RequestOptions.DEFAULT, listener);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void genData(){
        Generator gn = new Generator();
        System.out.println("NEW DOCS: "+gn.run().size());
        bulkSaving(gn.run());
    }
}



//b8306ee0-ee23-11eb-b886-0d18f0efadb0
//http://localhost:5601/app/dashboards#/view/53bf4cb0-ee37-11eb-b886-0d18f0efadb0?_a=(description:'',filters:!(('$state':(store:appState),meta:(alias:!n,controlledBy:'1627320753943',disabled:!f,index:b8306ee0-ee23-11eb-b886-0d18f0efadb0,key:imsi.keyword,negate:!f,params:!('{{value}}'),type:phrases,value:'{{value}}'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(imsi.keyword:'{{value}}'))))))),fullScreenMode:!f,options:(hidePanelTitles:!f,useMargins:!t),query:(language:kuery,query:''),tags:!(),timeRestore:!f,title:Device-Single,viewMode:view)&_g=(filters:!(),query:(language:kuery,query:''),refreshInterval:(pause:!t,value:0),time:(from:now-15M,to:now))
//http://localhost:5601/app/dashboards#/view/53bf4cb0-ee37-11eb-b886-0d18f0efadb0?_a=(description:'',filters:!(('$state':(store:appState),meta:(alias:!n,controlledBy:'1627320753943',disabled:!f,index:b8306ee0-ee23-11eb-b886-0d18f0efadb0,key:imsi.keyword,negate:!f,params:!('621889151868968'),type:phrases,value:'621889151868968'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(imsi.keyword:'621889151868968'))))))),fullScreenMode:!f,options:(hidePanelTitles:!f,useMargins:!t),query:(language:kuery,query:''),tags:!(),timeRestore:!f,title:Device-Single,viewMode:view)&_g=(filters:!(),query:(language:kuery,query:''),refreshInterval:(pause:!t,value:0),time:(from:now-15M,to:now))