package com.github.zjiajun.java.core.other;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CreateMDFile {
	
	private static final String USER_HOME = "/Users/zhujiajun";
	
	private static final String POST_DIR = "/Work/ideaProjects/zJiaJun.github.io/_posts";
	
	private static final String MARKDOWN_RULE = "---";
	
	private static final String MARKDOWN_FILE = ".md";
	//开始语法高亮
	private static final String HIGHLIGHT = "{% highlight java %}";
	//结束语法高亮
	private static final String ENDHIGHLIGHT = "{% endhighlight %}";
	
	private static final String AUTHOR = "原创文章转载请注明出处: " ;
	
	private static final String WEB_SITE = "http://9leg.com/";
	
	private static final String HTML = ".html";
	
	public static void main(String[] args) {
        
		//default file title
		String fileTitle = "file-title";
		//default post title
		String postTitle = "PostTitle";
		//default post category
		String postCategory = "java";
		//是否转载
		boolean isReprint =  false;
		//customize
		if (args.length > 0 ) {
			fileTitle = args[0];
			postTitle = args[1];
			postCategory = args[2];
			isReprint = args[3].equals("1");
		} 
		
		String userHome = System.getProperties().getProperty("user.home",USER_HOME);

		String fileTime = getFormatTime("yyyy-MM-dd");
		// /Users/zhujiajun/Work/zJiaJun.github.io/_posts
		String fileDir =  userHome + POST_DIR;
		// /2015-01-11-FileTitle.md
		String fileName = File.separator + fileTime + "-" + fileTitle + MARKDOWN_FILE;
		
		File file = new File(fileDir + fileName);
		
		String postTime = getFormatTime("yyyy-MM-dd HH:mm:ss");

		PrintWriter pw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
				pw = new PrintWriter(file,"UTF-8");
				pw.println(MARKDOWN_RULE);
				pw.println("layout: post");
				pw.println("title: " + postTitle);
				pw.println("date: "+ postTime);
				pw.println("category: "+ "\"" + postCategory + "\"");
				pw.println(MARKDOWN_RULE);
				for (int i = 0;i < 10;i++) {
					pw.println();
				}
				for (int i = 0;i < 3;i++) {
					pw.println(HIGHLIGHT);
					pw.println();
					pw.println(ENDHIGHLIGHT);
					pw.println();
				}
				pw.println(
						AUTHOR + "[" + postTitle +"]" + 
				"("+ WEB_SITE + postCategory + getFormatTime("/yyyy/MM/dd/") + fileTitle + HTML +")");
				if (isReprint) {
					pw.println();
					pw.println("[英文原文链接]()");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	static private String getFormatTime(String pattern) {
		Objects.requireNonNull(pattern);
		return new SimpleDateFormat(pattern).format(new Date());

	}
}
