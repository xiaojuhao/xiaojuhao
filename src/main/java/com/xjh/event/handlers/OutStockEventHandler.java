package com.xjh.event.handlers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.xjh.event.BusCruise;
import com.xjh.event.events.OutStockEvent;

@Service
public class OutStockEventHandler implements InitializingBean{
	@Subscribe
	public void handle(OutStockEvent event){
		try{
			
		}catch(Exception ex){
			ex.printStackTrace(System.err);
		}finally{
			event.done();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		BusCruise.registerHandler(this);
	}
}
