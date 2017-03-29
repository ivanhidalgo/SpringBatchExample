package com.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadata",
    "asset"
})
@XmlRootElement(name = "ADI")
public class ADI {

    @XmlElement(name = "Metadata")
    protected CtAMS metadata;
    @XmlElement(name = "Asset")
    protected Asset asset;

    public CtAMS getMetadata() {
        return metadata;
    }

    public void setMetadata(CtAMS value) {
        this.metadata = value;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset value) {
        this.asset = value;
    }
}