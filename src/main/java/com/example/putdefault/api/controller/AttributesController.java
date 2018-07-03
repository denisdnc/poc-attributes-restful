package com.example.putdefault.api.controller;

import com.example.putdefault.api.service.AttributesService;
import com.example.putdefault.persistence.model.Attribute;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AttributesController {

    private final AttributesService attributesService;

    public AttributesController(AttributesService attributesService) {
        this.attributesService = attributesService;
    }

    @GetMapping("/v1/attributes")
    public Page<Attribute> getAttributes(@RequestParam(required = false, defaultValue = "10") Integer itemsPerPage,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        return attributesService.getAllAttributesPerPage(itemsPerPage, pageNum);
    }

    @GetMapping("/v1/attributes/{id}")
    public Attribute getAttributeById(@PathVariable String id) {
        return attributesService.findById(id);
    }


    @PutMapping("/v1/attribute/{id}")
    public Attribute updateAttribute(@PathVariable String id,
            @RequestBody Attribute attribute)  {
        return attributesService.persistAttributes(attribute);
    }


    @PostMapping("/v1/attribute")
    public Attribute createAttributes(@RequestBody Attribute attribute) {
        return attributesService.createAttributes(attribute);
    }
}
