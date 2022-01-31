package mallet.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import mallet.cache.SearchesCache;

public class SearchCacheTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private static String cacheEntry;
	
	public static String getCacheEntry() {
		return cacheEntry;
	}

	public static void setCacheEntry(String cacheEntry) {
		SearchCacheTag.cacheEntry = cacheEntry;
	}
	
	
	public static void addSearchCacheEntry(String cacheEntry) {
		SearchesCache.addSearchEntry(cacheEntry);
	}
	
	@Override
	public int doStartTag() throws JspException {
		addSearchCacheEntry( getCacheEntry() );
		
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		super.release();
		return EVAL_PAGE;
	}
}
