package com.amazonaws.services.s3.model;

import java.util.Arrays;
import java.util.List;

public class BucketCrossOriginConfiguration {
    private List<CORSRule> rules;

    public BucketCrossOriginConfiguration(List<CORSRule> list) {
        this.rules = list;
    }

    public List<CORSRule> getRules() {
        return this.rules;
    }

    public void setRules(List<CORSRule> list) {
        this.rules = list;
    }

    public BucketCrossOriginConfiguration withRules(List<CORSRule> list) {
        setRules(list);
        return this;
    }

    public BucketCrossOriginConfiguration withRules(CORSRule... cORSRuleArr) {
        setRules(Arrays.asList(cORSRuleArr));
        return this;
    }
}
