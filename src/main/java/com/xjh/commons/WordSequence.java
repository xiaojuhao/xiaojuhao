package com.xjh.commons;

import com.github.stuxuhai.jpinyin.ChineseHelper;

public class WordSequence {
	private String input;
	private int len;
	private int cursor;
	private int forwardStep=0;
	public WordSequence(String input){
		this.input = input;
		this.len = input==null?0:input.length();
		cursor = 0;
	}
	/**
	 * 返回一个中文字符或一个单词
	 * 单词的定义是：连续的字母或数字
	 * @return
	 */
	public String nextToken(){
	    forwardStep =0;
	    StringBuffer sb = new StringBuffer();
		while(cursor<len){
		    char c = input.charAt(cursor);
		    c = CommonUtils.toDBC(c);
		    boolean isChinese = ChineseHelper.isChinese(c);
		    if(isChinese){
		        //如果sb不为空，表示该中文之前，有连续的英文或数字，此时应该返回英文或数字
		        if(sb.length()==0){
		            sb.append(c);
		            cursor++;
		            forwardStep++;
		        }
		        break;
		    }else if(Character.isDigit(c) || Character.isLetter(c)){
		        sb.append(c);
		        cursor++;
		        forwardStep++;
		    }else{
		        //非中文，也非字母或数字
		        //如果sb有值，则返回sb中的值，否则，跳过
		        if(sb.length()>0){
		            break;
		        }
		        cursor++;
		        forwardStep++;
		    }
		}
		return sb.length()==0?null:sb.toString();
	}
	public void back(){
	    while(forwardStep-- > 0){
	        cursor --;
	    }
	}
}
