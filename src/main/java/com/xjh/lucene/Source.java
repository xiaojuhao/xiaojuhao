package com.xjh.lucene;

import java.util.Iterator;

import org.apache.lucene.document.Document;

public interface Source {
	public Iterator<Document> iter();
}
