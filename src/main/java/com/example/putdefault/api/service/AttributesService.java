package com.example.putdefault.api.service;

import com.example.putdefault.api.Features;
import com.example.putdefault.api.controller.exception.NotFoundException;
import com.example.putdefault.persistence.model.Attribute;
import com.example.putdefault.persistence.repository.AttributesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public final class AttributesService {

    private static final Logger LOG = LoggerFactory.getLogger(AttributesService.class);

    private final AttributesRepository attributesRepository;

    public AttributesService(AttributesRepository attributesRepository) {
        this.attributesRepository = attributesRepository;
    }

    public Attribute createAttributes(Attribute attribute) {
        LOG.info("Creating Attribute with values [{}]", attribute);
        return persistAttributes(attribute);
    }

    public Attribute findById(String id) throws NotFoundException {
        LOG.info("Finding attribute with id [{}]", id);
        Attribute attribute = attributesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        if(Features.FEATURE_RUN_MIGRATION.isOn()) {
            migrateAttribute(attribute);
        }
        return attribute;
    }

    public Page<Attribute> getAllAttributesPerPage(Integer itemsPerPage, Integer pageNum) {
        LOG.info("Finding all attributes paginated");
        return attributesRepository.findAll(getPage(itemsPerPage, pageNum));
    }

    public Attribute persistAttributes(Attribute attribute) {
        if(Features.FEATURE_RUN_MIGRATION.isOn()) {
            attribute = AttributeMigrationService.checkValue(attribute);
        }
        return attributesRepository.save(attribute);
    }

    private void migrateAttribute(Attribute attribute) {
        LOG.info("Migrating data from attribute, given the FEATURES turned on, ");
        Attribute newAttribute = AttributeMigrationService.checkValue(attribute);
        if(!attribute.equals(newAttribute)) {
            attributesRepository.save(attribute);
        }
    }

    private PageRequest getPage(Integer itemsPerPage, Integer pageNum) {
        LOG.info("Mounting page with itemsPerPage: [{}] and pageNum:[{}]", itemsPerPage, pageNum);
        return PageRequest.of(pageNum, itemsPerPage);
    }
}
