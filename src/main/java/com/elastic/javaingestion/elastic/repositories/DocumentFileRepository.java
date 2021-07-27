package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.DocumentFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentFileRepository extends ElasticsearchRepository<DocumentFile, String> {

}
