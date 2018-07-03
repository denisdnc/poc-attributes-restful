package com.example.putdefault.api.controller;

import com.example.putdefault.persistence.model.Attribute;
import com.example.putdefault.persistence.model.CompositeAttribute;
import com.example.putdefault.persistence.repository.AttributesRepository;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllAttributesFeatureTest {

    @Autowired
    AttributesController attributesController;

    @Autowired
    AttributesRepository attributesRepository;

    @After
    public void tearDown() throws Exception {
        attributesRepository.deleteAll();
    }

    @Test
    public void testGetAllSamePage() {
        Stream attributesStream = Stream.of(attributesRepository.save(Attribute.Builder.anAttributes()
                        .withId("0001-0001-001")
                        .withRequiredValue("always")
                        .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                        .withOldValue("not always")
                        .withNewValue(null)
                        .build()),
                attributesRepository.save(Attribute.Builder.anAttributes()
                        .withId("0001-0001-002")
                        .withRequiredValue("always")
                        .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                        .withOldValue(null)
                        .withNewValue(null)
                        .build()),
                attributesRepository.save(Attribute.Builder.anAttributes()
                        .withId("0002-0001-001")
                        .withRequiredValue("always")
                        .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                        .withOldValue(null)
                        .withNewValue(null)
                        .build()));
        Page<Attribute> attributesPage = attributesController.getAttributes(3, 0);
        assertArrayEquals(attributesStream.toArray(), attributesPage.stream().toArray());
    }

    @Test
    public void testGetAllPaginated() {
        attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0001-0001-001")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue("not always")
                .withNewValue(null)
                .build());
        attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0001-0001-002")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue(null)
                .withNewValue(null)
                .build());
        Attribute lastAttribute = attributesRepository.save(Attribute.Builder.anAttributes()
                .withId("0002-0001-001")
                .withRequiredValue("always")
                .withCompositeAttribute(CompositeAttribute.Builder.aCompositeAttribute().withInnerValue("innerValue").build())
                .withOldValue(null)
                .withNewValue(null)
                .build());
        Page<Attribute> attributesPage = attributesController.getAttributes(2, 1);
        assertArrayEquals(Arrays.array(lastAttribute), attributesPage.stream().toArray());
    }
}