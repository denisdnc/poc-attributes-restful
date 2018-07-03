package com.example.putdefault.api.service;

import com.example.putdefault.api.Features;
import com.example.putdefault.persistence.model.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public final class AttributeMigrationService {

    private static final Logger LOG = LoggerFactory.getLogger(AttributeMigrationService.class);
    private static final String DEFAULT_NEW_VALUE = "defaultValue";

    public static Attribute checkValue(Attribute attribute) {
        LOG.info("Checking for values to be updated by the migration");
        if(Features.FEATURE_ADD_MIGRATION_TO_ADD_DEFAULT_TO_NEW_VALUE.isOn() && StringUtils.isEmpty(attribute.getNewValue())) {
            LOG.info("Feature to add default for newValue is turned on, and attribute's new value is null, so adding default value for it");
            attribute = addDefaultValueForNewValue(attribute);
            LOG.debug("Updated attribute: [{}]", attribute);
        }
        if (Features.FEATURE_ADD_MIGRATION_TO_REMOVE_OLD_VALUE.isOn() && StringUtils.hasText(attribute.getOldValue())) {
            LOG.info("Feature to remove oldValue is turned on, and attribute oldValue is not null, so setting it to null");
            attribute = removeOldValueFromAttribute(attribute);
            LOG.debug("Updated attribute: [{}]", attribute);
        }
        return attribute;
    }

    private static Attribute removeOldValueFromAttribute(Attribute attribute) {
        attribute.setOldValue(null);
        return attribute;
    }

    private static Attribute addDefaultValueForNewValue(Attribute attribute) {
        attribute.setNewValue(DEFAULT_NEW_VALUE);
        return attribute;
    }
}
