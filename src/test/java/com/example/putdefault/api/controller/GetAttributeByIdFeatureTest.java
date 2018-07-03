package com.example.putdefault.api.controller;

import com.example.putdefault.api.controller.exception.NotFoundException;
import com.example.putdefault.persistence.model.Attribute;
import com.example.putdefault.persistence.model.CompositeAttribute;
import com.example.putdefault.persistence.repository.AttributesRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAttributeByIdFeatureTest {

    @Autowired
    AttributesController attributesController;

    @Autowired
    AttributesRepository attributesRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void tearDown() throws Exception {
        attributesRepository.deleteAll();
    }

    @Test
    public void testGetByIdSuccess() {
        Attribute lastAttribute = attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0002-0001-001")
                .withRequiredValue("always")
                .withCompositeAttribute(
                        CompositeAttribute.Builder.aCompositeAttribute()
                                .withInnerValue("innerValue")
                                .build())
                .withOldValue(null)
                .withNewValue(null)
                .build());
        assertEquals(lastAttribute, attributesController.getAttributeById("0002-0001-001"));
    }


    @Test(expected = NotFoundException.class)
    public void testGetByIdException() {
        attributesController.getAttributeById("0002-0001-001");
        expectedException.expectMessage("0002-0001-001");
    }
}