package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.OutgoingCall;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OutgoingCallRepository extends ElasticsearchRepository<OutgoingCall, String> {

}
