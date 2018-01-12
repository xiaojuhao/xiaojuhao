package com.xjh.support.excel.wirte;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CellData implements Serializable {

    private static final long         serialVersionUID = 1L;
    /** 列的名称 **/
    private List<String>              titlesList;
    /** 根据列名称对应值 **/
    private Map<String, List<String>> map;

    public List<String> getTitlesList() {
        return titlesList;
    }

    public void setTitlesList(List<String> titlesList) {
        this.titlesList = titlesList;
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }
}
