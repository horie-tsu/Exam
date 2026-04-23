<%-- 成績参照（科目別）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績参照（科目別）
			</h2>
		</section>

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

		<!-- ===== 結果エリア ===== -->
		<c:if test="${list != null && list.size() > 0}">
			<div class="px-4">

				<table class="table table-bordered mt-3">
					<thead class="table-secondary">
						<tr>
							<th>学生番号</th>
							<th>氏名</th>
							<th>クラス</th>

							<!-- 回数ヘッダ -->
							<c:forEach var="i" begin="1" end="5">
								<th>${i}回</th>
							</c:forEach>

						</tr>
					</thead>

					<tbody>
						<c:forEach var="stu" items="${list}">
							<tr>
								<td>${stu.studentNo}</td>
								<td>${stu.studentName}</td>
								<td>${stu.classNum}</td>

								<!-- Mapから点数取り出し -->
								<c:forEach var="i" begin="1" end="5">
									<td>
										<c:choose>
											<c:when test="${stu.points[i] != null}">
												${stu.points[i]}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
									</td>
								</c:forEach>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</c:if>

		<!-- データなし -->
		<c:if test="${list == null || list.size() == 0}">
			<div class="text-danger px-4 mt-3">
				成績情報が存在しません
			</div>
		</c:if>

		<div class="my-2 text-end px-4">
			<a href="Menu.action">戻る</a>
		</div>
	</c:param>
	
</c:import>