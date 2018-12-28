package com.huawei.esight.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.MetricKey;
import com.integrien.alive.common.adapter3.ResourceKey;
import com.integrien.alive.common.adapter3.config.ResourceIdentifierConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NetworkCard实体类.
 * Created by harbor on 7/18/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkCardBean implements TreeNodeResource {
    
    private String netWorkCardName;
    private String macAdress;
    private String moId;
    private String uuid;
    //added since 2018.12.12
    private String portName;
    private String linkStatus;
    
    public String getNetWorkCardName() {
        return netWorkCardName;
    }
    
    public void setNetWorkCardName(String netWorkCardName) {
        this.netWorkCardName = netWorkCardName;
    }
    
    public String getMacAdress() {
        return macAdress;
    }
    
    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }
    
    public String getMoId() {
        return moId;
    }
    
    public void setMoId(String moId) {
        this.moId = moId;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(String linkStatus) {
		this.linkStatus = linkStatus;
	}    
    
    @Override
    public ResourceKey convert2Resource(String dn, String adapterKind,
            Map<ResourceKey, List<MetricData>> metricsByResource) {
        
        ResourceKey resourceKey = 
                new ResourceKey(this.netWorkCardName, Constant.KIND_NETWORKCATD, adapterKind);
        //设定唯一的标识，保证同名的资源可以正常显示
        ResourceIdentifierConfig dnIdentifier = 
                new ResourceIdentifierConfig(Constant.ATTR_ID, dn + this.uuid, true);
        resourceKey.addIdentifier(dnIdentifier);
        long timestamp = System.currentTimeMillis();
        
        List<MetricData> metricDataList = new ArrayList<>();
        //写入resource属性
        metricDataList.add(new MetricData(
                new MetricKey(true, Constant.ATTR_NETWORKCARD_NAME), timestamp, this.netWorkCardName));
        metricDataList.add(
                new MetricData(new MetricKey(true, Constant.ATTR_MACADRESS), timestamp, this.macAdress));
        metricDataList.add(
                new MetricData(new MetricKey(true, Constant.ATTR_MOID), timestamp, this.moId));
        
        metricDataList.add(
                new MetricData(new MetricKey(true, Constant.ATTR_UUID), timestamp, this.uuid));
        
        metricDataList.add(
                new MetricData(new MetricKey(true, Constant.ATTR_PORT_NAME), timestamp, this.portName));
        
        metricDataList.add(
                new MetricData(new MetricKey(true, Constant.ATTR_LINK_STATUS), timestamp, this.linkStatus));
        
        //关联key和属性值
        metricsByResource.put(resourceKey, metricDataList);
        return resourceKey;
    }

}
