# Oi abiguinho! Tudo bom fofa? Bjs no seu coração!
# On Demand Migration Mechanism

This is a proof of concept to show some points in how to update new data and old data.

There a few points to raise about the code.
 - Mutable entities
 - Feature Toggle is hard coded in the Enumerator, there is no need to be rocket science (over-engineered) for this POC
 - Integration Test (SpringBootTest)
 - JUnit4
 
## The Problem
How should the micro-service handle migration from data between the first release and the creation of new fields or removal of old ones.
 
## The Solution explored
Well, at first, each migration should be handled as new Feature, and of course it should be toggleable.

So let's begin showing the part of the code of which the migration is handled. [Here is the link for the file](https://github.com/lPegz/poc-attributes-restful/blob/master/src/main/java/com/example/putdefault/api/service/AttributeMigrationService.java#L14)

```java
    private static Attribute removeOldValueFromAttribute(Attribute attribute) {
        attribute.setOldValue(null);
        return attribute;
    }

    private static Attribute addDefaultValueForNewValue(Attribute attribute) {
        attribute.setNewValue(DEFAULT_NEW_VALUE);
        return attribute;
    }
```

### How is this triggered?

WIP
