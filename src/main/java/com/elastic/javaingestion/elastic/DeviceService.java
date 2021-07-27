package com.elastic.javaingestion.elastic;

import com.elastic.javaingestion.elastic.models.*;
import com.elastic.javaingestion.elastic.repositories.*;
import com.elastic.javaingestion.elastic.utils.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DeviceService {

    private final SMSRepository repo_sms;
    private final ContactRepository repo_contact;
    private final ImageFileRepository repo_image;
    private final DocumentFileRepository repo_document;
    private final MissedCallRepository repo_missed_call;
    private final IncomingCallRepository repo_incoming_call;
    private final OutgoingCallRepository repo_outgoing_call;
    @Autowired
    public DeviceService(
                            SMSRepository repo_sms,
                            ContactRepository repo_contact,
                            ImageFileRepository repo_image,
                            DocumentFileRepository repo_document,
                            MissedCallRepository repo_missed_call,
                            IncomingCallRepository repo_incoming_call,
                            OutgoingCallRepository repo_outgoing_call
    ){
        this.repo_sms = repo_sms;
        this.repo_contact = repo_contact;
        this.repo_image = repo_image;
        this.repo_document = repo_document;
        this.repo_missed_call = repo_missed_call;
        this.repo_incoming_call = repo_incoming_call;
        this.repo_outgoing_call = repo_outgoing_call;
    }


    public List<Object> generateDevices() throws ParserConfigurationException, IOException, SAXException {

        int d = randomInt(3, 10);
        int min = 25;
        int max = 75;

        List<Object> docs = new ArrayList<>() {};
        for(int i = 0; i < d; i++){
            DataGenerator data = new DataGenerator();

            List<Contact> contacts = data.generateContacts(randomInt(min, max));
            repo_contact.saveAll(contacts);
            docs.addAll(contacts);

            List<SMSMessage> sms = data.generateSMSMessages(randomInt(min, max));
            repo_sms.saveAll(sms);
            docs.addAll(sms);

            List<ImageFile> image = data.generateImageFiles(randomInt(min, max));
            repo_image.saveAll(image);
            docs.addAll(image);

            List<DocumentFile> document = data.generateDocumentFiles(randomInt(min, max));
            repo_document.saveAll(document);
            docs.addAll(document);

            List<IncomingCall> incoming_call = data.generateIncomingCalls(randomInt(min, max));
            repo_incoming_call.saveAll(incoming_call);
            docs.addAll(incoming_call);

            List<OutgoingCall> outgoing_call = data.generateOutgoingCalls(randomInt(min, max));
            repo_outgoing_call.saveAll(outgoing_call);
            docs.addAll(outgoing_call);

            List<MissedCall> missed_call = data.generateMissedCalls(randomInt(min, max));
            repo_missed_call.saveAll(missed_call);
            docs.addAll(missed_call);
        }

        return docs;
    }

    private int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    @Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 2 * 60 * 1000)
    public void fixedRateSch() throws ParserConfigurationException, IOException, SAXException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);
        List<Object> data = generateDevices();
    }

}

//b8306ee0-ee23-11eb-b886-0d18f0efadb0
//http://localhost:5601/app/dashboards#/view/53bf4cb0-ee37-11eb-b886-0d18f0efadb0?_a=(description:'',filters:!(('$state':(store:appState),meta:(alias:!n,controlledBy:'1627320753943',disabled:!f,index:b8306ee0-ee23-11eb-b886-0d18f0efadb0,key:imsi.keyword,negate:!f,params:!('{{value}}'),type:phrases,value:'{{value}}'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(imsi.keyword:'{{value}}'))))))),fullScreenMode:!f,options:(hidePanelTitles:!f,useMargins:!t),query:(language:kuery,query:''),tags:!(),timeRestore:!f,title:Device-Single,viewMode:view)&_g=(filters:!(),query:(language:kuery,query:''),refreshInterval:(pause:!t,value:0),time:(from:now-15M,to:now))
//http://localhost:5601/app/dashboards#/view/53bf4cb0-ee37-11eb-b886-0d18f0efadb0?_a=(description:'',filters:!(('$state':(store:appState),meta:(alias:!n,controlledBy:'1627320753943',disabled:!f,index:b8306ee0-ee23-11eb-b886-0d18f0efadb0,key:imsi.keyword,negate:!f,params:!('621889151868968'),type:phrases,value:'621889151868968'),query:(bool:(minimum_should_match:1,should:!((match_phrase:(imsi.keyword:'621889151868968'))))))),fullScreenMode:!f,options:(hidePanelTitles:!f,useMargins:!t),query:(language:kuery,query:''),tags:!(),timeRestore:!f,title:Device-Single,viewMode:view)&_g=(filters:!(),query:(language:kuery,query:''),refreshInterval:(pause:!t,value:0),time:(from:now-15M,to:now))