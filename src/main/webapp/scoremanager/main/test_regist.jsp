<%-- 成績登録画面JSP（仮） --%>
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
				成績管理
			</h2>
		</section>

		<form method="post" action="TestRegist.action">
			<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					
				<div class="col-2">
					<label class="form-label" for="test-f1-select">入学年度</label>
					<select class="form-select" id="test-f1-select" name="f1">
						<option value="0">-------</option>
							
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}" <c:if test="${year == f1}">selected</c:if>>
								${year}
							</option>
						</c:forEach>
					</select>
				</div>
					
				<div class="col-2">
					<label class="form-label" for="test-f2-select">クラス</label>
					<select class="form-select" id="test-f2-select" name="f2">
						<option value="0">--------</option>
							
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num.classNum}" <c:if test="${num.classNum==f2}">selected</c:if>>
								${num.classNum}
							</option>
						</c:forEach>
					</select>
				</div>
					
				<div class="col-4">
					<label class="form-label" for="test-f3-select">科目</label>
					<select class="form-select" id="test-f3-select" name="f3">
						<option value="">--------</option>
                            
						<c:forEach var="sub" items="${subject_list}">
							<option value="${sub.cd}"
								<c:if test="${sub.cd == f3}">selected</c:if>>
								${sub.name}
							</option>
						</c:forEach>
					</select>
				</div>
					
				<div class="col-2">
					<label class="form-label" for="test-f4-select">回数</label>
					<select class="form-select" id="test-f4-select" name="f4">
						<option value="0">-------</option>
							
						<c:forEach var="no" items="${no_set}">
							<option value="${no}" <c:if test="${no==f4}">selected</c:if>>
								${no}
							</option>
						</c:forEach>
					</select>
				</div>
					
				<div class="col-2 text-center">
					<button class="btn btn-secondary" id="search-button">検索</button>
				</div>

			</div>

			<c:if test="${not empty error.filter}">
				<div style="color:red; margin-bottom:10px;">
					${error.filter}
				</div>
			</c:if>

		</form>
		
		<form method="post" action="TestRegistExecute.action">
		
		<input type="hidden" name="f1" value="${f1}">
		<input type="hidden" name="f2" value="${f2}">
		<input type="hidden" name="f3" value="${f3}">
		<input type="hidden" name="f4" value="${f4}">

			<c:if test="${searched}">
				<c:choose>
			
					<c:when test="${not empty tests}">
						<div>科目:${sub.name}(${f4}回)</div>
					
						<table class="table table-hover">
							<tr>
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>点数</th>
							</tr>						
						
							<c:forEach var="test" items="${tests}" varStatus="status">
								<tr>
									<td>${test.student.entYear}</td>
									<td>${test.classNum}</td>
									<td>${test.student.no}</td>
									<td>${test.student.name}</td>

									<td>
										<input type="hidden" name="studentNo[]" value="${test.student.no}">
<input type="hidden" name="studentName[]" value="${test.student.name}">
<input type="hidden" name="studentEntYear[]" value="${test.student.entYear}">
<input type="hidden" name="subjectCd[]" value="${test.subject.cd}">
<input type="hidden" name="schoolCd[]" value="${test.school.cd}">
<input type="hidden" name="no[]" value="${test.no}">

										<input type="text" name="point[]"
    value="${test.point == 0 ? '' : test.point}">

										<div style="color:red;">
											${errors['point'.concat(status.index)]}
										</div>
									</td>
								</tr>
							</c:forEach>
						
						</table>
					
						<button type="submit" class="btn btn-secondary">
							登録して終了
						</button> 
				
					</c:when>
				
					<c:otherwise>
                        <c:if test="${empty error}">
                            学生情報が存在しませんでした。
                        </c:if>
                    </c:otherwise>
				
				</c:choose>
				
			</c:if>

		</form>

	</c:param>
	
</c:import>