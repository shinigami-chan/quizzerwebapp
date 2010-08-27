<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
	<head>
		<title><bean:message key="resultsView.title" /></title>
		<link rel="stylesheet" type="text/css" href='<html:rewrite page="/css/design.css"/>'/>
		<html:base />
	</head>
	<body bgcolor="white">
		<%@include file="/header.jsp"%>
		<div id="content">
		
			<h2><html:img page="/images/results.png"/><bean:message key="resultsView.title" /></h2>
			
			<table class="left_float">
				<tr>
					<td colspan="2"><html:img page="/images/avatar.png"/></td>
				</tr>
				<tr>
					<th align="left"><bean:message key="player.login"/></th>
					<td align="left"><bean:write name="PLAYER" property="login"/>
				</tr>
				<tr>
					<th align="left"><bean:message key="player.birthDate"/></th>
					<td align="left"><bean:write name="PLAYER" property="birthDate"/></td>
				</tr>
				<tr>
					<th align="left"><bean:message key="player.email"/></th>
					<td align="left"><bean:write name="PLAYER" property="email"/></td>
				</tr>
				<tr>
					<bean:size id="nbPlayedGames" name="PLAYER" property="results"/>
					<th  align="left"><bean:message key="resultsView.nbPlayedGames"/></th>
					<td align="left"><bean:write name="nbPlayedGames"/></td>						
				</tr>
			</table>

			<table class="center">
				<thead>
					<tr>
						<th><bean:message key="number"/></th>
						<th><bean:message key="category.label"/></th>
						<th><bean:message key="gameResult.score"/></th>
						<th><bean:message key="gameResult.playedDate"/></th>
					</tr>				
				</thead>
				
				<logic:iterate id="result" name="PLAYER" property="results" scope="session" indexId="i" >
					<tr>
						<td align="right">${i+1}</td>
						<td align="left">
							<html:link action="/ViewCategory.do?ID=${result.category.id}">
								<bean:write  name="result" property="category.title"/>
							</html:link>
						</td>
						<td align="left"><bean:write name="result" property="score"/></td>
						<td align="left"><bean:write name="result" property="playedDate"
											formatKey="dateTimePattern"/>
						</td>
					</tr>
				</logic:iterate>				
			</table>
		</div>
		<%@include file="/footer.jsp"%>
	</body>
</html:html>
