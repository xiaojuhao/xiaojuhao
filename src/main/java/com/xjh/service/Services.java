package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class Services implements InitializingBean{
	@Resource SequenceService sequenceService;
	
	@Override
    public void afterPropertiesSet() throws Exception {
        holder.setValue(this);
    }
    public static Services inst(){
        return holder.getValue();
    }
    private static class Holder{
    	Services value = null;
        public void setValue(Services value){
            this.value = value;
        }
        public Services getValue(){
            return this.value;
        }
    }
    private static Holder holder = new Holder();
}
