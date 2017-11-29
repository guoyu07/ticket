package com.ticket.tags;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ticket.constants.ContextConstants;
import com.ticket.pojo.CommonAnnex;
import com.ticket.service.ICommonAnnexService;
import com.ticket.util.SpringUtil;

/**
 * 
 * @ClassName: CommonAnnexTag   
 * @Description: 通用上传附件标签类   
 * @author 李应龙  
 * @date 2013-10-31 上午11:14:02   
 *
 */
public class CommonAnnexTag extends TagSupport{
	
	private static final long serialVersionUID = -6650337553841796175L;
	
	//通用附件业务层
	private ICommonAnnexService commonAnnexService = (ICommonAnnexService) SpringUtil.getObjectFromSpring("commonAnnexService");
	
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; //语言版本标识,默认为中文版.
	private String type = null;              //自定义标签业务分类, 用type属性来区分自定义标签处理的不同的业务方法
	private Integer size = 1;                //当获取列表的时候. 这个size表示获取的数量.默认取1条.
	private String value = null;             //辅助传值进来. 
	private String entityUuid = null;        //实体UUID
	private Integer annexType = null;        //附件类型
	private File fileObject = null;          //文件对象
	private Integer width = 100;             //图片宽度

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			if("queryAnnexList".equals(type)) { //查询附件列表
				List<CommonAnnex> list = commonAnnexService.queryListByEntityId(entityUuid, annexType, size);
				if(list != null && !list.isEmpty()) {
					request.setAttribute(type, list);
				} else {
					request.removeAttribute(type);
				}
			} else if("annex".equals(type)) { //查询附件列表
				List<CommonAnnex> list = commonAnnexService.queryListByEntityId(entityUuid, annexType, size);
				if(list != null && !list.isEmpty()) {
					request.setAttribute(type, list.get(0));
				} else {
					request.removeAttribute(type);
				}
			} else if ("fileSize".equals(type)) { //获取文件大小
				Long fileSize = fileObject.length();
				DecimalFormat df = new DecimalFormat("######.##");
				out.print(df.format(fileSize / 1024) + "K");
			} else if ("fileLastModified".equals(type)) { //获取文件修改时间
				Long lastModified = fileObject.lastModified();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				out.print(sdf.format(lastModified));
			} else if ("showImageByAnnexPostfix".equals(type)) { //根据附件的后缀生成图片
				if (value != null && !value.equals("")) {
					String defaultImagePath = commonAnnexService.parseDefaultImageByFileName(value);
					out.print("<img width=\""+ width +"\" src=\""+ defaultImagePath +"\" filePath=\""+ value +"\" />");
				}
			}
		} catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEntityUuid() {
		return entityUuid;
	}

	public void setEntityUuid(String entityUuid) {
		this.entityUuid = entityUuid;
	}

	public Integer getAnnexType() {
		return annexType;
	}

	public void setAnnexType(Integer annexType) {
		this.annexType = annexType;
	}

	public File getFileObject() {
		return fileObject;
	}

	public void setFileObject(File fileObject) {
		this.fileObject = fileObject;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	
}
