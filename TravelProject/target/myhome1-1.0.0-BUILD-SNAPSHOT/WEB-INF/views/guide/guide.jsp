<%@ page language="java" 
    contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
  
<%@include file="../include/common.jsp"%>


<body class="admin" lang="en">


<!-- site align S -->
<div class="sa">

	<%@include file="../include/header.jsp"%>

	<%@include file="../include/left.jsp"%>
	

	<!-- section S -->
	<div id="section">

		<div id="pagetitle">

			<h2>관리자 모드</h2>


		</div>

		<div id="content">

			<h3>관리자 쓰기</h3>

			<!-- bbs default write S -->
			<table cellspacing="0" summary="[프로그램 입력] 글쓰기 입력 양식입니다." class="bdw">
				<caption>글쓰기 입력 양식</caption>
				<colgroup><col width="125" /><col width="*" /></colgroup>
				<tbody>
					<tr>
						<th scope="row" class="first"><label for="formname01">제목</label></th>
						<td class="last"><input type="text" id="formname01" name="formname01" size="70" /></td>
					</tr>
					<tr>
						<th scope="row" class="first"><label for="formname02">작성자</label></th>
						<td class="last"><input type="text" id="formname02" name="formname02" size="70" /> </td>
					</tr>
					<tr>
						<th scope="row" class="first"><label for="formname03">조회</label></th>
						<td class="last"><input type="text" id="formname03" name="formname03" size="70" /></td>
					</tr>
					<tr>
						<th scope="row" class="first"><label for="formname05">셀렉트박스</label></th>
						<td class="last">
							<select id="formname05" name="formname05" title="셀렉트박스 Select">
								<option>셀렉트박스</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row" class="first"><span for="radio1_1" class="label">라디오버튼</span></th>
						<td class="last">
							<input type="radio" id="radio1_1" name="radio1" class="radio" /><label for="radio1_1">Use</label>
							<input type="radio" id="radio1_2" name="radio1" class="radio" /><label for="radio1_2">Use</label>
							<input type="radio" id="radio1_3" name="radio1" class="radio" /><label for="radio1_3">Use</label>
							<input type="radio" id="radio1_4" name="radio1" class="radio" /><label for="radio1_4">Use</label>
							<input type="radio" id="radio1_5" name="radio1" class="radio" /><label for="radio1_5">Use</label>
						</td>
					</tr>
					<tr>
						<th scope="row" class="first"><span for="checkbox1_1" class="label">체크박스</span></th>
						<td class="last">
							<input type="checkbox" id="checkbox1_1" name="checkbox1" class="checkbox" /><label for="checkbox1_1">Check</label>
							<input type="checkbox" id="checkbox1_2" name="checkbox1" class="checkbox" /><label for="checkbox1_2">Check</label>
							<input type="checkbox" id="checkbox1_3" name="checkbox1" class="checkbox" /><label for="checkbox1_3">Check</label>
							<input type="checkbox" id="checkbox1_4" name="checkbox1" class="checkbox" /><label for="checkbox1_4">Check</label>
							<input type="checkbox" id="checkbox1_5" name="checkbox1" class="checkbox" /><label for="checkbox1_5">Check</label>
						</td>
					</tr>
					<tr>
						<th scope="row" class="first"><label for="memo">내용</label></th>
						<td class="last"><textarea id="memo" name="memo" rows="10"></textarea></td>
					</tr>
					<tr>
						<th scope="row" class="first"><label for="atchfile">첨부파일</label></th>
						<td class="last"><input type="file" id="atchfile" name="atchfile" size="50" class="atchfile" /></td>
					</tr>
				</tbody>
			</table>
			<!-- bbs default write E -->

			<!-- bbs footer S -->
			<div class="bft">

				<div class="bb">
					<ul>
						<li><a href="#">취소</a></li>
						<li class="inputBtn"><input type="submit" value="확인" /></li>
					</ul>
				</div>

			</div>
			<!-- bbs footer E -->

			<h3>관리자 리스트</h3>

			<!-- bbs search S -->
			<div class="bs">
				<fieldset>
					<legend class="desc">Search</legend>

					<h3><img src="${commonURL}/resources/images/board/def/search_title.gif" width="46" height="9" alt="search" /></h3>
					<p>
						<label for="target" class="desc">Target</label>
						<select id="target">
							<option value="subject">제목</option>
						</select>
						<label for="keyword" class="desc">Keyword</label>
						<input type="text" id="keyword" class="keyword" size="30" /> <span class="inbtn inputBtn"><input type="submit" class="submit" value="Search" /></span>
					</p>

				</fieldset>
			</div>
			<!-- bbs search E -->

			<!-- bbs header S -->
			<div class="bhd">
				<p class="fl"><strong>100 건</strong> 의 게시물이 있습니다.</p>
			</div>
			<!-- bbs header E -->

			<!-- bbs default list S -->
			<table cellspacing="0" summary="[게시판명 프로그램입력]의 게시물 목록 입니다." class="bdl">
				<caption class="desc">[게시판명 프로그램입력] 목록</caption>
				<colgroup><col width="10%" span="2" /><col width="*" /><col width="10%" span="2" /><col width="8%" /><col width="12%" /></colgroup>
				<thead>
					<tr>
						<th scope="col" class="first">번호</th>
						<th scope="col">구분</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">조회</th>
						<th scope="col" class="last">비고</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td class="first">254</td>
						<td>공지사항</td>
						<td class="list"><a href="#">제목들어갑니다.</a></td>
						<td>홍길동</td>
						<td>2011-12-31</td>
						<td>10</td>
						<td class="last">
							<a href="#" class="btn"><span>수정</span></a>
							<a href="#" class="btn"><span>삭제</span></a>
						</td>
					</tr>
					<tr>
						<td class="first">254</td>
						<td>공지사항</td>
						<td class="list"><a href="#">제목들어갑니다.</a></td>
						<td>홍길동</td>
						<td>2011-12-31</td>
						<td>10</td>
						<td class="last">
							<a href="#" class="btn"><span>수정</span></a>
							<a href="#" class="btn"><span>삭제</span></a>
						</td>
					</tr>
					<tr>
						<td class="first">254</td>
						<td>공지사항</td>
						<td class="list"><a href="#">제목들어갑니다.</a></td>
						<td>홍길동</td>
						<td>2011-12-31</td>
						<td>10</td>
						<td class="last">
							<a href="#" class="btn"><span>수정</span></a>
							<a href="#" class="btn"><span>삭제</span></a>
						</td>
					</tr>
					<tr>
						<td class="first">254</td>
						<td>공지사항</td>
						<td class="list"><a href="#">제목들어갑니다.</a></td>
						<td>홍길동</td>
						<td>2011-12-31</td>
						<td>10</td>
						<td class="last">
							<a href="#" class="btn"><span>수정</span></a>
							<a href="#" class="btn"><span>삭제</span></a>
						</td>
					</tr>
				</tbody>

			</table>
			<!-- bbs default list E -->

			<!-- bbs footer S -->
			<div class="bft">

				<div class="sbb">
					<ul>
						<li><a href="#"><img src="${commonURL}/resources/images/board/def/excel.gif" alt="Excel Save" /></a></li>
					</ul>
				</div>

				<!-- pagination S -->
				<div class="pg">

					<a href="#" class="f"><img src="${commonURL}/resources/images/board/def/pg_first.gif" alt="첫 페이지" /></a>
					<a href="#" class="p"><img src="${commonURL}/resources/images/board/def/pg_prev.gif" alt="이전 페이지" /></a>
					<ul>
						<li class="on"><strong>1</strong></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">6</a></li>
						<li><a href="#">7</a></li>
						<li><a href="#">8</a></li>
						<li><a href="#">9</a></li>
						<li class="last"><a href="#">10</a></li>
					</ul>
					<a href="#" class="n"><img src="${commonURL}/resources/images/board/def/pg_next.gif" alt="다음 페이지" /></a>
					<a href="#" class="l"><img src="${commonURL}/resources/images/board/def/pg_last.gif" alt="마지막 페이지" /></a>

				</div>
				<!-- pagination E -->

				<div class="bb withpg">
					<ul>
						<li><a href="#">목록</a></li>
						<li><a href="#">수정</a></li>
						<li><a href="#">삭제</a></li>
					</ul>
				</div>

			</div>
			<!-- bbs footer E -->

			<!-- bbs view header S -->
			<div class="bvhd dbline">
				<dl>
					<dt class="subject">제목</dt>
						<dd class="subject">제목들어갑니다.</dd>
					<dt class="date">작성일</dt>
						<dd class="date">2012-03-09</dd>
					<dt class="country">작성자</dt>
						<dd class="country">홍길동</dd>
					<dt class="hits">조회</dt>
						<dd class="hits">310</dd>
				</dl>
			</div>
			<!-- bbs view header E -->

			<!-- bbs view content S -->
			<div class="bvc">
				<p>
					내용들어갑니다.
				</p>
				<p>
					내용들어갑니다.내용들어갑니다.내용들어갑니다.내용들어갑니다.내용들어갑니다.내용들어갑니다.내용들어갑니다.
				</p>
				<p>
					내용들어갑니다.내용들어갑니다.내용들어갑니다.<br />내용들어갑니다.내용들어갑니다.내용들어갑니다.내용들어갑니다.
				</p>

				<dl class="files">
					<dt>첨부파일</dt>
					<dd><a href="#">첨부파일1.txt</a></dd>
					<dd><a href="#">첨부파일2.txt</a></dd>
					<dd><a href="#">첨부파일3.txt</a></dd>
					<dd><a href="#">첨부파일4.txt</a></dd>
				</dl>
			</div>
			<!-- bbs view content E -->

			<!-- bbs footer S -->
			<div class="bftv">

				<div class="bb">
					<ul class="sbb">
						<li><a href="#">목록</a></li>
						<li><a href="#">수정</a></li>
						<li><a href="#">삭제</a></li>
					</ul>
				</div>

			</div>
			<!-- bbs footer E -->

			<!-- other list S -->
			<div class="otl">
				<dl>
					<dt class="prev">이전글</dt>
					<dd class="prev"><a href="#">이전글 입니다.</a></dd>
					<dt class="next">다음글</dt>
					<dd class="next"><a href="#">다음글 입니다.</a></dd>
				</dl>
			</div>
			<!-- other list E -->


		</div>
		<!-- content E -->

	</div>
	<!-- section E -->

	<%@include file="../include/footer.jsp"%>

</div>
<!-- site align E -->


</body>
</html>