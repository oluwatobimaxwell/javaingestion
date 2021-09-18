package com.elastic.javaingestion.elastic.services.phonecall;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static com.elastic.javaingestion.elastic.services.device.DVService.getClient;

@Service
public class CallService {

    private JSONObject csvObject(String line, String first){
        System.out.println(line);
        System.out.println(first);
        try{
            String[] columns = first.split(",");
            String[] row = line.split(",");
            JSONObject obj = new JSONObject();
            return new JSONObject(first.split(","), line.split(","));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new JSONObject();
    }

    public XContentBuilder docContent(String line, String first){
        String[] column = first.split(",");
        String[] row = line.split(",");
        System.out.println(column);
        System.out.println(row);
        try {
            XContentBuilder build = XContentFactory.jsonBuilder();
            build.startObject();
            for (int i = 0; i < column.length; i++) {
                try{
                    String fieldName = column[i].toLowerCase().replace(" ", "_").replace(".", "");
                    build.field(fieldName, row[i]);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            build.endObject();
            return build;
        } catch (IOException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
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


    ActionListener<IndexResponse> indexlistener = new ActionListener<IndexResponse>() {
        @Override
        public void onResponse(IndexResponse bulkResponse) {
            System.out.println("SUCCESS");
            System.out.println(bulkResponse);
        }

        @Override
        public void onFailure(Exception e) {
            System.out.println("FAILED");
            e.printStackTrace();
        }
    };

    public void ingestImage(Map<String, String> data){
//        String path, String name, String img, String data
        try {
            BulkRequest request = new BulkRequest();
            IndexRequest doc = new IndexRequest("devices_test");
            doc.timeout(TimeValue.timeValueMinutes(5));
            doc.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
            doc.source(XContentType.SMILE);
            XContentBuilder build = XContentFactory.jsonBuilder();
            build.startObject();
            doc.source(data);
            build.endObject();
            request.add(doc);
            try {
                getClient().indexAsync(doc, RequestOptions.DEFAULT, indexlistener);
                System.out.println("Uploaded - Index");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void csvToJson(List<String> lines, String firstLine){
        BulkRequest request = new BulkRequest();
        request.timeout(TimeValue.timeValueMinutes(5));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        lines.forEach(line -> {
            IndexRequest doc = new IndexRequest("devices_test");
            doc.source(docContent(line, firstLine));
            request.add(doc);
        });
        try {
            getClient().bulkAsync(request, RequestOptions.DEFAULT, listener);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
