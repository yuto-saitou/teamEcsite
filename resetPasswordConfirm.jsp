<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">

<title>パスワード再設定確認</title>

</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">

		<h1>パスワード再設定確認画面</h1>

		<table class="vertical-list-table">
			<tr>
				<th scope="row"><s:label value="ユーザーID" /></th>
				<td><s:property value="userId" /></td>
			</tr>

			<tr>
				<th scope="row"><s:label value="新しいパスワード" /></th>
				<td><s:property value="concealedPassword" /></td>
			</tr>
		</table>

		<s:form action="ResetPasswordCompleteAction">
			<div class="submit_btn_box">
				<s:submit value="パスワード再設定" class="submit_btn" />
			</div>
		</s:form>

		<s:form action="ResetPasswordAction">
			<div class="submit_btn_box">
				<s:submit value="戻る" class="submit_btn" />
			</div>
			<s:hidden id="backFlag" name="backFlag" value="1" />
		</s:form>

	</div>

</body>

</html>