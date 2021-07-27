package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.ImageFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageFileRepository extends ElasticsearchRepository<ImageFile, String> {

}
