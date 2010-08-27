<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="addCategoryForm.title" /></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:javascript formName="addCategoryDynaForm"/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">	
			<h2><bean:message key="addCategoryForm.title" /></h2>
							
			<html:form action="/AddCategory" method="post" onsubmit="return validateAddCategoryDynaForm(this)">
				<table class="center">
					<tr>
						<td colspan="3">			
							<html:errors property="addCategoryFailed" header="errors.header"
								footer="errors.footer" prefix="" suffix="" />
						</td>
					</tr>
					<tr>
						<th align="right"><bean:message key="category.title" /></th>
						<td align="left"><html:text property="title" /></td>
						<td><html:errors property="title" header="errors.header"
							footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><html:submit><bean:message key="buttons.submit"/></html:submit></th>
						<td align="left" colspan="2"><html:reset><bean:message key="buttons.reset"/></html:reset></td>
					</tr>
				</table>
			</html:form>
		</div>
		<%@include file="/footer.jsp"%>		
	</body>
</html:html>
