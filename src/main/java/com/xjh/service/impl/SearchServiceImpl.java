package com.xjh.service.impl;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.lucene.Indexer;
import com.xjh.lucene.Source;
import com.xjh.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService, InitializingBean {
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;

	String indexDir = "/Users/yinguoliang/Documents/codes/luecene/material/";
	IndexSearcher indexSearcher = null;

	@Override
	public List<JSONObject> search(String keyword) {
		List<JSONObject> rs = Lists.newArrayList();
		if (indexSearcher == null) {
			return rs;
		}
		try {
			Term t = new Term("sk", keyword);
			Query query = new PrefixQuery(t);
			TopDocs hits = indexSearcher.search(query, 10);
			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document doc = indexSearcher.doc(scoreDoc.doc);
				JSONObject data = new JSONObject();
				data.put("code", doc.get("code"));
				data.put("name", doc.get("name"));
				rs.add(data);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void afterPropertiesSet() {
		try {
			List<WmsMaterialDO> list = tkWmsMaterialMapper.selectAll();
			List<Document> docs = new ArrayList<>();
			for (WmsMaterialDO m : list) {
				Document doc = new Document();
				docs.add(doc);
				doc.add(new TextField("type", "material", Field.Store.YES));
				doc.add(new TextField("code", m.getMaterialCode(), Field.Store.YES));
				doc.add(new TextField("name", m.getMaterialName(), Field.Store.YES));
				doc.add(new TextField("sk", m.getSearchKey(), Field.Store.YES));
			}
			Source source = new Source() {
				public Iterator<Document> iter() {
					return docs.iterator();
				}
			};
			Indexer indexer = new Indexer(indexDir);
			indexer.index(source);
			indexer.close();

			Directory dir = FSDirectory.open(Paths.get(indexDir));
			IndexReader reader = DirectoryReader.open(dir);
			indexSearcher = new IndexSearcher(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
