<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="addQuestionForm.title" /></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:javascript formName="addQuestionDynaForm"/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
			<h2><bean:message key="addQuestionForm.title" /></h2>
							
			<html:form action="/AddQuestion" method="post" onsubmit="return validateAddQuestionDynaForm(this)">
				<table class="center">
					<tr>
						<td colspan="3">			
							<html:errors property="addQuestionFailed" header="errors.header"
								footer="errors.footer" prefix="" suffix="" />
						</td>
					</tr>
					<tr>
						<th align="right"><bean:message key="category.label" /></th>
						<td align="left" colspan="2">
							<html:select property="idCategory" >
								<html:options collection="CATEGORIES" labelProperty="title" property="id"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right"><bean:message key="question.label" /></th>
						<td align="left"><html:text property="question" /></td>
						<td><html:errors property="question" header="errors.header"
							footer="errors.footer" prefix="" suffix="" /></td>
					</tr>
					<tr>
						<th align="right"><bean:message key="question.answer" /></th>
						<td align="left"><html:text property="answer" /></td>
						<td><html:errors property="answer" header="errors.header"
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
