<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<html:html>
	<head>
		<title><bean:message key="playView.title" arg0="${CATEGORY.title}"/></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
			
			<h2><html:img page="/images/cat2.png"/>
				<bean:message key="playView.title" arg0="${CATEGORY.title}"/>
			</h2>
			
			<html:form action="/Play" method="post">
				<html:hidden property="idCategory" value="${CATEGORY.id}"/>
				<table class="center">
						<logic:iterate id="question" name="QUESTIONS"  indexId="i" scope="session">							
							<tr>
								<th align="right"><bean:write name ="question" property="text" /></th>
								<td align="left"><html:text property="answers" value="${answers[i]}"/></td>
								<td align="left">
									<logic:present name="CORRECTIONS" scope="request">
										<bean:message key="${CORRECTIONS[i]}"/>
									</logic:present>
								</td>
							</tr>
						</logic:iterate>	
						<tr>
							<td align="right"><html:submit><bean:message key="buttons.submit"/></html:submit></td>
							<td align="left"><html:reset><bean:message key="buttons.reset"/></html:reset></td>
						</tr>
				</table>
			</html:form>
		</div>
		<%@include file="/footer.jsp"%>
	</body>
</html:html>