package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.IncomingCall;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IncomingCallRepository extends ElasticsearchRepository<IncomingCall, String> {

}
