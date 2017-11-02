package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;

import lombok.Data;

@Service
@Data
public class Mappers implements InitializingBean{
	@Resource TkWmsSessionMapper sessionMapper;
	@Resource TkWmsUserMapper userMapper;
	@Override
    public void afterPropertiesSet() throws Exception {
        holder.setValue(this);
    }
    public static Mappers inst(){
        return holder.getValue();
    }
    private static class Holder{
    	Mappers value = null;
        public void setValue(Mappers value){
            this.value = value;
        }
        public Mappers getValue(){
            return this.value;
        }
    }
    private static Holder holder = new Holder();
}
