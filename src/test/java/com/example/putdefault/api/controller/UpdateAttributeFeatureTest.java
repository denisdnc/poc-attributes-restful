package com.example.putdefault.api.controller;

import com.example.putdefault.persistence.model.Attribute;
import com.example.putdefault.persistence.model.CompositeAttribute;
import com.example.putdefault.persistence.repository.AttributesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAttributeFeatureTest {

    @Autowired
    AttributesController attributesController;

    @Autowired
    AttributesRepository attributesRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0001-0001-001")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue("some old Value")
                .withNewValue(null)
                .build());
        attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0001-0001-002")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue("some old value")
                .withNewValue(null)
                .build());
        attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0002-0001-001")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue(null)
                .withNewValue("not the default value")
                .build());
    }

    @After
    public void tearDown() throws Exception {
        attributesRepository.deleteAll();
    }

    @Test
    public void testWhenMigrationIsOn() throws Exception {
        Attribute updatedAttribute = attributesController.updateAttribute("0001-0001-002", Attribute.Builder.anAttributes()
                .withId("0001-0001-002")
                .withRequiredValue("somethingDifferent")
                .withOldValue("some old Value")
                .build());
        assertEquals("defaultValue", updatedAttribute.getNewValue());
        assertNull(updatedAttribute.getOldValue());
        assertEquals("{\"id\":\"0001-0001-002\",\"requiredValue\":\"somethingDifferent\",\"newValue\":\"defaultValue\"}",
                objectMapper.writeValueAsString(updatedAttribute));
    }
}