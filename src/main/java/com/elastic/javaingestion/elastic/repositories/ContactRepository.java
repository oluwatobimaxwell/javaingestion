package com.elastic.javaingestion.elastic.repositories;

import com.elastic.javaingestion.elastic.models.Contact;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContactRepository extends ElasticsearchRepository<Contact, String> {

}
