package com.test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.xjh.lucene.Indexer;
import com.xjh.lucene.Source;

public class IndexTest {
	static String indexPath = "/Users/yinguoliang/Documents/codes/luecene/";
	public static void index() throws Exception{

		Indexer indexer = new Indexer(indexPath);
		Source source = new Source(){
			public Iterator<Document> iter() {
				List<Document> list = new ArrayList<>();
				Document doc = new Document();
				doc.add(new TextField("code","M0001",Field.Store.YES));
				doc.add(new TextField("name","aaa00,bbb,ccc",Field.Store.YES));
				list.add(doc);
				
				doc = new Document();
				doc.add(new TextField("code","M0002",Field.Store.YES));
				doc.add(new TextField("name","aaa01,bbb,ccc",Field.Store.YES));
				list.add(doc);
				
				doc = new Document();
				doc.add(new TextField("code","M0003",Field.Store.YES));
				doc.add(new TextField("name","aaa02,ddd,ccc",Field.Store.YES));
				list.add(doc);
				
				doc = new Document();
				doc.add(new TextField("code","M0004",Field.Store.YES));
				doc.add(new TextField("name","aaa03,ddd,ccc",Field.Store.YES));
				list.add(doc);
				
				doc = new Document();
				doc.add(new TextField("code","M0005",Field.Store.YES));
				doc.add(new TextField("name","aaa04,fff,ccc",Field.Store.YES));
				list.add(doc);
				
				doc = new Document();
				doc.add(new TextField("code","M0006",Field.Store.YES));
				doc.add(new TextField("name","aaa05,fff,ccc",Field.Store.YES));
				list.add(doc);
				return list.iterator();
			}
		};
		indexer.index(source);
		indexer.close();
	
	}
	
	public static void search() throws Exception{
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is=new IndexSearcher(reader);
		Term t=new Term("name","bbb");
		Query query=new TermQuery(t);
	      TopDocs hits=is.search(query, 10);
	      System.out.println("总共查询到"+hits.totalHits+"个文档");
	      for(ScoreDoc scoreDoc:hits.scoreDocs){
	         Document doc=is.doc(scoreDoc.doc);
	         System.out.println(doc.get("id")+","+doc.get("code"));
	      }
	}
	public static void main(String[] args) throws Exception {
		index();
		search();
	}
}
