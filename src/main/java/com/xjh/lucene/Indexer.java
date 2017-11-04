package com.xjh.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	private IndexWriter writer;
	private String indexDir;

	public Indexer(String indexDir) {
		try {
			this.indexDir = indexDir;
			Directory dir = FSDirectory.open(Paths.get(indexDir));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			writer = new IndexWriter(dir, iwc);
		} catch (Exception ex) {

		}
	}

	public void close() {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (Exception ex) {

		}
	}

	public void clear() {
		if (StringUtils.isNotBlank(indexDir)) {
			File f = new File(indexDir);
			if (f.isDirectory() == false) {
				return;
			}
			File[] files = f.listFiles();
			if (files == null) {
				return;
			}
			for (File ff : files) {
				ff.delete();
			}
		}
	}

	public void index(Source... sources) {
		if (sources == null || sources.length == 0) {
			return;
		}
		try {
			writer.deleteAll();
			writer.forceMergeDeletes();
			for (Source source : sources) {
				Iterator<Document> iter = source.iter();
				while (iter.hasNext()) {
					Document doc = iter.next();
					writer.addDocument(doc);
				}
			}
		} catch (Exception ex) {

		}
	}
}
