package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.MissedCall;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MissedCallRepository extends ElasticsearchRepository<MissedCall, String> {

}
