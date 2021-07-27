package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.SMSMessage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SMSRepository extends ElasticsearchRepository<SMSMessage, String> {

}
