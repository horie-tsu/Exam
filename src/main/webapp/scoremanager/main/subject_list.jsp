<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
		</section>
		
		<form method="post" action="StudentCreateExecute.action">
			<div class="col-4">
				
				<%--<label>入学年度</label>
				<select name="entYear" class="form-select">
					<option value="0">-------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==param.entYear}">selected</c:if>>
							${year}
						</option>
					</c:forEach>
				</select>
				<span class="text-danger">${errors.entYear}</span>

				<label>学生番号</label>
				<input type="text" name="stuId" value="${param.stuId}">
				<span class="text-danger">${errors.stuId}</span>

				<br>

				<label>氏名</label>
				<input type="text" name="stuName" required="required">

				<div class="col-4">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="f2">
						<c:forEach var="num" items="${class_num_set}">
							<%--現在のnumと選択されていたf2が一致していた場合selectedを追記--%>
							<%--<option value="${num}" <c:if test="${num==f2}">selected</c:if>>
								${num}
							</option>
						</c:forEach>
					</select>
				</div>

				<button type="submit" class="btn btn-secondary">登録して終了</button>
			
			</div>
		</form>

		<div class="my-2 text-end px-4">
			<a href="StudentList.action">戻る</a>
		</div>
	</c:param>
	
</c:import> --%>