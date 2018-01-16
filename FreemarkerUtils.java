/**
 * 
 */
package com.ctg.itrdc.mf.server.freemarker;

import java.io.StringWriter;
import java.util.Map;

import com.ctg.itrdc.mf.common.util.ResourceUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author guobin
 */
public class FreemarkerUtils {
	private static Configuration cfg;
	private static Configuration getCfg() {
		if(cfg==null) {
			cfg = new Configuration();
			cfg.setTemplateLoader(new StringTemplateLoader());
			cfg.setNumberFormat("#");
			cfg.setLocalizedLookup(false);   //取消国际化
		}
		return cfg;
	}
	
	public static String parse(String template, Map<String, Object> root) {
		Configuration cfg = getCfg();
		StringWriter content = null;
		try {
			Template temp = cfg.getTemplate(template);
			content = new StringWriter();
			temp.process(root, content);
			return content.toString();
		} catch (Throwable e) {
			throw new RuntimeException("解析模板出错！"+template, e);
		} finally {
			ResourceUtil.close(content);
		}
		
	}
	
}
