<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<html:html>
	<head>
		<title><bean:message key="gameOverView.title"/></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
			
			<h2><html:img page="/images/cat2.png"/>
				<bean:message key="gameOverView.title"/>
			</h2>	
			<h2><bean:size id="nbQuestions" name="QUESTIONS" scope="request"/>
				<bean:message key="gameOverView.you.scored" arg0="${SCORE}" arg1="${nbQuestions}"/>
			</h2>			
			<table class="center">
				<logic:iterate id="question" name="QUESTIONS"  indexId="i" scope="session">							
					<tr>
						<th align="right"><bean:write name ="question" property="text" /></th>
						<td align="left">${ANSWERS[i]}</td>
						<td align="left"><bean:message key="${CORRECTIONS[i]}"/></td>
					</tr>
				</logic:iterate>	
			</table>
			<div class="center">
				<html:img page="/images/play.png"/>
				<html:link action="/PlayCategory.do?ID=${ID_CATEGORY}">
					<bean:message key="gameOverView.play.again"/>
				</html:link>
			</div>
		</div>
		<%@include file="/footer.jsp"%>
	</body>
</html:html>

