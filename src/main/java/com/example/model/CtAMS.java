package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctAMS", propOrder = {
    "ams",
    "appData"
})
public class CtAMS {

    @XmlElement(name = "AMS", required = true)
    protected Ams ams;
    @XmlElement(name = "App_Data")
    protected List<AppData> appData;

    /**
     * Gets the value of the ams property.
     * 
     * @return
     *     possible object is
     *     {@link CtAMS.AMS }
     *     
     */
    public Ams getAMS() {
        return ams;
    }

    /**
     * Sets the value of the ams property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtAMS.AMS }
     *     
     */
    public void setAMS(Ams value) {
        this.ams = value;
    }

    /**
     * Gets the value of the appData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CtAMS.AppData }
     * 
     * 
     */
    public List<AppData> getAppData() {
        if (appData == null) {
            appData = new ArrayList<AppData>();
        }
        return this.appData;
    }

}
