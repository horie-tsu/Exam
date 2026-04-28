<%-- 成績参照（科目別）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

		<form method="get" action="TestListSubjectExecute.action">
			<div class="col-4 px-4">

				<label class="form-label">入学年度</label>
				<select name="entYear" class="form-select">
					<option value="">-------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==param.entYear}">selected</c:if>>
							${year}
						</option>
					</c:forEach>
				</select>

				<br>

				<label class="form-label">クラス</label>
				<select name="classNum" class="form-select">
					<option value="">-------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num==param.classNum}">selected</c:if>>
							${num}
						</option>
					</c:forEach>
				</select>

				<br>

				<label class="form-label">科目</label>
				<select name="subCd" class="form-select">
					<option value="">-------</option>
					<c:forEach var="sub" items="${subject_list}">
						<option value="${sub.cd}" <c:if test="${sub.cd==param.subCd}">selected</c:if>>
							${sub.name}
						</option>
					</c:forEach>
				</select>

				<br>

				<button type="submit" class="btn btn-secondary">
					検索
				</button>

			</div>
		</form>

		