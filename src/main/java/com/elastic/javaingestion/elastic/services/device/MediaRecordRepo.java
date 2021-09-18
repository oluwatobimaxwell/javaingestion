package com.elastic.javaingestion.elastic.services.device;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MediaRecordRepo extends ElasticsearchRepository<MediaRecord, String> {
}
